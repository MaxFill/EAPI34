package ru.rt.fsom.nei.eapi;

import java.net.URL;
import java.util.List;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.il.nei.annotations.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import ru.rt.fsom.nei.eapi.conv.RequestConv;
import ru.rt.fsom.nei.eapi.conv.ResponseConv;
import ru.rt.fsom.nei.eapi.dict.Params;
import ru.rt.fsom.nei.eapi.utils.SOAPLogger;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import ru.rt.fsom.nei.eapi.conf.BssConf;
import ru.rt.fsom.nei.eapi.conf.UrlConf;
import ru.rt.fsom.nei.eapi.utils.SOAPLogHandler;
import ru.rt.fsom.nei.eapi.utils.Utils;
import ru.rt.nri.networkcapability.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import javax.crypto.spec.SecretKeySpec;

public class Connection {
	private static final String RTEAPI_WSDL_URL = "http://localhost:8080/EAPI34/NetworkCapabilityWebService.wsdl";
	private static final String AUTH_CONFIG = "/opt/wildfly/standalone/configuration/auth_conf.yml";

	private final MacroSet macro;

	@CommonParams(ne_type = "EAPI", product_name = "EAPI", product_version = "1")
	public Connection(MacroSet macro) {
		this.macro = macro;
	}

	@Capability( name="checkAccessCapability", description="", version="1")
	@Operation( name="checkAccessCapability", description="", req_type=OperationType.CREATE)
	@Keys(params={ @ValuePairs(param="REQ_TYPE", value="1"), @ValuePairs(param="ACTION", value="checkAccessCapability") })
	@Mandatory(params={Params.ORIGINATOR, Params.RECEIVER})
	public Arglist checkAccessCapability(Arglist argList, SOAPLogger logger, int sessionTimeout) throws Exception {
		RequestConv requestConv = new RequestConv(macro);
		ResponseConv responseConv = new ResponseConv(macro);
		String ossUrl = argList.getArg(Params.NRI_ADDRESS);
		NetworkCapabilityApi EAPIWebService = initWebService(ossUrl, logger, sessionTimeout);
		CheckAccessCapabilityRequest request = requestConv.prepCheckAccessCapabilityRequest(argList);
		CheckAccessCapabilityResponse response = EAPIWebService.checkAccessCapability(request);
		return responseConv.checkAccessCapabilityResponseToArgList(response);
	}

	/* *** privates *** */

	private NetworkCapabilityApi initWebService(String ossUrl, SOAPLogger logger, int sessionTimeout) throws Exception{
		logger.printDebug("Start init NetworkCapabilityApi_34 in URL [" + ossUrl + "]");
		URL wsdlUrl = new URL(RTEAPI_WSDL_URL);
		NetworkCapabilityWebService service = new NetworkCapabilityWebService(wsdlUrl);
		NetworkCapabilityApi ossService = service.getNetworkCapabilityWebServicePort();
		Map<String, Object> ctx = ((BindingProvider) ossService).getRequestContext();
		List<Handler> handlers = ((BindingProvider) ossService).getBinding().getHandlerChain();
		SOAPLogHandler logh = new SOAPLogHandler(macro, logger);
		handlers.add(logh);
		((BindingProvider) ossService).getBinding().setHandlerChain(handlers);
		if (ctx != null) {
			ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ossUrl);
			ctx.put("javax.xml.ws.client.receiveTimeout", sessionTimeout);
			ctx.put("javax.xml.ws.client.connectionTimeout", sessionTimeout);
			ctx.put("com.sun.xml.ws.request.timeout", sessionTimeout);
			ctx.put("com.sun.xml.ws.connect.timeout", sessionTimeout);
			ctx.put("com.sun.xml.internal.ws.connect.timeout", sessionTimeout);
			ctx.put("com.sun.xml.internal.ws.request.timeout", sessionTimeout);
			Map<String, UrlConf> authMap = initAuthConfig(logger);
			if (useBasicHttpAuth(ossUrl, authMap)) {
				logger.printDebug("useBasicHttpAuth for " + ossUrl + " login=" + getLogin(ossUrl, authMap));
				ctx.put(BindingProvider.USERNAME_PROPERTY, getLogin(ossUrl, authMap));
				ctx.put(BindingProvider.PASSWORD_PROPERTY, getPassword(ossUrl, authMap));
			} else {
				logger.printDebug("BasicHttpAuth for " + ossUrl + " not used!");
			}
		}
		logger.printDebug("NetworkCapabilityApi_34 init completed!");
		return ossService;
	}

	private Map<String, UrlConf> initAuthConfig(SOAPLogger logger){
		Map<String, UrlConf> authMap = new HashMap<>();
		try {
			File configFile = new File(AUTH_CONFIG);
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			BssConf bssConf = om.readValue(configFile, BssConf.class);
			if (bssConf == null){
				logger.printDebug("Could not read the file auth_config_file!");
				return null;
			}
			List<UrlConf> params = bssConf.getParams();
			AtomicBoolean needSaveConf = new AtomicBoolean(Boolean.FALSE);
			params.forEach(urlConf -> {
				if (!urlConf.getEncrypt()){
					try {
						urlConf.setPwl(Utils.encrypt(urlConf.getPwl(), getSecretKey()));
						urlConf.setEncrypt(Boolean.TRUE);
						needSaveConf.getAndSet(Boolean.TRUE);
					} catch (GeneralSecurityException | UnsupportedEncodingException ex) {
						logger.printDebug(ex.getMessage());
					}
				}
				authMap.put(urlConf.getUrl(), urlConf);
			});
			if (needSaveConf.get()){
				saveAuthConf(bssConf, AUTH_CONFIG, logger);
			}
		} catch (Exception ex){
			logger.printDebug("An error occurred while downloading the file auth_config_file: " + ex.getMessage());
		}
		return authMap;
	}

	private boolean useBasicHttpAuth(final String url, Map<String, UrlConf> authMap) {
		return authMap.get(url) != null;
	}

	private String getLogin(final String url, Map<String, UrlConf> authMap) {
		UrlConf urlConf = authMap.get(url);
		if (urlConf != null){
			return urlConf.getLogin();
		}
		return null;
	}

	private String getPassword(final String url, Map<String, UrlConf> authMap) throws NoSuchAlgorithmException, InvalidKeySpecException, GeneralSecurityException, IOException {
		UrlConf urlConf = authMap.get(url);
		if (urlConf != null){
			return Utils.decrypt(urlConf.getPwl(), getSecretKey());
		}
		return null;
	}

	private void saveAuthConf(BssConf bssConf, String fileConfPath, SOAPLogger logger){
		try {
			//logger.printDebug("begin save auth config to " + fileConfPath);
			File configFile = new File(fileConfPath);
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			om.writeValue(configFile, bssConf);
			//logger.printDebug("auth config file saved successfully!");
		} catch (Exception ex){
			logger.printDebug(ex.getMessage());
		}
	}

	private SecretKeySpec getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
		String pwd = "F23_sd01RT";
		byte[] salt = new String("12345678").getBytes();
		int iterationCount = 40000;
		int keyLength = 128;
		return Utils.createSecretKey(pwd.toCharArray(), salt, iterationCount, keyLength);
	}
}
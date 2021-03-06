package ru.rt.nri.networkcapability;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "NetworkCapabilityWebService", targetNamespace = "http://nri.rt.ru/NetworkCapability", wsdlLocation = "file:/C:/javawork/mavenproject1/src/wsdl/NetworkCapabilityWebService.wsdl")
public class NetworkCapabilityWebService
    extends Service
{

    private final static URL NETWORKCAPABILITYWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException NETWORKCAPABILITYWEBSERVICE_EXCEPTION;
    private final static QName NETWORKCAPABILITYWEBSERVICE_QNAME = new QName("http://nri.rt.ru/NetworkCapability", "NetworkCapabilityWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/javawork/mavenproject1/src/wsdl/NetworkCapabilityWebService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NETWORKCAPABILITYWEBSERVICE_WSDL_LOCATION = url;
        NETWORKCAPABILITYWEBSERVICE_EXCEPTION = e;
    }

    public NetworkCapabilityWebService() {
        super(__getWsdlLocation(), NETWORKCAPABILITYWEBSERVICE_QNAME);
    }

    public NetworkCapabilityWebService(WebServiceFeature... features) {
        super(__getWsdlLocation(), NETWORKCAPABILITYWEBSERVICE_QNAME, features);
    }

    public NetworkCapabilityWebService(URL wsdlLocation) {
        super(wsdlLocation, NETWORKCAPABILITYWEBSERVICE_QNAME);
    }

    public NetworkCapabilityWebService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NETWORKCAPABILITYWEBSERVICE_QNAME, features);
    }

    public NetworkCapabilityWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NetworkCapabilityWebService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns NetworkCapabilityApi
     */
    @WebEndpoint(name = "NetworkCapabilityWebServicePort")
    public NetworkCapabilityApi getNetworkCapabilityWebServicePort() {
        return super.getPort(new QName("http://nri.rt.ru/NetworkCapability", "NetworkCapabilityWebServicePort"), NetworkCapabilityApi.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NetworkCapabilityApi
     */
    @WebEndpoint(name = "NetworkCapabilityWebServicePort")
    public NetworkCapabilityApi getNetworkCapabilityWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://nri.rt.ru/NetworkCapability", "NetworkCapabilityWebServicePort"), NetworkCapabilityApi.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NETWORKCAPABILITYWEBSERVICE_EXCEPTION!= null) {
            throw NETWORKCAPABILITYWEBSERVICE_EXCEPTION;
        }
        return NETWORKCAPABILITYWEBSERVICE_WSDL_LOCATION;
    }

}

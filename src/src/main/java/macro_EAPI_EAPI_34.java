import com.comptel.mds.sas.java_macroserver.IMacroSet;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.Status;
import com.comptel.il.nei.annotations.*;
import static com.comptel.mds.sas.java_macroserver.MacroSet.connClosed;
import static com.comptel.mds.sas.java_macroserver.MacroSet.connFailed;
import static com.comptel.mds.sas.java_macroserver.MacroSet.connOk;
import static com.comptel.mds.sas.java_macroserver.MacroSet.taskFailed;
import static com.comptel.mds.sas.java_macroserver.MacroSet.taskOk;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Enumeration;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import ru.rt.fsom.nei.eapi.Connection;
import ru.rt.fsom.nei.eapi.dict.Params;
import ru.rt.fsom.nei.eapi.utils.SOAPLogger;
import ru.rt.oms.Fault;

/**
 * This is a main class of a NEI.
 *
 * @author <a href="mailto:firstname.lastname@company.com">Firstname Lastname</a>
 * @version 1.0
 * @since jdk1.4
 */
public final class macro_EAPI_EAPI_34 extends MacroSet implements IMacroSet {
    private static final String FAULT_CODE = "FAULTCODE";
    private static final String FAULT_TEXT = "FAULTSTRING";
    private static final String SMESSAGE_ID = "SMESSAGE_ID";
    private static final String SMESSAGE = "SMESSAGE";
    private static final String STATUS = "STATUS";    
    private static final String NEI_NAME = "NEI_EAPI_34";
    private static final String INTERNAL_ERROR_TEXT = "INTERNAL_ERROR_TEXT";
    private static final String INTERNAL_ERROR_CODE = "INTERNAL_ERROR_CODE";
    protected static final String NEI_SYNC_RESP_TIMEOUT = "NEI_SYNC_RESP_TIMEOUT";
    private static final int MIN_CONNECT_TIMEOUT = 5000; 
    private static final int MAX_CONNECT_TIMEOUT = 120000;
    private Integer sessionTimeout;

    Connection connection;
  
    static {MACPREF = "SOAP";}
    
    public macro_EAPI_EAPI_34() {
    }

    @Override
    public Status init(Arglist arglist) {
    	return new Status(connOk, taskOk);
    }
    
    @Keys(params = {@ValuePairs(param="user_id1",value=""), @ValuePairs(param="user_pswd1",value=""), @ValuePairs(param="address1",value=""), @ValuePairs(param="transport_cmd_line1",value="")})
    @Override
    public Status login(Arglist arglist) {
	return new Status(connOk, taskOk);
    }

    @Override
    public Status logout(Arglist arglist) {    	
    	return new Status(connClosed, taskOk);
    }

    @Override
    public Status create(Arglist arglist) {
    	return this.CommonRequestMethod(arglist);
    }

    @Override
    public Status modify(Arglist arglist) {
    	return this.CommonRequestMethod(arglist);
    }

    @Override
    public Status delete(Arglist arglist) {
    	return this.CommonRequestMethod(arglist);
    }

    @Override
    public Status display(Arglist arglist) {
    	return this.CommonRequestMethod(arglist);
    }

    Connection getConnection(MacroSet macroSet) {
	if (connection == null) {
	    connection =  new Connection(macroSet);
	}
	return connection;
    }

    void setConnection(Connection connection) {
	this.connection = connection;
    }
    
    /* *** privates *** */

    private Status CommonRequestMethod(Arglist arglist){
	if (!checkParams(arglist)){
		return new Status(connFailed, taskFailed);
	}
	final String orderId = arglist.getArg(Params.ORDER_ID);
	final String taskId = arglist.getArg(Params.PARAM_TASK_ID);
	SOAPLogger logger = new SOAPLogger(this, orderId, taskId, NEI_NAME);

	connection = getConnection(this);
	if (!checkHaveParamReqType(arglist)){
		return new Status(connOk, taskFailed);
	}

	try {
	    Arglist returnArg = doRun(arglist, connection, logger);
	    int taskStatus = taskOk;
	    if (returnArg.containsArg(Params.ORDER_RESULT_CODE)){
		final String resultCode = returnArg.getArg(Params.ORDER_RESULT_CODE);
		if (!"0".equals(resultCode)){
			MacroSet.sendData("NEW", SMESSAGE, "Task execution failed");
			MacroSet.sendData("NEW", SMESSAGE_ID, "0");
			MacroSet.sendData("NEW", STATUS, "9");
			taskStatus = taskFailed;
		}
	    } else {
		    printDebug("returnArg not contains param ORDER_RESULT_CODE!");
	    }
	    sendParamsToIL(returnArg);
	    printDebug("Task status was send to IL");
	    printDebug("-----------------------------------------");
	    return new Status(connOk, taskStatus);
	} catch (Exception  ex) {
	    sendErrCodeToIL(ex, logger);
	    if (ex instanceof Fault) {
		    logger.printDebug("ERROR Fault: " + ex.getMessage());
		    return new Status(connFailed, taskFailed);
	    }
	    if (ex instanceof MalformedURLException) {
		    logger.printDebug("ERROR MalformedURLException: " + ex.getMessage());
		    return new Status(connFailed, taskFailed);
	    }
	    if (ex instanceof IOException) {
		    logger.printDebug("ERROR IOException: " + ex.getMessage());
		    return new Status(connFailed, taskFailed);
	    }
	    if (ex instanceof IllegalAccessException) {
		    logger.printDebug("ERROR IllegalAccessException: " + ex.getMessage());
		    return new Status(connOk, taskFailed);
	    }
	    if (ex instanceof IllegalArgumentException) {
		    logger.printDebug("ERROR IllegalArgumentException: " + ex.getMessage());
		    return new Status(connOk, taskFailed);
	    }
	    if (ex instanceof InvocationTargetException) {
		    logger.printDebug("ERROR InvocationTargetException: " + ex.getCause().toString());
		    return new Status(connOk, taskFailed);
	    }
	    if (ex instanceof SOAPFaultException) {
		    logger.printDebug("ERROR ServerSOAPFaultException:");
		    return new Status(connOk, taskFailed);
	    }
	    if (ex instanceof javax.xml.ws.WebServiceException){
		    logger.printDebug("ERROR ClientTransportException: " + getExceptionStack(ex));
		    return new Status(connFailed, taskFailed);
	    }
	    logger.printDebug("ERROR other: " + ex.getMessage());
	    return new Status(connOk, taskFailed);
	}
    }

    private boolean checkParams(Arglist arglist){
        if (!arglist.containsArg(NEI_SYNC_RESP_TIMEOUT)){ //https://ihelp.rt.ru/browse/FSOM-260
            sendMissingParamError("NEI_SYNC_RESP_TIMEOUT is not installed!");
            return false;
        }
        try {
            sessionTimeout = Integer.valueOf(arglist.getArg(NEI_SYNC_RESP_TIMEOUT));
            printDebug("NEI_SYNC_RESP_TIMEOUT=" + sessionTimeout);
            if (sessionTimeout < MIN_CONNECT_TIMEOUT || sessionTimeout > MAX_CONNECT_TIMEOUT){ //https://ihelp.rt.ru/browse/FSOM-260
                throw new NumberFormatException();
            }
            return true;
        } catch(NumberFormatException ex){
            sendMissingParamError("NEI_SYNC_RESP_TIMEOUT value is not correct!");
            return false;
        }
    }

    private void sendMissingParamError(String msg){
        MacroSet.sendData("NEW", SMESSAGE, msg);
        MacroSet.sendData("NEW", SMESSAGE_ID, "0");
        MacroSet.sendData("NEW", STATUS, "9");
        MacroSet.sendData("NEW", INTERNAL_ERROR_TEXT, msg);
        MacroSet.sendData("NEW", INTERNAL_ERROR_CODE, "IllegalParameter");
    }

    private void sendParamsToIL(Arglist returnArg){
        if (returnArg == null) return;
        Enumeration<String> keys = returnArg.getKeys();
        while (keys.hasMoreElements()) {
            String paramName = keys.nextElement();
            String value = returnArg.getArg(paramName);
            MacroSet.sendData("NEW", paramName, value);
        }
    }

    protected Arglist doRun(Arglist argList, ru.rt.fsom.nei.eapi.Connection connection, SOAPLogger logger) throws Exception{
        final String action = argList.getArg("ACTION");
        logger.printDebug("NEI ACTION [" + action + "] was started...");
        if (action == null || action.isEmpty()){
            String errMsg = "ERROR ACTION: parameter ACTION not found in input params!";
            logger.printDebug(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        Arglist returnArg;
        switch(action){
            case Params.ACTION_CHECK_ACCESS_CAPABILITY:{
                returnArg = connection.checkAccessCapability(argList, logger, sessionTimeout);
                break;
            }
            default:{
                logger.printDebug("Parameter ACTION [" + action + "] incorreced!");
                throw new IllegalArgumentException("Input parameter ACTION = [" + action + "] incorreced!");
            }
        }
        return returnArg;
    }

    private boolean checkHaveParamReqType(Arglist arglist){
        if(! arglist.containsArg(Params.PARAM_REQ_TYPE)) {
            String type = arglist.getArg("task_type");
            if (type == null) {
                this.printDebug("Error! Argument task_type is not defined.");
                return false;
            }
            String reqType;
            switch (type) {
                case "create":
                    reqType = "1";
                    break;
                case "modify":
                    reqType = "2";
                    break;
                case "delete":
                    reqType = "3";
                    break;
                case "display":
                    reqType = "4";
                    break;
                default:
                    this.printDebug("Error! Invalid task_type.");
                    return false;
            }
            arglist.setArg(Params.PARAM_REQ_TYPE, reqType);
        }
        return true;
    }

    private void sendErrCodeToIL(Exception exception, SOAPLogger logger){
		MacroSet.sendData("NEW", SMESSAGE_ID, "0");
		MacroSet.sendData("NEW", STATUS, "9");

		if (exception instanceof Fault){
			logger.printDebug("Fault exception detected !");
			Fault fault = (Fault)exception;
			String msg = fault.getFaultInfo().getMessage();
			String faultCode = "";
			if (fault.getFaultInfo().getResultCode() != null){
				faultCode = String.valueOf(fault.getFaultInfo().getResultCode());
			}
			MacroSet.sendData("NEW", FAULT_CODE, faultCode);
			MacroSet.sendData("NEW", FAULT_TEXT, msg);
			logger.printDebug("Fault: " + faultCode + " " + msg);
			return;
		}

		if (exception.getCause() != null && exception.getCause() instanceof Fault){
			logger.printDebug("Fault cause detected!");
			Fault fault = (Fault)exception.getCause();
			String msg = fault.getFaultInfo().getMessage();
			String faultCode = "";
			if (fault.getFaultInfo().getResultCode() != null){
				faultCode = String.valueOf(fault.getFaultInfo().getResultCode());
			}
			MacroSet.sendData("NEW", FAULT_CODE, faultCode);
			MacroSet.sendData("NEW", FAULT_TEXT, msg);
			logger.printDebug("Fault: " + faultCode + " " + msg);
			return;
		}
	
	if (exception instanceof SOAPFaultException) {
	    logger.printDebug("ServerSOAPFaultException detected!");
	    SOAPFaultException sfe = (SOAPFaultException)exception;
	    SOAPFault fault = sfe.getFault();	    
	    MacroSet.sendData("NEW", FAULT_CODE, fault.getFaultCode());
	    MacroSet.sendData("NEW", FAULT_TEXT, fault.getFaultString());
	    return;
	}
	
	String errCode = null;
	String err = null;
	
	if (exception instanceof javax.xml.ws.WebServiceException) { //com.sun.xml.internal.ws.client.ClientTransportException 	    
	    if (exception.getCause() != null && exception.getCause() instanceof SocketTimeoutException){
		MacroSet.sendData("NEW", SMESSAGE, "Task execution failed.");
		err = "A synchronous response from the system was not received within " + sessionTimeout + " milliseconds."; 
		errCode = "SocketTimeoutException";
	    } else {
		errCode = "WebServiceException";
	    }
	} else 
	    if (exception instanceof SocketTimeoutException) {
		MacroSet.sendData("NEW", SMESSAGE, "Task execution failed.");
		err = "A synchronous response from the system was not received within " + sessionTimeout + " milliseconds."; 
		errCode = "SocketTimeoutException";
	    }
 
	if (err == null) err = getExceptionStack(exception);	
	if (errCode == null) errCode = "Exception";
	
	MacroSet.sendData("NEW", INTERNAL_ERROR_TEXT, err);
	MacroSet.sendData("NEW", INTERNAL_ERROR_CODE, errCode);	
	
	logger.printDebug("NEI INTERNAL ERROR! " + errCode + ": " + getExceptionStack(exception));		
    } 
    
    private String getExceptionStack(final Throwable ex) {
	StringWriter sw = new StringWriter(256);
	PrintWriter pw = new PrintWriter(sw, true);
	ex.printStackTrace(pw);
	return sw.getBuffer().toString();
    }

}
package ru.rt.fsom.nei.eapi.utils;

import com.comptel.mds.sas.java_macroserver.MacroSet;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Set;

public class SOAPLogHandler implements SOAPHandler<SOAPMessageContext> {
    private final MacroSet macro;
    private final SOAPLogger logger;

    public SOAPLogHandler(MacroSet macro, SOAPLogger logger) {
        this.macro = macro;
        this.logger = logger;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
        logToMML(smc);
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext smc) {
        logToMML(smc);
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {
    }

    private void logToMML(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        try {
            String msgType;
            if (outboundProperty.booleanValue()){
                msgType = "SOAP Request:";
            } else {
                msgType = "SOAP Response:";
            }
            SOAPMessage message = smc.getMessage();
            String soapMsg = soapMessageToString(message);
            String formated = formatXML(soapMsg);
            printOrderInfo(msgType, formated);
        } catch (Exception e) {
            logger.printDebug(e.getMessage());
        }
    }

    public String soapMessageToString(SOAPMessage message) {
        String result = null;
        if (message != null) {
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream();
                message.writeTo(baos);
                result = baos.toString();
            } catch (Exception ex) {
                logger.printDebug("ERROR in soapMessageToString: " + ex.getMessage());
            } finally {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException ioe) {
                    }
                }
            }
        }
        return result;
    }

    private void printOrderInfo(String prefix, String xml){
        macro.printMml(prefix);
        StringBuilder content = new StringBuilder();
        content
                .append(Utils.getCurrentDateAsString())
                .append(" ").append(logger.getLogerName())
                .append(" ").append(logger.getOrderInfo())
                .append(" ").append(xml);
        macro.printMml(content.toString());
    }

    private String formatXML(String mmlMessage) {
        try {
            Source xmlInput = new StreamSource(new StringReader(mmlMessage));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            //transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().trim();
        } catch (TransformerException ex) {
            logger.printDebug("Error formatXML: " + ex.getMessage());
        }
        return "Error occured while formatting SOAP!";
    }
}
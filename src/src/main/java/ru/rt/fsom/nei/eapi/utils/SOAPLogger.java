package ru.rt.fsom.nei.eapi.utils;

import com.comptel.mds.sas.java_macroserver.MacroSet;

/**
 *
 * @author Maksim.Filatov
 * @param <T>
 */
public class SOAPLogger<T> {
    private final MacroSet macro;
    private final String orderInfo;
    private final String logerName;

    public SOAPLogger(MacroSet macro, String orderId, String taskId, String logerName) {
        this.macro = macro;
        this.orderInfo = orderId + " " + taskId;
        this.logerName = logerName;
        macro.printDebug("Run NEI =" + logerName);
    }

    public String getLogerName() {
        return logerName;
    }

    public void printDebug(final String msg){
        macro.printDebug(orderInfo + " " + msg);
    }

    public String getOrderInfo(){
        return orderInfo;
    }
}
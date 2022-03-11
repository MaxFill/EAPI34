package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for LogicalState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LogicalState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AVAILABLE"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *     &lt;enumeration value="RESERVED"/>
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="LEASE"/>
 *     &lt;enumeration value="SUPPLY"/>
 *     &lt;enumeration value="CONTROL"/>
 *     &lt;enumeration value="PLUGGED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LogicalState", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum LogicalState {

    AVAILABLE,
    UNAVAILABLE,
    RESERVED,
    PENDING,
    LEASE,
    SUPPLY,
    CONTROL,
    PLUGGED;

    public String value() {
        return name();
    }

    public static LogicalState fromValue(String v) {
        return valueOf(v);
    }

}

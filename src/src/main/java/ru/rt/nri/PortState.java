package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PortState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PortState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="FAULTY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PortState", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum PortState {

    ACTIVE,
    FAULTY;

    public String value() {
        return name();
    }

    public static PortState fromValue(String v) {
        return valueOf(v);
    }

}

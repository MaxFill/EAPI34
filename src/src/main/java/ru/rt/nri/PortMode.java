package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PortMode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PortMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUBSCRIBER"/>
 *     &lt;enumeration value="TECHNOLOGICAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PortMode", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum PortMode {

    SUBSCRIBER,
    TECHNOLOGICAL;

    public String value() {
        return name();
    }

    public static PortMode fromValue(String v) {
        return valueOf(v);
    }

}

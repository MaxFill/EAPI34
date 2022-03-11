package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PortType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PortType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NETWORK"/>
 *     &lt;enumeration value="PON"/>
 *     &lt;enumeration value="TRANSCEIVER"/>
 *     &lt;enumeration value="UNDEFINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PortType", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum PortType {

    NETWORK,
    PON,
    TRANSCEIVER,
    UNDEFINED;

    public String value() {
        return name();
    }

    public static PortType fromValue(String v) {
        return valueOf(v);
    }

}

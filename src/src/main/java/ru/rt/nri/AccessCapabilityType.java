package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AccessCapabilityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccessCapabilityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSTALLATION"/>
 *     &lt;enumeration value="NETWORK_DEVELOPMENT"/>
 *     &lt;enumeration value="NEIGHBOR_DEVELOPMENT"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *     &lt;enumeration value="ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccessCapabilityType", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum AccessCapabilityType {

    INSTALLATION,
    NETWORK_DEVELOPMENT,
    NEIGHBOR_DEVELOPMENT,
    UNAVAILABLE,
    ERROR;

    public String value() {
        return name();
    }

    public static AccessCapabilityType fromValue(String v) {
        return valueOf(v);
    }

}

package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CheckMode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CheckMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INSTALLATION"/>
 *     &lt;enumeration value="NETWORK_DEVELOPMENT"/>
 *     &lt;enumeration value="NEIGHBOR_DEVELOPMENT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CheckMode", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum CheckMode {

    INSTALLATION,
    NETWORK_DEVELOPMENT,
    NEIGHBOR_DEVELOPMENT;

    public String value() {
        return name();
    }

    public static CheckMode fromValue(String v) {
        return valueOf(v);
    }

}

package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Status.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Status">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="PLANNING"/>
 *     &lt;enumeration value="PROJECT"/>
 *     &lt;enumeration value="INSTALLED"/>
 *     &lt;enumeration value="PREPARED"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *     &lt;enumeration value="DEACTIVATED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Status", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum Status {

    ACTIVE,
    PLANNING,
    PROJECT,
    INSTALLED,
    PREPARED,
    UNAVAILABLE,
    DEACTIVATED;

    public String value() {
        return name();
    }

    public static Status fromValue(String v) {
        return valueOf(v);
    }

}

package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AccessTechnology.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccessTechnology">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="XDSL"/>
 *     &lt;enumeration value="XPON"/>
 *     &lt;enumeration value="FTTB"/>
 *     &lt;enumeration value="FTTH"/>
 *     &lt;enumeration value="WBA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccessTechnology", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum AccessTechnology {

    XDSL,
    XPON,
    FTTB,
    FTTH,
    WBA;

    public String value() {
        return name();
    }

    public static AccessTechnology fromValue(String v) {
        return valueOf(v);
    }

}

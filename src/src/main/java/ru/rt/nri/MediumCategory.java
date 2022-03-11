package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for MediumCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MediumCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPTICAL"/>
 *     &lt;enumeration value="COPPER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MediumCategory", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum MediumCategory {

    OPTICAL,
    COPPER;

    public String value() {
        return name();
    }

    public static MediumCategory fromValue(String v) {
        return valueOf(v);
    }

}

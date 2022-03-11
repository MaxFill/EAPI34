package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CableCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CableCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OPTICAL_CABLE"/>
 *     &lt;enumeration value="COPPER_CABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CableCategory", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum CableCategory {

    OPTICAL_CABLE,
    COPPER_CABLE;

    public String value() {
        return name();
    }

    public static CableCategory fromValue(String v) {
        return valueOf(v);
    }

}

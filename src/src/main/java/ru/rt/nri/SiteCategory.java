package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for SiteCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SiteCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BUILDING"/>
 *     &lt;enumeration value="MANHOLE"/>
 *     &lt;enumeration value="POLE"/>
 *     &lt;enumeration value="CABLE_ENTRY"/>
 *     &lt;enumeration value="LEP"/>
 *     &lt;enumeration value="STREET_CASE"/>
 *     &lt;enumeration value="STREET_OPTICAL_CASE"/>
 *     &lt;enumeration value="ROOF_POST"/>
 *     &lt;enumeration value="UNDEFINED_POINT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SiteCategory", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum SiteCategory {

    BUILDING,
    MANHOLE,
    POLE,
    CABLE_ENTRY,
    LEP,
    STREET_CASE,
    STREET_OPTICAL_CASE,
    ROOF_POST,
    UNDEFINED_POINT;

    public String value() {
        return name();
    }

    public static SiteCategory fromValue(String v) {
        return valueOf(v);
    }

}

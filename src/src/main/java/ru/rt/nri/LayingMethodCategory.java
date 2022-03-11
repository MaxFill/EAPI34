package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for LayingMethodCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LayingMethodCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GROUND"/>
 *     &lt;enumeration value="DUCT"/>
 *     &lt;enumeration value="WALL"/>
 *     &lt;enumeration value="SUPPORT"/>
 *     &lt;enumeration value="AIR"/>
 *     &lt;enumeration value="WATER"/>
 *     &lt;enumeration value="UNDEFINED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LayingMethodCategory", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum LayingMethodCategory {

    GROUND,
    DUCT,
    WALL,
    SUPPORT,
    AIR,
    WATER,
    UNDEFINED;

    public String value() {
        return name();
    }

    public static LayingMethodCategory fromValue(String v) {
        return valueOf(v);
    }

}

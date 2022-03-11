package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for UnitTypeFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitTypeFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PLANNER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitTypeFilter", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum UnitTypeFilter {

    PLANNER;

    public String value() {
        return name();
    }

    public static UnitTypeFilter fromValue(String v) {
        return valueOf(v);
    }

}

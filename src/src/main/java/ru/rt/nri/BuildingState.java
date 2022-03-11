package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for BuildingState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BuildingState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="READY"/>
 *     &lt;enumeration value="NEED_CHECK"/>
 *     &lt;enumeration value="NEED_APPROVE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BuildingState", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum BuildingState {

    READY,
    NEED_CHECK,
    NEED_APPROVE;

    public String value() {
        return name();
    }

    public static BuildingState fromValue(String v) {
        return valueOf(v);
    }

}

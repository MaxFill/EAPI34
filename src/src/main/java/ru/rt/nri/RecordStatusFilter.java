package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RecordStatusFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RecordStatusFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ANY"/>
 *     &lt;enumeration value="ACTIVE_ONLY"/>
 *     &lt;enumeration value="DELETED_ONLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RecordStatusFilter", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum RecordStatusFilter {

    ANY,
    ACTIVE_ONLY,
    DELETED_ONLY;

    public String value() {
        return name();
    }

    public static RecordStatusFilter fromValue(String v) {
        return valueOf(v);
    }

}

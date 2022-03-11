package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ProjectState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProjectState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CONFIRM"/>
 *     &lt;enumeration value="COMPLETED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProjectState", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum ProjectState {


    /**
     * Проект согласован
     * 
     */
    CONFIRM,

    /**
     * Проект завершен
     * 
     */
    COMPLETED;

    public String value() {
        return name();
    }

    public static ProjectState fromValue(String v) {
        return valueOf(v);
    }

}

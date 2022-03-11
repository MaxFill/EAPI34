package ru.rt.oms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PartyRole.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PartyRole">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CLIENT"/>
 *     &lt;enumeration value="INITIATOR"/>
 *     &lt;enumeration value="SALES"/>
 *     &lt;enumeration value="ACCOUNT"/>
 *     &lt;enumeration value="CONTACT"/>
 *     &lt;enumeration value="WORKER"/>
 *     &lt;enumeration value="KZ"/>
 *     &lt;enumeration value="KP"/>
 *     &lt;enumeration value="SZ"/>
 *     &lt;enumeration value="IP"/>
 *     &lt;enumeration value="SOLUTION"/>
 *     &lt;enumeration value="RESERVATION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PartyRole")
@XmlEnum
public enum PartyRole {

    CLIENT,

    INITIATOR,

    SALES,

    ACCOUNT,

    CONTACT,

    WORKER,

    KZ,

    KP,

    SZ,
    
    IP,

    SOLUTION,

    RESERVATION;

    public String value() {
        return name();
    }

    public static PartyRole fromValue(String v) {
        return valueOf(v);
    }

}

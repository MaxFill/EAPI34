package ru.rt.nri;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AccessSubTechnology.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AccessSubTechnology">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GPON"/>
 *     &lt;enumeration value="GEPON"/>
 *     &lt;enumeration value="FTTB_MC"/>
 *     &lt;enumeration value="FTTB_SHDSL"/>
 *     &lt;enumeration value="FTTB_UTP"/>
 *     &lt;enumeration value="ADSLA"/>
 *     &lt;enumeration value="ADSLB"/>
 *     &lt;enumeration value="ADSL2A"/>
 *     &lt;enumeration value="ADSL2B"/>
 *     &lt;enumeration value="ADSL2AA"/>
 *     &lt;enumeration value="ADSL2AB"/>
 *     &lt;enumeration value="SHDSL"/>
 *     &lt;enumeration value="VDSL"/>
 *     &lt;enumeration value="VDSL2"/>
 *     &lt;enumeration value="WIFI"/>
 *     &lt;enumeration value="WIMAX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AccessSubTechnology", namespace = "http://nri.rt.ru/")
@XmlEnum
public enum AccessSubTechnology {

    GPON("GPON"),
    GEPON("GEPON"),
    FTTB_MC("FTTB_MC"),
    FTTB_SHDSL("FTTB_SHDSL"),
    FTTB_UTP("FTTB_UTP"),
    ADSLA("ADSLA"),
    ADSLB("ADSLB"),
    @XmlEnumValue("ADSL2A")
    ADSL_2_A("ADSL2A"),
    @XmlEnumValue("ADSL2B")
    ADSL_2_B("ADSL2B"),
    @XmlEnumValue("ADSL2AA")
    ADSL_2_AA("ADSL2AA"),
    @XmlEnumValue("ADSL2AB")
    ADSL_2_AB("ADSL2AB"),
    SHDSL("SHDSL"),
    VDSL("VDSL"),
    @XmlEnumValue("VDSL2")
    VDSL_2("VDSL2"),
    WIFI("WIFI"),
    WIMAX("WIMAX");
    private final String value;

    AccessSubTechnology(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AccessSubTechnology fromValue(String v) {
        for (AccessSubTechnology c: AccessSubTechnology.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

package ru.rt.oms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for OrderItemAction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderItemAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CREATE"/>
 *     &lt;enumeration value="CHANGE"/>
 *     &lt;enumeration value="CEASE"/>
 *     &lt;enumeration value="QUERY"/>
 *     &lt;enumeration value="SUSPEND"/>
 *     &lt;enumeration value="RESUME"/>
 *     &lt;enumeration value="INFO"/>
 *     &lt;enumeration value="REMOVE"/>
 *     &lt;enumeration value="MOVE"/>
 *     &lt;enumeration value="FINBLOCK"/>
 *     &lt;enumeration value="FINUNBLOCK"/>
 *     &lt;enumeration value="ADMBLOCK"/>
 *     &lt;enumeration value="ADMUNBLOCK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderItemAction")
@XmlEnum
public enum OrderItemAction {

    CREATE,

    CHANGE,

    CEASE,

    QUERY,

    SUSPEND,

    RESUME,
    INFO,
   
    REMOVE,

    MOVE,

    FINBLOCK,

    FINUNBLOCK,

    ADMBLOCK,

    ADMUNBLOCK;

    public String value() {
        return name();
    }

    public static OrderItemAction fromValue(String v) {
        return valueOf(v);
    }

}

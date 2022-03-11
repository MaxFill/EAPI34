package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.rt.oms.RTAPIRequest;

/**
 * <p>Java class for CheckAccessCapabilityRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckAccessCapabilityRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://oms.rt.ru/}RTAPIRequest">
 *       &lt;sequence>
 *         &lt;element name="payload" type="{http://nri.rt.ru/NetworkCapability}CheckAccessCapabilityParams"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAccessCapabilityRequest", propOrder = {
    "payload"
})
public class CheckAccessCapabilityRequest
    extends RTAPIRequest
{

    @XmlElement(required = true)
    protected CheckAccessCapabilityParams payload;

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link CheckAccessCapabilityParams }
     *     
     */
    public CheckAccessCapabilityParams getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAccessCapabilityParams }
     *     
     */
    public void setPayload(CheckAccessCapabilityParams value) {
        this.payload = value;
    }

}

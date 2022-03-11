package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.rt.oms.RTAPIResponse;

/**
 * <p>Java class for CheckAccessCapabilityResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckAccessCapabilityResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://oms.rt.ru/}RTAPIResponse">
 *       &lt;sequence>
 *         &lt;element name="payload" type="{http://nri.rt.ru/NetworkCapability}CheckAccessCapabilityResult"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAccessCapabilityResponse", propOrder = {
    "payload"
})
public class CheckAccessCapabilityResponse
    extends RTAPIResponse
{

    @XmlElement(required = true)
    protected CheckAccessCapabilityResult payload;

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link CheckAccessCapabilityResult }
     *     
     */
    public CheckAccessCapabilityResult getPayload() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAccessCapabilityResult }
     *     
     */
    public void setPayload(CheckAccessCapabilityResult value) {
        this.payload = value;
    }

}

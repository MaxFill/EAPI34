package ru.rt.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderResult", propOrder = {

})
public class OrderResult {

    @XmlElement(required = true)
    protected String orderResultCode;
    protected String orderResultText;

    /**
     * Gets the value of the orderResultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderResultCode() {
        return orderResultCode;
    }

    /**
     * Sets the value of the orderResultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderResultCode(String value) {
        this.orderResultCode = value;
    }

    /**
     * Gets the value of the orderResultText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderResultText() {
        return orderResultText;
    }

    /**
     * Sets the value of the orderResultText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderResultText(String value) {
        this.orderResultText = value;
    }

}

package ru.rt.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderItemResult", propOrder = {

})
public class OrderItemResult {

    protected String orderItemResultCode;
    protected String orderItemResultText;

    /**
     * Gets the value of the orderItemResultCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemResultCode() {
        return orderItemResultCode;
    }

    /**
     * Sets the value of the orderItemResultCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemResultCode(String value) {
        this.orderItemResultCode = value;
    }

    /**
     * Gets the value of the orderItemResultText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemResultText() {
        return orderItemResultText;
    }

    /**
     * Sets the value of the orderItemResultText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemResultText(String value) {
        this.orderItemResultText = value;
    }

}

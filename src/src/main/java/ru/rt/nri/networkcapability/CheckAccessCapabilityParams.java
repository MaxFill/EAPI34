package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.rt.oms.Attributes;
import ru.rt.oms.Location;

/**
 * <p>Java class for CheckAccessCapabilityParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckAccessCapabilityParams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetLocation" type="{http://oms.rt.ru/}Location"/>
 *         &lt;element name="requiredCapacity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="listTechnologies" type="{http://nri.rt.ru/NetworkCapability}Technologies" minOccurs="0"/>
 *         &lt;element name="checkList" type="{http://nri.rt.ru/NetworkCapability}CheckList" minOccurs="0"/>
 *         &lt;element name="checkTargetLocation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="searchRadius" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="existingLines" type="{http://nri.rt.ru/NetworkCapability}LineParam" minOccurs="0"/>
 *         &lt;element name="orderAttributes" type="{http://oms.rt.ru/}Attributes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAccessCapabilityParams", propOrder = {
    "orderId",
    "targetLocation",
    "requiredCapacity",
    "listTechnologies",
    "checkList",
    "checkTargetLocation",
    "searchRadius",
    "existingLines",
    "orderAttributes"
})
public class CheckAccessCapabilityParams {

    protected String orderId;
    @XmlElement(required = true)
    protected Location targetLocation;
    @XmlElement(defaultValue = "1")
    protected Integer requiredCapacity;
    protected Technologies listTechnologies;
    protected CheckList checkList;
    @XmlElement(defaultValue = "true")
    protected boolean checkTargetLocation;
    protected Integer searchRadius;
    protected LineParam existingLines;
    protected Attributes orderAttributes;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the targetLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getTargetLocation() {
        return targetLocation;
    }

    /**
     * Sets the value of the targetLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setTargetLocation(Location value) {
        this.targetLocation = value;
    }

    /**
     * Gets the value of the requiredCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequiredCapacity() {
        return requiredCapacity;
    }

    /**
     * Sets the value of the requiredCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequiredCapacity(Integer value) {
        this.requiredCapacity = value;
    }

    /**
     * Gets the value of the listTechnologies property.
     * 
     * @return
     *     possible object is
     *     {@link Technologies }
     *     
     */
    public Technologies getListTechnologies() {
        return listTechnologies;
    }

    /**
     * Sets the value of the listTechnologies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Technologies }
     *     
     */
    public void setListTechnologies(Technologies value) {
        this.listTechnologies = value;
    }

    /**
     * Gets the value of the checkList property.
     * 
     * @return
     *     possible object is
     *     {@link CheckList }
     *     
     */
    public CheckList getCheckList() {
        return checkList;
    }

    /**
     * Sets the value of the checkList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckList }
     *     
     */
    public void setCheckList(CheckList value) {
        this.checkList = value;
    }

    /**
     * Gets the value of the checkTargetLocation property.
     * 
     */
    public boolean isCheckTargetLocation() {
        return checkTargetLocation;
    }

    /**
     * Sets the value of the checkTargetLocation property.
     * 
     */
    public void setCheckTargetLocation(boolean value) {
        this.checkTargetLocation = value;
    }

    /**
     * Gets the value of the searchRadius property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSearchRadius() {
        return searchRadius;
    }

    /**
     * Sets the value of the searchRadius property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSearchRadius(Integer value) {
        this.searchRadius = value;
    }

    /**
     * Gets the value of the existingLines property.
     * 
     * @return
     *     possible object is
     *     {@link LineParam }
     *     
     */
    public LineParam getExistingLines() {
        return existingLines;
    }

    /**
     * Sets the value of the existingLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link LineParam }
     *     
     */
    public void setExistingLines(LineParam value) {
        this.existingLines = value;
    }

    /**
     * Gets the value of the orderAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getOrderAttributes() {
        return orderAttributes;
    }

    /**
     * Sets the value of the orderAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setOrderAttributes(Attributes value) {
        this.orderAttributes = value;
    }

}

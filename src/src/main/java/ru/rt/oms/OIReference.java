package ru.rt.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OIReference", propOrder = {
    "value"
})
public class OIReference {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "specId", required = true)
    protected String specId;
    @XmlAttribute(name = "status")
    protected AttributeStatus status;
    @XmlAttribute(name = "isChanged")
    protected Boolean isChanged;
    @XmlAttribute(name = "isUpdated")
    protected Boolean isUpdated;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the specId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecId() {
        return specId;
    }

    /**
     * Sets the value of the specId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecId(String value) {
        this.specId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeStatus }
     *     
     */
    public AttributeStatus getStatus() {
        if (status == null) {
            return AttributeStatus.AC;
        } else {
            return status;
        }
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeStatus }
     *     
     */
    public void setStatus(AttributeStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the isChanged property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsChanged() {
        if (isChanged == null) {
            return false;
        } else {
            return isChanged;
        }
    }

    /**
     * Sets the value of the isChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsChanged(Boolean value) {
        this.isChanged = value;
    }

    /**
     * Gets the value of the isUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsUpdated() {
        if (isUpdated == null) {
            return false;
        } else {
            return isUpdated;
        }
    }

    /**
     * Sets the value of the isUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsUpdated(Boolean value) {
        this.isUpdated = value;
    }

}
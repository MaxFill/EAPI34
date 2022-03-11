package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AddressInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hasCableEntry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="hasCableDuct" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="buildingState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressInfo", propOrder = {
    "hasCableEntry",
    "hasCableDuct",
    "buildingState"
})
public class AddressInfo {

    protected Boolean hasCableEntry;
    protected Boolean hasCableDuct;
    protected String buildingState;

    /**
     * Gets the value of the hasCableEntry property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasCableEntry() {
        return hasCableEntry;
    }

    /**
     * Sets the value of the hasCableEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasCableEntry(Boolean value) {
        this.hasCableEntry = value;
    }

    /**
     * Gets the value of the hasCableDuct property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasCableDuct() {
        return hasCableDuct;
    }

    /**
     * Sets the value of the hasCableDuct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasCableDuct(Boolean value) {
        this.hasCableDuct = value;
    }

    /**
     * Gets the value of the buildingState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingState() {
        return buildingState;
    }

    /**
     * Sets the value of the buildingState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingState(String value) {
        this.buildingState = value;
    }

}

package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RouteSite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RouteSite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="routeSiteNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="routeSiteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="routeSiteState" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="routeSiteCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="routeSiteOwner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RouteSite", propOrder = {
    "routeSiteNumber",
    "routeSiteName",
    "routeSiteState",
    "routeSiteCategory",
    "routeSiteOwner"
})
public class RouteSite {

    @XmlElement(required = true)
    protected String routeSiteNumber;
    @XmlElement(required = true)
    protected String routeSiteName;
    protected boolean routeSiteState;
    @XmlElement(required = true)
    protected String routeSiteCategory;
    protected Boolean routeSiteOwner;

    /**
     * Gets the value of the routeSiteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteSiteNumber() {
        return routeSiteNumber;
    }

    /**
     * Sets the value of the routeSiteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteSiteNumber(String value) {
        this.routeSiteNumber = value;
    }

    /**
     * Gets the value of the routeSiteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteSiteName() {
        return routeSiteName;
    }

    /**
     * Sets the value of the routeSiteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteSiteName(String value) {
        this.routeSiteName = value;
    }

    /**
     * Gets the value of the routeSiteState property.
     * 
     */
    public boolean isRouteSiteState() {
        return routeSiteState;
    }

    /**
     * Sets the value of the routeSiteState property.
     * 
     */
    public void setRouteSiteState(boolean value) {
        this.routeSiteState = value;
    }

    /**
     * Gets the value of the routeSiteCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteSiteCategory() {
        return routeSiteCategory;
    }

    /**
     * Sets the value of the routeSiteCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteSiteCategory(String value) {
        this.routeSiteCategory = value;
    }

    /**
     * Gets the value of the routeSiteOwner property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRouteSiteOwner() {
        return routeSiteOwner;
    }

    /**
     * Sets the value of the routeSiteOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRouteSiteOwner(Boolean value) {
        this.routeSiteOwner = value;
    }

}

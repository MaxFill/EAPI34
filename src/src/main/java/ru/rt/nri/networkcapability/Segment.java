package ru.rt.nri.networkcapability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for Segment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Segment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="segmentNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segmentState" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="layingMethodCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="segmentLength">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="segmentOwner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Segment", propOrder = {
    "segmentNumber",
    "segmentState",
    "layingMethodCategory",
    "segmentLength",
    "segmentOwner"
})
public class Segment {

    @XmlElement(required = true)
    protected String segmentNumber;
    protected boolean segmentState;
    @XmlElement(required = true)
    protected String layingMethodCategory;
    protected int segmentLength;
    protected Boolean segmentOwner;

    /**
     * Gets the value of the segmentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentNumber() {
        return segmentNumber;
    }

    /**
     * Sets the value of the segmentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentNumber(String value) {
        this.segmentNumber = value;
    }

    /**
     * Gets the value of the segmentState property.
     * 
     */
    public boolean isSegmentState() {
        return segmentState;
    }

    /**
     * Sets the value of the segmentState property.
     * 
     */
    public void setSegmentState(boolean value) {
        this.segmentState = value;
    }

    /**
     * Gets the value of the layingMethodCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLayingMethodCategory() {
        return layingMethodCategory;
    }

    /**
     * Sets the value of the layingMethodCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLayingMethodCategory(String value) {
        this.layingMethodCategory = value;
    }

    /**
     * Gets the value of the segmentLength property.
     * 
     */
    public int getSegmentLength() {
        return segmentLength;
    }

    /**
     * Sets the value of the segmentLength property.
     * 
     */
    public void setSegmentLength(int value) {
        this.segmentLength = value;
    }

    /**
     * Gets the value of the segmentOwner property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSegmentOwner() {
        return segmentOwner;
    }

    /**
     * Sets the value of the segmentOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSegmentOwner(Boolean value) {
        this.segmentOwner = value;
    }

}

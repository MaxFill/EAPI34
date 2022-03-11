package ru.rt.nri.networkcapability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.rt.oms.Attributes;
import ru.rt.oms.EquipmentInfo;

/**
 * <p>Java class for AccessCapability complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessCapability">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capabilityType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="technology" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkDevelopment" type="{http://nri.rt.ru/NetworkCapability}NetworkDevelopmentInfo" minOccurs="0"/>
 *         &lt;element name="equipmentList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="equipment" type="{http://oms.rt.ru/}EquipmentInfo" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributes" type="{http://oms.rt.ru/}Attributes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessCapability", propOrder = {
    "capabilityType",
    "technology",
    "networkDevelopment",
    "equipmentList",
    "description",
    "attributes"
})
public class AccessCapability {

    @XmlElement(required = true)
    protected String capabilityType;
    @XmlElement(required = true)
    protected String technology;
    protected NetworkDevelopmentInfo networkDevelopment;
    protected AccessCapability.EquipmentList equipmentList;
    protected String description;
    protected Attributes attributes;

    /**
     * Gets the value of the capabilityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapabilityType() {
        return capabilityType;
    }

    /**
     * Sets the value of the capabilityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapabilityType(String value) {
        this.capabilityType = value;
    }

    /**
     * Gets the value of the technology property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * Sets the value of the technology property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnology(String value) {
        this.technology = value;
    }

    /**
     * Gets the value of the networkDevelopment property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkDevelopmentInfo }
     *     
     */
    public NetworkDevelopmentInfo getNetworkDevelopment() {
        return networkDevelopment;
    }

    /**
     * Sets the value of the networkDevelopment property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkDevelopmentInfo }
     *     
     */
    public void setNetworkDevelopment(NetworkDevelopmentInfo value) {
        this.networkDevelopment = value;
    }

    /**
     * Gets the value of the equipmentList property.
     * 
     * @return
     *     possible object is
     *     {@link AccessCapability.EquipmentList }
     *     
     */
    public AccessCapability.EquipmentList getEquipmentList() {
        return equipmentList;
    }

    /**
     * Sets the value of the equipmentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessCapability.EquipmentList }
     *     
     */
    public void setEquipmentList(AccessCapability.EquipmentList value) {
        this.equipmentList = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setAttributes(Attributes value) {
        this.attributes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="equipment" type="{http://oms.rt.ru/}EquipmentInfo" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "equipment"
    })
    public static class EquipmentList {

        protected List<EquipmentInfo> equipment;

        /**
         * Gets the value of the equipment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the equipment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEquipment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EquipmentInfo }
         * 
         * 
         */
        public List<EquipmentInfo> getEquipment() {
            if (equipment == null) {
                equipment = new ArrayList<EquipmentInfo>();
            }
            return this.equipment;
        }

    }

}

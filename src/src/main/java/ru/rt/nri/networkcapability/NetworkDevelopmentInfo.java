package ru.rt.nri.networkcapability;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ru.rt.oms.Location;

/**
 * <p>Java class for NetworkDevelopmentInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NetworkDevelopmentInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solutionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siteType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siteStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siteState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="siteOwnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siteLocation" type="{http://oms.rt.ru/}Location"/>
 *         &lt;element name="siteHasCableEntry" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="routeSiteList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="routeSite" type="{http://nri.rt.ru/NetworkCapability}RouteSite" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="segmentList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="segment" type="{http://nri.rt.ru/NetworkCapability}Segment" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NetworkDevelopmentInfo", propOrder = {
    "solutionId",
    "siteId",
    "siteName",
    "siteType",
    "siteStatus",
    "siteState",
    "siteOwnerName",
    "siteLocation",
    "siteHasCableEntry",
    "routeSiteList",
    "segmentList"
})
public class NetworkDevelopmentInfo {

    protected String solutionId;
    @XmlElement(required = true)
    protected String siteId;
    @XmlElement(required = true)
    protected String siteName;
    @XmlElement(required = true)
    protected String siteType;
    @XmlElement(required = true)
    protected String siteStatus;
    @XmlElement(required = true)
    protected String siteState;
    protected String siteOwnerName;
    @XmlElement(required = true)
    protected Location siteLocation;
    protected Boolean siteHasCableEntry;
    @XmlElement(required = true)
    protected NetworkDevelopmentInfo.RouteSiteList routeSiteList;
    @XmlElement(required = true)
    protected NetworkDevelopmentInfo.SegmentList segmentList;

    /**
     * Gets the value of the solutionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionId() {
        return solutionId;
    }

    /**
     * Sets the value of the solutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionId(String value) {
        this.solutionId = value;
    }

    /**
     * Gets the value of the siteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * Sets the value of the siteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteId(String value) {
        this.siteId = value;
    }

    /**
     * Gets the value of the siteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Sets the value of the siteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteName(String value) {
        this.siteName = value;
    }

    /**
     * Gets the value of the siteType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteType() {
        return siteType;
    }

    /**
     * Sets the value of the siteType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteType(String value) {
        this.siteType = value;
    }

    /**
     * Gets the value of the siteStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteStatus() {
        return siteStatus;
    }

    /**
     * Sets the value of the siteStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteStatus(String value) {
        this.siteStatus = value;
    }

    /**
     * Gets the value of the siteState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteState() {
        return siteState;
    }

    /**
     * Sets the value of the siteState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteState(String value) {
        this.siteState = value;
    }

    /**
     * Gets the value of the siteOwnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteOwnerName() {
        return siteOwnerName;
    }

    /**
     * Sets the value of the siteOwnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteOwnerName(String value) {
        this.siteOwnerName = value;
    }

    /**
     * Gets the value of the siteLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getSiteLocation() {
        return siteLocation;
    }

    /**
     * Sets the value of the siteLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setSiteLocation(Location value) {
        this.siteLocation = value;
    }

    /**
     * Gets the value of the siteHasCableEntry property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSiteHasCableEntry() {
        return siteHasCableEntry;
    }

    /**
     * Sets the value of the siteHasCableEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSiteHasCableEntry(Boolean value) {
        this.siteHasCableEntry = value;
    }

    /**
     * Gets the value of the routeSiteList property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkDevelopmentInfo.RouteSiteList }
     *     
     */
    public NetworkDevelopmentInfo.RouteSiteList getRouteSiteList() {
        return routeSiteList;
    }

    /**
     * Sets the value of the routeSiteList property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkDevelopmentInfo.RouteSiteList }
     *     
     */
    public void setRouteSiteList(NetworkDevelopmentInfo.RouteSiteList value) {
        this.routeSiteList = value;
    }

    /**
     * Gets the value of the segmentList property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkDevelopmentInfo.SegmentList }
     *     
     */
    public NetworkDevelopmentInfo.SegmentList getSegmentList() {
        return segmentList;
    }

    /**
     * Sets the value of the segmentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkDevelopmentInfo.SegmentList }
     *     
     */
    public void setSegmentList(NetworkDevelopmentInfo.SegmentList value) {
        this.segmentList = value;
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
     *         &lt;element name="routeSite" type="{http://nri.rt.ru/NetworkCapability}RouteSite" maxOccurs="unbounded"/>
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
        "routeSite"
    })
    public static class RouteSiteList {

        @XmlElement(required = true)
        protected List<RouteSite> routeSite;

        /**
         * Gets the value of the routeSite property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the routeSite property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRouteSite().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RouteSite }
         * 
         * 
         */
        public List<RouteSite> getRouteSite() {
            if (routeSite == null) {
                routeSite = new ArrayList<RouteSite>();
            }
            return this.routeSite;
        }

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
     *         &lt;element name="segment" type="{http://nri.rt.ru/NetworkCapability}Segment" maxOccurs="unbounded"/>
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
        "segment"
    })
    public static class SegmentList {

        @XmlElement(required = true)
        protected List<Segment> segment;

        /**
         * Gets the value of the segment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the segment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSegment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Segment }
         * 
         * 
         */
        public List<Segment> getSegment() {
            if (segment == null) {
                segment = new ArrayList<Segment>();
            }
            return this.segment;
        }

    }

}

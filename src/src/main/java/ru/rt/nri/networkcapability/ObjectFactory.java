package ru.rt.nri.networkcapability;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.rt.nri.networkcapability package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckAccessCapabilityRequest_QNAME = new QName("http://nri.rt.ru/NetworkCapability", "checkAccessCapabilityRequest");
    private final static QName _CheckAccessCapabilityResponse_QNAME = new QName("http://nri.rt.ru/NetworkCapability", "checkAccessCapabilityResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.rt.nri.networkcapability
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccessCapability }
     * 
     */
    public AccessCapability createAccessCapability() {
        return new AccessCapability();
    }

    /**
     * Create an instance of {@link CheckAccessCapabilityResult }
     * 
     */
    public CheckAccessCapabilityResult createCheckAccessCapabilityResult() {
        return new CheckAccessCapabilityResult();
    }

    /**
     * Create an instance of {@link NetworkDevelopmentInfo }
     * 
     */
    public NetworkDevelopmentInfo createNetworkDevelopmentInfo() {
        return new NetworkDevelopmentInfo();
    }

    /**
     * Create an instance of {@link CheckAccessCapabilityResponse }
     * 
     */
    public CheckAccessCapabilityResponse createCheckAccessCapabilityResponse() {
        return new CheckAccessCapabilityResponse();
    }

    /**
     * Create an instance of {@link CheckAccessCapabilityRequest }
     * 
     */
    public CheckAccessCapabilityRequest createCheckAccessCapabilityRequest() {
        return new CheckAccessCapabilityRequest();
    }

    /**
     * Create an instance of {@link Technologies }
     * 
     */
    public Technologies createTechnologies() {
        return new Technologies();
    }

    /**
     * Create an instance of {@link Segment }
     * 
     */
    public Segment createSegment() {
        return new Segment();
    }

    /**
     * Create an instance of {@link LineParam }
     * 
     */
    public LineParam createLineParam() {
        return new LineParam();
    }

    /**
     * Create an instance of {@link CheckList }
     * 
     */
    public CheckList createCheckList() {
        return new CheckList();
    }

    /**
     * Create an instance of {@link CheckAccessCapabilityParams }
     * 
     */
    public CheckAccessCapabilityParams createCheckAccessCapabilityParams() {
        return new CheckAccessCapabilityParams();
    }

    /**
     * Create an instance of {@link RouteSite }
     * 
     */
    public RouteSite createRouteSite() {
        return new RouteSite();
    }

    /**
     * Create an instance of {@link AddressInfo }
     * 
     */
    public AddressInfo createAddressInfo() {
        return new AddressInfo();
    }

    /**
     * Create an instance of {@link AccessCapability.EquipmentList }
     * 
     */
    public AccessCapability.EquipmentList createAccessCapabilityEquipmentList() {
        return new AccessCapability.EquipmentList();
    }

    /**
     * Create an instance of {@link CheckAccessCapabilityResult.Capabilities }
     * 
     */
    public CheckAccessCapabilityResult.Capabilities createCheckAccessCapabilityResultCapabilities() {
        return new CheckAccessCapabilityResult.Capabilities();
    }

    /**
     * Create an instance of {@link NetworkDevelopmentInfo.RouteSiteList }
     * 
     */
    public NetworkDevelopmentInfo.RouteSiteList createNetworkDevelopmentInfoRouteSiteList() {
        return new NetworkDevelopmentInfo.RouteSiteList();
    }

    /**
     * Create an instance of {@link NetworkDevelopmentInfo.SegmentList }
     * 
     */
    public NetworkDevelopmentInfo.SegmentList createNetworkDevelopmentInfoSegmentList() {
        return new NetworkDevelopmentInfo.SegmentList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAccessCapabilityRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nri.rt.ru/NetworkCapability", name = "checkAccessCapabilityRequest")
    public JAXBElement<CheckAccessCapabilityRequest> createCheckAccessCapabilityRequest(CheckAccessCapabilityRequest value) {
        return new JAXBElement<CheckAccessCapabilityRequest>(_CheckAccessCapabilityRequest_QNAME, CheckAccessCapabilityRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAccessCapabilityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nri.rt.ru/NetworkCapability", name = "checkAccessCapabilityResponse")
    public JAXBElement<CheckAccessCapabilityResponse> createCheckAccessCapabilityResponse(CheckAccessCapabilityResponse value) {
        return new JAXBElement<CheckAccessCapabilityResponse>(_CheckAccessCapabilityResponse_QNAME, CheckAccessCapabilityResponse.class, null, value);
    }

}

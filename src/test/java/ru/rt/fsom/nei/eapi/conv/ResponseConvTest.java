package ru.rt.fsom.nei.eapi.conv;

import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.rt.fsom.nei.eapi.dict.Params;
import ru.rt.nri.networkcapability.*;
import ru.rt.oms.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseConvTest {

    // пустой response.
    @Test
    public void testCheckAccessCapabilityResponseToArgListEmptyResponse() {
        CheckAccessCapabilityResponse response = new CheckAccessCapabilityResponse();
        ResponseConv responseConv = new ResponseConv(new MacroSet());
        Arglist arglist = responseConv.checkAccessCapabilityResponseToArgList(response);
        assertNotNull(arglist);
        assertFalse(arglist.getKeys().hasMoreElements());
    }

    @Test
    public void testCheckAccessCapabilityResponseToArgList() throws DatatypeConfigurationException {
        CheckAccessCapabilityResponse response = new CheckAccessCapabilityResponse();
        response.setRequestId("request_id"); /* !!! */
        response.setOriginator("originator");
        response.setReceiver("receiver");
        // установка AddressInfo
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setBuildingState("building state");
        addressInfo.setHasCableDuct(true);
        addressInfo.setHasCableEntry(true);
        // установка Capabilities
        AccessCapability accessCapability1 = new AccessCapability();
        accessCapability1.setCapabilityType("type1");
        accessCapability1.setTechnology("technology1");
        XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar("2021-12-08T16:42:43.348+03:00");
        EquipmentInfo equipmentInfo = new EquipmentInfo();
        equipmentInfo.setId("equipment_id");
        equipmentInfo.setAvailableCapacity(1234567L);
        equipmentInfo.setExtraCapacity(2345678L);
        equipmentInfo.setCategory("equipment category");
        equipmentInfo.setCommissioningDate(date);
        equipmentInfo.setStatus("active");
        equipmentInfo.setName("equipment name");
        equipmentInfo.setDescription("equipment description");
        equipmentInfo.setCentralOfficeId("central_office_id");
        equipmentInfo.setHasProjectFiberLink(true);
        equipmentInfo.setResolution("equipment resolution");
        equipmentInfo.setTypeName("equipment type");
        AccessCapability.EquipmentList equipmentList = new AccessCapability.EquipmentList();
        equipmentList.getEquipment().add(equipmentInfo);
        accessCapability1.setEquipmentList(equipmentList);
        AccessCapability accessCapability2 = new AccessCapability();
        accessCapability2.setCapabilityType("type2");
        accessCapability2.setTechnology("technology2");
        RouteSite routeSite = new RouteSite();
        routeSite.setRouteSiteCategory("route site category1");
        routeSite.setRouteSiteName("route site name1");
        routeSite.setRouteSiteNumber("12");
        routeSite.setRouteSiteOwner(true);
        routeSite.setRouteSiteState(true);
        NetworkDevelopmentInfo.RouteSiteList routeSiteList = new NetworkDevelopmentInfo.RouteSiteList();
        routeSiteList.getRouteSite().add(routeSite);
        Segment segment1 = new Segment();
        segment1.setSegmentLength(15);
        segment1.setSegmentNumber("01a");
        segment1.setSegmentOwner(true);
        NetworkDevelopmentInfo.SegmentList segmentList = new NetworkDevelopmentInfo.SegmentList();
        segmentList.getSegment().add(segment1);
        Location location = new Location();
        Attributes locationAttributes =  new Attributes();
        Attribute attribute = new Attribute();
        attribute.setStatus(AttributeStatus.AC);
        attribute.setDesc("attribute description");
        attribute.setName("attribute name");
        attribute.setDesc("attribute description");
        attribute.getContent().add("content1");
        attribute.getContent().add("content2");
        locationAttributes.getAttribute().add(attribute);
        location.setLocationId("location_id");
        location.setLocationCategory(LocationCategory.LANDMARK);
        location.setLocationRegister("location register");
        location.setLocationAttributes(locationAttributes);
        NetworkDevelopmentInfo network = new NetworkDevelopmentInfo();
        network.setRouteSiteList(routeSiteList);
        network.setSegmentList(segmentList);
        network.setSiteLocation(location);
        network.setSiteId("site_id");
        network.setSiteName("site name");
        network.setSiteOwnerName("site owner name");
        accessCapability2.setNetworkDevelopment(network);
        CheckAccessCapabilityResult.Capabilities capabilities = new CheckAccessCapabilityResult.Capabilities();
        capabilities.getCapability().add(accessCapability1);
        capabilities.getCapability().add(accessCapability2);

        CheckAccessCapabilityResult payload = new CheckAccessCapabilityResult();
        payload.setAddressInfo(addressInfo);
        payload.setCapabilities(capabilities);
        response.setPayload(payload);

        ResponseConv responseConv = new ResponseConv(new MacroSet());
        Arglist arglist = responseConv.checkAccessCapabilityResponseToArgList(response);
        assertNotNull(arglist);
        assertEquals("originator", arglist.getArg(Params.ORIGINATOR));
        assertEquals("receiver", arglist.getArg(Params.RECEIVER));
        assertEquals("building state", arglist.getArg(Params.ADDRESSINFO_BUILDINGSTATE));
        assertEquals("true", arglist.getArg(Params.ADDRESSINFO_HASCABLEDUCT));
        assertEquals("true", arglist.getArg(Params.ADDRESSINFO_HASCABLEENTRY));
        assertEquals("2", arglist.getArg(Params.CAPABILITIES_LISTSIZE));
        assertEquals("type1", arglist.getArg("CAPABILITIES_1_CAPABILITYTYPE"));
        assertEquals("technology1", arglist.getArg("CAPABILITIES_1_TECHNOLOGY"));
        assertEquals("1", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_LISTSIZE"));
        assertEquals("equipment_id", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_ID"));
        assertEquals("central_office_id", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_CENTRALOFFICE_ID"));
        assertEquals("equipment category", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_CATEGORY"));
        assertEquals("active", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_STATUS"));
        assertEquals(date.toString() , arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_COMMISSIONING_DATE"));
        assertEquals("equipment type", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_TYPENAME"));
        assertEquals("equipment name", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_NAME"));
        assertEquals("equipment description", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_DESCRIPTION"));
        assertEquals("equipment resolution", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_RESOLUTION"));
        assertEquals("true", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_HAS_PROJECT_FIBERLINK"));
        assertEquals("1234567", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_AVAILABLE_CAPACITY"));
        assertEquals("2345678", arglist.getArg("CAPABILITIES_1_EQUIPMENTLIST_1_EXTRACAPACITY"));
        assertEquals("type2", arglist.getArg("CAPABILITIES_2_CAPABILITYTYPE"));
        assertEquals("technology2", arglist.getArg("CAPABILITIES_2_TECHNOLOGY"));
        assertEquals("1", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_LISTSIZE"));
        assertEquals("route site name1", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_1_ROUTE_SITE_NAME"));
        assertEquals("12", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_1_ROUTE_SITE_NUMBER"));
        assertEquals("route site category1", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_1_ROUTE_SITE_CATEGORY"));
        assertEquals("true", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_1_ROUTE_SITE_OWNER"));
        assertEquals("true", arglist.getArg("CAPABILITIES_2_ROUTE_SITE_1_ROUTE_SITE_STATE"));
        assertEquals("1", arglist.getArg("CAPABILITIES_2_SEGMENT_LISTSIZE"));
        assertEquals("15", arglist.getArg("CAPABILITIES_2_SEGMENT_1_SEGMENT_LENGHT"));
        assertEquals("01a", arglist.getArg("CAPABILITIES_2_SEGMENT_1_SEGMENT_NUMBER"));
        assertEquals("true", arglist.getArg("CAPABILITIES_2_SEGMENT_1_SEGMENT_OWNER"));
        assertEquals("false", arglist.getArg("CAPABILITIES_2_SEGMENT_1_SEGMENT_STATE"));
        assertEquals("location_id", arglist.getArg("CAPABILITIES_2_ID"));
        assertEquals("location register", arglist.getArg("CAPABILITIES_2_REGISTER"));
        assertEquals("LANDMARK", arglist.getArg("CAPABILITIES_2_CATEGORY"));
        assertEquals("site_id", arglist.getArg("CAPABILITIES_2_SITEID"));
        assertEquals("site name", arglist.getArg("CAPABILITIES_2_SITENAME"));
        assertEquals("1", arglist.getArg("CAPABILITIES_2_ATTR_LISTSIZE"));
        assertEquals("site owner name", arglist.getArg("CAPABILITIES_2_OWNER_NAME"));
        assertEquals("attribute name", arglist.getArg("CAPABILITIES_2_ATTR_1_NAME"));
        assertEquals("attribute description", arglist.getArg("CAPABILITIES_2_ATTR_1_DESCRIPTION"));
        assertEquals("content1, content2", arglist.getArg("CAPABILITIES_2_ATTR_1_VALUE"));
        assertEquals("AC", arglist.getArg("CAPABILITIES_2_ATTR_1_STATUS"));
        assertTrue(arglist.getKeys().hasMoreElements());
    }
}

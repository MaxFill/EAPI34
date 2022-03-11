package ru.rt.fsom.nei.eapi.conv;

import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.rt.fsom.nei.eapi.dict.Params;
import ru.rt.nri.networkcapability.CheckAccessCapabilityParams;
import ru.rt.nri.networkcapability.CheckAccessCapabilityRequest;
import ru.rt.oms.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RequestConvTest {

    @Test(expected = NullPointerException.class)
    public void testPrepCheckAccessCapabilityRequestNullArgs() throws Fault {
        RequestConv requestConv = new RequestConv(new MacroSet());
        requestConv.prepCheckAccessCapabilityRequest(null);
    }

    @Test
    public void testPrepCheckAccessCapabilityRequestWrongArgument() throws Fault {
        Arglist argList = new Arglist();
        argList.setArg("foo", "bar");
        RequestConv requestConv = new RequestConv(new MacroSet());
        CheckAccessCapabilityRequest request = requestConv.prepCheckAccessCapabilityRequest(argList);
        assertNotNull(request);
        assertNull(request.getRequestId());
        assertNull(request.getOriginator());
        assertNull(request.getReceiver());
    }

    @Test
    public void testPrepCheckAccessCapabilityRequest() throws Fault {
        Arglist argList = new Arglist();
        argList.setArg(Params.ORIGINATOR, "originator");
        argList.setArg(Params.RECEIVER, "receiver");
        argList.setArg(Params.CHECK_TARGET_LOCATION, "true");
        argList.setArg(Params.ORDER_ID, "order_id");
        argList.setArg(Params.EXISTINGLINES, "existinglines");
        argList.setArg(Params.REQUIREDCAPACITY, "1234");
        argList.setArg(Params.SEARCHRADIUS, "123");
        argList.setArg(Params.ORDER, "order");

        argList.setArg(Params.TARGET_LOCATION_ID, "target_location_id");
        argList.setArg(Params.TARGET_LOCATION_CATEGORY, LocationCategory.GEO.value());
        argList.setArg(Params.TARGET_LOCATION_REGISTER, LocationRegister.KLADR.value());
        argList.setArg("TARGET_LOCATION_ATTR_LISTSIZE", "3");
        argList.setArg("TARGET_LOCATION_ATTR_1_NAME", "target location name 1");
        argList.setArg("TARGET_LOCATION_ATTR_2_NAME", "target location name 2");
        argList.setArg("TARGET_LOCATION_ATTR_2_VALUE", "attr2 value1, attr2 value2, attr2 value3");
        argList.setArg("TARGET_LOCATION_ATTR_2_ISCHANGED", "true");
        argList.setArg("TARGET_LOCATION_ATTR_3_NAME", "target location name 3");
        argList.setArg("TARGET_LOCATION_ATTR_3_ISCHANGED", "false");

        argList.setArg("TECHNOLOGY_LISTSIZE", "2");
        argList.setArg("TECHNOLOGY_1", "2345");
        argList.setArg("TECHNOLOGY_2", "777");

        argList.setArg("ORDERATTR_LISTSIZE", "2");
        argList.setArg("ORDERATTR_1_NAME", "attr1 name");
        argList.setArg("ORDERATTR_1_VALUE", "attr1 value1, attr1 value2, attr1 value3");
        argList.setArg("ORDERATTR_1_DESCRIPTION", "attr1 description");
        argList.setArg("ORDERATTR_1_ISCHANGED", "true");
        argList.setArg("ORDERATTR_2_NAME", "attr2 name");
        argList.setArg("ORDERATTR_2_VALUE", "attr2 value");
        argList.setArg("ORDERATTR_2_DESCRIPTION", "attr2 description");
        argList.setArg("ORDERATTR_2_ISCHANGED", "false");

        RequestConv requestConv = new RequestConv(new MacroSet());
        CheckAccessCapabilityRequest request = requestConv.prepCheckAccessCapabilityRequest(argList);

        assertNotNull(request);
        assertEquals("originator", request.getOriginator());
        assertEquals("receiver", request.getReceiver());
        CheckAccessCapabilityParams payload = request.getPayload();
        assertNotNull(payload);
        assertTrue(request.getPayload().isCheckTargetLocation());
        assertNotNull(payload.getOrderId());
        assertEquals("order_id", request.getPayload().getOrderId());
        assertNotNull(payload.getExistingLines());
        assertEquals("existinglines", payload.getExistingLines().getLine());
        assertNotNull(payload.getRequiredCapacity());
        assertEquals(Integer.valueOf(1234), payload.getRequiredCapacity());
        assertNotNull(payload.getSearchRadius());
        assertEquals(Integer.valueOf(123), payload.getSearchRadius());
        assertNotNull(payload.getOrderAttributes());
        assertEquals(2, payload.getOrderAttributes().getAttribute().size());

        Location targetLocation = payload.getTargetLocation();
        assertNotNull(targetLocation);
        assertEquals("target_location_id", targetLocation.getLocationId());
        assertEquals(LocationCategory.GEO, targetLocation.getLocationCategory());
        assertEquals(LocationRegister.KLADR.value(), targetLocation.getLocationRegister());
        assertNotNull(targetLocation.getLocationAttributes());
        assertEquals(3, targetLocation.getLocationAttributes().getAttribute().size());
        assertEquals("target location name 1", targetLocation.getLocationAttributes().getAttribute().get(0).getName());
        assertEquals(0, targetLocation.getLocationAttributes().getAttribute().get(0).getContent().size());
        assertFalse(targetLocation.getLocationAttributes().getAttribute().get(0).isIsChanged());
        assertEquals("target location name 2", targetLocation.getLocationAttributes().getAttribute().get(1).getName());
        assertNotNull(targetLocation.getLocationAttributes().getAttribute().get(1).getContent());
        assertEquals(3, targetLocation.getLocationAttributes().getAttribute().get(1).getContent().size());
        assertEquals("attr2 value1", targetLocation.getLocationAttributes().getAttribute().get(1).getContent().get(0));
        assertEquals("attr2 value2", targetLocation.getLocationAttributes().getAttribute().get(1).getContent().get(1));
        assertEquals("attr2 value3", targetLocation.getLocationAttributes().getAttribute().get(1).getContent().get(2));
        assertTrue(targetLocation.getLocationAttributes().getAttribute().get(1).isIsChanged());
        assertEquals("target location name 3", targetLocation.getLocationAttributes().getAttribute().get(2).getName());
        assertEquals(0, targetLocation.getLocationAttributes().getAttribute().get(2).getContent().size());
        assertFalse(targetLocation.getLocationAttributes().getAttribute().get(2).isIsChanged());

        assertNotNull(payload.getListTechnologies());
        assertEquals(2, payload.getListTechnologies().getTechnology().size());
        assertEquals("2345", payload.getListTechnologies().getTechnology().get(0));
        assertEquals("777", payload.getListTechnologies().getTechnology().get(1));

        Attributes attributes = payload.getOrderAttributes();
        assertNotNull(attributes);
        assertEquals(2, attributes.getAttribute().size());
        assertEquals(3, attributes.getAttribute().get(0).getContent().size());
        assertEquals("attr1 name", attributes.getAttribute().get(0).getName());
        assertEquals("attr1 value1", attributes.getAttribute().get(0).getContent().get(0));
        assertEquals("attr1 value2", attributes.getAttribute().get(0).getContent().get(1));
        assertEquals("attr1 value3", attributes.getAttribute().get(0).getContent().get(2));
        assertEquals("attr1 description", attributes.getAttribute().get(0).getDesc());
        assertTrue(attributes.getAttribute().get(0).isIsChanged());
        assertEquals("attr2 name", attributes.getAttribute().get(1).getName());
        assertEquals(1, attributes.getAttribute().get(1).getContent().size());
        assertEquals("attr2 value", attributes.getAttribute().get(1).getContent().get(0));
        assertEquals("attr2 description", attributes.getAttribute().get(1).getDesc());
        assertFalse(attributes.getAttribute().get(1).isIsChanged());

    }
}

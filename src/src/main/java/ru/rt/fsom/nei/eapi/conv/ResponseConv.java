package ru.rt.fsom.nei.eapi.conv;

import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.MacroSet;
import ru.rt.fsom.nei.conv.BaseResponseConverter;
import ru.rt.fsom.nei.eapi.dict.Params;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.w3c.dom.NodeList;
import ru.rt.nri.networkcapability.AccessCapability;
import ru.rt.nri.networkcapability.AccessCapability.EquipmentList;
import ru.rt.nri.networkcapability.AddressInfo;
import ru.rt.nri.networkcapability.CheckAccessCapabilityResponse;
import ru.rt.nri.networkcapability.CheckAccessCapabilityResult;
import ru.rt.oms.EquipmentInfo;
import ru.rt.nri.networkcapability.NetworkDevelopmentInfo;
import ru.rt.nri.networkcapability.NetworkDevelopmentInfo.RouteSiteList;
import ru.rt.nri.networkcapability.NetworkDevelopmentInfo.SegmentList;
import ru.rt.nri.networkcapability.RouteSite;
import ru.rt.nri.networkcapability.Segment;
import ru.rt.oms.Attribute;
import ru.rt.oms.Attributes;
import ru.rt.oms.Location;
import ru.rt.oms.OrderResult;

/**
 *
 * @author Maksim.Filatov
 */
public class ResponseConv extends BaseResponseConverter {
	private final MacroSet macro;

	public ResponseConv(MacroSet macro) {
		super(macro);
		this.macro = macro;
	}

	public Arglist checkAccessCapabilityResponseToArgList(CheckAccessCapabilityResponse response){
		macro.printDebug("Start convert checkAccessCapabilityResponse to ArgList ...");
		Arglist arglist = new Arglist();
		loadOrderResult(arglist, response.getOrderResult());
		setArg(arglist, Params.ORIGINATOR, response.getOriginator());
		setArg(arglist, Params.RECEIVER, response.getReceiver());
		loadPayload(arglist, response.getPayload());
		macro.printDebug("Finish convert checkAccessCapabilityResponse.");
		return arglist;
	}

	/* *** privates *** */

	private void loadPayload(Arglist arglist, CheckAccessCapabilityResult result){
		if (result == null) return;
		loadAddressInfo(arglist, result.getAddressInfo());
		loadCapabilities(arglist, result.getCapabilities());
		loadAttributes(arglist, result.getOrderAttributes(), Params.ORDER);
	}

	private void loadAddressInfo(Arglist arglist, AddressInfo addressInfo){
		if (addressInfo == null) return;
		if (addressInfo.getBuildingState() != null){
			setArg(arglist, Params.ADDRESSINFO_BUILDINGSTATE, addressInfo.getBuildingState());
		}
		if (addressInfo.isHasCableDuct() != null){
			setArg(arglist, Params.ADDRESSINFO_HASCABLEDUCT, addressInfo.isHasCableDuct().toString());
		}
		if (addressInfo.isHasCableEntry() != null){
			setArg(arglist, Params.ADDRESSINFO_HASCABLEENTRY, addressInfo.isHasCableEntry().toString());
		}
	}

	private void loadCapabilities(Arglist arglist, CheckAccessCapabilityResult.Capabilities capabilities){
		if (capabilities == null) return;
		List<AccessCapability> items = capabilities.getCapability();
		getListSize(items).ifPresent(listSize->{
			final String key = Params.CAPABILITIES + "_";
			setArg(arglist, key + Params.LISTSIZE, String.valueOf(listSize));
			IntStream.range(0, listSize)
					.forEach(index -> {
						final String itemKey = key + (index + 1);
						loadCapability(arglist, items.get(index), itemKey);
					});
		});
	}

	private void loadCapability(Arglist arglist, AccessCapability capability, String itemKey){
		setArg(arglist, itemKey + "_" + Params.DESCRIPTION, capability.getDescription());
		if (capability.getCapabilityType() != null){
			setArg(arglist, itemKey + "_" + Params.CAPABILITYTYPE, capability.getCapabilityType());
		}
		if (capability.getTechnology() != null){
			setArg(arglist, itemKey + "_" + Params.TECHNOLOGY, capability.getTechnology());
		}
		loadEquipmentList(arglist, capability.getEquipmentList(), itemKey);
		loadNetworkDevelopmentInfo(arglist, capability.getNetworkDevelopment(), itemKey);
		loadAttributes(arglist, capability.getAttributes(), itemKey);
	}

	private void loadNetworkDevelopmentInfo(Arglist arglist, NetworkDevelopmentInfo ndi, final String itemKey){
		if (ndi == null) return;
		setArg(arglist, itemKey + "_" + Params.SITEID, ndi.getSiteId());
		setArg(arglist, itemKey + "_" + Params.SITENAME, ndi.getSiteName());
		setArg(arglist, itemKey + "_" + Params.SOLUTIONID, ndi.getSolutionId());
		setArg(arglist, itemKey + "_" + Params.SITETYPE, ndi.getSiteType());
		setArg(arglist, itemKey + "_" + Params.SITESTATE, ndi.getSiteState());
		setArg(arglist, itemKey + "_" + Params.OWNER_NAME, ndi.getSiteOwnerName());
		loadRouteSiteList(arglist, ndi.getRouteSiteList(), itemKey);
		loadSegmentList(arglist, ndi.getSegmentList(), itemKey);
		loadLocation(arglist, ndi.getSiteLocation(), itemKey);
	}

	private void loadRouteSiteList(Arglist arglist, RouteSiteList routeSiteList, final String prefix){
		if (routeSiteList == null) return;
		List<RouteSite> items = routeSiteList.getRouteSite();
		getListSize(items).ifPresent(listSize->{
			final String key = prefix + "_" + Params.ROUTE_SITE + "_";
			setArg(arglist, key + Params.LISTSIZE, String.valueOf(listSize));
			IntStream.range(0, listSize)
					.forEach(index -> {
						final String itemKey = key + (index + 1) + "_";
						RouteSite rs = items.get(index);
						setArg(arglist, itemKey + Params.ROUTE_SITE_OWNER, rs.isRouteSiteOwner());
						setArg(arglist, itemKey + Params.ROUTE_SITE_STATE, rs.isRouteSiteState());
						setArg(arglist, itemKey + Params.ROUTE_SITE_NAME, rs.getRouteSiteName());
						setArg(arglist, itemKey + Params.ROUTE_SITE_CATEGORY, rs.getRouteSiteCategory());
						setArg(arglist, itemKey + Params.ROUTE_SITE_NUMBER, rs.getRouteSiteNumber());
					});
		});
	}

	private void loadSegmentList(Arglist arglist, SegmentList segmentList, final String prefix){
		if (segmentList == null) return;
		List<Segment> items = segmentList.getSegment();
		getListSize(items).ifPresent(listSize->{
			final String key = prefix + "_" + Params.SEGMENT + "_";
			setArg(arglist, key + Params.LISTSIZE, String.valueOf(listSize));
			IntStream.range(0, listSize)
					.forEach(index -> {
						final String itemKey = key + (index + 1) + "_";
						Segment segment = items.get(index);
						setArg(arglist, itemKey + Params.SEGMENT_OWNER, segment.isSegmentOwner());
						setArg(arglist, itemKey + Params.SEGMENT_STATE, segment.isSegmentState());
						setArg(arglist, itemKey + Params.LAYING_METHOD_CATEGORY, segment.getLayingMethodCategory());
						setArg(arglist, itemKey + Params.SEGMENT_LENGHT, String.valueOf(segment.getSegmentLength()));
						setArg(arglist, itemKey + Params.SEGMENT_NUMBER, segment.getSegmentNumber());
					});
		});
	}

	private void loadLocation(Arglist arglist, Location location, final String itemKey){
		if (location == null) return;
		setArg(arglist, itemKey + "_" + Params.ID, location.getLocationId());
		loadAttributes(arglist, location.getLocationAttributes(), itemKey);
		if (location.getLocationCategory() != null){
			setArg(arglist, itemKey + "_" + Params.CATEGORY, location.getLocationCategory().value());
		}
		if (location.getLocationRegister() != null){
			setArg(arglist, itemKey + "_" + Params.REGISTER, location.getLocationRegister());
		}
	}

	private void loadEquipmentList(Arglist arglist, EquipmentList equipmentList, String prefix){
		if (equipmentList == null) return;
		List<EquipmentInfo> items = equipmentList.getEquipment();
		getListSize(items).ifPresent(listSize->{
			final String key = prefix + "_" + Params.EQUIPMENTLIST + "_";
			AtomicInteger count = new AtomicInteger();
			IntStream.range(0, listSize)
					.forEach(index -> {
						EquipmentInfo item = items.get(index);
						String itemId = item.getId();
						if (itemId != null && !itemId.isEmpty()){
							final String itemKey = key + (count.incrementAndGet());
							setArg(arglist, itemKey + "_" + Params.ID, itemId);
							setArg(arglist, itemKey + "_" + Params.NAME, item.getName());
							setArg(arglist, itemKey + "_" + Params.TYPENAME, item.getTypeName());
							if (item.getCategory() != null){
								setArg(arglist, itemKey + "_" + Params.CATEGORY, item.getCategory());
							}
							setArg(arglist, itemKey + "_" + Params.AVAILABLE_CAPACITY, String.valueOf(item.getAvailableCapacity()));
							setArg(arglist, itemKey + "_" + Params.CENTRALOFFICE_ID, item.getCentralOfficeId());
							setArg(arglist, itemKey + "_" + Params.DESCRIPTION, item.getDescription());
							setArg(arglist, itemKey + "_" + Params.STATUS, item.getStatus());
							setArg(arglist, itemKey + "_" + Params.COMMISSIONING_DATE, getFromXMLGregorianCalendar(item.getCommissioningDate()));
							setArg(arglist, itemKey + "_" + Params.EXTRACAPACITY, String.valueOf(item.getExtraCapacity()));
							setArg(arglist, itemKey + "_" + Params.HAS_PROJECT_FIBERLINK, String.valueOf(item.isHasProjectFiberLink()));
							if (item.getResolution() != null){
								setArg(arglist, itemKey + "_" + Params.RESOLUTION, item.getResolution());
							}
							loadAttributes(arglist, item.getAttributes(), itemKey);
						}
					});
			setArg(arglist, key + Params.LISTSIZE, String.valueOf(count.get()));
		});
	}

	private void loadAttributes(Arglist arglist, Attributes attributes, final String prefix){
		if (attributes == null) return;
		List<Attribute> items = attributes.getAttribute();
		getListSize(items).ifPresent(listSize->{
			final String key = prefix + "_" + Params.ATTR + "_";
			setArg(arglist, key + Params.LISTSIZE, String.valueOf(listSize));
			IntStream.range(0, listSize)
					.forEach(index -> {
						final String itemKey = key + (index + 1) + "_";
						final String atrName = items.get(index).getName();
						setArg(arglist, itemKey + Params.NAME, atrName);
						setArg(arglist, itemKey + Params.DESCRIPTION, items.get(index).getDesc());
						setArg(arglist, itemKey + Params.STATUS, String.valueOf(items.get(index).getStatus()));
						setArg(arglist, itemKey + Params.ISCHANGED, String.valueOf(items.get(index).isIsChanged()));
						loadContents(arglist, atrName, items.get(index).getContent(), itemKey);
					});
		});
	}

	private void loadContents(Arglist arglist,  final String atrName, List<Object> contents, final String itemKey_){
		if (contents == null) return;
		if ("equipmentList".equals(atrName)){
			//Utils.printDebug(isDebug, " start load equipmentList ...", macro);
			getListSize(contents).ifPresent(size->{
				final String prefix = itemKey_ + Params.EQUIPMENT + "_";
				setArg(arglist, prefix + Params.LISTSIZE, String.valueOf(size));
				AtomicInteger itemIndex = new AtomicInteger();
				IntStream.range(0, size)
						.forEach(index->{
							Object item = contents.get(index);
							if (item instanceof ElementNSImpl){
								ElementNSImpl element = (ElementNSImpl) contents.get(index);
								final String equipKey = prefix + itemIndex.incrementAndGet() + "_";
								getTagValue("id", element).ifPresent(v->setArg(arglist, equipKey + Params.ID, v));
								getTagValue("name", element).ifPresent(v->setArg(arglist, equipKey + Params.NAME, v));
								getTagValue("resolution", element).ifPresent(v->setArg(arglist, equipKey + Params.RESOLUTION, v));
								getTagValue("typeName", element).ifPresent(v->setArg(arglist, equipKey + Params.TYPE, v));
								getTagValue("availableCapaciy", element).ifPresent(v->setArg(arglist, equipKey + Params.AVAILABLE_CAPACIY, v));
								getTagValue("status", element).ifPresent(v->setArg(arglist, equipKey + Params.STATUS, v));
								getTagValue("exclusiveUse", element).ifPresent(v->setArg(arglist, equipKey + Params.EXCLUSIVEUSE, v));
								getTagValue("description", element).ifPresent(v->setArg(arglist, equipKey + Params.DESCRIPTION, v));
								getTagValue("centralOfficeId", element).ifPresent(v->setArg(arglist, equipKey + Params.CENTRALOFFICE_ID, v));
								getTagValue("category", element).ifPresent(v->setArg(arglist, equipKey + Params.CATEGORY, v));
								getTagValue("extraCapacity", element).ifPresent(v->setArg(arglist, equipKey + Params.EXTRACAPACITY, v));
								getTagValue("hasProjectFiberlink", element).ifPresent(v->setArg(arglist, equipKey + Params.HAS_PROJECT_FIBERLINK, v));
								getTagValue("commissioningDate", element).ifPresent(v->setArg(arglist, equipKey + Params.COMMISSIONING_DATE, v));
							}
						});
			});
		} else {
			final String value = contents.stream().map(Object::toString).collect(Collectors.joining(", "));
			setArg(arglist, itemKey_ + Params.VALUE, value);
		}
	}

	private Optional<String> getTagValue(String tagName, ElementNSImpl element){
		if (element == null) return Optional.empty();
		//Params.LOGGER.log(Level.INFO, "teg {0} found!", tagName.toLowerCase());
		NodeList listNode = element.getElementsByTagName(tagName);
		//Params.LOGGER.log(Level.INFO, "load listNode, teg={0}", tagName.toLowerCase());
		if (listNode == null || listNode.getLength() == 0) return Optional.empty();
		//Params.LOGGER.log(Level.INFO, "load textImpl, teg={0}", tagName.toLowerCase());
		TextImpl textImpl = (TextImpl)listNode.item(0).getFirstChild();
		return Optional.of(textImpl.getData());
	}

	private void loadOrderResult(Arglist arglist, OrderResult orderResult){
		//macro.printDebug("Start load OrderResult ...");
		if (orderResult == null){
			//macro.printDebug("Fail! OrderResult is null...");
			return;
		}
		String code = String.valueOf(orderResult.getOrderResultCode());
		setArg(arglist, Params.ORDER_RESULT_CODE, code);
		setArg(arglist, Params.ORDER_RESULT_TEXT, orderResult.getOrderResultText());
		//macro.printDebug("Finish load OrderResult, code = " + code);
	}

	private Optional<Integer> getListSize(List items){
		int size = items.size();
		if (size > 0) return Optional.of(size);
		return Optional.empty();
	}

}
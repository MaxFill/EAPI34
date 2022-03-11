package ru.rt.fsom.nei.eapi.conv;

import com.comptel.mds.sas.java_macroserver.Arglist;
import com.comptel.mds.sas.java_macroserver.MacroSet;

import ru.rt.fsom.nei.conv.BaseRequestConverter;
import ru.rt.fsom.nei.eapi.dict.Params;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import ru.rt.nri.networkcapability.CheckAccessCapabilityParams;
import ru.rt.nri.networkcapability.CheckAccessCapabilityRequest;
import ru.rt.nri.networkcapability.CheckList;
import ru.rt.nri.networkcapability.LineParam;
import ru.rt.nri.networkcapability.Technologies;
import ru.rt.oms.Attribute;
import ru.rt.oms.AttributeStatus;
import ru.rt.oms.Attributes;
import ru.rt.oms.Fault;
import ru.rt.oms.Location;
import ru.rt.oms.LocationCategory;


/**
 *
 * @author Maksim.Filatov
 */
public class RequestConv extends BaseRequestConverter {
	private final MacroSet macro;

	public RequestConv(MacroSet macro) {
		super(macro);
		this.macro = macro;
	}

	public CheckAccessCapabilityRequest prepCheckAccessCapabilityRequest(Arglist argList) throws Fault{
		//macro.printDebug("Start convert Arglist to CheckAccessCapabilityRequest ...");
		CheckAccessCapabilityRequest request = new CheckAccessCapabilityRequest();
		request.setPayload(loadCheckAccessCapabilityParams(argList));
		request.setOriginator(getMandatoryParam(argList, Params.ORIGINATOR));
		request.setReceiver(getMandatoryParam(argList, Params.RECEIVER));
		//macro.printDebug("Finish convert Arglist to CheckAccessCapabilityRequest");
		return request;
	}

	/* *** privates *** */

	private CheckAccessCapabilityParams loadCheckAccessCapabilityParams(Arglist argList) {
		CheckAccessCapabilityParams params = new CheckAccessCapabilityParams();
		getOptionalBoolean(argList, Params.CHECK_TARGET_LOCATION).ifPresent(v->params.setCheckTargetLocation(v));
		getOptionalString(argList, Params.ORDER_ID).ifPresent(v->params.setOrderId(v));
		getOptionalString(argList, Params.EXISTINGLINES).ifPresent(v->{
			LineParam lineParam = new LineParam();
			lineParam.setLine(v);
			params.setExistingLines(lineParam);
		});
		getOptionalString(argList, Params.REQUIREDCAPACITY).ifPresent(v->params.setRequiredCapacity(Integer.valueOf(v)));
		getOptionalString(argList, Params.SEARCHRADIUS).ifPresent(v->params.setSearchRadius(Integer.valueOf(v)));
		getAttributes(argList, Params.ORDER).ifPresent(v->params.setOrderAttributes(v));
		getTechnologies(argList).ifPresent(v->params.setListTechnologies(v));
		getCheckList(argList).ifPresent(v->params.setCheckList(v));
		getLocation(argList).ifPresent(v->params.setTargetLocation(v));
		return params;
	}

	private Optional<CheckList> getCheckList(Arglist argList){
		final String key = Params.CHECK_LIST;
		int listSize = getListSize(argList, key);
		if (listSize == 0) return Optional.empty();
		CheckList checkList = new CheckList();
		checkList.getCheck().addAll(
				IntStream.rangeClosed(1, listSize)
						.mapToObj(index->getCheckMode(argList, key + "_" + index))
						.collect(Collectors.toList())
		);
		return Optional.of(checkList);
	}

	private Optional<Location> getLocation(Arglist argList){
		final String key = Params.TARGET_LOCATION + "_";
		return Optional.ofNullable(argList.getArg(key + Params.ID)).map(id->{
			Location location = new Location();
			location.setLocationId(id);
			getLocationCategory(argList, key).ifPresent(v->location.setLocationCategory(v));
			getAttributes(argList, key).ifPresent(v->location.setLocationAttributes(v));
			getOptionalString(argList, key + Params.REGISTER).ifPresent(v->location.setLocationRegister(v));
			return location;
		});
	}

	private Optional<Technologies> getTechnologies(Arglist argList) {
		final String key = Params.TECHNOLOGY;
		int listSize = getListSize(argList, key);
		if (listSize == 0) return Optional.empty();
		Technologies tech = new Technologies();
		tech.getTechnology().addAll(
				IntStream.rangeClosed(1, listSize)
						.mapToObj(index->getAccessTechnology(argList, key + "_" + index))
						.collect(Collectors.toList())
		);
		return Optional.of(tech);
	}

	private Optional<Attributes> getAttributes(Arglist argList, final String prefix_){
		final String key = prefix_ + Params.ATTR;
		int listSize = getListSize(argList, key);
		if (listSize == 0) return Optional.empty();
		Attributes atrs = new Attributes();
		atrs.getAttribute().addAll(
				IntStream.rangeClosed(1, listSize)
						.mapToObj(index->{
							final String itemKey = key + "_" + index;
							Attribute atr = new Attribute();
							getOptionalString(argList, itemKey + "_" + Params.NAME).ifPresent(v->atr.setName(v));
							getOptionalString(argList, itemKey + "_" + Params.DESCRIPTION).ifPresent(v->atr.setDesc(v));
							getOptionalString(argList, itemKey + "_" + Params.VALUE).ifPresent(v->atr.getContent().addAll(Arrays.stream(v.split(", ")).collect(Collectors.toList())));
							getOptionalBoolean(argList, itemKey + "_" + Params.ISCHANGED).ifPresent(v->atr.setIsChanged(v));
							getAttributeStatus(argList, itemKey).ifPresent(v->atr.setStatus(v));
							return atr;
						})
						.collect(Collectors.toList())
		);
		return Optional.of(atrs);
	}

	private Optional<AttributeStatus> getAttributeStatus(Arglist argList, final String itemKey){
		try {
			return getOptionalString(argList, itemKey + "_" + Params.STATUS).map(v->AttributeStatus.valueOf(v.toUpperCase()));
		}catch (Exception ex) {
			printParamErr(Params.TECHNOLOGY);
		}
		return Optional.empty();
	}

	private Optional<LocationCategory> getLocationCategory(Arglist argList, final String prefix) {
		try {
			return getOptionalString(argList, prefix + Params.CATEGORY).map(v->LocationCategory.valueOf(v.toUpperCase()));
		}catch (Exception ex) {
			printParamErr(prefix + Params.CATEGORY);
		}
		return Optional.empty();
	}

	private String getAccessTechnology(Arglist argList, final String itemKey) {
		try {
			if (argList.containsArg(itemKey)){
				return argList.getArg(itemKey);
			}
		} catch (Exception ex) {
			printParamErr(itemKey);
		}
		return null;
	}

	private String getCheckMode(Arglist argList, final String itemKey){
		try {
			if (argList.containsArg(itemKey)){
				return argList.getArg(itemKey);
			}
		} catch (Exception ex) {
			printParamErr(itemKey);
		}
		return null;
	}

	private int getListSize(Arglist argList, String itemKey) {
		String listSizeParam = itemKey + "_" + Params.LISTSIZE;
		if (argList.containsArg(listSizeParam)) {
			return Integer.valueOf(argList.getArg(listSizeParam));
		}
		return 0;
	}
}

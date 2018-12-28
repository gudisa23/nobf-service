package com.att.api.nobf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amind.att.QualToolConstants;
import com.amind.att.controller.solution.avpn.SSDFQualifyAddressRequest;
import com.amind.att.service.avpn.SSDFQualifyAddressServiceImpl;
import com.amind.att.service.csi.ValidateAddressServiceAvailabilityHandler;
import com.att.api.nobf.model.ADIQualifyAddressResponse;
import com.att.ssdf.cpq.commondatamodel.StateCodeUSOnlyInfo;
import com.att.ssdf.cpq.commondatamodel.TrueFalseInfo;
import com.att.ssdf.cpq.commondatamodel.USAUnfieldedAddressInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.QualifyAddressesRequestInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityrequest.ValidateAddressServiceAvailabilityRequestInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityresponse.ValidateAddressServiceAvailabilityResponseInfo;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.AddressMatchInfo;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.AddressStateInfo;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.AddressZipInfo;
import com.google.common.base.Strings;

@Service
public class NewOnlineBuyFlowService implements INewOnlineBuyFlowService {
	private static final Logger logger = LoggerFactory.getLogger(INewOnlineBuyFlowService.class);
	private static final String GRID_LOOKUP_ID = "1";
	private static final Map<String, String> ERROR_CODE_TO_DESCRIPTION = initializeErrorCodeToDescriptionMap();

	@Autowired
	private SSDFQualifyAddressServiceImpl ssdfService;

	@Autowired
	private ValidateAddressServiceAvailabilityHandler validateAddressServiceAvailabilityHandler;

	@Value(value = "${ssdf.userid:ssdf}")
	private String ssdfUserId;

	public NewOnlineBuyFlowService() {
	}

	/**
	 * Validates if the given address is valid. Note: This implemenation was
	 * borrowed in part from SalesExpress
	 *
	 * @param line1
	 *            Address line 1
	 * @param line2
	 *            Address line 2
	 * @param city
	 *            Address city
	 * @param state
	 *            Address state two char
	 * @param zip
	 *            Address zip code
	 *
	 * @return A ValidateAddressServiceAvailabilityResponseInfo structure containing
	 *         standardized address
	 */
	@Override
	public ValidateAddressServiceAvailabilityResponseInfo validateAddressServiceAvail(final String line1,
			final String line2, final String city, final String state, final String zip) {
		ValidateAddressServiceAvailabilityRequestInfo request = new ValidateAddressServiceAvailabilityRequestInfo();
		request.setMode("A");
		request.setNearMatchIndicator(true);
		List<ValidateAddressServiceAvailabilityRequestInfo.AddressDetails> addressDetailList = request
				.getAddressDetails();
		ValidateAddressServiceAvailabilityRequestInfo.AddressDetails addressDetails = new ValidateAddressServiceAvailabilityRequestInfo.AddressDetails();
		addressDetailList.add(addressDetails);

		AddressMatchInfo addressMatchInfo = new AddressMatchInfo();
		addressMatchInfo.setCity(city);
		addressMatchInfo.setCountry("US"); // NOT "USA"!
		try {
			addressMatchInfo.setState(AddressStateInfo.valueOf(state));
		} catch (IllegalArgumentException ex) {
			logger.info("IllegalArgumentException in validatePostalAddress: " + ex);
			ValidateAddressServiceAvailabilityResponseInfo result = new ValidateAddressServiceAvailabilityResponseInfo();
			com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo responseInfo = new com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo();
			responseInfo.setCode("-1");
			responseInfo.setDescription("Invalid State");
			result.setResponse(responseInfo);
			return result;
		}

		addressMatchInfo.setStreet(line1 + " " + line2);

		AddressZipInfo zipInfo = new AddressZipInfo();
		zipInfo.setZipCode(zip);
		addressMatchInfo.setZip(zipInfo);
		addressDetails.setAddress(addressMatchInfo);

		try {
			ValidateAddressServiceAvailabilityResponseInfo result = validateAddressServiceAvailabilityHandler
					.initiateService(request).get();
			return result;
		} catch (RuntimeException e) {
			logger.error("Error validatePostalAddress - re: ", e);
			throw e;
		}

	}

	/**
	 * Returns information about the solutions for which the given address
	 * qualifies. Note: This implemenation was borrowed in part from SalesExpress
	 *
	 * @param request
	 *            The request, containing detailed address information
	 *
	 * @return A QualifyAddressesResponseInfo structure, representing the solutions
	 *         available at the given address.
	 */
	@Override
	public ADIQualifyAddressResponse processAddressQualificationRequest(final SSDFQualifyAddressRequest request) {
		request.setUserId(ssdfUserId);
		request.setGlidLookup(GRID_LOOKUP_ID);

		QualifyAddressesResponseInfo qualifyAddressesResponseInfo = new QualifyAddressesResponseInfo();
		QualifyAddressesRequestInfo qualifyAddressesRequest = new QualifyAddressesRequestInfo();

		try {
			constructQualifyAddressSyncRequest(qualifyAddressesRequest, request);
			qualifyAddressesResponseInfo = ssdfService.qualifyAddresses(qualifyAddressesRequest);
		} catch (Exception e) {
			String errorCode = "";
			if (e.getCause() != null && e.getCause().getMessage() != null) {
				errorCode = e.getCause().getMessage().toString();
			}
			errorCode = ERROR_CODE_TO_DESCRIPTION.containsKey(errorCode) ? errorCode : "0";
			logger.warn("Exception occurred in AvpnAddressValidation#SSDFAddressValidation: " + errorCode + " - "
					+ ERROR_CODE_TO_DESCRIPTION.get(errorCode));
		}

		//Utilizing Java 8 null safety to avoid 7 levels of if != null statements
		TrueFalseInfo isAvailable = Optional.of(qualifyAddressesResponseInfo)
				.map(x -> x.getMultiProductAvailabilityResponse()).map(x -> x.isEmpty() ? null : x.get(0))
				.map(x -> x.getMultiProductAvailability()).map(x -> x.getQualifiedProducts())
				.map(x -> x.getDataProducts()).map(x -> x.getMisAvailabilityFlag()).orElse(TrueFalseInfo.FALSE);

		return new ADIQualifyAddressResponse(isAvailable == TrueFalseInfo.TRUE);

	}

	/**
	 * Note: This implemenation was borrowed in part from SalesExpress
	 *
	 * @param qualifyAddressesRequest
	 * @param request
	 */
	private void constructQualifyAddressSyncRequest(final QualifyAddressesRequestInfo qualifyAddressesRequest,
			SSDFQualifyAddressRequest request) {

		qualifyAddressesRequest.setProcessMethod(QualToolConstants.SYNC_PROCESS_METHOD);
		qualifyAddressesRequest.setClientName(QualToolConstants.QUAL_CLIENT_NAME);
		QualifyAddressesRequestInfo.MultiProductAvailabilityRequest multiProductAvailabilityRequest = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest();

		setMultiProductAvailabilityRequestObject(multiProductAvailabilityRequest);

		multiProductAvailabilityRequest.getRequestedProducts().setAvpnExpressIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setAniraIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setAttDSLIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setAttPremisesBasedFirewallIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setDdosDefenseIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setAttWIFIIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setMisExpressIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setMisIndicator(true);

		QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP bvoip = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP();
		bvoip.setBvoipIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setBVOIP(bvoip);

		QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE ase = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE();
		ase.setAseIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setASE(ase);

		QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ATTCollaborate attCollaborate = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ATTCollaborate();
		attCollaborate.setAttCollaborateIndicator(true);
		multiProductAvailabilityRequest.getRequestedProducts().setATTCollaborate(attCollaborate);

		setMultiProductAvailabilityRequestWithSiteData(multiProductAvailabilityRequest, request);

		qualifyAddressesRequest.getMultiProductAvailabilityRequest().add(multiProductAvailabilityRequest);

	}

	/**
	 * Note: This implemenation was borrowed in part from SalesExpress
	 * 
	 * @param multiProductAvailabilityRequest
	 */
	private void setMultiProductAvailabilityRequestObject(
			final QualifyAddressesRequestInfo.MultiProductAvailabilityRequest multiProductAvailabilityRequest) {

		if (multiProductAvailabilityRequest.getLocation() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location location = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location();
			multiProductAvailabilityRequest.setLocation(location);
		}
		if (multiProductAvailabilityRequest.getLocation().getLocationOptions() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location.LocationOptions locationOptions = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location.LocationOptions();
			multiProductAvailabilityRequest.getLocation().setLocationOptions(locationOptions);
		}
		if (multiProductAvailabilityRequest.getLocation().getValidationOptions() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location.ValidationOptions validationOptions = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.Location.ValidationOptions();
			multiProductAvailabilityRequest.getLocation().setValidationOptions(validationOptions);
		}
		if (multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress() == null) {
			USAUnfieldedAddressInfo usaUnfieldedAddressInfo = new USAUnfieldedAddressInfo();
			multiProductAvailabilityRequest.getLocation().getLocationOptions()
					.setUnfieldedAddress(usaUnfieldedAddressInfo);
		}

		if (multiProductAvailabilityRequest.getRequestedProducts() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts requestedProducts = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts();

			requestedProducts.setAvpnExpressIndicator(true);
			multiProductAvailabilityRequest.setRequestedProducts(requestedProducts);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getASE() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE aseProduct = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE();
			multiProductAvailabilityRequest.getRequestedProducts().setASE(aseProduct);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getASE().getAdditionalRequestData() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE.AdditionalRequestData additionalReqDataForASE = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.ASE.AdditionalRequestData();
			multiProductAvailabilityRequest.getRequestedProducts().getASE()
					.setAdditionalRequestData(additionalReqDataForASE);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getBVOIP() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP bvoipProduct = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP();
			multiProductAvailabilityRequest.getRequestedProducts().setBVOIP(bvoipProduct);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getBVOIP().getAdditionalRequestData() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP.AdditionalRequestData additionalReqDataForBVOIP = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.BVOIP.AdditionalRequestData();
			multiProductAvailabilityRequest.getRequestedProducts().getBVOIP()
					.setAdditionalRequestData(additionalReqDataForBVOIP);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getHSIAE() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.HSIAE hsiaeProduct = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.HSIAE();
			multiProductAvailabilityRequest.getRequestedProducts().setHSIAE(hsiaeProduct);
		}
		if (multiProductAvailabilityRequest.getRequestedProducts().getHSIAE().getSpeedOptions() == null) {
			QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.HSIAE.SpeedOptions speedOptions = new QualifyAddressesRequestInfo.MultiProductAvailabilityRequest.RequestedProducts.HSIAE.SpeedOptions();
			multiProductAvailabilityRequest.getRequestedProducts().getHSIAE().setSpeedOptions(speedOptions);
		}
	}

	/**
	 * Note: This implemenation was borrowed in part from SalesExpress
	 * 
	 * @param multiProductAvailabilityRequest
	 * @param request
	 */
	private void setMultiProductAvailabilityRequestWithSiteData(
			final QualifyAddressesRequestInfo.MultiProductAvailabilityRequest multiProductAvailabilityRequest,
			final SSDFQualifyAddressRequest request) {

		if (Strings.isNullOrEmpty(request.getGlobalLocationId())) {
			// Address Fields
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setAddressLine(request.getStreetName());
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setCity(request.getCity());
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setState(StateCodeUSOnlyInfo.valueOf(request.getState()));

			String zip = request.getZip();
			String zipPlus4 = null;
			int idxZip = request.getZip().indexOf("-");
			if (idxZip != -1) {
				zip = request.getZip().substring(0, idxZip);
				zipPlus4 = request.getZip().substring(idxZip + 1);
			}
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress().setPostalCode(zip);
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setPostalCodePlus4(zipPlus4);

		} else {
			multiProductAvailabilityRequest.getLocation().getLocationOptions()
					.setGlobalLocationId(request.getGlobalLocationId());
		}
		multiProductAvailabilityRequest.getLocation().getLocationOptions().setLocationID("1");
		multiProductAvailabilityRequest.getLocation().getLocationOptions().setUserID(request.getUserId());

		multiProductAvailabilityRequest.getLocation().getValidationOptions().setMaxAlternativeReturn(5);
		multiProductAvailabilityRequest.getLocation().getValidationOptions().setLecValidationIndicator(true);

		multiProductAvailabilityRequest.getLocation().getValidationOptions().setGlidLookup(request.getGlidLookup());

		// Optional Fields
		if (!Strings.isNullOrEmpty(request.getBuilding())) {
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setStructureValue(request.getBuilding());
		}
		if (!Strings.isNullOrEmpty(request.getRoom())) {
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setUnitValue(request.getRoom());
		}
		if (!Strings.isNullOrEmpty(request.getFloor())) {
			multiProductAvailabilityRequest.getLocation().getLocationOptions().getUnfieldedAddress()
					.setLevelValue(request.getFloor());
		}
		multiProductAvailabilityRequest.getLocation().getValidationOptions().setMaxAlternativeReturn(5);
	}

	private static Map<String, String> initializeErrorCodeToDescriptionMap() {
		Map<String, String> errorCodeToDescriptionMap = new HashMap<String, String>();

		errorCodeToDescriptionMap.put("00000", "No Data Found");
		errorCodeToDescriptionMap.put("00001", "Data Unreadable");
		errorCodeToDescriptionMap.put("00002", "Back-end System is down or not available");
		errorCodeToDescriptionMap.put("00005", "Out Of Sequence Tag: Tag Name");
		errorCodeToDescriptionMap.put("00006", "Unexpected Tag Name: Tag Name");
		errorCodeToDescriptionMap.put("00007", "Required Data Missing: Input Field");
		errorCodeToDescriptionMap.put("00008", "Type, Length, Format or Value is Invalid: Input Field");
		errorCodeToDescriptionMap.put("00009", "Others");
		errorCodeToDescriptionMap.put("00010", "No Structure Name Match");
		errorCodeToDescriptionMap.put("00011", "Processing Error Occurred");
		errorCodeToDescriptionMap.put("00012", "Multiple matches found");
		errorCodeToDescriptionMap.put("00014", "Timeout error.  Please retry later or issue a different request");
		errorCodeToDescriptionMap.put("00018", "Invalid format: <data element name>");
		errorCodeToDescriptionMap.put("00019", "Unexpected system error");
		errorCodeToDescriptionMap.put("00100", "System Exception");
		errorCodeToDescriptionMap.put("00202", "Max Rows Exceeded");
		errorCodeToDescriptionMap.put("00300", "Illegal Access");
		errorCodeToDescriptionMap.put("00301", "Invalid Input");
		errorCodeToDescriptionMap.put("00400", "User Exception");
		errorCodeToDescriptionMap.put("0", "No Error Code Retrieved from SSDF");

		return errorCodeToDescriptionMap;
	}
}

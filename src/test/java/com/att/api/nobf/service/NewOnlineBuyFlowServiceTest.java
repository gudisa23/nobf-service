package com.att.api.nobf.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.amind.att.controller.solution.avpn.SSDFQualifyAddressRequest;
import com.amind.att.service.avpn.SSDFQualifyAddressServiceImpl;
import com.amind.att.service.csi.ValidateAddressServiceAvailabilityHandler;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.att.api.nobf.model.ADIQualifyAddressResponse;
import com.att.api.nobf.service.NewOnlineBuyFlowService;
import com.att.ssdf.cpq.commondatamodel.ResponseInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.QualifyAddressesRequestInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import com.bcg.api.common.exception.Thrower;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityrequest.ValidateAddressServiceAvailabilityRequestInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityresponse.ValidateAddressServiceAvailabilityResponseInfo;

@RunWith(MockitoJUnitRunner.class)
public class NewOnlineBuyFlowServiceTest {

	private Thrower myThrower = new Thrower();
	
	private IWebServiceResponse<ValidateAddressServiceAvailabilityResponseInfo> mockValidateAddressWebServiceResponse;
	
	@Mock
	SSDFQualifyAddressServiceImpl ssdfService;
	
	@Mock
    private ValidateAddressServiceAvailabilityHandler validateAddressServiceAvailabilityHandler;
	
	@InjectMocks
	NewOnlineBuyFlowService newOnlineBuyFlowService;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
        mockValidateAddressWebServiceResponse = new IWebServiceResponse<ValidateAddressServiceAvailabilityResponseInfo>() {

			@Override
			public ValidateAddressServiceAvailabilityResponseInfo get() {
				ValidateAddressServiceAvailabilityResponseInfo result = new ValidateAddressServiceAvailabilityResponseInfo();
				com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo responseInfo = new com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo();
				responseInfo.setCode("1");
				responseInfo.setDescription("SUCCESS - found valid address");
				result.setResponse(responseInfo);
				return result;
			}

			@Override
			public Long getEntityId() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public String getEntityName() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public String getConversationId() {
				// Auto-generated method stub
				return null;
			}
        };
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void WhenAddressHasAnInvalidState_ThenAddressValidationResponseHasFailedCode() {
		String addrLine1 = "208 S Akard ST";
		String addrLine2 = "FLOOR 8";
		String city = "Dallas";
		String state = "XX";
		String zip = "75202";
		ValidateAddressServiceAvailabilityResponseInfo result = newOnlineBuyFlowService.validateAddressServiceAvail(addrLine1, addrLine2, city, state, zip);
		com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo responseInfo = result.getResponse();
		assertEquals(responseInfo.getCode(), "-1");
		assertEquals(responseInfo.getDescription(), "Invalid State");
	}
	
	@Test
	public void WhenAddressIsValid_ThenAddressValidationResponseHasSuccessCode() {
		String addrLine1 = "208 S Akard ST";
		String addrLine2 = "FLOOR 8";
		String city = "Dallas";
		String state = "TX";
		String zip = "75202";
        
        Mockito.when(validateAddressServiceAvailabilityHandler.initiateService(ArgumentMatchers.any(ValidateAddressServiceAvailabilityRequestInfo.class))).thenReturn(mockValidateAddressWebServiceResponse);
		ValidateAddressServiceAvailabilityResponseInfo result = newOnlineBuyFlowService.validateAddressServiceAvail(addrLine1, addrLine2, city, state, zip);
		com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ResponseInfo responseInfo = result.getResponse();
		assertEquals(responseInfo.getCode(), "1");
		assertEquals(responseInfo.getDescription(), "SUCCESS - found valid address");
	}
	
	@Test
	public void WhenQualifyAddressRequestIsInvalid_ThenReturnResponseWithErrorCode() {
		String addrLine1 = "208 S Akard ST";
		String addrLine2 = "FLOOR 8";
		String city = "Dallas";
		String state = "TX";
		String zip = "75202";
		
        final SSDFQualifyAddressRequest request = new SSDFQualifyAddressRequest();
        request.setStreetName(addrLine1 + " " + addrLine2);
        request.setCity(city);
        request.setState(state);
        request.setZip(zip);
        
        QualifyAddressesResponseInfo qualifyAddressesResponseInfo = new QualifyAddressesResponseInfo();
        ResponseInfo responseInfo = new ResponseInfo();
		responseInfo.setCode("00000");
		responseInfo.setDescription("No Data Found");
        qualifyAddressesResponseInfo.setResponse(responseInfo);
        
        Exception ex = new Exception();
        try { myThrower.throwsRuntimeWithCause("00000"); } catch (Exception ex1) { ex = ex1; }
		Mockito.when(ssdfService.qualifyAddresses(ArgumentMatchers.any(QualifyAddressesRequestInfo.class))).thenThrow(ex);
		
		ADIQualifyAddressResponse result = newOnlineBuyFlowService.processAddressQualificationRequest(request);
		assertFalse(result.isAvailable());
	}
}

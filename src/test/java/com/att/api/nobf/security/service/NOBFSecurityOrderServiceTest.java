package com.att.api.nobf.security.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.api.nobf.ApiConfig;
import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.security.model.SecurityOrder;



@RunWith(SpringRunner.class)
public class NOBFSecurityOrderServiceTest {
	
	@TestConfiguration
	static class SecurityOrderServiceTestConfiguration{
		@Bean
		public NOBFSecurityOrderService nobfsoService() {
			return new NOBFSecurityOrderService();
		}
	}
	
	@Autowired
	private NOBFSecurityOrderService nobfsoService; 
	
	@MockBean
	private ApiConfig apiConfig;

	@MockBean
	private DBService dbService;
	
	@MockBean
	private ISecurityEmailService securityEmailService;
	
	@Test
	public void testSubmitOrderAllServicesSuccessful() {
		SecurityOrder testOrder = new SecurityOrder();
		String testEmailBody = "email 1";
		String testOrderID = "ID 1";
		Mockito.when(dbService.saveSecurityOrder(testOrder)).thenReturn(testOrderID);
		Mockito.when(securityEmailService.emailSecurityOrder(testOrder)).thenReturn(testEmailBody);
		Mockito.when(dbService.saveOrderNotify(testOrder, "Security", testEmailBody, testOrderID)).thenReturn(true);
		
		ResponseEntity<FrontEndResponse> result = nobfsoService.submitOrder(testOrder);
		
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody().getMessage().contains(testOrderID + " was successfully emailed."));
		Assert.assertEquals(testOrderID, result.getBody().getOrderID());
		Assert.assertEquals(FrontEndResponse.SUCCESS_CODE, result.getBody().getSuccessOrErrorCode());
	}
	
	@Test
	public void testSubmitOrderOrderWriteFailure() {
		SecurityOrder testOrder = new SecurityOrder();
		String testEmailBody = "email 1";
		String testOrderID = null;
		Mockito.when(dbService.saveSecurityOrder(testOrder)).thenReturn(testOrderID);
		Mockito.when(securityEmailService.emailSecurityOrder(testOrder)).thenReturn(testEmailBody);
		Mockito.when(dbService.saveOrderNotify(testOrder, "Security", testEmailBody, testOrderID)).thenReturn(true);
		
		ResponseEntity<FrontEndResponse> result = nobfsoService.submitOrder(testOrder);
		
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody().getMessage().contains("Failed to write Security product order to database."));
		Assert.assertEquals(testOrderID, result.getBody().getOrderID());
		Assert.assertEquals(FrontEndResponse.SUCCESS_CODE, result.getBody().getSuccessOrErrorCode());
	}
	
	@Test
	public void testSubmitOrderEmailWriteFailure() {
		SecurityOrder testOrder = new SecurityOrder();
		String testEmailBody = null;
		String testOrderID = "ID 1";
		Mockito.when(dbService.saveSecurityOrder(testOrder)).thenReturn(testOrderID);
		Mockito.when(securityEmailService.emailSecurityOrder(testOrder)).thenReturn(testEmailBody);
		
		ResponseEntity<FrontEndResponse> result = nobfsoService.submitOrder(testOrder);
		
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody().getMessage().contains("ERROR - Failed to send Security product order email for order ID "+testOrderID+"."));
		Assert.assertEquals(testOrderID, result.getBody().getOrderID());
		Assert.assertEquals(FrontEndResponse.ERROR_CODE, result.getBody().getSuccessOrErrorCode());
	}
	
	@Test
	public void testSubmitOrderEmailDBWriteFailure() {
		SecurityOrder testOrder = new SecurityOrder();
		String testEmailBody = "email 1";
		String testOrderID = "ID 1";
		Mockito.when(dbService.saveSecurityOrder(testOrder)).thenReturn(testOrderID);
		Mockito.when(securityEmailService.emailSecurityOrder(testOrder)).thenReturn(testEmailBody);
		Mockito.when(dbService.saveOrderNotify(testOrder, "Security", testEmailBody, testOrderID)).thenReturn(false);
		
		ResponseEntity<FrontEndResponse> result = nobfsoService.submitOrder(testOrder);
		
		Assert.assertNotNull(result.getBody());
		Assert.assertTrue(result.getBody().getMessage().contains("WARNING - Failed to write Security product order email for order ID "+testOrderID+" to database."));
		Assert.assertEquals(testOrderID, result.getBody().getOrderID());
		Assert.assertEquals(FrontEndResponse.SUCCESS_CODE, result.getBody().getSuccessOrErrorCode());
	}

}

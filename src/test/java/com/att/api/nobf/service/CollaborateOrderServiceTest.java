package com.att.api.nobf.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.Equipment;
import com.att.api.nobf.model.OrderAddress;
import com.att.api.nobf.model.OrderContact;
import com.att.api.nobf.model.OrderNotify;

@RunWith(MockitoJUnitRunner.class)
public class CollaborateOrderServiceTest {

	@InjectMocks
	CollaborateOrderService coService;
	
	@Mock
	DBService dbService;

//	@Before
//	public void setUp() throws Exception {
//		List<OrderNotify> onList = new ArrayList<OrderNotify>();
//		OrderNotify on = new OrderNotify();
//		on.setCreateDate(new Date());
//		on.setEmailSent(true);
//		on.setLastModifiedDate(new Date());
//		on.setOrderType("Collaborate");
//		on.setReferenceOrderID("abcde");
//		on.setHtmlText("this was the email that was sent");
//		//on.setData(data);
//		
//		onList.add(on);
//		Mockito.when(dbService.retrieveOrders()).thenReturn(onList);
//		
//	}
	

	
	/**
	 * This method verifies that the equipment provider displayed is correct for purchased devices
	 */
	@Test
	public void testEquipmentProviderWithPurchasedDevices() {
		CollaborateOrder co = createBaseCollaborateOrder();
		
		co.setBuyATTDevice(true);
		co.setUseExistingDevice(false);
		Map<String, String> convertedMap  = coService.buildTemplateModelMap(co);
		
		String value = (String)convertedMap.get("equipmentProvider");
		
		assertEquals(value, CollaborateOrderService.EQUIPMENT_PROVIDER_ATT);
	}
	
	/**
	 * This method verifies that the equipment provider displayed is correct for BYO devices
	 */
	@Test
	public void testEquipmentProviderWithBYODevices() {
		CollaborateOrder co = createBaseCollaborateOrder();
		
		co.setBuyATTDevice(false);
		co.setUseExistingDevice(true);
		Map<String, String> convertedMap  = coService.buildTemplateModelMap(co);
		
		String value = (String)convertedMap.get("equipmentProvider");
		
		assertEquals(value, CollaborateOrderService.EQUIPMENT_PROVIDER_BYOD);
	}
	
	/**
	 * This method verifies that the equipment provider displayed is correct for both purchased and BYO devices
	 */
	@Test
	public void testEquipmentProviderWithPurchasedandBYODevices() {
		CollaborateOrder co = createBaseCollaborateOrder();
		
		co.setBuyATTDevice(true);
		co.setUseExistingDevice(true);
		Map<String, String> convertedMap  = coService.buildTemplateModelMap(co);
		
		String value = (String)convertedMap.get("equipmentProvider");
		
		assertEquals(value, CollaborateOrderService.EQUIPMENT_PROVIDER_ATT_AND_BYOD);
	}
	
	//This method should be used for creating a simple object 
	//which can then be used to override specific attributes
	private CollaborateOrder createBaseCollaborateOrder() {
		CollaborateOrder co = new CollaborateOrder();
		OrderAddress oa = new OrderAddress();
		oa.setAddress("123 main st");
		oa.setAddressSecondary("suite 240");
		oa.setAddressCity("Dallas");
		oa.setAddressState("Texas");
		oa.setAddressZip("23433");
		
		OrderContact oc = new OrderContact();
		oc.setFirstName("Jose");
		oc.setLastName("Canseco");
		oc.setEmail("jose@canstrikeout.com");
		oc.setJobTitle("Baseball Player");
		oc.setPhoneNumber("333.444.4423");
		oc.setOrderAddress(oa);
		
		co.setBillingAddress(oa);
		co.setBillingNumber("234.234.4323");
		co.setBusinessName("My Big Co");
		co.setBuyATTDevice(true);
		co.setComputerOrMobileOnly(true);
		co.setCustomerContact(oc);
		
		co.setEligiblePort(true);
		ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();
		Equipment eq = new Equipment();
		eq.setId("id2343");
		eq.setName("Motorola X113");
		eq.setPrice(125);
		eq.setQuantity(2);
		
		co.setEquipmentList(equipmentList);
		co.setExistingDevices(0);
		co.setId("111111");
		co.setNumberOfUsers(2);
		ArrayList<String> numList = new ArrayList<String>();
		numList.add("234.234.4444");
		numList.add("333.444.5555");
		co.setNumbersToPort(numList);
		co.setOrderCreateDate(new Date());
		co.setPortBillingNumber(true);
		co.setPortFullAccount(true);
		co.setPortNumbers(true);
		co.setSecondaryCustomerContact(oc);
		co.setServiceLocation(oa);
		co.setShippingContact(oc);
		co.setSiteContact(oc);
		co.setTechnicalContact(oc);
		co.setUseExistingDevice(true);
		
		
		return co;
		
	}
	
}

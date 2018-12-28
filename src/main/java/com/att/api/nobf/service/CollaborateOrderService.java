package com.att.api.nobf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.Equipment;
import com.att.api.nobf.model.OrderAddress;
import com.att.api.nobf.utility.EmailConstants;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.IEmailService;
import com.att.api.nobf.model.OrderNotify;

@Component
public class CollaborateOrderService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(CollaborateOrderService.class);

	public static final String EQUIPMENT_PROVIDER_ATT = "AT&T";
	public static final String EQUIPMENT_PROVIDER_BYOD = "Bring Your Own";
	public static final String EQUIPMENT_PROVIDER_ATT_AND_BYOD = "Bring Your Own, AT&T";
	
	@Autowired
	private DBService dbService;

	public ResponseEntity<?> generateOrder(CollaborateOrder order) {

		return submitInformation(order);

	}

	public List<OrderNotify> retrieveOrders(int daysToInclude, String product) {
		List<OrderNotify> orders = dbService.retrieveOrders(daysToInclude, product);
		return orders;

	}

	@Autowired
	private IEmailService emailService;

	public EmailDTO buildEmailDTO(CollaborateOrder order) {
		Map<String, String> templateModelMap = buildTemplateModelMap(order);

		EmailDTO emailToSend = new EmailDTO.Builder().to(apiConfig.getCollaborateEmailNotifyTo())
				.subject(EmailConstants.COLLABORATE_ORDER_CREATED_WITH_ORDER_ID)
				.from(apiConfig.getCollaborateEmailNotifyFrom()).withTemplate(true)
				.templateFileName(EmailConstants.COLLABORATE_ORDER_TEMPLATE_FILE_NAME)
				.templateModelMap(templateModelMap).isHtml(false).build();
		return emailToSend;
	}

	/**
	 * 
	 * @param order
	 * @return - email content that was generated based on the
	 */
	private void sendEmailNotification(CollaborateOrder order) {
		Map<String, String> templateModelMap = buildTemplateModelMap(order);

		EmailDTO emailToSend = new EmailDTO.Builder().to(apiConfig.getCollaborateEmailNotifyTo())
				.subject(EmailConstants.COLLABORATE_ORDER_CREATED_WITH_ORDER_ID)
				.from(apiConfig.getCollaborateEmailNotifyFrom()).withTemplate(true)
				.templateFileName(EmailConstants.COLLABORATE_ORDER_TEMPLATE_FILE_NAME)
				.templateModelMap(templateModelMap).isHtml(false).build();

		emailService.prepareAndSendEmail(emailToSend);

	}

	public Map<String, String> buildTemplateModelMap(CollaborateOrder order) {
		Map<String, String> templateModelMap = new HashMap<String, String>();

		OrderAddress billingAddress = order.getBillingAddress();
		OrderAddress serviceAddress = order.getServiceLocation();

		templateModelMap.put("orderID", order.getId());
		templateModelMap.put("businessName", order.getBusinessName());
		templateModelMap.put("orderDate", order.getOrderCreateDate().toString());
		templateModelMap.put("numberOfUsers", order.getNumberOfUsers() + "");
		templateModelMap.put("portFullAccount", order.isPortFullAccount() ? "Full Port" : "Partial Port");
		templateModelMap.put("portNumbers", order.isPortNumbers()?"Yes": "No");
		templateModelMap.put("portBillingNumber", order.isPortBillingNumber()?"Yes": "No");

		templateModelMap.put("billingPhoneNumber", order.getBillingNumber());
		if (order.getNumbersToPort() != null) {
			templateModelMap.put("qtyNumberToPort",
					(order.getNumbersToPort() != null) ? order.getNumbersToPort().size() + "" : "0");
			templateModelMap.put("qtyNewNumbersNeeded",
					order.getNumberOfUsers() - order.getNumbersToPort().size() + "");
		}
		
		String equipList = "";
		if (order != null && order.getEquipmentList() != null) {
			for (Equipment equip : order.getEquipmentList()) {
				String line = "item id: " + equip.getId() + " item: " + equip.getName() + " qty: " + equip.getQuantity()
						+ " price: " + equip.getPrice() + "\n";
				equipList += line;
			}

			
		}
		templateModelMap.put("equipmentList", equipList);

		templateModelMap.put("computerOrMobileOnly", order.isComputerOrMobileOnly()?"Yes" : "No");

	
		if (order.isUseExistingDevice() && order.isBuyATTDevice()) {
			templateModelMap.put("equipmentProvider", EQUIPMENT_PROVIDER_ATT_AND_BYOD);
		} else if(order.isUseExistingDevice()) {
			templateModelMap.put("equipmentProvider", EQUIPMENT_PROVIDER_BYOD);
		}else if(order.isBuyATTDevice()) {
			templateModelMap.put("equipmentProvider", EQUIPMENT_PROVIDER_ATT);
		}
		
		
		
		
		String phoneNumbersToPort = "";

		if (order.getNumbersToPort() != null) {
			for (String phone : order.getNumbersToPort()) {
				phoneNumbersToPort += phone + ";";
			}
		}

		templateModelMap.put("phoneNumbersToPort", phoneNumbersToPort);

		if (order != null && order.getBillingAddress() != null) {

			templateModelMap.put("billingAddress1", billingAddress.getAddress());
			templateModelMap.put("billingAddress2", billingAddress.getAddressSecondary());
			templateModelMap.put("billingCity", billingAddress.getAddressCity());
			templateModelMap.put("billingState", billingAddress.getAddressState());
			templateModelMap.put("billingZip", billingAddress.getAddressZip());
		}

		if (order != null && order.getServiceLocation() != null) {

			templateModelMap.put("serviceAddress1", serviceAddress.getAddress());
			templateModelMap.put("serviceAddress2", serviceAddress.getAddressSecondary());
			templateModelMap.put("serviceCity", serviceAddress.getAddressCity());
			templateModelMap.put("serviceState", serviceAddress.getAddressState());
			templateModelMap.put("serviceZip", serviceAddress.getAddressZip());
		}

		if (order != null && order.getCustomerContact() != null) {

			templateModelMap.put("primaryContactFirstName", order.getCustomerContact().getFirstName());
			templateModelMap.put("primaryContactLastName", order.getCustomerContact().getLastName());
			templateModelMap.put("primaryContactEmail", order.getCustomerContact().getEmail());
			templateModelMap.put("primaryContactPhone", order.getCustomerContact().getPhoneNumber());
			templateModelMap.put("primaryContactJobTitle", order.getCustomerContact().getJobTitle());
			if (order.getCustomerContact().getOrderAddress() != null) {
				templateModelMap.put("primaryContactAddress1",
						order.getCustomerContact().getOrderAddress().getAddress());
				templateModelMap.put("primaryContactAddress2",
						order.getCustomerContact().getOrderAddress().getAddressSecondary());
				templateModelMap.put("primaryContactCity",
						order.getCustomerContact().getOrderAddress().getAddressCity());
				templateModelMap.put("primaryContactState",
						order.getCustomerContact().getOrderAddress().getAddressState());
				templateModelMap.put("primaryContactZip", order.getCustomerContact().getOrderAddress().getAddressZip());
			}
		}
		if (order != null && order.getSecondaryCustomerContact() != null) {

			templateModelMap.put("secondaryContactFirstName", order.getSecondaryCustomerContact().getFirstName());
			templateModelMap.put("secondaryContactLastName", order.getSecondaryCustomerContact().getLastName());
			templateModelMap.put("secondaryContactEmail", order.getSecondaryCustomerContact().getEmail());
			templateModelMap.put("secondaryContactPhone", order.getSecondaryCustomerContact().getPhoneNumber());
			templateModelMap.put("secondaryContactJobTitle", order.getSecondaryCustomerContact().getJobTitle());
			if (order.getSecondaryCustomerContact().getOrderAddress() != null) {
				templateModelMap.put("secondaryContactAddress1",
						order.getSecondaryCustomerContact().getOrderAddress().getAddress());
				templateModelMap.put("secondaryContactAddress2",
						order.getSecondaryCustomerContact().getOrderAddress().getAddressSecondary());
				templateModelMap.put("secondaryContactCity",
						order.getSecondaryCustomerContact().getOrderAddress().getAddressCity());
				templateModelMap.put("secondaryContactState",
						order.getSecondaryCustomerContact().getOrderAddress().getAddressState());
				templateModelMap.put("secondaryContactZip",
						order.getSecondaryCustomerContact().getOrderAddress().getAddressZip());
			}
		}

		if (order != null && order.getTechnicalContact() != null) {
			logger.warn("Technical Contact information was not passed from UI");
			templateModelMap.put("technicalContactFirstName", order.getTechnicalContact().getFirstName());
			templateModelMap.put("technicalContactLastName", order.getTechnicalContact().getLastName());
			templateModelMap.put("technicalContactEmail", order.getTechnicalContact().getEmail());
			templateModelMap.put("technicalContactPhone", order.getTechnicalContact().getPhoneNumber());
			templateModelMap.put("technicalContactJobTitle", order.getTechnicalContact().getJobTitle());
			if (order.getTechnicalContact().getOrderAddress() != null) {
				templateModelMap.put("technicalContactAddress1",
						order.getTechnicalContact().getOrderAddress().getAddress());
				templateModelMap.put("technicalContactAddress2",
						order.getTechnicalContact().getOrderAddress().getAddressSecondary());
				templateModelMap.put("technicalContactCity",
						order.getTechnicalContact().getOrderAddress().getAddressCity());
				templateModelMap.put("technicalContactState",
						order.getTechnicalContact().getOrderAddress().getAddressState());
				templateModelMap.put("technicalContactZip",
						order.getTechnicalContact().getOrderAddress().getAddressZip());
			}
		}

		if (order != null && order.getSiteContact() != null) {
			logger.warn("Site Contact information was not passed from UI");
			templateModelMap.put("siteContactFirstName", order.getSiteContact().getFirstName());
			templateModelMap.put("siteContactLastName", order.getSiteContact().getLastName());
			templateModelMap.put("siteContactEmail", order.getSiteContact().getEmail());
			templateModelMap.put("siteContactPhone", order.getSiteContact().getPhoneNumber());
			templateModelMap.put("siteContactJobTitle", order.getSiteContact().getJobTitle());
			if (order.getSiteContact().getOrderAddress() != null) {
				templateModelMap.put("siteContactAddress1", order.getSiteContact().getOrderAddress().getAddress());
				templateModelMap.put("siteContactAddress2",
						order.getSiteContact().getOrderAddress().getAddressSecondary());
				templateModelMap.put("siteContactCity", order.getSiteContact().getOrderAddress().getAddressCity());
				templateModelMap.put("siteContactState", order.getSiteContact().getOrderAddress().getAddressState());
				templateModelMap.put("siteContactZip", order.getSiteContact().getOrderAddress().getAddressZip());
			}
		}

		if (order != null && order.getShippingContact() != null) {
			logger.warn("Shipping Contact information was not passed from UI");
			templateModelMap.put("shippingContactFirstName", order.getShippingContact().getFirstName());
			templateModelMap.put("shippingContactLastName", order.getShippingContact().getLastName());
			templateModelMap.put("shippingContactEmail", order.getShippingContact().getEmail());
			templateModelMap.put("shippingContactPhone", order.getShippingContact().getPhoneNumber());
			templateModelMap.put("shippingContactJobTitle", order.getShippingContact().getJobTitle());
			if (order.getShippingContact().getOrderAddress() != null) {
				templateModelMap.put("shippingContactAddress1",
						order.getShippingContact().getOrderAddress().getAddress());
				templateModelMap.put("shippingContactAddress2",
						order.getShippingContact().getOrderAddress().getAddressSecondary());
				templateModelMap.put("shippingContactCity",
						order.getShippingContact().getOrderAddress().getAddressCity());
				templateModelMap.put("shippingContactState",
						order.getShippingContact().getOrderAddress().getAddressState());
				templateModelMap.put("shippingContactZip",
						order.getShippingContact().getOrderAddress().getAddressZip());
			}
		}

		return templateModelMap;
	}

	private ResponseEntity<?> submitInformation(CollaborateOrder order) {
		FrontEndResponse feSuccess = new FrontEndResponse();
		feSuccess.setSuccessOrErrorCodeToSuccess();
		feSuccess.setMessage("Request was successful");

		FrontEndResponse feFailure = new FrontEndResponse();
		feFailure.setSuccessOrErrorCodeToError();
		feFailure.setMessage("An error was encountered");

		EmailDTO eDto = buildEmailDTO(order);
		String emailContent = emailService.applyTemplate(eDto.getTemplateFileName(), buildTemplateModelMap(order));

		String orderID = dbService.saveCustomerOrder(order, emailContent);

		sendEmailNotification(order);

		if (orderID != null) {
			feSuccess.setOrderID(orderID);
			return new ResponseEntity<FrontEndResponse>(feSuccess, HttpStatus.OK);
		} else {
			feFailure.setMessage("An error was encountered while creating the order");
			return new ResponseEntity<FrontEndResponse>(feFailure, HttpStatus.OK);
		}

	}

}

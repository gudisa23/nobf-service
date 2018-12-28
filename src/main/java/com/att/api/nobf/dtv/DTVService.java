package com.att.api.nobf.dtv;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.dtv.model.BusinessCustomer;
import com.att.api.nobf.dtv.model.DirectvAddon;
import com.att.api.nobf.dtv.model.DirectvEquipment;
import com.att.api.nobf.dtv.model.DirectvOrder;
import com.att.api.nobf.dtv.model.DirectvPromotion;
import com.att.api.nobf.service.BaseService;
import com.att.api.nobf.utility.EmailConstants;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.IEmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DTVService extends BaseService {

	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private DBService dbService;
	
	private static final Logger logger = LoggerFactory.getLogger(DTVService.class);

	public ResponseEntity<FrontEndResponse> submitOrder(DirectvOrder order) {
		String emailContent = sendEmailNotification(order);
		String orderID = dbService.saveDTVCustomerOrder(order, emailContent);
		
		if (null == orderID) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				logger.warn("Unabled to save DirecTV Order to Database: " + mapper.writeValueAsString(order));
			} catch (JsonProcessingException e) {
				logger.warn("Exception while logging database error: " + e.getLocalizedMessage());
			}
			return failure();
		}
		return success(orderID);
	}

	public ResponseEntity<FrontEndResponse> submitRFI(BusinessCustomer dtvCustomer) {
		String emailContent = sendEmailNotification(dtvCustomer);
		String rfiID = dbService.saveDTVRequestForInformation(dtvCustomer, emailContent);
		
		if (null == rfiID) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				logger.warn("Unabled to save DirecTV Customer to Database: " + mapper.writeValueAsString(rfiID));
			} catch (JsonProcessingException e) {
				logger.warn("Exception while logging database error: " + e.getLocalizedMessage());
			}
			return failure();
		}
		return success(rfiID);
	}
	
	private String parseNotes(String callNotes) {
		if (null == callNotes) {
			return "";
		}
		return callNotes.replaceAll("\"'", "");
	}
	
	private ResponseEntity<FrontEndResponse> success(String id) {
		FrontEndResponse feSuccess = new FrontEndResponse();
		feSuccess.setSuccessOrErrorCodeToSuccess();
		feSuccess.setMessage("Request was successful");
		feSuccess.setOrderID(id);
		return ResponseEntity.ok(feSuccess);
	}

	private ResponseEntity<FrontEndResponse> failure() {
		FrontEndResponse feFailure = new FrontEndResponse();
		feFailure.setSuccessOrErrorCodeToError();
		feFailure.setMessage("An error was encountered storing in database, however email delivery was still attempted");
		return ResponseEntity.ok(feFailure);
	}
	
	
	private String sendEmailNotification(DirectvOrder order) {
		Map<String, String> templateModelMap = buildTemplateModelMap(order);

		EmailDTO emailToSend = new EmailDTO.Builder().to(apiConfig.getDirectvEmailNotifyTo())
				.subject(EmailConstants.DIRECTV_ORDER_EMAIL_SUBJECT).from(apiConfig.getDirectvEmailNotifyFrom())
				.withTemplate(true).templateFileName(EmailConstants.DIRECTV_ORDER_EMAIL_FILENAME)
				.templateModelMap(templateModelMap).isHtml(true).build();
	
		String emailContent = emailService.applyTemplate(emailToSend.getTemplateFileName(), buildTemplateModelMap(order));
		
		emailService.prepareAndSendEmail(emailToSend);
		return emailContent;
	}

	private String sendEmailNotification(BusinessCustomer dtvCustomer) {
		Map<String, String> templateModelMap = buildTemplateModelMap(dtvCustomer);

		EmailDTO emailToSend = new EmailDTO.Builder().to(apiConfig.getDirectvEmailNotifyTo())
				.subject(EmailConstants.DIRECTV_RFI_EMAIL_SUBJECT).from(apiConfig.getDirectvEmailNotifyFrom())
				.withTemplate(true).templateFileName(EmailConstants.DIRECTV_RFI_EMAIL_FILENAME)
				.templateModelMap(templateModelMap).isHtml(true).build();

		emailService.prepareAndSendEmail(emailToSend);
		String emailContent = emailService.applyTemplate(emailToSend.getTemplateFileName(), buildTemplateModelMap(dtvCustomer));
		return emailContent;
	}

	private Map<String, String> buildTemplateModelMap(DirectvOrder order) {
		Map<String, String> templateModelMap = new HashMap<String, String>();
		/*
		 * <h1>DirecTV For Business Order</h1>
		 * 
		 * A new online DirecTV for Business order was submitted on [(${orderDate})]
		 * 
		 * Use the information below to complete the order in Dealer Website Service.
		 * For any questions or issues with this order, please contact
		 * [(${contactDetails})] .
		 * 
		 * <h3>Business Details</h3> Name: [(${firstName})] [(${lastName})] Email:
		 * [(${emailAddress})] Phone: [(${phoneNumber})] Company name:
		 * [(${companyname})] Service Address: [(${address})] Number of floors in
		 * Building: [(${floors})] Best time to call: [(${bestTimeToCall})] Comments:
		 * [(${comments})] Account Type: [(${accountType})] EVO Range: [(${evoRange})]
		 * Fire Code Occupancy: [(${fco})] Note! - Please confirm FCO with customer.
		 * 
		 * <h3>Cart Details</h3> Package Name: [(${packageName})] DWS Key:
		 * [(${packageKey})] Selected Addons (DWS Key): [(${addonKeyList})]
		 * 
		 * <h3>Equipment Details</h3> HD Receiver Count: [(${hdReceiverCount})] HD DVR
		 * Receiver Count: [(${DVRReceiverCount})]
		 * 
		 * <h3>Promotions</h3> Please apply the following promotions:
		 * [(${promotionKeyList})]
		 */
		templateModelMap.put("orderDate", Instant.now().toString());
		templateModelMap.put("contactDetails", EmailConstants.DIRECTV_CONTACT_DETAILS);
		templateModelMap.put("firstName", order.getBusinessCustomer().getFirstName());
		templateModelMap.put("lastName", order.getBusinessCustomer().getLastName());
		templateModelMap.put("emailAddress", order.getBusinessCustomer().getEmailAddress());
		templateModelMap.put("phoneNumber", order.getBusinessCustomer().getPhoneNumber());
		templateModelMap.put("companyname", order.getBusinessCustomer().getBusinessName());
		templateModelMap.put("address1", order.getBusinessCustomer().getAddressLine1());
		templateModelMap.put("address2", order.getBusinessCustomer().getAddressLine2());
		if (null != order.getBusinessCustomer().getNumberOfFloors()) {
			templateModelMap.put("floors", order.getBusinessCustomer().getNumberOfFloors().toString());
		}
		if (null != order.getBusinessCustomer().getCallBackTiming()) {
			templateModelMap.put("bestTimeToCall", order.getBusinessCustomer().getCallBackTiming());
		} else {
			templateModelMap.put("bestTimeToCall", order.getBusinessCustomer().getCallBackPreference());
		}
		templateModelMap.put("comments", parseNotes(order.getBusinessCustomer().getCallBackNotes()));
		templateModelMap.put("accountType", order.getOfferFilter().getAccountType());
		templateModelMap.put("evoRange", order.getOfferFilter().getEvoRange());
		if (null != order.getOfferFilter().getFCORange()) {
			templateModelMap.put("fco", order.getOfferFilter().getFCORange().toString());
		}
		templateModelMap.put("packageName", order.getSelectedPackage().getDisplayName());
		templateModelMap.put("packageKey", order.getSelectedPackage().getProductKey());
		templateModelMap.put("addonKeyList", addonsToString(order.getSelectedAddons()));
		for (DirectvEquipment equip: order.getSelectedEquipment()) {
			if (equip.getDisplayName().contains("DVR")) {
				if (null != equip.getTotalPurchased()) {
					templateModelMap.put("DVRReceiverCount", equip.getTotalPurchased().toString());
				}
			} else {
				if (null != equip.getTotalPurchased()) {
					templateModelMap.put("hdReceiverCount", equip.getTotalPurchased().toString());
				}
			}
		}
		templateModelMap.put("promotionKeyList", promotionsToString(order.getApplicablePromotions()));
		if (null != order.getFirstBillQuotedPrice()) {
			templateModelMap.put("firstBill", order.getFirstBillQuotedPrice().toString());
		}
		if (null != order.getSecondBillQuotedPrice()) {
			templateModelMap.put("secondBill", order.getSecondBillQuotedPrice().toString());
		}
		
		return templateModelMap;
	}
	
	private String addonsToString(List<DirectvAddon> addons) {
		StringBuilder sb = new StringBuilder();
		
		for (DirectvAddon addon: addons) {
			sb.append("<li>Name: " + addon.getDisplayName() + "</li>");
			sb.append("<li> DWS Key: " + addon.getProductKey() + "</li>");
		}
		
		return sb.toString();
	}
	
	private String promotionsToString(List<DirectvPromotion> promos) {
		StringBuilder sb = new StringBuilder();
		
		for (DirectvPromotion promo: promos) {
			sb.append("<li>Name: " + promo.getDisplayName() + "</li>");
			sb.append("<li> DWS Key: " + promo.getProductKey() + "</li>");
		}
		
		return sb.toString();
	}

	private Map<String, String> buildTemplateModelMap(BusinessCustomer dtvCustomer) {
		Map<String, String> templateModelMap = new HashMap<String, String>();
		/*
		 * A new online request for information was submitted on [(${orderDate})]
		 * 
		 * Name: [(${firstName})] [(${lastName})] Email: [(${emailAddress})] Phone:
		 * [(${phoneNumber})] Company name: [(${companyname})] Address: [(${address})]
		 * Number of floors in Building: [(${floors})] Best time to call:
		 * [(${bestTimeToCall})] Comments: [(${comments})]
		 */
		templateModelMap.put("orderDate", Instant.now().toString());
		templateModelMap.put("contactDetails", EmailConstants.DIRECTV_CONTACT_DETAILS);
		templateModelMap.put("firstName", dtvCustomer.getFirstName());
		templateModelMap.put("lastName", dtvCustomer.getLastName());
		templateModelMap.put("emailAddress", dtvCustomer.getEmailAddress());
		templateModelMap.put("phoneNumber", dtvCustomer.getPhoneNumber());
		templateModelMap.put("companyname", dtvCustomer.getBusinessName());
		templateModelMap.put("address1", dtvCustomer.getAddressLine1());
		templateModelMap.put("address2", dtvCustomer.getAddressLine2());
		if (null != dtvCustomer.getNumberOfFloors()) {
			templateModelMap.put("floors", dtvCustomer.getNumberOfFloors().toString());
		}
		if (null != dtvCustomer.getCallBackTiming()) {
			templateModelMap.put("bestTimeToCall", dtvCustomer.getCallBackTiming());
		} else {
			templateModelMap.put("bestTimeToCall", dtvCustomer.getCallBackPreference());
		}
		templateModelMap.put("comments", parseNotes(dtvCustomer.getCallBackNotes()));
		return templateModelMap;
	}
}

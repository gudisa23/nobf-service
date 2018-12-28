package com.att.api.nobf.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.att.api.nobf.db.DBService;
import com.att.api.nobf.dto.FrontEndResponse;
import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.utility.EmailConstants;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.IEmailService;

@Component
public class RFIService extends BaseService {

	@Autowired
	private DBService dbService;

	@Autowired
	private IEmailService emailService;

	private void sendEmailNotification(RFI rfi) {
		Map<String, String> templateModelMap = buildTemplateModelMap(rfi);

		EmailDTO emailToSend = new EmailDTO.Builder().to(apiConfig.getCollaborateEmailNotifyTo())
				.subject(EmailConstants.RFI_SUBJECT_LINE + rfi.getProductInterest())
				.from(apiConfig.getCollaborateEmailNotifyFrom()).withTemplate(true)
				.templateFileName(EmailConstants.RFI_TEMPLATE_FILE_NAME).templateModelMap(templateModelMap)
				.isHtml(false).build();

		emailService.prepareAndSendEmail(emailToSend);
	}

	private Map<String, String> buildTemplateModelMap(RFI rfi) {
		Map<String, String> templateModelMap = new HashMap<String, String>();

		templateModelMap.put("orderID", rfi.getId());

		templateModelMap.put("orderDate", rfi.getOrderCreateDate().toString());
		templateModelMap.put("productInterest", rfi.getProductInterest());

		if (rfi.getProspect() != null) {
			templateModelMap.put("firstName", rfi.getProspect().getFirstName());
			templateModelMap.put("lastName", rfi.getProspect().getLastName());
			templateModelMap.put("emailAddress", rfi.getProspect().getEmailAddress());
			templateModelMap.put("phoneNumber", rfi.getProspect().getPhoneNumber());
			templateModelMap.put("altPhoneNumber", rfi.getProspect().getAltPhoneNumber());
			templateModelMap.put("bestTimeToCall", rfi.getProspect().getBestTimeToCall());
			templateModelMap.put("comments", rfi.getProspect().getComments());
			templateModelMap.put("country", rfi.getProspect().getCountry());

			templateModelMap.put("companyname", rfi.getProspect().getCompanyName());
			templateModelMap.put("companyAddress", rfi.getProspect().getCompanyAddress());
			templateModelMap.put("companyAddress2", rfi.getProspect().getCompanyAddressSecondary());
			templateModelMap.put("companyCity", rfi.getProspect().getCompanyCity());
			templateModelMap.put("companyState", rfi.getProspect().getCompanyState());
			templateModelMap.put("companyZipcode", rfi.getProspect().getCompanyZipcode());
			templateModelMap.put("companyPhoneNumber", rfi.getProspect().getCompanyPhoneNumber());

			if ("moreUsers".equals(rfi.getProspect().getRfiConcern())) {
				templateModelMap.put("rfiDetails", "Customer requested more information on having more than 10 users.");
			} else if ("moreFeatures".equals(rfi.getProspect().getRfiConcern())) {
				templateModelMap.put("rfiDetails", "Customer requested information on additional features.");
			} else if ("morePhones".equals(rfi.getProspect().getRfiConcern())) {
				templateModelMap.put("rfiDetails", "Customer requested information on more phones.");
			} else if ("termsAndConditions".equals(rfi.getProspect().getRfiConcern())) {
				templateModelMap.put("rfiDetails", "Customer requested more information on the terms and conditions.");
			} else {
				templateModelMap.put("rfiDetails", "");
			}

		}

		return templateModelMap;
	}

	public ResponseEntity<?> generateRFI(RFI rfi) {
		return submitInformation(rfi);

	}

	private ResponseEntity<?> submitInformation(RFI rfi) {
		FrontEndResponse feSuccess = new FrontEndResponse();
		feSuccess.setSuccessOrErrorCodeToSuccess();
		feSuccess.setMessage("Request was successful");

		FrontEndResponse feFailure = new FrontEndResponse();
		feFailure.setSuccessOrErrorCodeToError();
		feFailure.setMessage("An error was encountered");

		String orderID = dbService.saveRequestForInformation(rfi);

		sendEmailNotification(rfi);

		if (orderID != null) {
			feSuccess.setOrderID(orderID);
			return new ResponseEntity<FrontEndResponse>(feSuccess, HttpStatus.OK);
		} else {
			feFailure.setMessage("An error was encountered while creating the order");
			return new ResponseEntity<FrontEndResponse>(feFailure, HttpStatus.OK);
		}

	}

}

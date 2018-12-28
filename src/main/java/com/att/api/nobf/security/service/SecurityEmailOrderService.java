package com.att.api.nobf.security.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.api.nobf.model.Address;
import com.att.api.nobf.security.model.SecurityBusinessDetail;
import com.att.api.nobf.security.model.SecurityContact;
import com.att.api.nobf.security.model.SecurityCustomerDetail;
import com.att.api.nobf.security.model.SecurityOrder;
import com.att.api.nobf.security.model.SecurityProduct;
import com.att.api.nobf.service.BaseService;
import com.att.api.nobf.security.constants.SecurityConstants;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.IEmailService;
import com.bcg.api.common.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityEmailOrderService extends BaseService implements ISecurityEmailService {

	@Autowired
	private IEmailService emailService;

	@Override
	public String emailSecurityOrder(SecurityOrder securityOrder) {
		return sendEmailNotification(securityOrder);
	}

	private String sendEmailNotification(SecurityOrder securityOrder) {
		Optional.of(securityOrder).orElseThrow(() -> new IllegalArgumentException("Security order cannot be null!"));

		Map<String, String> templateModelMap = buildTemplateModelMap(securityOrder);

		String[] toEmailAddresses = apiConfig.getSecurityEmailNotifyTo().split(",");
		EmailDTO emailToSend = new EmailDTO.Builder().to(toEmailAddresses[0])
				.subject(SecurityConstants.NEW_SECURITY_ORDER + securityOrder.getId() + " - "
						+ securityOrder.getCustomerDetail().getBusinessDetail().getBusinessName())
				.from(apiConfig.getSecurityEmailNotifyFrom()).withTemplate(true)
				.templateFileName(SecurityConstants.SECURITY_ORDER_TEMPLATE_FILE_NAME)
				.templateModelMap(templateModelMap).build();

		emailService.prepareAndSendEmail(emailToSend);
		return emailToSend.getBody();
	}

	private Map<String, String> buildTemplateModelMap(@NotNull SecurityOrder securityOrder) {
		if (securityOrder == null)
			throw new IllegalArgumentException("Security order cannot be null!");

		Map<String, String> templateModelMap = new HashMap<String, String>();

		templateModelMap.put("securityorderID", securityOrder.getId());
		templateModelMap.put("securityorderDate", getAdiFormattedDate(securityOrder.getCreatedDate()));

		populateServiceDetails(securityOrder, templateModelMap);
		populateCustomerDetails(securityOrder, templateModelMap);

		return templateModelMap;
	}

	private void populateServiceDetails(SecurityOrder securityOrder, Map<String, String> templateModelMap) {
		SecurityEmailDetailsGeneratorFactory securityEmailDtlGenFactory = new SecurityEmailDetailsGeneratorFactory();
		StringBuilder productConfigsSB = new StringBuilder();
		String productConfigurations;
		int index = 0;
		for (SecurityProduct product : securityOrder.getProducts()) {
			ISecurityEmailDetailsGenerator secEmailGenerator = securityEmailDtlGenFactory
					.getSecurityEmailDetailsGenerator(product.getSecurityProductType());
			productConfigurations = secEmailGenerator.generateEmailTemplateDetails(product,
					securityOrder.getInternetSpeed());
			if (index == 0) {
				productConfigsSB.append(productConfigurations);
			}
			if (index > 1)
				productConfigsSB.append("    ").append(productConfigurations);
			index++;

		}
		templateModelMap.put("productConfigurations", productConfigsSB.toString());
	}

	private void populateCustomerDetails(SecurityOrder securityOrder, Map<String, String> templateModelMap) {
		Optional<SecurityCustomerDetail> customer = Optional.ofNullable(securityOrder.getCustomerDetail());
		customer.ifPresent(cust -> {
			populatePrimaryContact(templateModelMap, cust);
			populateSecondaryContact(templateModelMap, cust);
			populateBusinessDetails(templateModelMap, cust);
		});
	}

	private void populatePrimaryContact(Map<String, String> templateModelMap, SecurityCustomerDetail cust) {
		Optional<SecurityContact> primaryContact = Optional.ofNullable(cust.getPrimaryContact());
		primaryContact.ifPresent(priContact -> {
			templateModelMap.put("primaryLastName", priContact.getFirstName());
			templateModelMap.put("primaryFirstName", priContact.getLastName());
			templateModelMap.put("primaryPhoneNumber", priContact.getPhoneNumber());
			templateModelMap.put("primaryEmailId", priContact.getEmail());
		});
		Optional<Address> primaryAddress = Optional.ofNullable(cust.getPrimaryContact().getAddress());
		primaryAddress.ifPresent(primAddress -> {
			templateModelMap.put("primaryAddress1", primAddress.getAddressLine1());
			templateModelMap.put("primaryAddress2", primAddress.getAddressLine2());
			templateModelMap.put("primaryCity", primAddress.getCity());
			templateModelMap.put("primaryState", primAddress.getState());
			templateModelMap.put("primaryZip", primAddress.getZip());
		});
	}

	private void populateSecondaryContact(Map<String, String> templateModelMap, SecurityCustomerDetail cust) {
		Optional<SecurityContact> secondaryContact = Optional.ofNullable(cust.getSecondaryContact());
		secondaryContact.ifPresent(secondContact -> {
			templateModelMap.put("secondaryLastName", secondContact.getFirstName());
			templateModelMap.put("secondaryFirstName", secondContact.getLastName());
			templateModelMap.put("secondaryPhoneNumber", secondContact.getPhoneNumber());
			templateModelMap.put("secondaryEmailId", secondContact.getEmail());
		});
		Optional<Address> secondaryAddress = Optional.ofNullable(cust.getSecondaryContact().getAddress());
		secondaryAddress.ifPresent(secondAddress -> {
			templateModelMap.put("secondaryAddress1", secondAddress.getAddressLine1());
			templateModelMap.put("secondaryAddress2", secondAddress.getAddressLine2());
			templateModelMap.put("secondaryCity", secondAddress.getCity());
			templateModelMap.put("secondaryState", secondAddress.getState());
			templateModelMap.put("secondaryZip", secondAddress.getZip());
		});
	}

	private void populateBusinessDetails(Map<String, String> templateModelMap, SecurityCustomerDetail cust) {
		Optional<SecurityBusinessDetail> securityBusinessDetail = Optional.ofNullable(cust.getBusinessDetail());
		securityBusinessDetail.ifPresent(businessDetail -> {
			templateModelMap.put("businessName", businessDetail.getBusinessName());
		});
		Optional<Address> businessAddress = Optional.ofNullable(cust.getBusinessDetail().getAddress());
		businessAddress.ifPresent(busiAddress -> {
			templateModelMap.put("businessAddress1", busiAddress.getAddressLine1());
			templateModelMap.put("businessAddress2", busiAddress.getAddressLine2());
			templateModelMap.put("businessCity", busiAddress.getCity());
			templateModelMap.put("businessState", busiAddress.getState());
			templateModelMap.put("businessZip", busiAddress.getZip());
		});
	}

	private String getAdiFormattedDate(DateTime givenDate) {
		return DateUtils.getUTCFormattedDate(givenDate, "MM/dd/yyyy HH:mm").replaceAll(" ", " at ") + " UTC";
	}
}

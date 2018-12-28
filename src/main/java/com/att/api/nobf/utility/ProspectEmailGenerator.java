package com.att.api.nobf.utility;

import org.springframework.stereotype.Component;

import com.att.api.nobf.model.Prospect;

@Component
public class ProspectEmailGenerator {

	public String generateHTMLEmail(Prospect p) {
		return String.format(EmailConstants.RFI_PROSPECT_HTML, p.getFirstName(), p.getLastName(), p.getEmailAddress(), p.getPhoneNumber(), p.getCompanyName());
	}
}

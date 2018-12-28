package com.att.api.nobf.utility;

import org.springframework.stereotype.Component;

import com.att.api.nobf.model.OrderAddress;
import com.att.api.nobf.model.OrderContact;
import com.att.api.nobf.model.Prospect;

@Component
public class OrderContactEmailGenerator {

	public static final String COLLABORATE_HTML = "<h2>%s Contact Information</h2><p>"
			+ "<ul><li>Name: %s %s</li>"
			+ "<li>Email: %s</li>"
			+ "<li>Phone: %s</li>"
			+ "<li>Address: %s</li>"
			+ "<li>Address Line 2: %s</li>"
			+ "<li>City: %s</li>"
			+ "<li>State: %s</li>"
			+ "<li>Zip: %s</li>"
			+ "</ul></p>";
	
	public static final String ADDRESS_ONLY_COLLABORATE_HTML = "<h2>%s Address</h2><p>"
			+ "<ul><li>Address: %s</li>"
			+ "<li>Address Line 2: %s</li>"
			+ "<li>City: %s</li>"
			+ "<li>State: %s</li>"
			+ "<li>Zip: %s</li>"
			+ "</ul></p>";
	

	/**
	 * 
	 * @param orderContact
	 * @param contactHeaderDescription - describe the type of contact. i.e. Billing, Technical, etc.
	 * @return
	 */
	public String generateHTMLEmail(OrderContact orderContact, String contactHeaderDescription) {
		return String.format(COLLABORATE_HTML, contactHeaderDescription, orderContact.getFirstName(), orderContact.getLastName(), orderContact.getEmail(), orderContact.getPhoneNumber(), orderContact.getOrderAddress().getAddress(), orderContact.getOrderAddress().getAddressSecondary(), orderContact.getOrderAddress().getAddressCity(), orderContact.getOrderAddress().getAddressState(), orderContact.getOrderAddress().getAddressZip());
	}

	
	public String generateHTMLEmail(OrderAddress orderAddress, String headerDescription) {
		return String.format(ADDRESS_ONLY_COLLABORATE_HTML, headerDescription, orderAddress.getAddress(), orderAddress.getAddressSecondary(), orderAddress.getAddressCity(), orderAddress.getAddressState(), orderAddress.getAddressZip());
	}
}

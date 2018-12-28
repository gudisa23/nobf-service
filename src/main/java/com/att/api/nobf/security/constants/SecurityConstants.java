package com.att.api.nobf.security.constants;

import java.util.ArrayList;
import java.util.List;

public class SecurityConstants {
	public static final List<String> INTERNET_SPEED_RANGES = new ArrayList<String>();
	static {
		INTERNET_SPEED_RANGES.add("0 - 100Mbps");
		INTERNET_SPEED_RANGES.add("100 - 250Mbps");
		INTERNET_SPEED_RANGES.add("250 - 500Mbps");
		INTERNET_SPEED_RANGES.add("500Mbps - 1G");
		INTERNET_SPEED_RANGES.add("1G+");
	}
	
   public static final String NEW_SECURITY_ORDER = "New Security Order - Confirmation #";
   public static final String SECURITY_ORDER_TEMPLATE_FILE_NAME = "SecurityOrder";
	
}

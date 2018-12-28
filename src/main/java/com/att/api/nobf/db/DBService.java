package com.att.api.nobf.db;

import java.util.List;

import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.dtv.model.BusinessCustomer;
import com.att.api.nobf.dtv.model.DirectvOrder;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.OrderNotify;
import com.att.api.nobf.security.model.SecurityOrder;

public interface DBService {
	public String saveRequestForInformation(RFI rfi);
	public String saveCustomerOrder(CollaborateOrder order, String emailContent);
	public String saveSecurityOrder(SecurityOrder order);
	public boolean saveOrderNotify(Object data, String dataType, String emailContent, String referenceID);
	public String saveDTVRequestForInformation(BusinessCustomer rfi, String emailContent);
	public String saveDTVCustomerOrder(DirectvOrder order, String emailContent);
	public List<OrderNotify> retrieveOrders(int daysToInclude, String product);
}


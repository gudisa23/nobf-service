package com.att.api.nobf.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.att.api.nobf.dto.RFI;
import com.att.api.nobf.dtv.integration.DTVOrderRepository;
import com.att.api.nobf.dtv.integration.DTVRFIRepository;
import com.att.api.nobf.dtv.model.BusinessCustomer;
import com.att.api.nobf.dtv.model.DirectvOrder;
import com.att.api.nobf.model.CollaborateOrder;
import com.att.api.nobf.model.OrderNotify;
import com.att.api.nobf.model.OrderType;
import com.att.api.nobf.repository.CollaborateOrderRepository;
import com.att.api.nobf.repository.OrderNotifyRepository;
import com.att.api.nobf.repository.RFIRepository;
import com.att.api.nobf.security.model.SecurityOrder;
import com.att.api.nobf.security.repository.SecurityOrderRepository;


@Component
public class DBServiceImpl implements DBService {
	private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);
	public static final String ADI = "ADI";
	public static final String COLLABORATE = "Collaborate";
	public static final String DIRECTV = "dtv";

	
	@Autowired
	RFIRepository rfiRepository;
	
	@Override
	public String saveRequestForInformation(RFI rfi) {
		logger.info("In saveRequestForInformation method");
		rfi.setId(null); //make sure id is not set, value will be retrieved from db
		logger.info("request for information: "+rfi);
		RFI savedOrder = rfiRepository.save(rfi);
		if (savedOrder != null) 
			logger.info("Request for Information inserted successfully");
		else
			logger.error("Error was encountered while trying to save Request for Information");
		
		saveOrderNotify(rfi, rfi.getProductInterest(), null, (savedOrder != null)?savedOrder.getId():null);
		if (savedOrder != null) {
			return savedOrder.getId();
		} else {
			return null;
		}
	}

	@Autowired
	CollaborateOrderRepository collaborateOrderRepository;

	@Override
	public String saveCustomerOrder(CollaborateOrder order, String emailContent) {
		logger.info("In saveCustomerOrder method");
		order.setId(null); //make sure id is not set, value will be retrieved from db
		logger.info("collaborate order: "+order);
		CollaborateOrder savedOrder = collaborateOrderRepository.save(order);
		if (savedOrder != null) 
			logger.info("Collaborate order inserted successfully");
		else
			logger.info("#######>>>>>>>>>>>Error was encountered while trying to save Collaborate order");
		
		saveOrderNotify(order, COLLABORATE, emailContent, (savedOrder != null)?savedOrder.getId():null);
		if (savedOrder != null) {
			return savedOrder.getId();
		} else {
			return null;
		}
	}

	@Autowired
	OrderNotifyRepository orderNotifyRepository;
	
	@Override
	public boolean saveOrderNotify(Object data, String dataType, String emailContent, String referenceID) {
		logger.info("In saveOrderNotify method");
		logger.info("data: "+data);
		
		OrderNotify notify = new OrderNotify();
		Date now = new Date();
		notify.setCreateDate(now);
		notify.setLastModifiedDate(now);
		notify.setData(data);
		notify.setOrderType(dataType);
		notify.setHtmlText(emailContent);
		notify.setReferenceOrderID(referenceID);
		
		OrderNotify savedOrder = orderNotifyRepository.save(notify);
		if (savedOrder != null) { 
			logger.info("Order Notification inserted successfully");
			return true;
		}
		else {
			logger.info("#######>>>>>>>>>>>Error was encountered while trying to save order notification");
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param daysToInclude - if 0 or negative number is passed, return all records; otherwise only return records within the last X days
	 * @return
	 */
	public List<OrderNotify> retrieveOrders(int daysToInclude, String product) {
		List<OrderNotify> onList = null;
		if (daysToInclude < 1) {
			onList = orderNotifyRepository.findAll();
		} else {
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, 0-daysToInclude);
			Date lastMonth = cal.getTime();
			
			onList = orderNotifyRepository.findByCreateDateAfter(lastMonth);
		}
		
	
	
		if (product == null || product.equalsIgnoreCase("all")) {
			return onList;
		}
		
		List<OrderNotify> productFilter = new ArrayList<OrderNotify>();
		for (OrderNotify on: onList) {
			if (on.getOrderType().equalsIgnoreCase(product)) {
				productFilter.add(on);
			}
		}
		
		return productFilter;
	}
	
	@Autowired
	DTVRFIRepository dtvRFIRepository;

	@Override
	public String saveDTVRequestForInformation(BusinessCustomer rfi, String emailContent) {
		rfi.setTimeStamp(Instant.now().toString());
		BusinessCustomer savedCustomer = dtvRFIRepository.save(rfi);
		
		saveOrderNotify(rfi, DIRECTV, emailContent, (savedCustomer != null)?savedCustomer.getId():null);
		
		return (null != savedCustomer ? savedCustomer.getId() : null);
	}

	@Autowired
	DTVOrderRepository dtvOrderRepository;
	
	@Override
	public String saveDTVCustomerOrder(DirectvOrder order, String emailContent) {
		order.setTimeStamp(Instant.now().toString());
		DirectvOrder savedOrder = dtvOrderRepository.save(order);
		
		saveOrderNotify(order, DIRECTV, emailContent, (savedOrder != null)?savedOrder.getId():null);
		
		
		return (null != savedOrder ? savedOrder.getId() : null);
	}
	
	@Autowired
	SecurityOrderRepository securityOrderRepository;
	
	@Override
	public String saveSecurityOrder(SecurityOrder order) {
		order.setId(null); //make sure id is not set, value will be retrieved from db
		order.setTimeStamp(Instant.now().toString());
		logger.info("Security order: "+order);
		
		SecurityOrder savedOrder = securityOrderRepository.save(order);		
		if (savedOrder != null) {
			logger.info("Security order inserted successfully");
			return savedOrder.getId();
		} else {
			logger.info("#######>>>>>>>>>>>Error was encountered while trying to save Security order");
			return null;
		}
	}
	
}

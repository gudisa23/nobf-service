package com.att.api.nobf.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection ="collaborateorder")
public class CollaborateOrder {

	@Id
	private String id;
	
	private OrderContact customerContact;
	private OrderContact secondaryCustomerContact;
	private String businessName;
	private OrderAddress serviceLocation;
	private OrderContact technicalContact;
	private OrderContact siteContact;
	private OrderAddress billingAddress;
	private int numberOfUsers;
	private Date orderCreateDate;
	private OrderContact shippingContact;
	
	private List<Equipment> equipmentList;
	
	private boolean portNumbers;
	private boolean eligiblePort;
	private String billingNumber;
	private boolean portBillingNumber;
	private boolean portFullAccount;
	private boolean computerOrMobileOnly;
	private boolean useExistingDevice;
	private boolean buyATTDevice;
	
	private int existingDevices;
	
	private List<String> numbersToPort;
	
	public CollaborateOrder() {
		orderCreateDate = new Date();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public OrderContact getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(OrderContact customerContact) {
		this.customerContact = customerContact;
	}

	public OrderAddress getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(OrderAddress serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public OrderContact getTechnicalContact() {
		return technicalContact;
	}

	public void setTechnicalContact(OrderContact technicalContact) {
		this.technicalContact = technicalContact;
	}

	public OrderContact getSiteContact() {
		return siteContact;
	}

	public void setSiteContact(OrderContact siteContact) {
		this.siteContact = siteContact;
	}

	public OrderAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(OrderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public boolean isPortFullAccount() {
		return portFullAccount;
	}

	public void setPortFullAccount(boolean portFullAccount) {
		this.portFullAccount = portFullAccount;
	}

	public List<String> getNumbersToPort() {
		return numbersToPort;
	}

	public void setNumbersToPort(List<String> numbersToPort) {
		this.numbersToPort = numbersToPort;
	}

	public OrderContact getSecondaryCustomerContact() {
		return secondaryCustomerContact;
	}

	public void setSecondaryCustomerContact(OrderContact secondaryCustomerContact) {
		this.secondaryCustomerContact = secondaryCustomerContact;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}


	public OrderContact getShippingContact() {
		return shippingContact;
	}


	public void setShippingContact(OrderContact shippingContact) {
		this.shippingContact = shippingContact;
	}


	public int getExistingDevices() {
		return existingDevices;
	}


	public void setExistingDevices(int existingDevices) {
		this.existingDevices = existingDevices;
	}


	public boolean isPortNumbers() {
		return portNumbers;
	}


	public void setPortNumbers(boolean portNumbers) {
		this.portNumbers = portNumbers;
	}


	public boolean isEligiblePort() {
		return eligiblePort;
	}


	public void setEligiblePort(boolean eligiblePort) {
		this.eligiblePort = eligiblePort;
	}


	public String getBillingNumber() {
		return billingNumber;
	}


	public void setBillingNumber(String billingNumber) {
		this.billingNumber = billingNumber;
	}


	public boolean isPortBillingNumber() {
		return portBillingNumber;
	}


	public void setPortBillingNumber(boolean portBillingNumber) {
		this.portBillingNumber = portBillingNumber;
	}


	public boolean isComputerOrMobileOnly() {
		return computerOrMobileOnly;
	}


	public void setComputerOrMobileOnly(boolean computerOrMobileOnly) {
		this.computerOrMobileOnly = computerOrMobileOnly;
	}


	public boolean isUseExistingDevice() {
		return useExistingDevice;
	}


	public void setUseExistingDevice(boolean useExistingDevice) {
		this.useExistingDevice = useExistingDevice;
	}


	public boolean isBuyATTDevice() {
		return buyATTDevice;
	}


	public void setBuyATTDevice(boolean buyATTDevice) {
		this.buyATTDevice = buyATTDevice;
	}

	
}

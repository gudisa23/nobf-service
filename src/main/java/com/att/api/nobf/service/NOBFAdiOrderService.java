package com.att.api.nobf.service;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.api.nobf.dto.AdditionalInfoDTO;
import com.att.api.nobf.dto.FalloutCallbackInfoDTO;
import com.att.api.nobf.dto.OrderDTO;
import com.att.api.nobf.exception.OrderNotFoundException;
import com.att.api.nobf.model.ADIOrder;
import com.att.api.nobf.model.AdditionalInfo;
import com.att.api.nobf.model.Address;
import com.att.api.nobf.model.Contact;
import com.att.api.nobf.model.Customer;
import com.att.api.nobf.model.FalloutCallbackInfo;
import com.att.api.nobf.model.InstallationType;
import com.att.api.nobf.model.ScheduleInstallation;
import com.att.api.nobf.repository.AdditionalInfoRepository;
import com.att.api.nobf.repository.FalloutCallbackInfoRepository;
import com.att.api.nobf.repository.AdiOrderRepository;
import com.att.api.nobf.utility.EmailConstants;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.IEmailService;
import com.bcg.api.common.util.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NOBFAdiOrderService extends BaseService implements INOBFAdiOrderService {

		@Autowired
		private AdiOrderRepository orderRepository;
	    
		@Autowired
	    private IEmailService emailService;
		
		@Autowired
		private AdditionalInfoRepository additionalInfoRepository;
		
		@Autowired
		private FalloutCallbackInfoRepository falloutCallbackInfoRepository;

	    @Override
	    public OrderDTO create(OrderDTO orderDTO) {
	    	if (orderDTO == null) throw new IllegalArgumentException("ADI Order DTO cannot be null!");
	    	
	        log.info("Creating a new orderDTO entry with information: {}", orderDTO);
	        
	        ADIOrder orderToPersist = mapToOrder(orderDTO);
	    	
	    	orderToPersist = orderRepository.save(orderToPersist);
	        log.info("Created a new orderDTO entry with information: {}", orderToPersist);

	        sendEmailNotification(orderToPersist);
	        return mapToOrderDTO(orderToPersist);
	    }

	    @Override
	    public AdditionalInfoDTO createAdditionalInfo(AdditionalInfoDTO additionalInfoDTO) {
	    	if (additionalInfoDTO == null) throw new IllegalArgumentException("ADI AdditionalInfoDTO cannot be null!");
	    	
	        log.info("Creating a new AdditionalInfoDTO entry with information: {}", additionalInfoDTO);
	        
	        AdditionalInfo additionalInfoToPersist = mapToAdditionalInfo(additionalInfoDTO);
	    	
	        additionalInfoToPersist = additionalInfoRepository.save(additionalInfoToPersist);
	        log.info("Created a new orderDTO entry with information: {}", additionalInfoToPersist);

	        sendEmailNotificationAdditionalInfo(additionalInfoToPersist);
	        return mapToAdditionalInfoDTO(additionalInfoToPersist);
	    }
	    @Override
	    public FalloutCallbackInfoDTO createFalloutCallbackInfo(FalloutCallbackInfoDTO falloutCallbackInfoDTO) {
	    	if (falloutCallbackInfoDTO == null) throw new IllegalArgumentException("ADI FalloutCallbackInfoDTO cannot be null!");
	    	
	        log.info("Creating a new FalloutCallbackInfoDTO entry with information: {}", falloutCallbackInfoDTO);
	        
	        FalloutCallbackInfo falloutCallbackInfoToPersist = mapToFalloutCallbackInfo(falloutCallbackInfoDTO);
	    	
	        falloutCallbackInfoToPersist = falloutCallbackInfoRepository.save(falloutCallbackInfoToPersist);
	        log.info("Created a new orderDTO entry with information: {}", falloutCallbackInfoToPersist);

	        sendEmailNotificationFalloutCallbackInfo(falloutCallbackInfoToPersist);
	        return mapToFalloutCallbackInfoDTO(falloutCallbackInfoToPersist);
	    }
	    private void sendEmailNotification(ADIOrder order) {
	    	Optional.of(order).orElseThrow(() -> new IllegalArgumentException("ADI order cannot be null!"));
	    	
	    	Map<String, String> templateModelMap = buildTemplateModelMap(order);
	    	
	    	String[] toEmailAddresses = apiConfig.getAdiEmailNotifyTo().split(",");
	    	EmailDTO emailToSend = new EmailDTO.Builder()
								    			.to(toEmailAddresses[0])
								    			.subject(EmailConstants.NEW_ADI_ORDER +order.getId()+" - "+ order.getCustomer().getRegisteredBusinessName())
								    			.from(apiConfig.getAdiEmailNotifyFrom())
								    			.withTemplate(true)
								    			.templateFileName(EmailConstants.ADI_ORDER_TEMPLATE_FILE_NAME)
								    			.templateModelMap(templateModelMap)
								    			.build();
	    	
	    	emailService.prepareAndSendEmail(emailToSend);
	    }

	    private Map<String, String> buildTemplateModelMap(@NotNull ADIOrder order) {
	 		if (order == null) throw new IllegalArgumentException("ADI order cannot be null!");
	 		
	 		Map<String, String> templateModelMap = new HashMap<String, String>();
	 		
	 		templateModelMap.put("orderID", order.getId());
			templateModelMap.put("orderType", order.getOrderType().toString());
			templateModelMap.put("orderDate", getAdiFormattedDate(order.getCreatedDate()));
			
			populateServiceDetails(order, templateModelMap);
			populateCustomerDetails(order, templateModelMap);
			populateScheduleInstallation(order, templateModelMap);
			
			return templateModelMap;
	    }

		private void populateServiceDetails(ADIOrder order, Map<String, String> templateModelMap) {
			// Populate primary service
			com.att.api.nobf.model.Service primaryService = order.getServices().get(0);
			templateModelMap.put("speed", primaryService.getSpeed());
			templateModelMap.put("interfaceName", primaryService.getInterfaceName());
			templateModelMap.put("installationType", primaryService.getInstallationType().toString());
			
			
			// Populate additional services
			int index = 0;
			String newLine = System.lineSeparator();
			StringBuffer additionalServices = new StringBuffer(100);
			for (com.att.api.nobf.model.Service eachAdditionalService : order.getServices()) {
				if (index == 0) { index++; continue; } // ignore the primary already taken care of
				if (index > 1) additionalServices.append("    "); // no need to add spaces for the first service name as the template already has it
				additionalServices.append("Name: ").append(eachAdditionalService.getName()).append(newLine);
				String speedOfEachAdditionalService = eachAdditionalService.getSpeed();
				String numOfUsersOfEachAdditionalService = eachAdditionalService.getNumberOfUsers();
				String installationTypeOfEachAdditionalService = eachAdditionalService.getInstallationType().toString();
				if (speedOfEachAdditionalService != null) additionalServices.append("    ").append("Speed: ").append(speedOfEachAdditionalService).append(newLine);
				if (numOfUsersOfEachAdditionalService != null) additionalServices.append("    ").append("Number of Seats/Users: ").append(numOfUsersOfEachAdditionalService).append(newLine);
				if (installationTypeOfEachAdditionalService != null) additionalServices.append("    ").append("Installation: ").append(installationTypeOfEachAdditionalService).append(newLine);
				index++;
			}
			templateModelMap.put("additionalServices", additionalServices.toString());
		}

		private void populateCustomerDetails(ADIOrder order, Map<String, String> templateModelMap) {
			Optional<Customer> customer = Optional.ofNullable(order.getCustomer());
			customer.ifPresent(cust -> {
				templateModelMap.put("businessName", cust.getRegisteredBusinessName());
				templateModelMap.put("otherBusinessName", cust.getOtherBusinessName());
				populateInstallAddress(templateModelMap, cust);
				populatePrimaryContact(templateModelMap, cust);
				populateShippingAddress(templateModelMap, cust);
				populateBillingAddress(templateModelMap, cust);
				});
		}

		private void populateBillingAddress(Map<String, String> templateModelMap, Customer cust) {
			Optional<Address> billingAddress = Optional.ofNullable(cust.getBillingAddress());
			billingAddress.ifPresent(billAddr -> {
				templateModelMap.put("billingAddress1", billAddr.getAddressLine1());
				templateModelMap.put("billingAddress2", billAddr.getAddressLine2());
				templateModelMap.put("billingCity", billAddr.getCity());
				templateModelMap.put("billingState", billAddr.getState());
				templateModelMap.put("billingZip", billAddr.getZip());
			});
		}

		private void populateShippingAddress(Map<String, String> templateModelMap, Customer cust) {
			Optional<Address> shippingAddress = Optional.ofNullable(cust.getShippingAddress());
			shippingAddress.ifPresent(shipAddr -> {
				templateModelMap.put("shippingAddress1", shipAddr.getAddressLine1());
				templateModelMap.put("shippingAddress2", shipAddr.getAddressLine2());
				templateModelMap.put("shippingCity", shipAddr.getCity());
				templateModelMap.put("shippingState", shipAddr.getState());
				templateModelMap.put("shippingZip", shipAddr.getZip());
			});
		}

		private void populatePrimaryContact(Map<String, String> templateModelMap, Customer cust) {
			Optional<Contact> primaryContact = Optional.ofNullable(cust.getPrimaryContact());
			primaryContact.ifPresent(primContact -> {
				templateModelMap.put("customerName", primContact.getFirstName() + " " + primContact.getLastName());
				templateModelMap.put("secondaryContactName", primContact.getSecondaryContactName());
				templateModelMap.put("primaryNumber", primContact.getPrimaryNumber());
				templateModelMap.put("secondaryNumber", primContact.getSecondaryNumber());
				templateModelMap.put("email", primContact.getEmailAddress());
				templateModelMap.put("secondaryEmailAddress", primContact.getSecondaryEmailAddress());
			});
		}

		private void populateInstallAddress(Map<String, String> templateModelMap, Customer cust) {
			Optional<Address> installAddress =  Optional.ofNullable(cust.getInstallAddress());
			installAddress.ifPresent(installAddr -> {
				templateModelMap.put("installAddress1", installAddr.getAddressLine1());
				templateModelMap.put("installAddress2", installAddr.getAddressLine2());
				templateModelMap.put("installCity", installAddr.getCity());
				templateModelMap.put("installState", installAddr.getState());
				templateModelMap.put("installZip", installAddr.getZip());
			});
		}
		
		private void populateScheduleInstallation(ADIOrder order, Map<String, String> templateModelMap) {
			Optional<ScheduleInstallation> scheduleInstallation =  Optional.ofNullable(order.getScheduleInstallation());
			scheduleInstallation.ifPresent(scheduleInstall -> {
				templateModelMap.put("installationDate", getAdiFormattedDate(scheduleInstall.getInstallationDate()));
				templateModelMap.put("arrivalTimeFrame", scheduleInstall.getArrivalTimeFrame());
				templateModelMap.put("installInfo", scheduleInstall.getInstallInfo());
			});	
		}
	

		private String getAdiFormattedDate(DateTime givenDate) {
			return DateUtils.getUTCFormattedDate(givenDate, "MM/dd/yyyy HH:mm").replaceAll(" ", " at ") + " UTC";
		}
		
		private String getAdiFormattedDate(Date givenDate) {
			return DateUtils.getUTCFormattedDate(new DateTime(givenDate), "MM/dd/yyyy").replaceAll(" ", " at ") + " UTC";
		}
	    
	    @Override
	    public OrderDTO delete(String id) {
	        log.info("Deleting a orderDTO entry with id: {}", id);

	        ADIOrder deleted = findOrderById(id);
	        orderRepository.delete(deleted);

	        log.info("Deleted orderDTO entry with informtation: {}", deleted);

	        return mapToOrderDTO(deleted);
	    }

	    @Override
	    public List<OrderDTO> findAll() {
	        log.info("Finding all orderDTO entries.");

	        List<ADIOrder> ordersInDB = orderRepository.findAll();

	        log.info("Found {} orderDTO entries", ordersInDB.size());

	        return convertToDTOs(ordersInDB);
	    }

	    private List<OrderDTO> convertToDTOs(List<ADIOrder> models) {
	        return models.stream()
	                .map(this::mapToOrderDTO)
	                .collect(toList());
	    }

	    @Override
	    public OrderDTO findById(String id) {
	        log.info("Finding orderDTO entry with id: {}", id);

	        ADIOrder found = findOrderById(id);

	        log.info("Found orderDTO entry: {}", found);

	        return mapToOrderDTO(found);
	    }

	    @Override
	    public OrderDTO update(OrderDTO orderDTO) {
	        log.info("Updating orderDTO entry with information: {}", orderDTO);

	        ADIOrder orderToBePersisted = findOrderById(orderDTO.getId());
	        
	        ModelMapper mapper = new ModelMapper(); 
	    	mapper.map(orderDTO, orderToBePersisted);
	    	mapper.validate();
	    	
	    	orderToBePersisted = orderRepository.save(orderToBePersisted);

	        log.info("Updated orderDTO entry with information: {}", orderToBePersisted);

	        return mapToOrderDTO(orderToBePersisted);
	    }

	    private ADIOrder findOrderById(String id) {
	        Optional<ADIOrder> result = orderRepository.findById(id);
	        return result.orElseThrow(() -> new OrderNotFoundException(id));

	    }

		private ADIOrder mapToOrder(OrderDTO orderDTO) {
			ModelMapper mapper = new ModelMapper();
	        TypeMap<OrderDTO, ADIOrder> typeMap = mapper.createTypeMap(OrderDTO.class, ADIOrder.class);
	        typeMap.addMappings(mymap -> mymap.skip(ADIOrder::setId));
	    	// No need to setCreationDate - will be taken care of by @EnableMongoAuditing in Api.java
	    	// orderToPersist.setCreationDate(new DateTime());
	        typeMap.addMappings(mymap -> mymap.skip(ADIOrder::setCreatedDate));
	        typeMap.addMappings(mymap -> mymap.skip(ADIOrder::setCreatedBy));
	        typeMap.addMappings(mymap -> mymap.skip(ADIOrder::setLastModifiedBy));
	        typeMap.addMappings(mymap -> mymap.skip(ADIOrder::setLastModifiedDate));
	        typeMap.validate();
	    	ADIOrder orderToPersist = typeMap.map(orderDTO);
			return orderToPersist;
		}

	    private OrderDTO mapToOrderDTO(ADIOrder orderModel) {
	    	ModelMapper mapper = new ModelMapper();
	    	OrderDTO orderDTO = mapper.map(orderModel, OrderDTO.class);
	    	mapper.validate();
	        return orderDTO;
	    }
	    
  
	    private void sendEmailNotificationAdditionalInfo(AdditionalInfo additionalInfo) {
	    	Optional.of(additionalInfo).orElseThrow(() -> new IllegalArgumentException("ADI order cannot be null!"));
	    	
	    	Map<String, String> templateModelMap = buildTemplateAdditionalInfoModelMap(additionalInfo);
	    	
	    	String[] toEmailAddresses = apiConfig.getAdiEmailNotifyTo().split(",");
	    	EmailDTO emailToSend = new EmailDTO.Builder()
								    			.to(toEmailAddresses[0])
								    			.subject(EmailConstants.New_ADI_REQUEST_ADDITIONALINFO + additionalInfo.getCompanyName())
								    			.from(apiConfig.getAdiEmailNotifyFrom())
								    			.withTemplate(true)
								    			.templateFileName(EmailConstants.ADI_ORDER_ADDITIONALINFO_TEMPLATE_FILE_NAME)
								    			.templateModelMap(templateModelMap)
								    			.build();
	    	
	    	emailService.prepareAndSendEmail(emailToSend);
	    }
	    
	    private Map<String, String> buildTemplateAdditionalInfoModelMap(@NotNull AdditionalInfo additionalInfo) {
	 		if (additionalInfo == null) throw new IllegalArgumentException("ADI order cannot be null!");
	 		
	 		Map<String, String> templateModelMap = new HashMap<String, String>();
	 		templateModelMap.put("orderDate", getAdiFormattedDate(additionalInfo.getCreatedDate()));
	 		templateModelMap.put("companyName", additionalInfo.getCompanyName());
			templateModelMap.put("firstName", additionalInfo.getFirstName());
	 		templateModelMap.put("lastName", additionalInfo.getLastName());
			templateModelMap.put("email", additionalInfo.getEmail());
	 		templateModelMap.put("phoneNumber", additionalInfo.getPhoneNumber());
			templateModelMap.put("state",additionalInfo.getState());
			return templateModelMap;
	    }
	    
		private AdditionalInfo mapToAdditionalInfo(AdditionalInfoDTO additionalInfoDTO) {
			ModelMapper mapper = new ModelMapper();
	        TypeMap<AdditionalInfoDTO, AdditionalInfo> typeMap = mapper.createTypeMap(AdditionalInfoDTO.class, AdditionalInfo.class);
	        typeMap.addMappings(mymap -> mymap.skip(AdditionalInfo::setId));
	    	// No need to setCreationDate - will be taken care of by @EnableMongoAuditing in Api.java
	    	// orderToPersist.setCreationDate(new DateTime());
	        typeMap.addMappings(mymap -> mymap.skip(AdditionalInfo::setCreatedDate));
	        typeMap.addMappings(mymap -> mymap.skip(AdditionalInfo::setCreatedBy));
	        typeMap.addMappings(mymap -> mymap.skip(AdditionalInfo::setLastModifiedBy));
	        typeMap.addMappings(mymap -> mymap.skip(AdditionalInfo::setLastModifiedDate));
	        typeMap.validate();
	        AdditionalInfo additionalInfoToPersist = typeMap.map(additionalInfoDTO);
			return additionalInfoToPersist;
		}

	    private AdditionalInfoDTO mapToAdditionalInfoDTO(AdditionalInfo orderModel) {
	    	ModelMapper mapper = new ModelMapper();
	    	AdditionalInfoDTO additionalInfoDTO = mapper.map(orderModel, AdditionalInfoDTO.class);
	    	mapper.validate();
	        return additionalInfoDTO;
	    }
	    
	    private void sendEmailNotificationFalloutCallbackInfo(FalloutCallbackInfo falloutCallbackInfo) {
	    	Optional.of(falloutCallbackInfo).orElseThrow(() -> new IllegalArgumentException("ADI order cannot be null!"));
	    	
	    	Map<String, String> templateModelMap = buildTemplateFalloutCallbackInfoModelMap(falloutCallbackInfo);
	    	
	    	String[] toEmailAddresses = apiConfig.getAdiEmailNotifyTo().split(",");
	    	EmailDTO emailToSend = new EmailDTO.Builder()
								    			.to(toEmailAddresses[0])
								    			.subject(EmailConstants.NEW_ADI_REQUEST_FALLOUTCALLBACKINFO + falloutCallbackInfo.getCompanyName())
								    			.from(apiConfig.getAdiEmailNotifyFrom())
								    			.withTemplate(true)
								    			.templateFileName(EmailConstants.ADI_ORDER_FALLOUTCALLBACKINFO_TEMPLATE_FILE_NAME)
								    			.templateModelMap(templateModelMap)
								    			.build();
	    	
	    	emailService.prepareAndSendEmail(emailToSend);
	    }
	    
	    private Map<String, String> buildTemplateFalloutCallbackInfoModelMap(@NotNull FalloutCallbackInfo falloutCallbackInfo) {
	 		if (falloutCallbackInfo == null) throw new IllegalArgumentException("ADI order cannot be null!");
	 		
	 		Map<String, String> templateModelMap = new HashMap<String, String>();
	 		templateModelMap.put("orderDate", getAdiFormattedDate(falloutCallbackInfo.getDateToCall()));
			templateModelMap.put("firstName", falloutCallbackInfo.getFirstName());
	 		templateModelMap.put("lastName", falloutCallbackInfo.getLastName());
			templateModelMap.put("phoneNumber", falloutCallbackInfo.getPhoneNumber());
	 		templateModelMap.put("altPhoneNumber", falloutCallbackInfo.getAltPhoneNumber());
	 		templateModelMap.put("email", falloutCallbackInfo.getEmail());
	 		templateModelMap.put("companyName", falloutCallbackInfo.getCompanyName());
	 		templateModelMap.put("businessPhoneNumber", falloutCallbackInfo.getBusinessPhoneNumber());
	 		templateModelMap.put("address1", falloutCallbackInfo.getBusinessAddress().getAddressLine1());
			templateModelMap.put("address2", falloutCallbackInfo.getBusinessAddress().getAddressLine2());
			templateModelMap.put("city", falloutCallbackInfo.getBusinessAddress().getCity());
			templateModelMap.put("state", falloutCallbackInfo.getBusinessAddress().getState());
			templateModelMap.put("zip", falloutCallbackInfo.getBusinessAddress().getZip());
			templateModelMap.put("suiteNumber", falloutCallbackInfo.getSuiteNumber());
	 		templateModelMap.put("dateToCall", getAdiFormattedDate(falloutCallbackInfo.getDateToCall()).toString());
	 		
	 		
			//templateModelMap.put("state",falloutCallbackInfo.getState());
			return templateModelMap;
	    }
		private FalloutCallbackInfo mapToFalloutCallbackInfo(FalloutCallbackInfoDTO falloutCallbackInfoDTO) {
			ModelMapper mapper = new ModelMapper();
	        TypeMap<FalloutCallbackInfoDTO, FalloutCallbackInfo> typeMap = mapper.createTypeMap(FalloutCallbackInfoDTO.class, FalloutCallbackInfo.class);
	        typeMap.addMappings(mymap -> mymap.skip(FalloutCallbackInfo::setId));
	    	// No need to setCreationDate - will be taken care of by @EnableMongoAuditing in Api.java
	    	// orderToPersist.setCreationDate(new DateTime());
	        typeMap.addMappings(mymap -> mymap.skip(FalloutCallbackInfo::setCreatedDate));
	        typeMap.addMappings(mymap -> mymap.skip(FalloutCallbackInfo::setCreatedBy));
	        typeMap.addMappings(mymap -> mymap.skip(FalloutCallbackInfo::setLastModifiedBy));
	        typeMap.addMappings(mymap -> mymap.skip(FalloutCallbackInfo::setLastModifiedDate));
	        typeMap.validate();
	        FalloutCallbackInfo falloutCallbackInfoToPersist = typeMap.map(falloutCallbackInfoDTO);
			return falloutCallbackInfoToPersist;
		}

	    private FalloutCallbackInfoDTO mapToFalloutCallbackInfoDTO(FalloutCallbackInfo falloutCallbackInfo) {
	    	ModelMapper mapper = new ModelMapper();
	    	FalloutCallbackInfoDTO falloutCallbackInfoDTO = mapper.map(falloutCallbackInfo, FalloutCallbackInfoDTO.class);
	    	mapper.validate();
	        return falloutCallbackInfoDTO;
	    }
}

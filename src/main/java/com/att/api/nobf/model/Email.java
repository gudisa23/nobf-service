package com.att.api.nobf.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "email")
public class Email {
	
	  	@Id private String id;
	  
	  	private String to;
	  	private String from;
	  	private String subject;
	  	private String body; 
	  	private boolean isHtml;
	  	private boolean withAttachment;
	  	private String attachmentFileName;
	  	private boolean withTemplate; // body is empty if hasTemplate is true
	  	private String templateFileName;
	  	private Map<String, String> templateModelMap;
	  	private String deliveryStatus;
	  	private String failureDeliveryMessage;
}

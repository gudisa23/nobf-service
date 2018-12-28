package com.att.api.nobf.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.att.api.nobf.model.Email;
import com.att.api.nobf.repository.EmailRepository;
import com.bcg.api.common.dto.EmailDTO;
import com.bcg.api.common.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NOBFEmailService extends EmailService {

	private final EmailRepository emailRepository;
	
	@Autowired
	public NOBFEmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine, EmailRepository emailRepository) {
		super(mailSender, templateEngine);
		this.emailRepository = emailRepository;
	}

	@Override
	public void auditSuccessEmail(EmailDTO email) {
		saveEmailStatus(email, true);
	}

	@Override
	public void auditFailureEmail(EmailDTO email) {
		saveEmailStatus(email, false);
	}
	
	private EmailDTO saveEmailStatus(EmailDTO emailDTO, boolean isSuccess) {
		log.info("Creating a new emailDTO entry with information: {}", emailDTO);
        ModelMapper mapper = new ModelMapper();
        TypeMap<EmailDTO, Email> typeMap = mapper.createTypeMap(EmailDTO.class, Email.class);
        typeMap.addMappings(mymap -> mymap.skip(Email::setId));
        typeMap.validate();
    	Email emailToPersist = typeMap.map(emailDTO);
    	
    	if (isSuccess) emailToPersist.setDeliveryStatus("SUCCESS");
    	else emailToPersist.setDeliveryStatus("FAILURE");
    	
    	emailToPersist = emailRepository.save(emailToPersist);
        log.info("Created a new emailDTO entry with information: {}", emailToPersist);

        return convertToDTO(emailToPersist);
	}
	
	private EmailDTO convertToDTO(Email emailModel) {
    	ModelMapper mapper = new ModelMapper();
    	EmailDTO emailDTO = mapper.map(emailModel, EmailDTO.class);
    	mapper.validate();
        return emailDTO;
    }

}

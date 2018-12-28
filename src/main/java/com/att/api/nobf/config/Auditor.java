package com.att.api.nobf.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Auditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("NOBF-Service");
        //TODO: move to common and use Spring Security to return currently logged in user
		/*
		Optional<Object> value = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String userName = null;
		if(value.isPresent()){
			userName= ((User) value.get()).getUsername();
		}else{
			userName = "Service";
		}
		return Optional.of(userName);
		*/
    }
}

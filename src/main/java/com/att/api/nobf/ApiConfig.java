package com.att.api.nobf;

import com.bcg.api.common.config.CommonServiceConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ApiConfig extends CommonServiceConfig {

    @Value(value = "${spring.application.name}")
    private String appName;

    @Value(value = "${info.app.description}")
    private String appDescription;

    @Value(value = "${info.app.version}")
    private String appVersion;

    @Value(value = "${order.adi.email.notify.to:SalesExpCSR@att.com}")
    private String adiEmailNotifyTo;

    @Value(value = "${order.adi.email.notify.from:NOBF_ADI}")
    private String adiEmailNotifyFrom;

    @Value(value = "${order.collaborate.email.notify.to:SalesExpCSR@att.com}")
    private String collaborateEmailNotifyTo;

    @Value(value = "${order.collaborate.email.notify.from:NOBF_ADI}")
    private String collaborateEmailNotifyFrom;
    
    @Value(value = "${order.directv.email.notify.from:quickstrikebcg@gmail.com}")
    private String directvEmailNotifyFrom;
    
    @Value(value = "${order.directv.email.notify.to:quickstrikebcg@gmail.com}")
    private String directvEmailNotifyTo;
    
    @Value(value = "${order.security.email.notify.from:quickstrikebcg@gmail.com}")
    private String securityEmailNotifyFrom;
    
    @Value(value = "${order.security.email.notify.to:quickstrikebcg@gmail.com}")
    private String securityEmailNotifyTo;


    public ApiConfig() {
    }

    public String getAppName() {
        return appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public String getAppVersion() {
        return appVersion;
    }

	public String getAdiEmailNotifyTo() {
		return adiEmailNotifyTo;
	}

	public void setAdiEmailNotifyTo(String adiEmailNotifyTo) {
		this.adiEmailNotifyTo = adiEmailNotifyTo;
	}

	public String getAdiEmailNotifyFrom() {
		return adiEmailNotifyFrom;
	}

	public void setAdiEmailNotifyFrom(String adiEmailNotifyFrom) {
		this.adiEmailNotifyFrom = adiEmailNotifyFrom;
	}

	public String getCollaborateEmailNotifyTo() {
		return collaborateEmailNotifyTo;
	}

	public void setCollaborateEmailNotifyTo(String collaborateEmailNotifyTo) {
		this.collaborateEmailNotifyTo = collaborateEmailNotifyTo;
	}

	public String getCollaborateEmailNotifyFrom() {
		return collaborateEmailNotifyFrom;
	}

	public void setCollaborateEmailNotifyFrom(String collaborateEmailNotifyFrom) {
		this.collaborateEmailNotifyFrom = collaborateEmailNotifyFrom;
	}

	public String getDirectvEmailNotifyFrom() {
		return directvEmailNotifyFrom;
	}

	public void setDirectvEmailNotifyFrom(String directvEmailNotifyFrom) {
		this.directvEmailNotifyFrom = directvEmailNotifyFrom;
	}

	public String getDirectvEmailNotifyTo() {
		return directvEmailNotifyTo;
	}

	public void setDirectvEmailNotifyTo(String directvEmailNotifyTo) {
		this.directvEmailNotifyTo = directvEmailNotifyTo;
	}

	public String getSecurityEmailNotifyFrom() {
		return securityEmailNotifyFrom;
	}

	public void setSecurityEmailNotifyFrom(String securityEmailNotifyFrom) {
		this.securityEmailNotifyFrom = securityEmailNotifyFrom;
	}

	public String getSecurityEmailNotifyTo() {
		return securityEmailNotifyTo;
	}

	public void setSecurityEmailNotifyTo(String securityEmailNotifyTo) {
		this.securityEmailNotifyTo = securityEmailNotifyTo;
	}
	
	
	
}

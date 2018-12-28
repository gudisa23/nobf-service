package com.att.api.nobf.service;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.api.nobf.ApiConfig;
import com.att.api.nobf.dto.ResponseHeader;

import javax.annotation.PostConstruct;

@Service
public abstract class BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected ApiConfig apiConfig;

    protected ResponseHeader responseHeader() {
        return new ResponseHeader(apiConfig.getAppName(), apiConfig.getAppVersion(), DateTime.now().toString());
    }

    @PostConstruct
    protected void displayInitMessage() {
        logger.info(String.format("\r**********\rService %s (%s) initialized\r**********", apiConfig.getAppName(), apiConfig.getAppVersion()));
    }
}

package com.att.api.nobf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.api.nobf.ApiConfig;
import com.att.api.nobf.dto.PingResponse;
import com.att.api.nobf.mapper.IMyEntityMapper;

@Service
public class MyService extends BaseService implements IMyService {
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);

    @Autowired
    private ApiConfig config;

    @Autowired
    private IMyEntityMapper sessionMapper;

    /**
     * Default constructor
     */
    public MyService() {
        MyService.logger.debug("Initializing service ...");
    }

    /**
     * Returns information about the service
     * @return Service information
     */
    public PingResponse ping() {
        return new PingResponse(responseHeader(), "Greetings!");
    }

}

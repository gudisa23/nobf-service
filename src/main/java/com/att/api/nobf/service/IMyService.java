package com.att.api.nobf.service;

import com.att.api.nobf.dto.PingResponse;

public interface IMyService {

    /**
     * Returns information about the service
     * @return Service information
     */
    PingResponse ping();
}

/**
 * Copyright (2012-2014) TMX Finance, Inc.
 */
package com.att.api.nobf.dto;

import org.joda.time.DateTime;

/**
 * @author marc.archin
 *
 */
public abstract class BaseResponse {

    protected String service;
    protected String version;
    protected final String responseDate = DateTime.now().toString();

    public BaseResponse() {
        // Jackson requires a default constructor
    }

    public BaseResponse(final ResponseHeader header) {
        service = header.getService();
        version = header.getVersion();
    }

    public String getService() {
        return service;
    }

    public String getVersion() {
        return version;
    }

    public String getResponseDate() {
        return responseDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((responseDate == null) ? 0 : responseDate.hashCode());
        result = (prime * result) + ((service == null) ? 0 : service.hashCode());
        result = (prime * result) + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final BaseResponse other = (BaseResponse) obj;
        if (responseDate == null) {
            if (other.responseDate != null) {
                return false;
            }
        } else if (!responseDate.equals(other.responseDate)) {
            return false;
        }
        if (service == null) {
            if (other.service != null) {
                return false;
            }
        } else if (!service.equals(other.service)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BaseResponse [service=" + service + ", version=" + version + ", responseDate=" + responseDate + "]";
    }

}

/**
 * Copyright (2012-2014) TMX Finance, Inc.
 */
package com.att.api.nobf.dto;

/**
 * @author marc.archin
 *
 */
public class ResponseHeader {

    private String service;
    private String version;
    private String responseDate;

    public ResponseHeader() {
        // Jackson requires a default constructor
    }

    public ResponseHeader(final String service, final String version, final String responseDate) {
        this.service = service;
        this.version = version;
        this.responseDate = responseDate;

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
        final ResponseHeader other = (ResponseHeader) obj;
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
        return "ResponseHeader [service=" + service + ", version=" + version + ", responseDate=" + responseDate + "]";
    }

}

package com.att.api.nobf.service;

import com.amind.att.controller.solution.avpn.SSDFQualifyAddressRequest;
import com.att.api.nobf.model.ADIQualifyAddressResponse;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityresponse.ValidateAddressServiceAvailabilityResponseInfo;

public interface INewOnlineBuyFlowService {
    /**
     * Validates if the given address is valid.
     *
     * @param line1 Address line 1
     * @param line2 Address line 2
     * @param city Address city
     * @param state Address state two char
     * @param zip Address zip code
     *
     * @return A ValidateAddressServiceAvailabilityResponseInfo structure containing standardized address
     */
    public ValidateAddressServiceAvailabilityResponseInfo validateAddressServiceAvail(final String line1, final String line2, final String city, final String state, final String zip);;

    /**
     * Returns information about the solutions for which the given address qualifies.
     *
     * @param request The request, containing detailed address information
     *
     * @return A QualifyAddressesResponseInfo structure, representing the solutions available at the given address.
     */
    public ADIQualifyAddressResponse processAddressQualificationRequest(final SSDFQualifyAddressRequest request);

}

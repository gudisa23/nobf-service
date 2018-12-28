package com.amind.att.service.avpn;

import com.amind.att.exception.MobileFirstGenericException;
import com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.QualifyAddressesRequestInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;

/**
 * This is used to handle all services related SSDFQualifyAddress
 */
public interface SSDFQualifyAddressService {

	/**
	 *
	 * @param request
	 * @return QualifyAddressesResponseInfo
	 * @throws MobileFirstGenericException
	 */
	QualifyAddressesResponseInfo qualifyAddresses(QualifyAddressesRequestInfo request) throws MobileFirstGenericException;

}

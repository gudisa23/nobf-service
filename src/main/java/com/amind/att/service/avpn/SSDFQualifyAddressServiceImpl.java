package com.amind.att.service.avpn;

import com.amind.att.exception.MobileFirstGenericException;
import com.amind.att.service.ssdf.QualifyAddressesHandler;
import com.amind.att.service.webservice.util.Property;
import com.att.cio.commonheader.v3.WSCallback;
import com.att.cio.commonheader.v3.WSHeader;
import com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.QualifyAddressesRequestInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SSDFQualifyAddressServiceImpl
 */
@Component
public class SSDFQualifyAddressServiceImpl implements SSDFQualifyAddressService {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public static final String SSDF_ASYNC_QUALIFY_ADDRESS_CALLBACK_URL = "SSDF_ASYNC_QUALIFY_ADDRESS_CALLBACK_URL";

  @Autowired
  private QualifyAddressesHandler qualifyAddressesHandler;


  /**
   * @param request
   * @return
   * @throws MobileFirstGenericException
   */
  @Override
  public QualifyAddressesResponseInfo qualifyAddresses(QualifyAddressesRequestInfo request) throws MobileFirstGenericException {
    WSCallback callbackInfo = new WSCallback();
    callbackInfo.setURL(Property.SSDF_WS.get(SSDF_ASYNC_QUALIFY_ADDRESS_CALLBACK_URL));
    WSHeader wsHeader = request.getWSHeader();

    if (wsHeader == null) {
      wsHeader = new WSHeader();
    }
    wsHeader.setWSCallback(callbackInfo);
    request.setWSHeader(wsHeader);
    return qualifyAddressesHandler.initiateService(request).get();
  }


}

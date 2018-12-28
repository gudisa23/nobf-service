package com.amind.att.service.ssdf;

import com.amind.att.exception.MobileFirstGenericException;
import com.amind.att.service.webservice.domain.IWebServiceRequest;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.handler.SSDFWebserviceHandler;
import com.amind.att.service.webservice.util.Property;
import com.att.ssdf.cpq.services.soap.qualifyaddresses.v1.QualifyAddressesPortType;
import com.att.ssdf.cpq.services.soap.qualifyaddresses.v1.WSException;
import com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.QualifyAddressesRequestInfo;
import com.att.ssdf.cpq.services.soap.qualifyaddressesresponse.v1.QualifyAddressesResponseInfo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Qualify Address API Handler
 */
@Component
public class QualifyAddressesHandler extends
        SSDFWebserviceHandler<QualifyAddressesPortType, QualifyAddressesRequestInfo, QualifyAddressesResponseInfo> {

  private final String url = Property.SSDF_WS.get("SSDF_GET_QUALIFY_ADDRESSES");
  private QualifyAddressesPortType port;
  private boolean alwaysInitPort = false;

  /**
   * Initializer.
   */
  @PostConstruct
  public void init() {
    port = initializeNewPort(url, QualifyAddressesPortType.class);
    addTimeoutsToPort(port, 720000L, 720000L);
  }

  /**
   * @param request outgoing request
   * @return
   */
  @Override
  public IWebServiceResponse<QualifyAddressesResponseInfo> invoke(
      IWebServiceRequest<QualifyAddressesRequestInfo> request) {
    try {
      QualifyAddressesPortType portToUse;
      alwaysInitPort = true;
      if (alwaysInitPort) {
        portToUse = initializeNewPort(url, QualifyAddressesPortType.class);
        addTimeoutsToPort(port, 720000L, 720000L);
      } else {
        portToUse = port;
      }
      //TODO
      //final QualifyAddressesResponseInfo response = portToUse.getQualifyAddresses(getMessageHeader(), request.get());
      final QualifyAddressesResponseInfo response = portToUse.getQualifyAddresses(request.get());
      return response(response);
    } catch (final WSException e) {
      getWsLogger().error("Exception in calling SSDF Interface ", e);
      throw new MobileFirstGenericException(e);
    }
  }

}

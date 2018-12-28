package com.amind.att.service.csi;

import com.amind.att.exception.MobileFirstGenericException;
import com.amind.att.service.webservice.domain.IWebServiceRequest;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.handler.CSIWebserviceHandler;
import com.amind.att.service.webservice.util.Property;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityrequest.ValidateAddressServiceAvailabilityRequestInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressserviceavailabilityresponse.ValidateAddressServiceAvailabilityResponseInfo;
import com.cingular.csi.csi.namespaces.v116.wsdl.cingularwirelesscsi_wsdl.CSIApplicationException;
import com.cingular.csi.csi.namespaces.v116.wsdl.cingularwirelesscsi_wsdl.ValidateAddressServiceAvailabilityPortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Class for managing Validate Address Service Availability Handler
 */
@Component
public class ValidateAddressServiceAvailabilityHandler extends CSIWebserviceHandler<ValidateAddressServiceAvailabilityPortType, ValidateAddressServiceAvailabilityRequestInfo, ValidateAddressServiceAvailabilityResponseInfo> {

  private final String url = Property.CSI.get("CSI_VALIDATE_SERVICE_URL");
  private final Logger logger = LoggerFactory.getLogger(ValidateAddressServiceAvailabilityHandler.class);
  private ValidateAddressServiceAvailabilityPortType port;
  private boolean alwaysInitPort = false;

  /**
   * Method for managing init function
   */
  @PostConstruct
  public void init() {
    port = initializeNewPort(url, ValidateAddressServiceAvailabilityPortType.class);
  }

  @Override
  public IWebServiceResponse<ValidateAddressServiceAvailabilityResponseInfo> invoke(final IWebServiceRequest<ValidateAddressServiceAvailabilityRequestInfo> request) {
    try {
      final ValidateAddressServiceAvailabilityPortType portToUse =
              (alwaysInitPort) ? initializeNewPort(url, ValidateAddressServiceAvailabilityPortType.class) : port;
      final ValidateAddressServiceAvailabilityResponseInfo response = portToUse.validateAddressServiceAvailability(getMessageHeader(), request.get());
      return response(response);
    } catch (CSIApplicationException e) {
      getWsLogger().error("Exception in calling CSI Interface ", e);
      throw new MobileFirstGenericException(e);
    }
  }
}

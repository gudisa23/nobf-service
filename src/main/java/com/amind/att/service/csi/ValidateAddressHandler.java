package com.amind.att.service.csi;

import com.amind.att.exception.MobileFirstGenericException;
import com.amind.att.service.webservice.domain.IWebServiceRequest;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.handler.CSIWebserviceHandler;
import com.amind.att.service.webservice.util.Property;
import com.cingular.csi.csi.namespaces.container._public.validateaddressrequest.ValidateAddressRequestInfo;
import com.cingular.csi.csi.namespaces.container._public.validateaddressresponse.ValidateAddressResponseInfo;
import com.cingular.csi.csi.namespaces.v116.wsdl.cingularwirelesscsi_wsdl.CSIApplicationException;
import com.cingular.csi.csi.namespaces.v116.wsdl.cingularwirelesscsi_wsdl.ValidateAddressPortType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Class for validating Address Handler
 */
@Component
public class ValidateAddressHandler extends CSIWebserviceHandler<ValidateAddressPortType, ValidateAddressRequestInfo, ValidateAddressResponseInfo> {

  private final String url = Property.CSI.get("CSI_VALIDATE_ADDRESS_URL");
  private ValidateAddressPortType port;
  private boolean alwaysInitPort = false;

  /**
   * Method for managing init function
   */
  @PostConstruct
  public void init() {
    port = initializeNewPort(url, ValidateAddressPortType.class);
  }

  @Override
  public IWebServiceResponse<ValidateAddressResponseInfo> invoke(IWebServiceRequest<ValidateAddressRequestInfo> request) {
    try {
      final ValidateAddressPortType portToUse =
              (alwaysInitPort) ? initializeNewPort(url, ValidateAddressPortType.class) : port;
      final ValidateAddressResponseInfo response = portToUse.validateAddress(getMessageHeader(), request.get());
      return response(response);
    } catch (final CSIApplicationException e) {
      getWsLogger().error("Exception in calling CSI Interface ", e);
      throw new MobileFirstGenericException(e);
    }
  }

}

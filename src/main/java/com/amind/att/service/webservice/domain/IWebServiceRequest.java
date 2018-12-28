package com.amind.att.service.webservice.domain;

import com.google.common.base.Supplier;

/**
 * IWeb Service Request
 *
 * @param <T>
 */
@SuppressWarnings("squid:S00115")
public interface IWebServiceRequest<T> extends Supplier<T> {

  String CRB_CSI_VALIDATE_ADDRESS_TARGET = "CRB_CSI_ValidateAddressRequest";

  String CRB_CSI_InquireFiberDesignDetailsV81_TARGET = "CRB_CSI_InquireFiberDesignDetailsV81Request";

  String CRB_CSI_InquireServiceAvailForLocation_TARGET = "CRB_CSI_InquireServiceAvailabilityForLocationReq";

  String CRB_CSI_VALIDATE_ADDRESS_SERVICE_AVAIL_TARGET = "CRB_CSI_ValidateAddressServiceRequest";

  String CRB_CSI_CREATE_FIBER_DESIGN_ORDER_TARGET = "CRB_CSI_CreateFiberDesignOrderRequest";

  String CRB_CSI_INQUIRE_FIBER_DESIGN_DETAILS_TARGET = "CRB_CSI_InquireFiberDesignDetailsRequest";

  String CRB_CSI_VALIDATE_FIBER_FACILITY_AVA_TARGET = "CRB_CSI_ValidateFiberFacilityAvailabilityRequest";

  String CRB_CSI_CreateFiberDesignOrderv81_TARGET = "CRB_CSI_CreateFiberDesignOrderV81Request";

  String CRB_CSI_Inquire_FTTB_Product_Details_TARGET = "CRB_CSI_InquireFTTBProductDetailsRequest";

  String CRB_CSI_InquireEnterpriseAccountList_TARGET = "CRB_CSI_InquireEnterpriseAccountListRequest";

  String CRB_PRICERD_INQUIRE_ACCESS_AVAILABILITY_URL = "CRB_PRICERD_INQUIRE_ACCESS_AVAILABILITY_URL";

  String CRB_PRICERD_INQUIRE_ACCESS_QUOTE_URL = "CRB_PRICERD_INQUIRE_ACCESS_QUOTE_URL";

  String CSI_USER_ID = "CSI_UserId";

  /**
   * Get Service Name
   *
   * @return
   */
  String getServiceName();

  /**
   * Get User ID
   *
   * @return
   */
  String getUserId();

}

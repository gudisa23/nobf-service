package com.amind.att.service.webservice.handler;

import com.amind.att.service.csi.CSISOAPHandler;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.util.Property;
import com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.MessageHeaderSecurity;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.MessageHeaderSequence;
import com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.MessageHeaderTracking;
import com.cingular.csi.csi.namespaces.types._public.messageheader.MessageHeaderInfo;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import java.util.List;

/**
 * CSI Webservice Handler
 *
 * @param <PortType>
 * @param <Request>
 * @param <Response>
 */
@SuppressWarnings({"squid:S2221", "squid:S1200"})
public abstract class CSIWebserviceHandler<PortType, Request, Response> extends AbstractWebserviceHandler<PortType, Request, Response> {

  public static final String CSI_AUTHENTICATION_USER_NAME = "CSI_AUTHENTICATION_USER_NAME";
  public static final String DEFAULT_USERNAME = "eitcrb";

  public static final String CSI_AUTHENTICATION_PASS = "CSI_AUTHENTICATION_PASSWORD";
  public static final String DEFAULT_PASS = "crb909";

  public static final String CSI_VERSION = "CSI_VERSION";

  public static final String CSI_GCP_VERSION = "CSI_GCP_VERSION";
  public static final String CSI_GCP_INFRASTRUCTURE_VERSION = "CSI_GCP_INFRASTRUCTURE_VERSION";

  public static final String GCP_SERVICE_ASSURANCE = "GCP_SERVICE_ASSURANCE";
  public static final String GCP_SERVICE_ASSURANCE_INFRASTRUCTURE_VERSION = "GCP_SERVICE_ASSURANCE_INFRASTRUCTURE_VERSION";

  public static final String CSI_NME_VERSION = "CSI_NME_VERSION";
  public static final String CSI_NME_INFRASTRUCTURE_VERSION = "CSI_NME_INFRASTRUCTURE_VERSION";

  public static final String CSI_OMX_VERSION = "CSI_OMX_VERSION";
  public static final String CSI_OMX_INFRASTRUCTURE_VERSION = "CSI_OMX_INFRASTRUCTURE_VERSION";

  public static final String CSI_FOBPM_VERSION = "CSI_FOBPM_VERSION";
  public static final String CSI_FOBPM_INFRASTRUCTURE_VERSION = "CSI_FOBPM_INFRASTRUCTURE_VERSION";

  public static final String CSI_FOBPME_VERSION = "CSI_FOBPME_VERSION";
  public static final String CSI_FOBPME_INFRASTRUCTURE_VERSION = "CSI_FOBPME_INFRASTRUCTURE_VERSION";

  public static final String CSI_ADDRESS_VERSION = "CSI_ADDRESS_VERSION";
  public static final String CSI_ADDRESS_INFRASTRUCTURE_VERSION = "CSI_ADDRESS_INFRASTRUCTURE_VERSION";

  public static final String ETF_APPLICATION_NAME = "ETF_APPLICATION_NAME";
  public static final String CSI_ETF_INFRASTRUCTURE_VERSION = "CSI_ETF_INFRASTRUCTURE_VERSION";
  public static final String CSI_ETF_VERSION = "CSI_ETF_VERSION";

  public static final String CSI_OCX_VERSION = "CSI_OCX_VERSION";
  public static final String CSI_OCX_INFRASTRUCTURE_VERSION = "CSI_OCX_INFRASTRUCTURE_VERSION";

  public static final String CSI_QUAL_VERSION = "CSI_QUAL_VERSION";
  public static final String CSI_QUAL_INFRASTRUCTURE_VERSION = "CSI_QUAL_INFRASTRUCTURE_VERSION";

  public static final String CSI_NMPHYSICAL_VERSION = "CSI_NMPHYSICAL_VERSION";
  public static final String CSI_NMPHYSICAL_INFRASTRUCTURE_VERSION = "CSI_NMPHYSICAL_INFRASTRUCTURE_VERSION";

  public static final String CSI_MSOD_VERSION = "CSI_MSOD_VERSION";

  protected static final com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ObjectFactory dataObjectFactory =
      new com.cingular.csi.csi.namespaces.types._public.cingulardatamodel.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.types._public.messageheader.ObjectFactory messageObjectFactory =
      new com.cingular.csi.csi.namespaces.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.ObjectFactory unifiedServiceMessageObjectFactory =
      new com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.ObjectFactory customerCareProfileMessageObjectFactory =
          new com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.ObjectFactory csiGcpObjectFactory =
      new com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.ObjectFactory csiGcpObjectInventoryFactory =
      new com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.ObjectFactory csiOmxObjectFactory =
      new com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.ObjectFactory messageOMXObjectFactory =
      new com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.ObjectFactory messageAddressFactory =
      new com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.ObjectFactory csiAddressObjectFactory =
      new com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.ObjectFactory csiFobpmeObjectFactory =
      new com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.ObjectFactory csiFobpmObjectFactory =
      new com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.ObjectFactory messageFobpmObjectFactory = new
      com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.ObjectFactory messageFobpmeObjectFactory = new
      com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.ObjectFactory csiEtfObjectFactory = new
      com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.ObjectFactory csiOCXObjectFactory =
      new com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.ObjectFactory messageOCXObjectFactory = new
      com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.ObjectFactory csiQualObjectFactory =
      new com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.ObjectFactory messageQualObjectFactory = new
      com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.ObjectFactory csiIglooObjectFactory =
      new com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.ObjectFactory csiNMPhysicalFactory =
      new com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.ObjectFactory messageNMPhysicalFactory =
      new com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.ObjectFactory();

  protected com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.ObjectFactory csiNmeObjectFactory =
      new com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.ObjectFactory();
  protected static final com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.ObjectFactory messageNmeObjectFactory = new
      com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.ObjectFactory();

  protected String conversationId;

  /**
   * Initialize New Port
   *
   * @param serviceUrl
   * @param serviceClass
   * @return
   */
  public PortType initializeNewPort(final String serviceUrl, final Class<PortType> serviceClass) {
    PortType newPort = null;
    long time_init_port_starts = System.currentTimeMillis();
    try {
      Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceUrl), "Missing URL for " + serviceClass.getSimpleName());
      newPort = getPort(serviceUrl, serviceClass);
      try {
        CSISOAPHandler csiSoap = new CSISOAPHandler();
        BindingProvider bindProv = (BindingProvider) newPort;
        List<Handler> handlers = bindProv.getBinding().getHandlerChain();
        handlers.add(csiSoap);
        bindProv.getBinding().setHandlerChain(handlers);
      } catch (Exception e) {
        getWsLogger().error("Exception setting CSISOAPHandler on port:", e);
      }
    } catch (Exception e) {
      getWsLogger().error("Exception creating port " + serviceClass.getSimpleName(), e);
    }
    long time_init_port_ends = System.currentTimeMillis();
    getWsLogger().info(String.format("Time taken to init port %s : %s milli secs", serviceClass.getSimpleName(), time_init_port_ends - time_init_port_starts));
    return newPort;
  }

  /**
   * You can access <code>dataObjectFactory</code> and <code>messageObjectFactory</code> for assistance in creating CSI types.
   *
   * @param request outgoing request
   * @return incoming response
   */
  @Override
  public IWebServiceResponse<Response> initiateService(final Request request) {
    // nothing modified, just a pass-through so we can provide a JavaDoc
    return super.initiateService(request);
  }

  /**
   * Initialize Service
   *
   * @param request
   * @param conversationId
   * @return
   */
  public IWebServiceResponse<Response> initiateService(final Request request, String conversationId) {
    // nothing modified, just a pass-through so we can provide a JavaDoc
    this.conversationId = conversationId;
    return initiateService(request);    // Call the above method
  }

  /**
   * This is the default implementation for setting the MessageHeaderInfo
   *
   * @return a JAX-WS Holder containing the <code>MessageHeaderInfo</code>
   */
  public Holder<MessageHeaderInfo> getMessageHeader() {
    final MessageHeaderInfo header = messageObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getMessageHeaderTracking());
    header.setSecurityMessageHeader(getMessageHeaderSecurity());
    header.setSequenceMessageHeader(getMessageHeaderSequence());
    messageObjectFactory.createMessageHeader(header);
    return new Holder<MessageHeaderInfo>(header);
  }

  /**
   * @param version
   * @return a JAX-WS Holder containing the <code>MessageHeaderInfo</code>
   */
  public Holder<MessageHeaderInfo> getMessageHeader(String version) {
    final MessageHeaderInfo header = messageObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getMessageHeaderTracking(version));
    header.setSecurityMessageHeader(getMessageHeaderSecurity());
    header.setSequenceMessageHeader(getMessageHeaderSequence());
    messageObjectFactory.createMessageHeader(header);
    return new Holder<MessageHeaderInfo>(header);
  }

  /**
   * This is the default implementation for setting the MessageHeaderInfo
   *
   * @return a JAX-WS Holder containing the <code>MessageHeaderInfo</code>
   */
  public Holder<com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getUnifiedServiceMessageHeader() {
    final com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo
        header = unifiedServiceMessageObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(geUnifiedServicetMessageHeaderTracking());
    header.setSecurityMessageHeader(getUnifiedServiceMessageHeaderSecurity());
    header.setSequenceMessageHeader(getUnifiedServiceMessageHeaderSequence());
    unifiedServiceMessageObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  /**
   * This is the default implementation for setting the MessageHeaderInfo
   *
   * @return a JAX-WS Holder containing the <code>MessageHeaderInfo</code>
   */
  public Holder<com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCustomercareprofileMessageHeader() {
    final com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo
            header = customerCareProfileMessageObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(geCustomerCareProfileMessageHeaderTracking());
    header.setSecurityMessageHeader(getCustomerCareProfileMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCustomerCareProfileMessageHeaderSequence());
    customerCareProfileMessageObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  /**
   * The <code>MessageHeaderSequence</code>
   *
   * @return the message header sequence
   */
  public MessageHeaderSequence getMessageHeaderSequence() {
    final MessageHeaderSequence sequence = dataObjectFactory.createMessageHeaderSequence();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  /**
   * The <code>MessageHeaderSecurity</code>
   *
   * @return the message header security
   */
  public MessageHeaderSecurity getMessageHeaderSecurity() {
    final MessageHeaderSecurity security = dataObjectFactory.createMessageHeaderSecurity();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  /**
   * The <code>MessageHeaderTracking</code>
   *
   * @return the message header tracking
   */
  public MessageHeaderTracking getMessageHeaderTracking() {
    final MessageHeaderTracking tracking = dataObjectFactory.createMessageHeaderTracking();
    tracking.setVersion(Property.CSI.get(CSI_VERSION));
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  /**
   * The <code>MessageHeaderTracking</code>
   *
   * @param version
   * @return the message header tracking
   */
  public MessageHeaderTracking getMessageHeaderTracking(String version) {
    final MessageHeaderTracking tracking = dataObjectFactory.createMessageHeaderTracking();
    tracking.setVersion(version);
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  /**
   * The <code>MessageHeaderSequence</code>
   *
   * @return the message header sequence
   */
  public com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCustomerCareProfileMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader
            sequence = customerCareProfileMessageObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  /**
   * The <code>MessageHeaderSecurity</code>
   *
   * @return the message header security
   */
  public com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCustomerCareProfileMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader
            security = customerCareProfileMessageObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  /**
   * The <code>MessageHeaderTracking</code>
   *
   * @return the message header tracking
   */
  public com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader geCustomerCareProfileMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.customercareprofile.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader
            tracking = customerCareProfileMessageObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setInfrastructureVersion("86");
    tracking.setApplicationName("CustomerCareProfile");
    tracking.setVersion("224");
    tracking.setMessageId("");
    tracking.setOriginatorId("");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  /**
   * The <code>MessageHeaderSequence</code>
   *
   * @return the message header sequence
   */
  public com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getUnifiedServiceMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader
        sequence = unifiedServiceMessageObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  /**
   * The <code>MessageHeaderSecurity</code>
   *
   * @return the message header security
   */
  public com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getUnifiedServiceMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader
        security = unifiedServiceMessageObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  /**
   * The <code>MessageHeaderTracking</code>
   *
   * @return the message header tracking
   */
  public com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader geUnifiedServicetMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.unifiedservices.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader
        tracking = unifiedServiceMessageObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setInfrastructureVersion("86");
    tracking.setApplicationName("UnifiedServices");
    tracking.setVersion(Property.CSI.get("CSI_UNIFIED_SERVICE_VERSION"));
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setOriginatorId("TESTT");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  /**
   * Optional username for authenticated requests
   *
   * @return the username
   */
  public String getUsername() {
    //return Property.CSI.find(CSI_AUTHENTICATION_USER_NAME).or(DEFAULT_USERNAME);
    return Property.AUTH.find(CSI_AUTHENTICATION_USER_NAME).or(DEFAULT_USERNAME);
  }

  /**
   * Optional password for authenticated requests
   *
   * @return the password
   */
  public String getPassword() {
    return Property.AUTH.find(CSI_AUTHENTICATION_PASS).or(DEFAULT_PASS);
  }

  /**
   * Get GCP MSOD Message Header
   *
   * @return
   */
  public Holder<com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIGCPMSODMessageHeader() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo csigcpMessageInventoryHeader = csiGcpObjectInventoryFactory.createMessageHeaderInfo();
    csigcpMessageInventoryHeader.setTrackingMessageHeader(getCSIGCPMSODHeaderTracking());
    csigcpMessageInventoryHeader.setSecurityMessageHeader(getCSIGCPMessageInventoryHeaderSecurity());
    csigcpMessageInventoryHeader.setSequenceMessageHeader(getCSIGCPMessageInventoryHeaderSequence());
    csiGcpObjectInventoryFactory.createMessageHeader(csigcpMessageInventoryHeader);
    return new Holder<com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(csigcpMessageInventoryHeader);
  }

  public Holder<com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIGCPInventoryMessageHeader() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo csigcpMessageInventoryHeader = csiGcpObjectInventoryFactory.createMessageHeaderInfo();
    csigcpMessageInventoryHeader.setTrackingMessageHeader(getCSIGCPMessageInventoryHeaderTracking());
    csigcpMessageInventoryHeader.setSecurityMessageHeader(getCSIGCPMessageInventoryHeaderSecurity());
    csigcpMessageInventoryHeader.setSequenceMessageHeader(getCSIGCPMessageInventoryHeaderSequence());
    csiGcpObjectInventoryFactory.createMessageHeader(csigcpMessageInventoryHeader);
    return new Holder<com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(csigcpMessageInventoryHeader);
  }

  public com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIGCPMessageInventoryHeaderSequence() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiGcpObjectInventoryFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIGCPMessageInventoryHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiGcpObjectInventoryFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }


  public Holder<com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIGCPMessageHeader() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo csigcpMessageHeader = csiGcpObjectFactory.createMessageHeaderInfo();
    csigcpMessageHeader.setTrackingMessageHeader(getCSIGCPMessageHeaderTracking());
    csigcpMessageHeader.setSecurityMessageHeader(getCSIGCPMessageHeaderSecurity());
    csigcpMessageHeader.setSequenceMessageHeader(getCSIGCPMessageHeaderSequence());
    csiGcpObjectFactory.createMessageHeader(csigcpMessageHeader);
    return new Holder<com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(csigcpMessageHeader);
  }

  public Holder<com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getGCPServiceAssuranceHeader() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo csigcpMessageHeader = csiGcpObjectFactory.createMessageHeaderInfo();
    csigcpMessageHeader.setTrackingMessageHeader(getGCPServiceAssuranceTracking());
    csigcpMessageHeader.setSecurityMessageHeader(getCSIGCPMessageHeaderSecurity());
    csigcpMessageHeader.setSequenceMessageHeader(getCSIGCPMessageHeaderSequence());
    csiGcpObjectFactory.createMessageHeader(csigcpMessageHeader);
    return new Holder<com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(csigcpMessageHeader);
  }

  public com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIGCPMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiGcpObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIGCPMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiGcpObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIGCPMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiGcpObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_GCP_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_GCP_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("GCPServiceAssurance");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getGCPServiceAssuranceTracking() {
    final com.cingular.csi.csi.namespaces.gcpserviceassurance.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiGcpObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(GCP_SERVICE_ASSURANCE));
    tracking.setInfrastructureVersion(Property.CSI.get(GCP_SERVICE_ASSURANCE_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("GCPServiceAssurance");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));

    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIGCPMessageInventoryHeaderTracking() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiGcpObjectInventoryFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_GCP_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_GCP_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("GlobalComputingPlatformSales");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  /**
   * return MSOD header
   *
   * @return
   */
  public com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIGCPMSODHeaderTracking() {
    final com.cingular.csi.csi.namespaces.globalcomputingplatformsales.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiGcpObjectInventoryFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_MSOD_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_GCP_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("GlobalComputingPlatformSales");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public Holder<com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getOMXMessageHeader() {
    final com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageOMXObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIOMXMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIOMXMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIOMXMessageHeaderSequence());
    messageOMXObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIOMXMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiOmxObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_OMX_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_OMX_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("OrderManagementExpressWorkflow");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIOMXMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.ordermanagementexpressworkflow.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiOmxObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public SecurityMessageHeader getCSIOMXMessageHeaderSecurity() {
    final SecurityMessageHeader security = csiOmxObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }


  public Holder<com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getMessageHeaderNew() {
    final com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageAddressFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getAddressMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIAddressMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIAddressMessageHeaderSequence());
    messageAddressFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getAddressMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiAddressObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_ADDRESS_VERSION));
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_ADDRESS_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("OneViewAddressLocationSystem");
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIAddressMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiAddressObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIAddressMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.oneviewaddresslocationsystem.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiAddressObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getFOBPMMessageHeader() {
    final com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageFobpmObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIFOBPMMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIFOBPMMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIFOBPMMessageHeaderSequence());
    messageFobpmObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }


  public com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIFOBPMMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiFobpmObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_FOBPM_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_FOBPM_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("FOBPMOrders");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIFOBPMMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiFobpmObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIFOBPMMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.fobpmorders.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiFobpmObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getFOBPMEMessageHeader() {
    final com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageFobpmeObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIFOBPMEMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIFOBPMEMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIFOBPMEMessageHeaderSequence());
    messageFobpmeObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIFOBPMEMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiFobpmeObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_FOBPME_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_FOBPME_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("FOBPMEnrollments");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIFOBPMEMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiFobpmeObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIFOBPMEMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.fobpmenrollments.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiFobpmeObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIOCXMessageHeader() {
    final com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageOCXObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIOCXMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIOCXMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIOCXMessageHeaderSequence());
    messageOCXObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIOCXMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiOCXObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_OCX_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_OCX_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("OrderCaptureExpress");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIOCXMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiOCXObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIOCXMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.ordercaptureexpress.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiOCXObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  /**
   * Get Response Conversation ID
   *
   * @param port
   * @return
   */
  public String getResponseConversationId(PortType port) {
    return (String) ((BindingProvider) port).getResponseContext().get(CSISOAPHandler.CONVERSATION_ID);
  }

  public Holder<com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIETFMessageHeader() {
    final com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo csietfMessageHeader = csiEtfObjectFactory.createMessageHeaderInfo();
    csietfMessageHeader.setTrackingMessageHeader(getCSIETFMessageHeaderTracking());
    csietfMessageHeader.setSecurityMessageHeader(getCSIETFMessageHeaderSecurity());
    csietfMessageHeader.setSequenceMessageHeader(getCSIETFMessageHeaderSequence());
    csiEtfObjectFactory.createMessageHeader(csietfMessageHeader);
    return new Holder<com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(csietfMessageHeader);
  }

  public com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIETFMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiEtfObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIETFMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiEtfObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIETFMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.rthreeniche.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiEtfObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_ETF_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_ETF_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName(Property.CSI.get(ETF_APPLICATION_NAME));
    tracking.setMessageId("");
    tracking.setTimeToLive(new java.math.BigInteger("300000"));
    tracking.setConversationId("");
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    tracking.setOriginalInfrastructureVersion("");
    tracking.setOriginalVersion("");
    tracking.setOriginatorId("");
    tracking.setResponseTo("");
    tracking.setReturnURL("");
    tracking.setUniqueTransactionId("");
    tracking.getRoutingRegionOverride();
    return tracking;
  }

  public Holder<com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIQUALMessageHeader() {
    final com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageQualObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIQUALMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIQUALMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIQUALMessageHeaderSequence());
    messageQualObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIQUALMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiQualObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_QUAL_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_QUAL_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("OneATTQual");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIQUALMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiQualObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIQUALMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.oneattqual.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiQualObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSIIGLOOMessageHeader() {
    final com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = csiIglooObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSIIGLOOMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSIIGLOOMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSIIGLOOMessageHeaderSequence());
    csiIglooObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSIIGLOOMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiIglooObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setInfrastructureVersion(Property.CSI.get("IGLOO_INFRASTRUCTURE_VERSION"));
    tracking.setVersion(Property.CSI.get("IGLOO_VERSION"));
    tracking.setOriginalVersion(Property.CSI.get("IGLOO_VERSION"));
    tracking.setApplicationName(Property.CSI.get("IGLOO_APPLICATION_NAME"));
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSIIGLOOMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiIglooObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSIIGLOOMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.productandoffermanagement.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiIglooObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getCSINMEMessageHeader() {
    final com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageNmeObjectFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getCSINMEMessageHeaderTracking());
    header.setSecurityMessageHeader(getCSINMEMessageHeaderSecurity());
    header.setSequenceMessageHeader(getCSINMEMessageHeaderSequence());
    messageNmeObjectFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }


  public com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getCSINMEMessageHeaderTracking() {
    final com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiNmeObjectFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_NME_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_NME_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("NetworkManagementEthernetOverFiber");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getCSINMEMessageHeaderSequence() {
    final com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiNmeObjectFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getCSINMEMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.networkmanagementethernetoverfiber.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiNmeObjectFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }

  public Holder<com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo> getNMPhysicalMessageHeader() {
    final com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo header = messageNMPhysicalFactory.createMessageHeaderInfo();
    header.setTrackingMessageHeader(getNMPhysicalHeaderTracking());
    header.setSecurityMessageHeader(getNMPhysicalMessageHeaderSecurity());
    header.setSequenceMessageHeader(getNMPhysicalHeaderSequence());
    messageNMPhysicalFactory.createMessageHeader(header);
    return new Holder<com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo>(header);
  }

  public com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader getNMPhysicalHeaderTracking() {
    final com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.TrackingMessageHeader tracking = csiNMPhysicalFactory.createMessageHeaderInfoTrackingMessageHeader();
    tracking.setVersion(Property.CSI.get(CSI_NMPHYSICAL_VERSION));
    tracking.setInfrastructureVersion(Property.CSI.get(CSI_NMPHYSICAL_INFRASTRUCTURE_VERSION));
    tracking.setApplicationName("NMPhysical");
    tracking.setMessageId("NotUniqueSameIdForAllTransactions");
    tracking.setTimeToLive(new java.math.BigInteger("360000"));
    if (this.conversationId != null && this.conversationId.length() > 0) {
      getWsLogger().info("My conversationId=" + conversationId);
      tracking.setConversationId(this.conversationId);
    }
    tracking.setDateTimeStamp(getFormattedDate("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    return tracking;
  }

  public com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader getNMPhysicalHeaderSequence() {
    final com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SequenceMessageHeader sequence = csiNMPhysicalFactory.createMessageHeaderInfoSequenceMessageHeader();
    sequence.setSequenceNumber("1");
    sequence.setTotalInSequence("1");
    return sequence;
  }

  public com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader getNMPhysicalMessageHeaderSecurity() {
    final com.cingular.csi.csi.namespaces.nmphysical.infrastructurecommon.types._public.messageheader.MessageHeaderInfo.SecurityMessageHeader security = csiNMPhysicalFactory.createMessageHeaderInfoSecurityMessageHeader();
    security.setUserName(getUsername());
    security.setUserPassword(getPassword());
    return security;
  }
}

package com.amind.att.service.webservice.handler;

import com.amind.att.service.ssdf.SSDFSOAPHandler;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.util.Property;
import com.att.ssdf.cpq.commondatamodel.MessageHeaderSequence;
import com.cingular.csi.csi.namespaces.types._public.messageheader.MessageHeaderInfo;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import javax.xml.ws.handler.Handler;
import java.util.List;

//TODO replace with ssdf head once provided by ssdf team
//TODO temporary until SSDF fixes the header

/**
 *
 * @param <PortType>
 * @param <Request>
 * @param <Response>
 */
@SuppressWarnings({"squid:S1200","squid:S00118"}) //grandfathered for sonar warnings
public abstract class SSDFWebserviceHandler<PortType, Request, Response> extends AbstractWebserviceHandler<PortType, Request, Response> {

	public static final String SSDF_AUTHENTICATION_USER_NAME = "SSDF_AUTHENTICATION_USER_NAME";
	public static final String DEFAULT_USERNAME = "eitcrb";

	public static final String SSDF_AUTHENTICATION_PSWD = "SSDF_AUTHENTICATION_PASSWORD";
	public static final String DEFAULT_PSWD = "crb909";

	public static final String SSDF_VERSION = "SSDF_VERSION";

	protected static final com.att.ssdf.cpq.commondatamodel.ObjectFactory dataObjectFactory =
			new com.att.ssdf.cpq.commondatamodel.ObjectFactory();

	protected static final com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.ObjectFactory	 messageObjectFactory =
			new com.att.ssdf.cpq.services.soap.qualifyaddressesrequest.v1.ObjectFactory();

	private String conversationId;


	/**
	 * Method to initialize New Port
	 * @param serviceClass
	 * @param serviceUrl
	 * @return newPort
	 */
	public PortType initializeNewPort(final String serviceUrl, final Class<PortType> serviceClass) {
		PortType newPort = null;
		long time_init_port_starts = System.currentTimeMillis();
		try {
			Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceUrl), "Missing URL for " + serviceClass.getSimpleName());
			newPort = getSOAP12Port(serviceUrl, serviceClass);
            setBindingProvider(newPort);
		} catch (RuntimeException e){
			getWsLogger().error("Exception creating port " + serviceClass.getSimpleName(), e);
		}
		long time_init_port_ends = System.currentTimeMillis();
		getWsLogger().info(String.format("Time taken to init port %s : %s milli secs", serviceClass.getSimpleName(), time_init_port_ends - time_init_port_starts));
		return newPort;
	}



	/**
	 * You can access <code>dataObjectFactory</code> and <code>messageObjectFactory</code> for assistance in creating SSDF types.
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
     *  Overloaded Method with conversationId
	 *  @param conversationId
	 *  @param request
	 *  @return Request for Initiate Service
     */
	public IWebServiceResponse<Response> initiateService(final Request request, String conversationId) {
		// nothing modified, just a pass-through so we can provide a JavaDoc
		this.conversationId = conversationId;  
		return initiateService(request);	// Call the above method
	}

	/**
	 * This is the default implementation for setting the MessageHeaderInfo
	 *
	 * @return a JAX-WS Holder containing the <code>MessageHeaderInfo</code>
	 */
	public Holder<MessageHeaderInfo> getMessageHeader() {
		return null;
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
	public com.att.ssdf.cpq.commondatamodel.MessageHeaderSecurity getMessageHeaderSecurity() {
		final com.att.ssdf.cpq.commondatamodel.MessageHeaderSecurity security = dataObjectFactory.createMessageHeaderSecurity();
		security.setUserName(getUsername());
		security.setUserPassword(getPassword());
		return security;
	}

	/**
	 * The <code>MessageHeaderTracking</code>
	 *
	 * @return the message header tracking
	 */
	public com.att.ssdf.cpq.commondatamodel.MessageHeaderTracking getMessageHeaderTracking() {
		final com.att.ssdf.cpq.commondatamodel.MessageHeaderTracking tracking = dataObjectFactory.createMessageHeaderTracking();
		tracking.setVersion(Property.SSDF.get(SSDF_VERSION));
		tracking.setMessageId("NotUniqueSameIdForAllTransactions");
		tracking.setTimeToLive(new java.math.BigInteger("360000"));
		if(this.conversationId!=null  && this.conversationId.length()>0 ) {
			tracking.setConversationId(this.conversationId);  // SET THE <conversationId>
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
		return Property.AUTH.find(SSDF_AUTHENTICATION_USER_NAME).or(DEFAULT_USERNAME);
	}

	/**
	 * Optional password for authenticated requests
	 *
	 * @return the password
	 */
	public String getPassword() {
		return Property.AUTH.find(SSDF_AUTHENTICATION_PSWD).or(DEFAULT_PSWD);
	}

    /**
     * Method to get Response Conversation Id
	 * @param port
	 * @return
     */
	public String getResponseConversationId(PortType port){
		return (String) ((BindingProvider)port).getResponseContext().get(SSDFSOAPHandler.CONVERSATION_ID);
	}

    /**
     * Method to check if Port list is not empty
	 * @param refListsData
	 * @return alwaysInitPort
     */
//	public boolean isAlwaysInitPort(RefListsData refListsData){
//		boolean alwaysInitPort = false;
//		// This check is temporary - a switch to init the port every time if things are not working properly
//		try{
//			List<RefLists> refList = refListsData.GetAllRefListsDataByListName("SERVICE_PORT_ALWAYS_INIT");
//			if (refList != null && refList.size() > 0){
//				alwaysInitPort = true;
//			}
//		}catch (RuntimeException e){
//			getWsLogger().error("Unable to retrieve RefList for always init port check", e);
//		}
//		return alwaysInitPort;
//	}

    /**
     * Method to initialize New Port
	 * @param serviceClass
	 * @param serviceUrl
	 * @return newPort
	 *
     */
	public PortType initializeNewPort2(final String serviceUrl, final Class<PortType> serviceClass) {
        PortType newPort = null;
        long time_init_port_starts = System.currentTimeMillis();
        try {
                Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceUrl), "Missing URL for " + serviceClass.getSimpleName());
                newPort = getPort(serviceUrl, serviceClass);
                setBindingProvider(newPort);
        } catch (RuntimeException e){
                        getWsLogger().error("Exception creating port " + serviceClass.getSimpleName(), e);
        }
        long time_init_port_ends = System.currentTimeMillis();
        getWsLogger().info(String.format("Time taken to init port %s : %s milli secs", serviceClass.getSimpleName(), time_init_port_ends - time_init_port_starts));
        return newPort;
}
    private void setBindingProvider(PortType newPort)
    {
        try {
            SSDFSOAPHandler ssdfSoap = new SSDFSOAPHandler(getUsername(), getPassword());
            BindingProvider bindProv = (BindingProvider) newPort;
            List<Handler> handlers = bindProv.getBinding().getHandlerChain();
            handlers.add(ssdfSoap);
            bindProv.getBinding().setHandlerChain(handlers);
        } catch (RuntimeException e) {
            getWsLogger().error("Exception setting SSDFSOAPHandler on port:", e);
        }
    }
}

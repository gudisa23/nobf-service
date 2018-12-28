package com.amind.att.service.webservice.handler;

import com.amind.att.exception.MobileFirstGenericException;
import com.amind.att.service.webservice.domain.IWebServiceRequest;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import com.amind.att.service.webservice.util.Slf4jCustomEventSender;
import com.amind.att.service.webservice.util.WSLogger;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.apache.cxf.binding.soap.Soap12;
import org.apache.cxf.binding.soap.SoapBindingConfiguration;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Abstract Webservice Handler
 *
 * @param <PortType>
 * @param <Request>
 * @param <Response>
 */
public abstract class AbstractWebserviceHandler<PortType, Request, Response> {

  protected CallbackHandler getPasswordCallbackHandler(final String key) {
    return new CallbackHandler() {

      @Override
      public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback.class.cast(callbacks[0]).setPassword(key);
      }

    };
  }

  protected static XMLGregorianCalendar getFormattedDate(final String pattern) {
    try {
      final String value = new SimpleDateFormat(pattern).format(new Date());
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(value);
    } catch (DatatypeConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  protected final WSLogger wslog = WSLogger.of(getClass());
  protected final Optional<ImmutableMap<String, Object>> security;

  protected AbstractWebserviceHandler() {
    security = Optional.absent();
  }

  protected AbstractWebserviceHandler(final String username, final String key) {
    security = Optional.of(ImmutableMap.<String, Object>builder()
        .put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN)
        .put(WSHandlerConstants.USER, username)
        .put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT)
        .put(WSHandlerConstants.PW_CALLBACK_REF, getPasswordCallbackHandler(key))
        .build());
  }

  protected AbstractWebserviceHandler(final String username, final String key, final String mustUnderstand) {
    security = Optional.of(ImmutableMap.<String, Object>builder()
        .put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN)
        .put(WSHandlerConstants.USER, username)
        .put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT)
        .put(WSHandlerConstants.PW_CALLBACK_REF, getPasswordCallbackHandler(key))
        .put(WSHandlerConstants.MUST_UNDERSTAND, mustUnderstand)
        .build());
  }

  /* Abstract Methods */

  /**
   * This must be overridden in order to invoke the SOAP method
   *
   * @param request outgoing request
   * @return incoming response
   */
  public abstract IWebServiceResponse<Response> invoke(final IWebServiceRequest<Request> request);

  /**
   * A helper method that wraps the request inside of a <code>IWebServiceRequest</code>
   *
   * @param request outgoing request
   * @return incoming response
   */
  public IWebServiceResponse<Response> invoke(final Request request) {
    return invoke(request(request));
  }

  /**
   * Used by webservice handlers to initiate a webservice call
   *
   * @param request outgoing request
   * @return incoming response
   */
  public IWebServiceResponse<Response> initiateService(final Request request) {
    return initiateService(request(request));
  }

  /**
   * Prepare Request
   *
   * @param request
   * @return
   */
  public IWebServiceRequest<Request> prepareRequest(IWebServiceRequest<Request> request) {
    return request;
  }

  /**
   * Process Response
   *
   * @param response
   * @return
   */
  public IWebServiceResponse<Response> processResponse(IWebServiceResponse<Response> response) {
    return response;
  }

  /**
   * Get Port
   *
   * @param serviceUrl
   * @param serviceClass
   * @return
   */
  public PortType getPort(final String serviceUrl, final Class<PortType> serviceClass) {
    final JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
    client.setAddress(serviceUrl);
    client.setServiceClass(serviceClass);
    LoggingFeature logFeature = new LoggingFeature();
    logFeature.setSender(new Slf4jCustomEventSender());
    client.getFeatures().add(logFeature);

    if (security.isPresent()) {
      client.getOutInterceptors().add(new WSS4JOutInterceptor(security.get()));
    }
    return serviceClass.cast(client.create());
  }

  /**
   * Get Validated Port
   *
   * @param serviceUrl
   * @param serviceClass
   * @return
   */
  public PortType getValidatedPort(final String serviceUrl, final Class<PortType> serviceClass) {
    final JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
    client.setAddress(serviceUrl);
    client.setServiceClass(serviceClass);
    LoggingFeature logFeature = new LoggingFeature();
    logFeature.setSender(new Slf4jCustomEventSender());
    client.getFeatures().add(logFeature);
    if (security.isPresent()) {
      client.getOutInterceptors().add(new WSS4JOutInterceptor(security.get()));
    }
    final PortType newClient = serviceClass.cast(client.create());
    ClientProxy.getClient(newClient).getRequestContext().put("schema-validation-enabled", "true"); // Add HTTP headers to the web service request
    return newClient;
  }

  /**
   * Add Timeouts To Port
   *
   * @param port
   * @param connectionTimeout
   * @param receiveTimeout
   */
  public void addTimeoutsToPort(PortType port, Long connectionTimeout, Long receiveTimeout) {
    Client client = ClientProxy.getClient(port);
    HTTPConduit http = (HTTPConduit) client.getConduit();
    HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
    if (connectionTimeout != null) {
      httpClientPolicy.setConnectionTimeout(connectionTimeout);
    }
    if (receiveTimeout != null) {
      httpClientPolicy.setReceiveTimeout(receiveTimeout);
    }
    http.setClient(httpClientPolicy);
  }

  /**
   * Initiate Service
   *
   * @param requestParameters
   * @return
   */
  public IWebServiceResponse<Response> initiateService(final IWebServiceRequest<Request> requestParameters) {

    wslog.info("Processing API - " + requestParameters.getServiceName());

    final long time_start = System.currentTimeMillis();

		/* Abstract Prepare Request - Exposed to individual handlers */
    final IWebServiceRequest<Request> request = prepareRequest(requestParameters);

    long time_prepare_request = System.currentTimeMillis();
    wslog.info("Time Elapsed to prepare request : %s milli secs", time_prepare_request - time_start);
    wslog.dump(request.get());

		/* Invoke the corresponding webservice */
    try {
      long time_csi_call_starts = System.currentTimeMillis();
      final IWebServiceResponse<Response> response = invoke(request);
      Preconditions.checkNotNull(response, "response was null");
      long time_csi_call_ends = System.currentTimeMillis();

      wslog.info(String.format("Time taken only for the API call - " + requestParameters.getServiceName() + " : %s milli secs", time_csi_call_ends - time_csi_call_starts));

      long time_get_ws_response = System.currentTimeMillis();
      wslog.info(String.format("Time Elapsed for Interface call : %s milli secs", time_get_ws_response - time_prepare_request));
      wslog.dump(response.get());

  		/* Processing the response */
      IWebServiceResponse<Response> reponseObject = processResponse(response);
      long time_process_response = System.currentTimeMillis();
      wslog.info(String.format("Time Elapsed to Process Response : %s milli secs", time_process_response - time_get_ws_response));
      wslog.info(String.format("Time Elapsed for service : %s milli secs", time_process_response - time_start));

      return reponseObject;
    } catch (MobileFirstGenericException mobileFirstException) {
      wslog.error("Exception in calling Interface " + requestParameters.getServiceName(), mobileFirstException);
      throw mobileFirstException;
    }
  }

  /**
   * Request
   *
   * @param request
   * @return
   */
  public IWebServiceRequest<Request> request(final Request request) {
    String serviceName = null;
    if (request != null && request.getClass() != null && request.getClass().getSimpleName() != null) {
      int indexOf = request.getClass().getSimpleName().indexOf("Request");
      if (indexOf > -1 && indexOf != 0) {
        serviceName = request.getClass().getSimpleName().substring(0, indexOf);
      } else {
        serviceName = request.getClass().getSimpleName();
      }
    }

    return request(request, serviceName, null);
  }

  /**
   * Request
   *
   * @param request
   * @param serviceName
   * @param userId
   * @return
   */
  public IWebServiceRequest<Request> request(final Request request, final String serviceName, final String userId) {
    return new IWebServiceRequest<Request>() {

      @Override
      public String getServiceName() {
        return serviceName;
      }

      @Override
      public String getUserId() {
        return userId;
      }

      @Override
      public Request get() {
        return request;
      }

    };
  }

  /**
   * Response
   *
   * @param response
   * @return
   */
  public IWebServiceResponse<Response> response(final Response response) {
    return response(response, null, null);
  }

  /**
   * Response
   *
   * @param response
   * @param serviceName
   * @param userId
   * @return
   */
  public IWebServiceResponse<Response> response(final Response response, final String serviceName, final String userId) {
    return response(response, null, null, null);
  }

  /**
   * Response
   *
   * @param response
   * @param serviceName
   * @param userId
   * @param conversationId
   * @return
   */
  public IWebServiceResponse<Response> response(final Response response, final String serviceName, final String userId, final String conversationId) {
    return new IWebServiceResponse<Response>() {

      @Override
      public Long getEntityId() {
        return null;
      }

      @Override
      public String getEntityName() {
        return null;
      }

      @Override
      public String getConversationId() {
        return conversationId;
      }

      @Override
      public Response get() {
        return response;
      }

    };
  }

  /**
   * Get WS Logger
   *
   * @return
   */
  public WSLogger getWsLogger() {
    return WSLogger.of(getClass().getDeclaringClass() == null ? getClass() : getClass().getDeclaringClass());
  }

  /**
   * Get SOAP 12 Port
   *
   * @param serviceUrl
   * @param serviceClass
   * @return
   */
  public PortType getSOAP12Port(final String serviceUrl, final Class<PortType> serviceClass) {
    final JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
    client.setAddress(serviceUrl);
    client.setServiceClass(serviceClass);
    SoapBindingConfiguration conf = new SoapBindingConfiguration();
    conf.setVersion(Soap12.getInstance());
    client.setBindingConfig(conf);
    LoggingFeature logFeature = new LoggingFeature();
    logFeature.setSender(new Slf4jCustomEventSender());
    client.getFeatures().add(logFeature);
    if (security.isPresent()) {
      client.getOutInterceptors().add(new WSS4JOutInterceptor(security.get()));
    }
    return serviceClass.cast(client.create());
  }
}

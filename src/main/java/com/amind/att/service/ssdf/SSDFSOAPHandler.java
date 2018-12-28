package com.amind.att.service.ssdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This is SOAP handler class for SSDF web service
 * @author 
 *
 */
public class SSDFSOAPHandler implements SOAPHandler<SOAPMessageContext> {

	public static final String CONVERSATION_ID = "conversationId";
	private static final String SOAP_ELEMENT_PSWD = "Password";
	private static final String SOAP_ELEMENT_USERNAME = "Username";
	private static final String SOAP_ELEMENT_USERNAME_TOKEN = "UsernameToken";
	private static final String SOAP_ELEMENT_SECURITY = "Security";
	private static final String NAMESPACE_SECURITY = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static final String PREFIX_SECURITY = "wsse";

	private String usernameText;
	private String keyText;

	private final Logger logger = LoggerFactory.getLogger(SSDFSOAPHandler.class);

	/**
	 * 
	 * @param usernameText
	 * @param keyText
	 */
	public SSDFSOAPHandler(String usernameText, String keyText) {
		this.usernameText = usernameText;
		this.keyText = keyText;
		
	}

    /**
     * Method to handle Message
     */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		saveConversationIdInResponseContext(context);
		return true;
	}

    /**
     * Method to handle fault
     */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		saveConversationIdInResponseContext(context);
		return true;
	}

	/** Note we are setting the conversation id on the response context!
	 * CXF guarantees this is thread local, meaning that this should be thread safe
	 * even if the client port/proxy is shared.
	 *
	 * @param context
	 */
	private void saveConversationIdInResponseContext(SOAPMessageContext context) {
		try {
			Boolean outBound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (outBound.booleanValue()) {

				 SOAPEnvelope soapEnvelope = context.getMessage().getSOAPPart().getEnvelope();
				 
				 SOAPHeader header = soapEnvelope.getHeader();
				 if (header == null) {
				 header = soapEnvelope.addHeader();
				 }

				SOAPElement soapElementSecurityHeader = header.addChildElement(SOAP_ELEMENT_SECURITY, PREFIX_SECURITY,
						NAMESPACE_SECURITY);

				SOAPElement soapElementUsernameToken = soapElementSecurityHeader.addChildElement(SOAP_ELEMENT_USERNAME_TOKEN, PREFIX_SECURITY);
				SOAPElement soapElementUsername = soapElementUsernameToken.addChildElement(SOAP_ELEMENT_USERNAME, PREFIX_SECURITY);
				soapElementUsername.addTextNode(this.usernameText);

				SOAPElement soapElementPassword = soapElementUsernameToken.addChildElement(SOAP_ELEMENT_PSWD, PREFIX_SECURITY);
				soapElementPassword.addTextNode(this.keyText);

					
		        String conversationId = fetchConversationId(context.getMessage().getSOAPHeader());
				
		        context.put(CONVERSATION_ID, conversationId);
				context.setScope(CONVERSATION_ID, MessageContext.Scope.APPLICATION);
			}
		} catch (SOAPException e) {
			logger.error("SOAPException :", e);
		}
		catch (RuntimeException e){
			logger.error("RuntimeException :", e);
		}
	}
	/**
	 * 
	 * @param header
	 * @return
	 */
	private String fetchConversationId(SOAPHeader header) {
		// Get Children of SOAPHeader
		Iterator<?> it = header.getChildElements();
		while (it.hasNext()) {
			Node node = (Node) it.next();
			if (node.getNodeName().toLowerCase().contains("MessageHeader".toLowerCase())) {
				NodeList nList = node.getChildNodes();
				for (int i = 0; i < nList.getLength(); i++) {
					org.w3c.dom.Node n = nList.item(i);
					if (n.getNodeName().toLowerCase().contains("TrackingMessageHeader".toLowerCase())) {
						NodeList nList1 = n.getChildNodes();
						for (int j = 0; j < nList1.getLength(); j++) {
							org.w3c.dom.Node n1 = nList1.item(j);
							if (n1.getNodeName().toLowerCase().contains("conversationId".toLowerCase())) {
								logger.info(n1.getNodeName() + "=" + n1.getTextContent());
								return n1.getTextContent();
							}
						}
					}
				}
			}
		}

		return "";
	}


	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
        Set<QName> qNameSet = new HashSet<>();
		return qNameSet;
	}
}
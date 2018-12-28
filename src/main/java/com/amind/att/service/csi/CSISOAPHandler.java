package com.amind.att.service.csi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Iterator;
import java.util.Set;

/**
 * CSI SOAP Handler
 */
public class CSISOAPHandler implements SOAPHandler<SOAPMessageContext> {

  public static final String CONVERSATION_ID = "conversationId";

  private final Logger logger = LoggerFactory.getLogger(CSISOAPHandler.class);

  @Override
  public boolean handleMessage(SOAPMessageContext context) {
    saveConversationIdInResponseContext(context);
    return true;
  }

  @Override
  public boolean handleFault(SOAPMessageContext context) {
    saveConversationIdInResponseContext(context);
    return true;
  }

  /**
   * Note we are setting the conversation id on the response context!
   * CXF guarantees this is thread local, meaning that this should be thread safe
   * even if the client port/proxy is shared.
   *
   * @param context
   */
  private void saveConversationIdInResponseContext(SOAPMessageContext context) {
    try {
      Boolean outBound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
      if (!outBound.booleanValue()) {    // If NOT OUTBOUND_MESSAGE (Webservice Response)
        String conversationId = fetchConversationId(context.getMessage().getSOAPHeader());
        context.put(CONVERSATION_ID, conversationId);
        context.setScope(CONVERSATION_ID, MessageContext.Scope.APPLICATION);
      }
    } catch (Exception e) {
      logger.error("Exception :", e);
    }
  }

  private String fetchConversationId(SOAPHeader header) {
    // Get Children of SOAPHeader
    Iterator it = header.getChildElements();
    while (it.hasNext()) {
      Node node = (Node) it.next();        // System.out.println(node.getNodeName());
      if (node.getNodeName().toLowerCase().contains("MessageHeader".toLowerCase())) {
        NodeList nList = node.getChildNodes();    // Get Children of <MessageHeader>
        for (int i = 0; i < nList.getLength(); i++) {
          org.w3c.dom.Node n = nList.item(i);        // System.out.println(n.getNodeName());
          if (n.getNodeName().toLowerCase().contains("TrackingMessageHeader".toLowerCase())) {
            NodeList nList1 = n.getChildNodes(); // Get Children of <TrackingMessageHeader>
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
    return null;
  }
}
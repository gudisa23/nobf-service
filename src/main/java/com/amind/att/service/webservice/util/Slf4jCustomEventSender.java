package com.amind.att.service.webservice.util;

import com.google.common.base.Strings;
import org.apache.cxf.ext.logging.event.EventType;
import org.apache.cxf.ext.logging.event.LogEvent;
import org.apache.cxf.ext.logging.event.LogEventSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.xml.namespace.QName;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  This class modifies LogEvents by adding additional Information to each Log Event which can be useful for developers and testers when looking
 *  through logs.
 */

public class Slf4jCustomEventSender implements LogEventSender {

    private static ThreadLocal<String> threadUserId = new ThreadLocal<>();

    public static void setThreadUserId(String userId){
        threadUserId.set(userId);
    }

    public Slf4jCustomEventSender() {
    }

    public void send(LogEvent event) {
        String cat = "org.apache.cxf.services." + event.getPortTypeName().getLocalPart() + "." + event.getType();
        Logger log = LoggerFactory.getLogger(cat);
        HashSet keys = new HashSet();
        boolean var11 = false;

        try {
            var11 = true;
            this.put(keys, "type", event.getType().toString());
            this.put(keys, "address", event.getAddress());
            this.put(keys, "content-type", event.getContentType());
            this.put(keys, "encoding", event.getEncoding());
            this.put(keys, "exchangeId", event.getExchangeId());
            this.put(keys, "httpMethod", event.getHttpMethod());
            this.put(keys, "messageId", event.getMessageId());
            this.put(keys, "responseCode", event.getResponseCode());
            this.put(keys, "serviceName", this.localPart(event.getServiceName()));
            this.put(keys, "portName", this.localPart(event.getPortName()));
            this.put(keys, "portTypeName", this.localPart(event.getPortTypeName()));
            if(event.getFullContentFile() != null) {
                this.put(keys, "fullContentFile", event.getFullContentFile().getAbsolutePath());
            }

            this.put(keys, "headers", event.getHeaders().toString());
            log.info(this.getLogMessage(event));
            var11 = false;
        } finally {
            if(var11) {
                Iterator i$1 = keys.iterator();

                while(i$1.hasNext()) {
                    String key1 = (String)i$1.next();
                    MDC.remove(key1);
                }

            }
        }

        Iterator i$ = keys.iterator();

        while(i$.hasNext()) {
            String key = (String)i$.next();
            MDC.remove(key);
        }
    }

    private String localPart(QName name) {
        return name == null?null:name.getLocalPart();
    }

    private String getLogMessage(LogEvent event) {
        StringBuilder sb = new StringBuilder();
        EventType eventType = event.getType();
        if(eventType == eventType.RESP_IN || eventType == eventType.REQ_IN || eventType == eventType.FAULT_IN){
            sb.append("Inbound Message\n----------------------------");
        }
        else if (eventType == eventType.RESP_OUT || eventType == eventType.REQ_OUT || eventType == eventType.FAULT_OUT) {
            sb.append("Outbound Message\n----------------------------");
        }
        sb.append("\nEvent-Type: " + event.getType());
        sb.append("\nMessage-ID: " + event.getMessageId());
        sb.append("\nExchange-ID: " + event.getExchangeId());
        if(!Strings.isNullOrEmpty(threadUserId.get())){
            sb.append("\nUser-ID: " + threadUserId.get());
        }
        if(event.getResponseCode() != null) {
            sb.append("\nResponse-Code: " + event.getResponseCode());
        }
        if(event.getAddress() != null) {
            sb.append("\nAddress: " + event.getAddress());
        }
        sb.append("\nEncoding: " + event.getEncoding());
        if(event.getHttpMethod() != null) {
            sb.append("\nHTTP-Method: " + event.getHttpMethod());
        }
        sb.append("\nContent-Type: " + event.getContentType());
        sb.append("\nHeaders: " + MDC.get("headers").toString());
        sb.append("\nService-Name: " + event.getServiceName());
        sb.append("\nPort-Type-Name: " + event.getPortTypeName());
        sb.append("\nOperation-Name: " + event.getOperationName());
        if(event.isBinaryContent()) {
            sb.append("\nBinary-Content: true");
        }
        if(event.isTruncated()) {
            sb.append("\nTruncated: true");
        }
        sb.append("\nPayload: " + maskSecurityTags(event.getPayload()));
        sb.append("\n--------------------------------------");
        String eventMessage = sb.toString();
        return eventMessage;
    }

    private void put(Set<String> keys, String key, String value) {
        if(value != null) {
            MDC.put(key, value);
            keys.add(key);
        }

    }

    /**
     * Cleans the logs payload from userName and password
     * Expected tags needs to be added as string comparision is used.
     * Runtime exception needs to be captured as execution should not stop.
     * @param payload  - the whole payload
     * @return - masked payload
     */
    private String maskSecurityTags(String payload){
        String result = "";
        try{
            if (payload != null && payload.length() > 0) {
                result = payload.replaceAll("<userName>([^<]*)</userName>", "<userName>XXX</userName>")
                        .replaceAll("<userPassword>([^<]*)</userPassword>", "<userPassword>XXXXX</userPassword>")
                        .replaceAll("<wsse:Password([^<]*)</wsse:Password>", "<wsse:Password>XXXXX</wsse:Password>")
                        .replaceAll("<wsse:Username>([^<]*)</wsse:Username>", "<wsse:Username>XXX</wsse:Username>");
            }
        } catch (RuntimeException exception){
            result = payload;
            LoggerFactory.getLogger(Slf4jCustomEventSender.class)
                    .error("Error masking the credentials::maskSecurityTags:: " + exception + ": message is :" + payload);
        }
        return result;
    }

}

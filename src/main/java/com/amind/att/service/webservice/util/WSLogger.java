package com.amind.att.service.webservice.util;

import com.amind.att.service.webservice.domain.IWebServiceRequest;
import com.amind.att.service.webservice.domain.IWebServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;

/**
 * WebService Logger Class
 * Utility to marshall and unmarshall webservice request and response xml
 */
public class WSLogger {

    /**
     * Of
     *
     * @param cls
     * @return
     */
    public static WSLogger of(final Class<?> cls) {
        return new WSLogger(LoggerFactory.getLogger(cls));
    }

    private static Logger localLog = LoggerFactory.getLogger(WSLogger.class);

    private final Logger log;

    private WSLogger(final Logger log) {
        this.log = log;
    }

    /**
     * Dump
     *
     * @param instance
     * @param <T>
     * @return
     */
    public <T extends IWebServiceRequest> WSLogger dump(final T instance) {
        if (!localLog.isDebugEnabled() || instance == null) {
            return this;
        }
        try {
            return dump(instance, JAXBContext.newInstance(instance.getClass().getSuperclass()));
        } catch (JAXBException e) {
            log.error("Exception while dumping " + instance.getClass(), e);
            return this;
        }
    }

    /**
     * Dump
     *
     * @param instance
     * @param <T>
     * @return
     */
    public <T extends IWebServiceResponse> WSLogger dump(final T instance) {
        if (!localLog.isDebugEnabled() || instance == null) {
            return this;
        }
        try {
            return dump(instance, JAXBContext.newInstance(instance.getClass().getSuperclass()));
        } catch (JAXBException e) {
            log.error("Exception while dumping " + instance.getClass(), e);
            return this;
        }
    }

    /**
     * Dump
     *
     * @param instance
     * @param <T>
     * @return
     */
    public <T> WSLogger dump(final T instance) {
        if (!localLog.isDebugEnabled() || instance == null) {
            return this;
        }
        try {
            log.debug("WSLogger#dump - Before creating JAXB Context");
            if (instance instanceof IWebServiceResponse || instance instanceof IWebServiceRequest) {
                return dump(instance, JAXBContext.newInstance(instance.getClass().getSuperclass()));
            } else {
                return dump(instance, JAXBContext.newInstance(instance.getClass()));
            }
        } catch (JAXBException e) {
            log.error("Exception while dumping " + instance.getClass(), e);
            return this;
        }
    }

    /**
     * Dump
     *
     * @param instance
     * @param context
     * @param <T>
     * @return
     */
    public <T> WSLogger dump(final T instance, final JAXBContext context) {
        if (!localLog.isDebugEnabled() || instance == null) {
            return this;
        }
        log.debug("WSLogger#dump - After creating JAXB Context");
        try {
            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            final StringWriter writer = new StringWriter();
            marshaller.marshal(new JAXBElement(new QName("uri", "local"), instance.getClass(), instance), writer);
            log.debug(writer.toString());
        } catch (final Exception e) {
            log.error("Exception while marshalling object " + instance.getClass(), e);
        }
        return this;
    }

    /**
     * Info
     *
     * @param message
     * @param arguments
     * @return
     */
    public WSLogger info(final String message, final Object... arguments) {
        log.info(String.format(message, arguments));
        return this;
    }

    /**
     * Error
     *
     * @param message
     * @param e
     * @return
     */
    public WSLogger error(final String message, final Exception e) {
        log.error(message, e);
        return this;
    }

    /**
     * Log
     *
     * @param message
     * @deprecated replaced by {@link #info(String, Object...)}
     */
    @Deprecated
    public void log(final String message) {
        info(message);
    }

    /**
     * Log XML
     *
     * @param request
     * @param <T>
     * @deprecated replaced by {@link #dump(Object)}
     */
    @Deprecated
    public <T> void logXML(final T request) {
        dump(request);
    }

    /**
     * Log Error
     *
     * @param message
     * @param e
     * @deprecated replaced by {@link #error(String, Exception)}
     */
    @Deprecated
    public void logError(final String message, final Exception e) {
        error(message, e);
    }

}

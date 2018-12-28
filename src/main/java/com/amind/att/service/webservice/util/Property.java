package com.amind.att.service.webservice.util;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Property
 */
public enum Property {

    ENVIRONMENT("environment"),

    MOBILE_FIRST("mobilefirst"),

    RTHREENICHE("rthreeniche"),

    SIEBEL("siebelcfg"),

    TSPACE("tspace"),

    LIFESAVER("life-saver"),

    CANARY("canary"),

    CSP("attcsp"),

    CSI("csi"),

    FTTB_RESP_CODE_TO_MESSAGE("FTTBRespCodeToMessage"),

    LOV_STUB("lov_stub"),

    SIEBEL_CONFIG("siebelcfg"),

    BUILD("build"),

    DATABASE("database"),

    SSDF("ssdfjson"),

    SSDF_WS("ssdf"),

    AUTH("auth"),

    EQT("eqt"),

    BOOST("boost"),

    OPUS("opus"),

    NETBOND("netbond"),

    GEOLINK("geolink"),

    EIH("eih");

    private final String name;
    private static final Logger logger = LoggerFactory.getLogger("Property Log");
    // Marking propertyPresent as transient because this enum is serializable and all variables need to be serializable or transient
    private final transient Predicate<String> propertyPresent = new Predicate<String>() {
        @Override
        public boolean apply(String key) {
            return find(key).isPresent();
        }
    };

    /**
     * Property
     *
     * @param name
     */
    Property(final String name) {
        this.name = name;
    }

    /**
     * Get
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return Bundle.get(name, key).orNull();
    }

    /**
     * Get URL
     *
     * @param key
     * @return
     */
    public URL getUrl(final String key) {
        try {
            return new URL(get(key));
        } catch (final MalformedURLException e) {
            logger.error("MalformedURLException: ", e);
            return null;
        }
    }

    /**
     * Find
     *
     * @param key
     * @return
     */
    public Optional<String> find(final String key) {
        return Bundle.get(name, key);
    }

    /**
     * Is Present
     *
     * @param firstKey
     * @param extraKeys
     * @return
     */
    public boolean isPresent(final String firstKey, final String... extraKeys) {
        final List<String> keys = Lists.asList(firstKey, extraKeys);
        final ImmutableList<String> filtered = FluentIterable.from(keys).filter(propertyPresent).toList();
        return filtered.equals(keys);
    }

    /**
     * Get File Name
     *
     * @return
     */
    public String getFileName() {
        return name;
    }

    /**
     * Get Boolean
     *
     * @param key
     * @return
     */
    public boolean getBoolean(final String key) {
        final Optional<String> optional = find(key);
        return optional.isPresent() ? Boolean.valueOf(optional.get()) : false;
    }

}

class Bundle {

    private static final Map<String, ResourceBundle> bundles = Maps.newHashMap();
    private static final Logger log = LoggerFactory.getLogger(Property.class);

    protected static Optional<String> get(final String name, final String key) {
        try {
            if (!bundles.containsKey(name))
                bundles.put(name, ResourceBundle.getBundle(name));
            return Optional.fromNullable(Strings.emptyToNull(bundles.get(name).getString(key)));
        } catch (final Exception e) {
            log.warn("Property \"{}\" not found in resource bundle \"{}\"", key, name, e);
            return Optional.absent();
        }
    }

}

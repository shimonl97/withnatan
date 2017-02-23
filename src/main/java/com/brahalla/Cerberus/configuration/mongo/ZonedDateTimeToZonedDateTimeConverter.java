package com.brahalla.Cerberus.configuration.mongo;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;


/**
 * Created by kfirf on 9/22/16.
 */
public class ZonedDateTimeToZonedDateTimeConverter implements Converter<ZonedDateTime, ZonedDateTime> {
    private final Logger log = Logger.getLogger(getClass());

    @Override
    public ZonedDateTime convert(ZonedDateTime zonedDateTime) {
        log.info("Convert from ZonedDateTime: " + zonedDateTime.toString() + " to ZonedDateTime: " + zonedDateTime.toString());
        return zonedDateTime;
    }

}

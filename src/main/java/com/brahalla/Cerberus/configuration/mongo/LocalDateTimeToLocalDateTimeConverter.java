package com.brahalla.Cerberus.configuration.mongo;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * Created by kfirf on 9/22/16.
 */
public class LocalDateTimeToLocalDateTimeConverter implements Converter<LocalDateTime, LocalDateTime> {
    private final Logger log = Logger.getLogger(getClass());

    @Override
    public LocalDateTime convert(LocalDateTime localDateTime) {
        log.info("Convert from LocalDateTime: " + localDateTime.toString() + " to LocalDateTime: " + localDateTime.toString());
        return localDateTime;
    }
}

package com.brahalla.Cerberus.configuration.mongo;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * Created by kfirf on 9/22/16.
 */
public class LocalDateToLocalDateConverter implements Converter<LocalDate, LocalDate> {
    private final Logger log = Logger.getLogger(getClass());

    @Override
    public LocalDate convert(LocalDate localDate) {
        log.info("Convert from localDate: " + localDate.toString() + " to localDate: " + localDate.toString());
        return localDate;
    }
}

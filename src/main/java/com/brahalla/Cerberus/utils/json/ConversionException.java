package com.brahalla.Cerberus.utils.json;

import java.io.IOException;

/**
 * Created by kfirf on 9/21/16.
 */
public class ConversionException extends RuntimeException {

    public ConversionException(IOException e) {
        super(e);
    }
}

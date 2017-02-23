package com.brahalla.Cerberus.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class JacksonConverter implements JsonConverter {
    private final ObjectMapper mapper =
            new ObjectMapper()
                    .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .registerModule(new JavaTimeModule())
                    .setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

    public <T> T fromJson(String source, Class<T> valueType) {
        try {
            return mapper.readValue(source, valueType);
        } catch (IOException e) {
            throw new ConversionException(e);
        }
    }

    public <T> String toJson(T source) {
        try {
            return mapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new ConversionException(e);
        }
    }
}
package com.brahalla.Cerberus.utils.json;


public interface JsonConverter {
    <T> T fromJson(String source, Class<T> type);

    <T> String toJson(T source);
}
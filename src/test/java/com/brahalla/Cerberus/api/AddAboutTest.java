package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.dbmodels.app.Page;
import com.brahalla.Cerberus.model.json.request.AddPageRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddAboutTest {

    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        AddPageRequest request = new AddPageRequest();
        request.setAppId("5816bfe110d0eb08c84f8a29");
        request.setPage(new Page("firstPage","Let's see if it works"));

        System.out.println(jsonConverter.toJson(request));
    }
}
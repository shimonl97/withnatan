package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.dbmodels.app.Page;
import com.brahalla.Cerberus.model.dbmodels.app.ServicePage;
import com.brahalla.Cerberus.model.json.request.AddPageRequest;
import com.brahalla.Cerberus.model.json.request.AddServicePageRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddServiceTest {

    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        AddServicePageRequest request = new AddServicePageRequest();
        request.setAppId("5816977110d0eb0504e1ad42");
        request.setPage(new ServicePage("ServicePage 1","Let's see if it workssss"));

        System.out.println(jsonConverter.toJson(request));
    }
}

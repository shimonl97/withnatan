package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.dbmodels.app.Extension;
import com.brahalla.Cerberus.model.dbmodels.app.ServicePage;
import com.brahalla.Cerberus.model.json.request.AddComRequest;
import com.brahalla.Cerberus.model.json.request.AddServicePageRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

/**
 * Created by mymac on 30/10/2016.
 */
public class AddCom {
    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        AddComRequest request = new AddComRequest();
        request.setAppId("5816977110d0eb0504e1ad42");
        Extension extension = new Extension();
        extension.setHolderName("Goland");
        extension.setName("Dani");
        extension.setPhone("0544678413");
        extension.setEmail("glossman@gmail.com");


        request.setExtension(extension);

        System.out.println(jsonConverter.toJson(request));
    }
}

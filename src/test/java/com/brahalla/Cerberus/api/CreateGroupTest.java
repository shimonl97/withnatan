package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.dbmodels.App;
import com.brahalla.Cerberus.model.json.request.CreateGroupRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

/**
 * Created by mymac on 01/11/2016.
 */
public class CreateGroupTest {

    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        CreateGroupRequest request = new CreateGroupRequest();
        request.setName("Dani's Group");
        request.setDescription("A nice group");

        System.out.println(jsonConverter.toJson(request));
    }
}

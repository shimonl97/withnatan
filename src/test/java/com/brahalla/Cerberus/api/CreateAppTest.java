package com.brahalla.Cerberus.api;

import com.amazonaws.services.opsworks.model.CreateAppRequest;
import com.brahalla.Cerberus.model.dbmodels.App;
import com.brahalla.Cerberus.model.dbmodels.app.AboutTile;
import com.brahalla.Cerberus.model.json.request.AddTileRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

/**
 * Created by mymac on 30/10/2016.
 */
public class CreateAppTest {

    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        App request = new App();
        request.setName("Aroma");
        request.setAddress("Tel Aviv");
        request.setPhone("0544678413");

        System.out.println(jsonConverter.toJson(request));
    }
}



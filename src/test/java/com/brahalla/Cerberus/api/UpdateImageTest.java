package com.brahalla.Cerberus.api;

import com.brahalla.Cerberus.model.dbmodels.app.Page;
import com.brahalla.Cerberus.model.json.request.AddPageRequest;
import com.brahalla.Cerberus.model.json.request.ImageUpdateRequest;
import com.brahalla.Cerberus.utils.json.JacksonConverter;
import com.brahalla.Cerberus.utils.json.JsonConverter;

/**
 * Created by mymac on 31/10/2016.
 */
public class UpdateImageTest {
    private static JsonConverter jsonConverter = new JacksonConverter();

    public static void main(String[] args) throws Exception {
        ImageUpdateRequest request = new ImageUpdateRequest();
        request.setAppId("5817c38a13de080c76b24863");
        request.setImage("https://cdn.productplan.com/wp-content/uploads/2016/03/Top-Down-Strategic-Planning.png");

        System.out.println(jsonConverter.toJson(request));
    }
}

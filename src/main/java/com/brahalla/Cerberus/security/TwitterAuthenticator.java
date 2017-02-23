package com.brahalla.Cerberus.security;

import com.brahalla.Cerberus.model.json.DigitsResponse;
import com.brahalla.Cerberus.model.json.request.AuthenticationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Me on 9/26/2016.
 */
@Component
public class TwitterAuthenticator {
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    public DigitsResponse authenticate(AuthenticationRequest request) throws IOException,RuntimeException{


            HttpGet getRequest = new HttpGet(request.getUsername());
            getRequest.addHeader("Authorization", request.getPassword());

            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                EntityUtils.consume(response.getEntity());

                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            DigitsResponse digitsResponse = mapper.readValue(response.getEntity().getContent(),DigitsResponse.class);

            System.out.println(digitsResponse.getPhoneNumber());
            EntityUtils.consume(response.getEntity());
            return digitsResponse;
/*
            httpClient.getConnectionManager().shutdown();
*/



    }

}

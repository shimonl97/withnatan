package com.brahalla.Cerberus.model.json;

/**
 * Created by Me on 9/26/2016.
 */
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "token",
        "secret"
})
public class AccessToken {

    @JsonProperty("token")
    private String token;
    @JsonProperty("secret")
    private String secret;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The token
     */
    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The secret
     */
    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    /**
     *
     * @param secret
     * The secret
     */
    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

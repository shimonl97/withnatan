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
        "phone_number",
        "access_token",
        "id_str",
        "verification_type",
        "id",
        "created_at"
})
public class DigitsResponse {

    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("access_token")
    private AccessToken accessToken;
    @JsonProperty("id_str")
    private String idStr;
    @JsonProperty("verification_type")
    private String verificationType;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The phoneNumber
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber The phone_number
     */
    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return The accessToken
     */
    @JsonProperty("access_token")
    public AccessToken getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken The access_token
     */
    @JsonProperty("access_token")
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return The idStr
     */
    @JsonProperty("id_str")
    public String getIdStr() {
        return idStr;
    }

    /**
     * @param idStr The id_str
     */
    @JsonProperty("id_str")
    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    /**
     * @return The verificationType
     */
    @JsonProperty("verification_type")
    public String getVerificationType() {
        return verificationType;
    }

    /**
     * @param verificationType The verification_type
     */
    @JsonProperty("verification_type")
    public void setVerificationType(String verificationType) {
        this.verificationType = verificationType;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The createdAt
     */
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

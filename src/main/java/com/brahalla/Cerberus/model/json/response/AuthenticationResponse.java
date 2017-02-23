package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.base.ModelBase;

public class AuthenticationResponse extends ModelBase {

    private static final long serialVersionUID = -6624726180748515507L;
    private String token;
    private String exists;
    private String name;
    private String nickName;
    private String image;


    public AuthenticationResponse() {
        super();
    }

    public AuthenticationResponse(String token) {
        this.setToken(token);
    }

    public AuthenticationResponse(String token, String exists) {
        this.token = token;
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AuthenticationResponse(String token, String exists, String name, String nickName, String image) {

        this.token = token;
        this.exists = exists;
        this.name = name;
        this.nickName = nickName;
        this.image = image;
    }

    public String getExists() {
        return exists;
    }

    public void setExists(String exists) {
        this.exists = exists;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

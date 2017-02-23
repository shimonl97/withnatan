package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.base.ModelBase;

public class AuthenticationRequest extends ModelBase {

    private static final long serialVersionUID = 6624726180748515507L;
    private String username;
    private String password;
    private String name=null;
    private String nickName = null;
    private String photo=null;
    public AuthenticationRequest() {
        super();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public AuthenticationRequest(String username, String password, String name, String photo, String nickName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.nickName = nickName;

        this.photo = photo;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

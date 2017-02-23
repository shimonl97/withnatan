package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.Conversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public class MyChatsResponse {

    private List<Conversation> chats = new ArrayList<>();

    public MyChatsResponse(List<Conversation> chats) {
        this.chats = chats;
    }

    public List<Conversation> getChats() {
        return chats;
    }

    public void setChats(List<Conversation> chats) {
        this.chats = chats;
    }
}


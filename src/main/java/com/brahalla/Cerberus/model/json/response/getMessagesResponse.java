package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.json.request.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public class getMessagesResponse {
    private List<ChatMessage> messages = new ArrayList<>();

    public getMessagesResponse(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public getMessagesResponse() {
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}

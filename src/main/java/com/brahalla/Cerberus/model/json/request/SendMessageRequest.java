package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/29/2016.
 */
public class SendMessageRequest {
    private String conversationId = null;
    private String to;
    private String message;

    public SendMessageRequest(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public SendMessageRequest(String conversationId, String to, String message) {
        this.conversationId = conversationId;
        this.to = to;
        this.message = message;
    }

    public SendMessageRequest() {
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

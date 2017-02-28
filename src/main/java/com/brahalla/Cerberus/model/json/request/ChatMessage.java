package com.brahalla.Cerberus.model.json.request;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created by Me on 9/27/2016.
 */

public class ChatMessage {
    @Id
    private String id;
    @Indexed
    private String conversationId;
    private String from;
    private String to;
    private String message;
    private String pic="";
    @Indexed
    private Date created;


    public ChatMessage() {
    }

    public ChatMessage(String conversationId, String from, String to, String message, Date created) {
        this.conversationId = conversationId;
        this.from = from;
        this.to = to;
        this.message = message;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
    
}

package com.brahalla.Cerberus.model.dbmodels;

import com.brahalla.Cerberus.model.json.request.ChatMessage;
import com.brahalla.Cerberus.model.user.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public class Conversation {
    @Id
    private String id;
    @Indexed
    private List<String> members = new ArrayList<>();
    
    @Indexed
    private Date updated;
    
    @Transient
    private User otherUser;
    
    @Transient
    private Boolean newExists;
    
    private List<String> newForMembers = new ArrayList<>();

    public Conversation(List<String> members) {
        this.members = members;
    }

    public Conversation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }
    

    public void setMembers(List<String> members) {
        this.members = members;
    }

	public List<String> getNewForMembers() {
		return newForMembers;
	}

	public void setNewForMembers(List<String> newForMembers) {
		this.newForMembers = newForMembers;
	}

	public void setIsNew(String userId) {
		setNewExists(getNewForMembers()!=null && getNewForMembers().contains(userId));
	}

	@Transient
	public Boolean getNewExists() {
		return newExists;
	}

	public void setNewExists(Boolean newExists) {
		this.newExists = newExists;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public User getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}
    
    
}

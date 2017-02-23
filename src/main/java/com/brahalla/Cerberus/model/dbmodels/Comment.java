package com.brahalla.Cerberus.model.dbmodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import com.brahalla.Cerberus.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/26/2016.
 */
public class Comment {
    private String commentId;
    private String userId;
    private String body;
    private String image;
    private Date created;
    private List<Report> reports;
    @Transient
    private User user;

    public Comment(String commentId, String userId, String body, Date created) {
        this.commentId = commentId;
        this.userId = userId;
        this.body = body;
        this.created = created;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Transient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonIgnore
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
    
}

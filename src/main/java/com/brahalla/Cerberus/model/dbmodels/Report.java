package com.brahalla.Cerberus.model.dbmodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.brahalla.Cerberus.model.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/26/2016.
 */
@Document
public class Report {
	
    @Id
    private String id;
    
    @Indexed
    private String groupId;
    @Indexed
    private String userId;
    @Indexed
    private String postId;
    @Indexed
    private String commentId;
    @Indexed
    private String reporterId;
    
    @Transient
    private User user;
    @Transient
    private Group group;
    @Transient
    private Post post;
    @Transient
    private Comment comment;
    @Transient
    private User reporter;

	private Date created;
    

    public Report() {
    }

	public Report(String id, String groupId, String userId, String postId, String commentId, String reporterId) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.userId = userId;
		this.postId = postId;
		this.commentId = commentId;
		this.reporterId = reporterId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public void setCreated(Date created) {
		this.created=created;
		
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public Date getCreated() {
		return created;
	}
	

    
    
}

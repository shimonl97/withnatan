package com.brahalla.Cerberus.model.dbmodels;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.brahalla.Cerberus.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/26/2016.
 */
@Document
public class Post {
	
    @Id
    private String id;

    @Indexed
    private String groupId;
    @Indexed
    private String userId;
    private String description;
    private String image;
    private int likes = 0;
    private List<Comment> comments = new ArrayList<>();
    private List<String> likers = new ArrayList<>();
    private Date created;
    private List<Report> reports;
    @Transient
    private User user;
    @Transient 
    private boolean liked;

    public Post() {
    }

    public Post(String id, String groupId, String userId, String description, String image, int likes, List<Comment> comments, Date created) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
        this.description = description;
        this.image = image;
        this.likes = likes;
        this.comments = comments;
        this.created = created;
    }


    public Post(String groupId, String description, String image) {
        this.groupId = groupId;
        this.description = description;
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    
    @Transient
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean like(String userId){
        if(!likers.contains(userId)){
            likers.add(userId);
            likes++;
            return true;

        }
        return false;

    }

    public boolean unlike(String userId){
        if(likers.contains(userId)){
            likers.remove(userId);
            likes--;
            return true;
        }
        return false;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
    public void removeComment(String commentId,String userId) throws Exception{
        for(Comment comment : comments){
            if(comment.getCommentId().equals(commentId)){
                if(comment.getUserId().equals(userId)) {
                    comments.remove(comment);
                }
                else throw new Exception("Unauthorized");

                return;
            }
        }
    }
    
    public boolean isLiked(String id) {
    	boolean liked=false;
    	if (likers!=null) {
	    	for (String liker:likers) {
	    		liked=id.equals(liker);
	    		if (liked) {
	    			break;
	    		}
	    	}
    	}
    	return liked;
    }

    @Transient
	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@JsonIgnore
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
    
    
}

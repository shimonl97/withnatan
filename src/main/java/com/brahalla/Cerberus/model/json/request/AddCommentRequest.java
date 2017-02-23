package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/29/2016.
 */
public class AddCommentRequest {
    private String body;
    private String postId;
    private String image;

    public AddCommentRequest(String body) {
        this.body = body;
    }

    public AddCommentRequest() {
    }

    public String getBody() {
        return body;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setBody(String body) {
        this.body = body;
    }

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
    
}

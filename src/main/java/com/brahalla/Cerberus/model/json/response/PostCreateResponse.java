package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.Post;

/**
 * Created by Me on 9/26/2016.
 */
public class PostCreateResponse {
    private Post post;

	public PostCreateResponse(Post post) {
		super();
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

    
}

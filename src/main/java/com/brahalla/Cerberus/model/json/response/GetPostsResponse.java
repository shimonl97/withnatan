package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
 */
public class GetPostsResponse {

    private List<Post> posts = new ArrayList<>();

    public GetPostsResponse(List<Post> posts) {
        this.posts = posts;
    }

    public GetPostsResponse() {
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}

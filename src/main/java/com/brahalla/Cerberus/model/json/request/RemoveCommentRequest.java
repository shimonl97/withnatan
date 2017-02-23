package com.brahalla.Cerberus.model.json.request;

/**
 * Created by Me on 9/29/2016.
 */
public class RemoveCommentRequest {

    private String commentId;
    private String postId;

    public RemoveCommentRequest() {



    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}

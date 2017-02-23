package com.brahalla.Cerberus.model.json.response;

import com.brahalla.Cerberus.model.dbmodels.Comment;

/**
 * Created by Me on 9/29/2016.
 */
public class AddCommentResponse {

    private Comment comment;

    public AddCommentResponse(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}

package com.brahalla.Cerberus.model.json.request;

import com.brahalla.Cerberus.model.dbmodels.Post;

/**
 * Created by Me on 9/29/2016.
 */
public class GroupNotification {

    private String groupId;
    private String groupName;
    private Post post;

    public GroupNotification(String groupId, Post post,String groupName) {
        this.groupId = groupId;
        this.post = post;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

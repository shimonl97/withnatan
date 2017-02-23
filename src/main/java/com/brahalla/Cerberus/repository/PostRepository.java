package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.json.request.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Me on 9/26/2016.
 */
public interface PostRepository extends MongoRepository<Post, String> {
    Page<Post> findByGroupId(String groupId, Pageable page);

}

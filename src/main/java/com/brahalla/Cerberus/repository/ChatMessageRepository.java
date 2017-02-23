package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.json.request.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Me on 9/29/2016.
 */
public interface ChatMessageRepository extends MongoRepository<ChatMessage,String> {
    Page<ChatMessage> findByConversationId(String conversationId,Pageable page);
}

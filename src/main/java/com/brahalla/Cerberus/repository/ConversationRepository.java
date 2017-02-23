package com.brahalla.Cerberus.repository;

import com.brahalla.Cerberus.model.dbmodels.Conversation;
import com.brahalla.Cerberus.model.json.request.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Me on 9/27/2016.
 */
public interface ConversationRepository extends MongoRepository<Conversation,String> {
    List<Conversation> findByMembersIn(String member);
    List<Conversation> findByMembersIn(String[] members);

}

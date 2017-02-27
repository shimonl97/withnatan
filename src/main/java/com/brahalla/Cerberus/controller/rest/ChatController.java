package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.dbmodels.Conversation;
import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.json.request.BasicRequest;
import com.brahalla.Cerberus.model.json.request.ChatMessage;
import com.brahalla.Cerberus.model.json.request.GetMessagesRequest;
import com.brahalla.Cerberus.model.json.request.SendMessageRequest;
import com.brahalla.Cerberus.model.json.response.MyChatsResponse;
import com.brahalla.Cerberus.model.json.response.PostCreateResponse;
import com.brahalla.Cerberus.model.json.response.getMessagesResponse;
import com.brahalla.Cerberus.repository.ChatMessageRepository;
import com.brahalla.Cerberus.repository.ConversationRepository;
import com.brahalla.Cerberus.repository.PostRepository;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import com.brahalla.Cerberus.service.ImageUploadService;
import com.brahalla.Cerberus.service.PushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/29/2016.
*/

@RestController
@RequestMapping("${cerberus.route.chat}")
public class ChatController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Value("${aws.bucket.url}")
    private String bucketUrl;
    @Autowired
    private PushService pushService;

    private final int PAGE_SIZE = 10;

    @RequestMapping(path = "getChats", method = RequestMethod.POST)
    public ResponseEntity<?> getChats() {
        String userId = CerberusUserContext.currentUserDetails().getId();
        List<Conversation> conversations = conversationRepository.findByMembersIn(userId);
        conversations.forEach(c->c.setIsNew(userId));
        return ResponseEntity.ok(new MyChatsResponse(conversations));
    }

    @RequestMapping(path = "getMessages", method = RequestMethod.POST)
    public ResponseEntity<?> getMessages(@RequestBody GetMessagesRequest request) {
        List<ChatMessage> messageList =  new ArrayList<>();
        String otherUserId = request.getId();
        String userId = CerberusUserContext.currentUserDetails().getId();
        List<Conversation> conversations = conversationRepository.findByMembersIn(new String[]{userId,otherUserId});
        if (conversations!=null && !conversations.isEmpty()) {
            Conversation conversation=conversations.get(0);
            
            List<String> newMessages = conversation.getNewForMembers();
            if (newMessages!=null) {
            	if(newMessages.remove(userId)) {
            		conversationRepository.save(conversation);
            	}
            }
	        Page<ChatMessage> messages = chatMessageRepository.findByConversationId(conversation.getId(),new PageRequest(request.getIndex()/PAGE_SIZE,PAGE_SIZE, Sort.Direction.DESC,"created"));
	        for (int i=messages.getNumberOfElements()-1;i>=0;i--) {
	        	messageList.add(messages.getContent().get(i));
	        }
        }
	
	        return ResponseEntity.ok(new getMessagesResponse(messageList));
    }
    @RequestMapping(path = "sendMessage", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody SendMessageRequest request) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        Conversation conversation;
        String to = request.getTo();
		if(request.getConversationId()==null){
            conversation = new Conversation(Arrays.asList(new String[]{userId,to}));
            conversation = conversationRepository.save(conversation);

        }
        else {
            conversation = conversationRepository.findOne(request.getConversationId());
        }
        List<String> newForMembers = conversation.getNewForMembers();
        if (newForMembers==null) {
        	newForMembers = new ArrayList<>();
        }
        if (!newForMembers.contains(to)) {
        	newForMembers.add(to);
        	conversation = conversationRepository.save(conversation);
        }
        ChatMessage data = new ChatMessage(conversation.getId(),userId,to,request.getMessage(),new Date());

        try {
            pushService.sendToUser(data);
            chatMessageRepository.save(data);
            if(request.getPic().equals("")){
                String fileName = "chat/"+conversation.getId()+"/"+data.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,request.getPic());
                data.setPic(bucketUrl+fileName+".png");
                chatMessageRepository.save(data);
            }
            return ResponseEntity.ok(data);

        }
        catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.ok("Message not sent");

        }
    }


}


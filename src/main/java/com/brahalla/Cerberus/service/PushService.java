package com.brahalla.Cerberus.service;

import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.json.request.ChatMessage;
import com.brahalla.Cerberus.model.json.request.GroupNotification;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.push.gcm.exception.GcmMulticastLimitExceededException;
import com.brahalla.Cerberus.push.gcm.model.impl.GcmPushImpl;
import com.brahalla.Cerberus.push.gcm.pushraven.FcmResponse;
import com.brahalla.Cerberus.push.gcm.vo.GcmPushInfo;
import com.brahalla.Cerberus.push.model.impl.ApnsPushImpl;
import com.brahalla.Cerberus.repository.GroupRepository;
import com.brahalla.Cerberus.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Me on 9/27/2016.
 */
@Component
public class PushService {

    @Autowired
    GcmPushImpl gcmPush;

    @Autowired
    ApnsPushImpl apnsPush;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    ObjectMapper mapper = new ObjectMapper();

    public void sendToGroup(Post post)throws GcmMulticastLimitExceededException,IOException,JSONException,CommunicationException,KeystoreException{
        List<String> apnsTokens = new ArrayList<>();
        List<String> gcmTokens = new ArrayList<>();
        Group group = groupRepository.findOne(post.getGroupId());
        GroupNotification data = new GroupNotification(group.getId(),post,group.getName());
        List<User> users = userRepository.findByIdIn(group.getMembers());
        if(group.isAnon()){
            post.setUserId(null);
        }
        for(User user:users){
            if(user.getGcmToken()!=null){
                gcmTokens.add(user.getGcmToken());
            }
            if(user.getApnToken()!=null){
                apnsTokens.add(user.getApnToken());
            }
        }
        if(gcmTokens.size()>0){
            GcmPushInfo info = new GcmPushInfo();
            info.setRegIdList(gcmTokens);
            info.setData(mapper.writeValueAsString(data));
            gcmPush.sendPush(info);
        }

        if(apnsTokens.size()>0){
            apnsPush.sendPayload(new PushNotificationPayload(mapper.writeValueAsString(data)),apnsTokens);
        }
    }
    public void sendToUser(ChatMessage data)throws GcmMulticastLimitExceededException,IOException,JSONException,CommunicationException,KeystoreException{
        List<String> apnsTokens = new ArrayList<>();
        List<String> gcmTokens = new ArrayList<>();
        User user = userRepository.findOne(data.getTo());
            if(user.getGcmToken()!=null){
                gcmTokens.add(user.getGcmToken());
            }
            if(user.getApnToken()!=null){
                apnsTokens.add(user.getApnToken());
            }

        if(gcmTokens.size()>0){
            GcmPushInfo info = new GcmPushInfo();
            info.setRegIdList(gcmTokens);
            info.setData(mapper.writeValueAsString(data));
            FcmResponse response = gcmPush.sendPush(info);
            System.out.println(response.toString());

        }

        if(apnsTokens.size()>0){
            apnsPush.sendPayload(new PushNotificationPayload(mapper.writeValueAsString(data)),apnsTokens);
        }
    }
}

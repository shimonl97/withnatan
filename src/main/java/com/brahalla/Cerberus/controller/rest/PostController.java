package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.dbmodels.Comment;
import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.json.request.AddCommentRequest;
import com.brahalla.Cerberus.model.json.request.BasicRequest;
import com.brahalla.Cerberus.model.json.request.RemoveCommentRequest;
import com.brahalla.Cerberus.model.json.request.SetPushTokenRequest;
import com.brahalla.Cerberus.model.json.response.AddCommentResponse;
import com.brahalla.Cerberus.model.json.response.PostCreateResponse;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.GroupRepository;
import com.brahalla.Cerberus.repository.PostRepository;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import com.brahalla.Cerberus.security.TokenUtils;
import com.brahalla.Cerberus.service.ImageUploadService;
import com.brahalla.Cerberus.service.PushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.MultiServerUserRegistry;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("${cerberus.route.post}")
public class PostController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PushService pushService;
    @Autowired
    private ImageUploadService imageUploadService;
    @Value("${aws.bucket.url}")
    private String bucketUrl;
    @RequestMapping(path = "createPost", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        post.setUserId(userId);
        post.setCreated(new Date());
        Group group = groupRepository.findOne(post.getGroupId());
        if(!group.getMembers().contains(userId)){
            return ResponseEntity.ok("User is not in this group");
        }
        Post addedPost = postRepository.save(post);
        addedPost.setUser(userRepository.findOne(addedPost.getUserId()));
        if(post.getImage()!=null){
            imageUploadService.UploadObjectSingleOperation("posts/"+addedPost.getId()+".png",post.getImage());
            post.setImage(bucketUrl+"posts/"+addedPost.getId()+".png");
            postRepository.save(post);
        }
        try {
            pushService.sendToGroup(addedPost);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        
        return ResponseEntity.ok(new PostCreateResponse(addedPost));
    }

    @RequestMapping(path = "like", method = RequestMethod.POST)
    public ResponseEntity<?> likePost(@RequestBody BasicRequest request) {

        String postId = request.getId();
        Post post =postRepository.findOne(postId);
        Group group = groupRepository.findOne(post.getGroupId());
        if(!group.getMembers().contains(CerberusUserContext.currentUserDetails().getId())){
            return ResponseEntity.ok("User is not in this group");
        }
        if(post!=null){
            boolean success = post.like(CerberusUserContext.currentUserDetails().getId());
            if(success) {
                postRepository.save(post);
                return ResponseEntity.ok("Success");
            }
            else {
                return ResponseEntity.ok("User did not like this post");

            }
        }
        return ResponseEntity.ok("Post not found");
    }
    @RequestMapping(path = "unlike", method = RequestMethod.POST)
    public ResponseEntity<?> unlikePost(@RequestBody BasicRequest request) {
        String postId = request.getId();
        Post post =postRepository.findOne(postId);
        if(post!=null){
            boolean success = post.unlike(CerberusUserContext.currentUserDetails().getId());
            if(success) {
                postRepository.save(post);

                return ResponseEntity.ok("Success");
            }
            else {
                return ResponseEntity.ok("User did not like this post");

            }
        }
        return ResponseEntity.ok("Post not " +
                "found");
    }
    @RequestMapping(path = "addComment", method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        String postId = request.getPostId();
        Post post =postRepository.findOne(postId);
        Group group = groupRepository.findOne(post.getGroupId());
        if(!group.getMembers().contains(CerberusUserContext.currentUserDetails().getId())){
            return ResponseEntity.ok("User is not in this group");
        }
        if(post!=null){
            Comment comment = new Comment(UUID.randomUUID().toString(),CerberusUserContext.currentUserDetails().getId(),request.getBody(),new Date());
            post.addComment(comment);

                postRepository.save(post);
                if(request.getImage()!=null){
                    imageUploadService.UploadObjectSingleOperation("comments/"+comment.getCommentId()+".png",request.getImage());
                    comment.setImage(bucketUrl+"comments/"+comment.getCommentId()+".png");
                    postRepository.save(post);
                } else {
                	comment.setImage("");
                }
                comment.setUser(userRepository.findOne(comment.getUserId()));
                return ResponseEntity.ok(new AddCommentResponse(comment));

        }
        return ResponseEntity.ok("Post not found");
    }
    @RequestMapping(path = "removeComment", method = RequestMethod.POST)
    public ResponseEntity<?> removeComment(@RequestBody RemoveCommentRequest request) {
        String commentId = request.getCommentId();
        String postId = request.getPostId();
        Post post =postRepository.findOne(postId);
        if(post!=null){
            try {
                post.removeComment(commentId, CerberusUserContext.currentUserDetails().getId());
                postRepository.save(post);

            }catch (Exception e){
                return ResponseEntity.ok(e.getMessage());
            }


            return ResponseEntity.ok("Success");

        }
        return ResponseEntity.ok("Post not found");
    }


}

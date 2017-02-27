package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.base.BaseResponse;
import com.brahalla.Cerberus.model.dbmodels.App;
import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.json.request.BasicRequest;
import com.brahalla.Cerberus.model.json.request.CreateGroupRequest;
import com.brahalla.Cerberus.model.json.request.GetMessagesRequest;
import com.brahalla.Cerberus.model.json.request.GroupsNearbyRequest;
import com.brahalla.Cerberus.model.json.request.SearchRequest;
import com.brahalla.Cerberus.model.json.request.UserToGroupRequest;
import com.brahalla.Cerberus.model.json.response.CreateGroupResponse;
import com.brahalla.Cerberus.model.json.response.GetGroupsResponse;
import com.brahalla.Cerberus.model.json.response.GetPostsResponse;
import com.brahalla.Cerberus.model.json.response.PostCreateResponse;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.AppRepository;
import com.brahalla.Cerberus.repository.GroupRepository;
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
import org.springframework.data.domain.Range;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Me on 9/29/2016.
 */
@RestController
@RequestMapping("${cerberus.route.group}")
public class GroupController {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ImageUploadService imageUploadService;
    @Autowired
    PostRepository postRepository;
    private final int PAGE_SIZE = 10;
    private final int DISTANCE_SEARCH = 20;
    @Value("${aws.bucket.url}")
    private String bucketUrl;


    @RequestMapping(path = "createGroup", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = new Group();
            group.setAnon(request.isAnon());
            group.setDescription(request.getDescription());
            group.setGroupAdmin(userId);
            group.setLocation(new GeoJsonPoint(request.getLat(), request.getLon()));
            group.addMember(userId);
            group.setPrivate(request.isPrivate());
            group.setName(request.getName());
            group.setAddress(request.getAddress());
           	group.setBizGroup(request.isBizGroup());
            group = groupRepository.save(group);
            group.setCoverImage(request.getCoverImage());
            group.setForUser(userId);

            if(request.getProfileImage()!=null){
                imageUploadService.UploadObjectSingleOperation("group/profile/"+group.getId()+".png",request.getProfileImage());
                group.setProfileImage(bucketUrl+"group/profile/"+group.getId()+".png");
            }
            if(request.getCoverImage()!=null){
                imageUploadService.UploadObjectSingleOperation("group/cover/"+group.getId()+".png",request.getProfileImage());
                group.setCoverImage(bucketUrl+"group/cover/"+group.getId()+".png");
            }
            if(request.getProfileImage()!=null || request.getCoverImage()!=null){
                group = groupRepository.save(group);

            }
            if (group.isBizGroup()) {
            	group.setApp(new App());
            	groupRepository.save(group);
            }
            if (request.getApp()!=null) {
            	App app = request.getApp();
	            if(!app.getCoverImageUrl().equals("")){
	                imageUploadService.UploadObjectSingleOperation("card/cover/"+group.getId(),app.getCoverImageUrl());
	                app.setCoverImageUrl(bucketUrl+"card/cover/"+group.getId()+".png");
	                groupRepository.save(group);
	            }
	            if(!app.getLogoImageUrl().equals("")){
	                imageUploadService.UploadObjectSingleOperation("card/logo/"+group.getId(),app.getLogoImageUrl());
	                app.setLogoImageUrl(bucketUrl+"card/logo/"+group.getId()+".png");
	                groupRepository.save(group);
	            }
            }
            return ResponseEntity.ok(new BaseResponse("1",new CreateGroupResponse(group)));

        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("0",null));
        }
    }
    @RequestMapping(path = "leaveGroup", method = RequestMethod.POST)
    public ResponseEntity<?> leaveGroup(@RequestBody UserToGroupRequest request) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        Group group = groupRepository.findOne(request.getGroupId());
	    boolean isAdmin = userId.equals(group.getGroupAdmin());
    	if (request.getUserId()!=null && isAdmin) {
    		userId=request.getUserId();
    	}
		
        group.removeMember(userId);
        groupRepository.save(group);
        return ResponseEntity.ok("User leaved the group");
    }
    @RequestMapping(path = "getPosts", method = RequestMethod.POST)
    public ResponseEntity<?> getPosts(@RequestBody GetMessagesRequest request) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        String groupId = request.getId();
        Group group = groupRepository.findOne(groupId);
        /*if(!group.getMembers().contains(userId)){
            return ResponseEntity.ok("User is not in this group");
        }*/
        int index = request.getIndex();
        Page<Post> postPage = postRepository.findByGroupId(groupId,new PageRequest(index/PAGE_SIZE,PAGE_SIZE, Sort.Direction.DESC,"created"));
        List<Post> posts = new ArrayList<>();
        postPage.forEach(post ->
                {
                	post.setLiked(post.isLiked(userId));
                	if(post.getImage()==null) post.setImage("");
                	post.setUser(userRepository.findOne(post.getUserId()));
                	posts.add(post);if (!group.isAnon()) post.getUser().setNickName("");
                	if (post.getComments()!=null) {
                		post.getComments().forEach(comment->comment.setUser(userRepository.findOne(comment.getUserId())));
                	}
                });
        Collections.sort(posts, new Comparator<Post>(
        		) {

					@Override
					public int compare(Post o1, Post o2) {
						return o1.getCreated().compareTo(o2.getCreated());
					}
		});
        return ResponseEntity.ok(new GetPostsResponse(posts));
    }
    @RequestMapping(path = "getGroups", method = RequestMethod.POST)
    public ResponseEntity<?> getGroups() {
        String userId = CerberusUserContext.currentUserDetails().getId();
        List<Group> groups = groupRepository.findByMembersIn(userId);
        groups.forEach(g->{g.setForUser(userId);if (!g.isAdmin()) g.setMembers(null);});
        return ResponseEntity.ok(new GetGroupsResponse(groups));
    }
    
    @RequestMapping(path = "search", method = RequestMethod.POST)
    public ResponseEntity<?> search(@RequestBody  SearchRequest request) {
    	String userId = CerberusUserContext.currentUserDetails().getId();
    	String pattern = request.getPattern();
		List<Group> groups = getMatchingGroups(userId, pattern);
		groups.forEach(g->{g.setForUser(userId);if (!g.isAdmin()) g.setMembers(null);});
		List<User> users = getMatchingUsers(userId, pattern);
		Map<String,Object> m = new HashMap<>();
		m.put("groups", groups);
		m.put("users", users);
        return ResponseEntity.ok(m);
    }
	private List<Group> getMatchingGroups(String userId, String pattern) {
		Page<Group> matchingGroups =  groupRepository.findBy(TextCriteria.forDefaultLanguage().matching(pattern), new PageRequest(0,PAGE_SIZE));
    	List<Group> myGroups = matchingGroups.getContent().stream()
     		   .filter(p -> p.getMembers().contains(userId))
     		   .collect(Collectors.toList());
    	List<Group> otherGroups = matchingGroups.getContent().stream()
      		   .filter(p -> !p.getMembers().contains(userId))
      		   .collect(Collectors.toList());
        List<Group> groups = myGroups;
        groups.addAll(otherGroups);
		return groups;
	}
	
	private List<User> getMatchingUsers(String userId, String pattern) {
		Page<User> matchingUsers =  userRepository.findBy(TextCriteria.forDefaultLanguage().matching(pattern), new PageRequest(0,PAGE_SIZE));
    	/*List<User> myGroups = matchingUsers.getContent().stream()
     		   .filter(p -> p.getMembers().contains(userId))
     		   .collect(Collectors.toList());
    	List<User> otherGroups = matchingUsers.getContent().stream()
      		   .filter(p -> !p.getMembers().contains(userId))
      		   .collect(Collectors.toList());*/
        List<User> users = matchingUsers.getContent();
        //groups.addAll(otherGroups);
		return users;
	}
    @RequestMapping(path = "getGroupsNearby", method = RequestMethod.POST)
    public ResponseEntity<?> getGroupsNearby(@RequestBody  GroupsNearbyRequest request) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        List<Group> groups = new ArrayList<>();
        Double maxDistanceRetrieved = 0d;
       if(request.getLat()!=null && request.getLon()!=null) {
    	   Double minDistance = request.getMinDistance()==null?0:request.getMinDistance();
    	   Double maxDistance= request.getMaxDistance()==null?DISTANCE_SEARCH:request.getMaxDistance();
    	   Range<Distance> range = Distance.between(new Distance(minDistance, Metrics.KILOMETERS), new Distance(maxDistance, Metrics.KILOMETERS));
    	   GeoResults<Group> groupsWithDistance=groupRepository.findTop30ByLocationNear(new Point(request.getLat(), request.getLon()), range);
    	   for (GeoResult<Group> groupWithDistance:groupsWithDistance) {
    		   double distanceValue = groupWithDistance.getDistance().getValue();
			groupWithDistance.getContent().setDistance(distanceValue);
    		   groups.add(groupWithDistance.getContent());
    		   if (maxDistanceRetrieved<distanceValue) {
    			   maxDistanceRetrieved=distanceValue;
    		   }
    	   }
        }
        else {
            Page<Group> page = groupRepository.findAll(new PageRequest(0,PAGE_SIZE*3));
            groups = page.getContent();

        }
       groups = groups.stream()
    		   .filter(p -> !p.getMembers().contains(userId) && !p.isPrivate())
    		   .collect(Collectors.toList());
       groups.forEach(g->{g.setForUser(userId);if (!g.isAdmin()) g.setMembers(null);});
       	Map<String,Object> groupWithMaxDistance=new HashMap<>();
       	groupWithMaxDistance.put("groups", groups);
      	groupWithMaxDistance.put("maxDistance", maxDistanceRetrieved);
      	       	
        return ResponseEntity.ok(groupWithMaxDistance);

    }
    @RequestMapping(path = "getGroupsByName", method = RequestMethod.POST)
    public ResponseEntity<?> getGroupsByName(@RequestBody GetMessagesRequest request) {
    	String userId = CerberusUserContext.currentUserDetails().getId();
        String groupName = request.getId();
        int index = request.getIndex();
        Page<Group> groupPage = groupRepository.findByNameStartsWith(groupName,new PageRequest(index/PAGE_SIZE,PAGE_SIZE));
        List<Group> groups = new ArrayList<>();
        groupPage.forEach(post ->
                groups.add(post));
        groups.forEach(g->{g.setForUser(userId);if (!g.isAdmin()) g.setMembers(null);});
        return ResponseEntity.ok(new GetGroupsResponse(groups));
    }
	@RequestMapping(path = "joinGroup", method = RequestMethod.POST)
	public ResponseEntity<?> joinGroup(@RequestBody UserToGroupRequest request) {
	    String userId = CerberusUserContext.currentUserDetails().getId();
	    Group group = groupRepository.findOne(request.getGroupId());
	    boolean closeGroup = group.isPrivate();
	    boolean canAdd=false;
	    boolean isAdmin = userId.equals(group.getGroupAdmin());
		if (!closeGroup || isAdmin) {
	    	canAdd=true;
	    	if (request.getUserId()!=null && isAdmin) {
	    		userId=request.getUserId();
	    	}
	    }
	
		if (canAdd) {
		    group.addMember(userId);
		    groupRepository.save(group);
		    Map<String,Object> m = new HashMap<>();
		    m.put("isAdmin", isAdmin);
		    m.put("joined", true);
		    return ResponseEntity.ok(m);
		} else {
			return ResponseEntity.ok(new BaseResponse("1","Only admin can add to private group"));
		}
	}

}

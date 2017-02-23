package com.brahalla.Cerberus.controller.rest;

import com.brahalla.Cerberus.model.dbmodels.Comment;
import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.dbmodels.Post;
import com.brahalla.Cerberus.model.dbmodels.Report;
import com.brahalla.Cerberus.model.user.User;
import com.brahalla.Cerberus.repository.GroupRepository;
import com.brahalla.Cerberus.repository.PostRepository;
import com.brahalla.Cerberus.repository.ReportRepository;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${cerberus.route.report}")
public class ReportController {

    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private GroupRepository groupRepository;

   @Value("${aws.bucket.url}")
    private String bucketUrl;
    @RequestMapping(path = "report", method = RequestMethod.POST)
    public ResponseEntity<?> report(@RequestBody Report report) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        report.setReporterId(userId);
        report.setCreated(new Date());
        reportRepository.save(report);
        if (report.getGroupId()!=null) {
        	Group group = groupRepository.findOne(report.getGroupId());
        	report.setGroup(group);
        	List<Report> reports = group.getReports();
        	if (reports==null) {
        		reports=new ArrayList<>();
        		group.setReports(reports);
        	}
        	reports.add(report);
        	groupRepository.save(group);
        } else if (report.getCommentId()!=null) {
        	Post post= postRepository.findOne(report.getPostId());
    		report.setPost(post);
        	if (post!=null &&post.getComments()!=null) {
        		for (Comment comment:post.getComments()) {
        			if (comment.getCommentId().equals(report.getCommentId())) {
        				report.setComment(comment);
        				List<Report> reports = comment.getReports();
        	        	if (reports==null) {
        	        		reports=new ArrayList<>();
        	        		comment.setReports(reports);
        	        	}
        	        	reports.add(report);
        	        	postRepository.save(post);
        	        	break;
        			}
        		}
        	}
        } else if (report.getPostId()!=null) {
        	Post post= postRepository.findOne(report.getPostId());
    		report.setPost(post);
			List<Report> reports = post.getReports();
        	if (reports==null) {
        		reports=new ArrayList<>();
        		post.setReports(reports);
        	}
        	reports.add(report);
        	postRepository.save(post);
        } else if (report.getUserId()!=null) {
        	User user = userRepository.findOne(report.getUserId());
        	report.setUser(user);
			List<Report> reports = user.getReports();
        	if (reports==null) {
        		reports=new ArrayList<>();
        		user.setReports(reports);
        	}
        	reports.add(report);
        	userRepository.save(user);
        }
        
         return ResponseEntity.ok(report);
    }

 

}

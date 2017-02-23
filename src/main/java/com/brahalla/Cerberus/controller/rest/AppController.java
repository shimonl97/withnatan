package com.brahalla.Cerberus.controller.rest;

import com.amazonaws.util.IOUtils;
import com.brahalla.Cerberus.model.base.BaseResponse;
import com.brahalla.Cerberus.model.dbmodels.App;
import com.brahalla.Cerberus.model.dbmodels.Group;
import com.brahalla.Cerberus.model.dbmodels.app.AboutTile;
import com.brahalla.Cerberus.model.dbmodels.app.Extension;
import com.brahalla.Cerberus.model.dbmodels.app.ServicePage;
import com.brahalla.Cerberus.model.dbmodels.app.ServiceTile;
import com.brahalla.Cerberus.model.json.request.*;
import com.brahalla.Cerberus.model.json.response.*;
import com.brahalla.Cerberus.repository.AppRepository;
import com.brahalla.Cerberus.repository.GroupRepository;
import com.brahalla.Cerberus.repository.UserRepository;
import com.brahalla.Cerberus.security.CerberusUserContext;
import com.brahalla.Cerberus.service.ImageUploadService;
import com.mongodb.client.model.geojson.Position;
import org.apache.log4j.Logger;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by dani on 10/5/2016.
 */
@RestController
@RequestMapping("${cerberus.route.app}")
public class AppController {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    private final int PAGE_SIZE = 10;
    private final int DISTANCE_SEARCH = 20;
    @Value("${aws.bucket.url}")
    private String bucketUrl;
    @Autowired
    private ImageUploadService imageUploadService;
/*    @RequestMapping(path = "addAboutPage", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup2(HttpEntity<String> httpEntity) {
        String json = httpEntity.getBody();
        System.out.println(json);
        return ResponseEntity.ok(new BaseResponse("1",null));

    }*/
    @RequestMapping(path = "createApp", method = RequestMethod.POST)
    public ResponseEntity<?> createGroup(@RequestBody Group request, HttpServletRequest req) {

        try {
        	Group group = groupRepository.findOne(request.getId());
        	App app = request.getApp();
            String userId = CerberusUserContext.currentUserDetails().getId();
            app.setUserId(userId);
            groupRepository.save(group);
            if(!app.getCoverImageUrl().equals("")){
                imageUploadService.UploadObjectSingleOperation("card/cover/"+app.getId(),app.getCoverImageUrl());
                app.setCoverImageUrl(bucketUrl+"card/cover/"+app.getId()+".png");
                groupRepository.save(group);
            }
            if(!app.getLogoImageUrl().equals("")){
                imageUploadService.UploadObjectSingleOperation("card/logo/"+app.getId(),app.getLogoImageUrl());
                app.setLogoImageUrl(bucketUrl+"card/logo/"+app.getId()+".png");
                groupRepository.save(group);
            }
            if(!app.getPic1().equals("")){
                String fileName = "card/logo/pic1"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,app.getPic1());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }
            if(!app.getPic2().equals("")){
                String fileName = "card/logo/pic2"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,app.getPic2());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }
            if(!app.getPic3().equals("")){
                String fileName = "card/logo/pic3"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,app.getPic3());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }

            return ResponseEntity.ok(new BaseResponse("1",new CreateCardResponse(group.getId())));

        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("0",null));
        }
    }
    @RequestMapping(path = "deleteApp", method = RequestMethod.POST)
    public ResponseEntity<?> deleteApp(@RequestBody Group request) {

        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getId());
            App app=group.getApp();
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            groupRepository.delete(app.getId());
            return ResponseEntity.ok(new BaseResponse("1",null));

        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("0",null));
        }
    }

 

    @RequestMapping(path = "addServicePage", method = RequestMethod.POST)
    public ResponseEntity<?> addServicePage(@RequestBody AddServicePageRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
            String pageId = "";

            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            List<ServicePage> appPages = app.getServices().getPages();
            ServicePage tempPage;
            ServicePage page = request.getPage();
                if(appPages.size() == 0 || !appPages.contains(request.getPage())){
                 tempPage = new ServicePage();
                 tempPage.setId(UUID.randomUUID().toString());
                appPages.add(tempPage);
            } else {
                tempPage = appPages.get(appPages.indexOf(request.getPage()));
            }
            if (page.getDescription()!=null)
            	tempPage.setDescription(page.getDescription());
            if (page.getName()!=null)
            	tempPage.setName(page.getName());
            
            pageId = tempPage.getId();
            if (page.getImage()!=null) {
                String fileName = "card/logo/"+app.getId()+"/"+pageId;
				imageUploadService.UploadObjectSingleOperation(fileName,page.getImage());
				tempPage.setImage(bucketUrl+fileName+".png");

            }
            Group res = groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1",new NewPageResponse(res.getApp(),pageId)));

        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("0",e.getMessage()));
        }
    }
 

    @RequestMapping(path = "addCom", method = RequestMethod.POST)
    public ResponseEntity<?> addCom(@RequestBody AddComRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            String tempTileId = "";
            
            List<Extension> extensionList = app.getCom().getExtensions();
            if(extensionList.size() == 0 || !extensionList.contains(request.getExtension())){
                Extension extension = request.getExtension();
                extension.setId(UUID.randomUUID().toString());
                tempTileId = extension.getId();
                extensionList.add(extension);
                if(!request.getExtension().getImageUrl().equals("") && request.getExtension().getImageUrl().contains("http")){
                    extension.setImageUrl(request.getExtension().getImageUrl());

                }else if (!request.getExtension().getImageUrl().equals("")) {
                    imageUploadService.UploadObjectSingleOperation("card/tile/"+extension.getId(),request.getExtension().getImageUrl());
                    extension.setImageUrl(bucketUrl+"card/tile/"+extension.getId()+".png");

                }
            }
            else if(extensionList.contains(request.getExtension())) {
                Extension tempPage = extensionList.get(extensionList.indexOf(request.getExtension()));
                tempPage.setName(request.getExtension().getName());
                tempPage.setHolderName(request.getExtension().getHolderName());
                tempPage.setEmail(request.getExtension().getEmail());
                tempPage.setPhone(request.getExtension().getPhone());
                if(!request.getExtension().getImageUrl().equals("") && request.getExtension().getImageUrl().contains("http")){
                    tempPage.setImageUrl(request.getExtension().getImageUrl());

                }else if (!request.getExtension().getImageUrl().equals("")) {
                    imageUploadService.UploadObjectSingleOperation("card/tile/"+request.getExtension().getId(),request.getExtension().getImageUrl());
                    tempPage.setImageUrl(bucketUrl+"card/tile/"+tempPage.getId()+".png");

                }
                tempTileId = tempPage.getId();
            }
            Group res = groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1",new NewTileResponse(res.getApp(),tempTileId)));

        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse("0",e.getMessage()));
        }
    }


    /*
     * not used use group method
    @RequestMapping(path = "getAppsNearby", method = RequestMethod.POST)
    public ResponseEntity<?> getAppsNearby(@RequestBody GroupsNearbyRequest request) {
        String userId = CerberusUserContext.currentUserDetails().getId();
        if(request.getLat()!=null && request.getLon()!=null) {
            List<App> apps = groupRepository.findByLocationNear(new Point(request.getLat(), request.getLon()), new Distance(DISTANCE_SEARCH, Metrics.KILOMETERS));
            return ResponseEntity.ok(new GetCardsResponse(apps));

        }else {
            Page<App> page = groupRepository.findAll(new PageRequest(0,10));
            List<App> apps = new ArrayList<>();
            page.forEach(card ->
                    apps.add(card));
            return ResponseEntity.ok(new GetCardsResponse(apps));


        }

    }
    */
    /*
     * not used user is member of holding group
    @RequestMapping(path = "getUserApps", method = RequestMethod.POST)
    public ResponseEntity<?> getUserApps() {
        String userId = CerberusUserContext.currentUserDetails().getId();
            List<App> apps = groupRepository.findByUserId(userId);
            return ResponseEntity.ok(new GetCardsResponse(apps));


        }
     */
    @RequestMapping(path = "updateAppCover", method = RequestMethod.POST)
    public ResponseEntity<?> updateAppCover(@RequestBody ImageUpdateRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            if(request.getImage()!=null && request.getImage().contains("http")){
                app.setCoverImageUrl(request.getImage());

            }else if (request.getImage()!=null) {
                imageUploadService.UploadObjectSingleOperation("card/cover/"+app.getId(),request.getImage());
                app.setCoverImageUrl(bucketUrl+"card/cover/"+app.getId()+".png");

            }
           Group res = groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", res.getApp()));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }
    @RequestMapping(path = "updateAppLogo", method = RequestMethod.POST)
    public ResponseEntity<?> updateAppLogo(@RequestBody ImageUpdateRequest request,HttpServletRequest req) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            if(request.getImage()!=null && request.getImage().contains("http")){
                app.setLogoImageUrl(request.getImage());

            }else if (request.getImage()!=null) {
                imageUploadService.UploadObjectSingleOperation("card/logo/"+app.getId(),request.getImage());
                app.setLogoImageUrl(bucketUrl+"card/logo/"+app.getId()+".png");

            }
            Group res = groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", res.getApp()));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }
    @RequestMapping(path = "updateExtensionImage", method = RequestMethod.POST)
    public ResponseEntity<?> updateExtensionImage(@RequestBody ImageUpdateRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            Extension tile = app.getCom().getExtensions().get(app.getCom().getExtensions().indexOf(new Extension(request.getPageId())));
            if(tile == null){
                return ResponseEntity.ok(new BaseResponse("0", "Does not exists"));

            }

            if(request.getImage()!=null && request.getImage().contains("http")){
                tile.setImageUrl(request.getImage());

            }else if (request.getImage()!=null) {
                imageUploadService.UploadObjectSingleOperation("card/tile/"+tile.getId(),request.getImage());
                tile.setImageUrl(bucketUrl+"card/tile/"+tile.getId()+".png");

            }
            groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", new ImageUpdateResponse(tile.getImageUrl(),tile)));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }

    @RequestMapping(path = "removeServicePage", method = RequestMethod.POST)
    public ResponseEntity<?> removeServicePage(@RequestBody ImageUpdateRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            app.getServices().getPages().remove(app.getServices().getPages().indexOf(new ServicePage(request.getPageId())));

            groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", "Removed"));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }


 

    @RequestMapping(path = "removeExtension", method = RequestMethod.POST)
    public ResponseEntity<?> removeExtension(@RequestBody ImageUpdateRequest request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getAppId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            app.getCom().getExtensions().remove(app.getCom().getExtensions().indexOf(new Extension(request.getPageId())));
            groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", "Removed"));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }
    @RequestMapping(path = "updateAppDetails", method = RequestMethod.POST)
    public ResponseEntity<?> updateAppDetails(@RequestBody Group request) {
        try {
            String userId = CerberusUserContext.currentUserDetails().getId();
            Group group = groupRepository.findOne(request.getId());
            App app=group.getApp();
 
            if(app==null || !app.getUserId().equals(userId)){
                return ResponseEntity.ok(new BaseResponse("0","Not Authorized To Edit App"));

            }
            App requestApp = request.getApp();
            if(!request.getName().equals("")){
                app.setName(request.getName());

            }
            if(!requestApp.getSlogan().equals("")){
                app.setSlogan(requestApp.getSlogan());

            }
            if(!requestApp.getDescription().equals("")){
                app.setDescription(requestApp.getDescription());

            }
            if(!requestApp.getAddress().equals("")){
                app.setAddress(requestApp.getAddress());

            }
            if(!requestApp.getPhone().equals("")){
                app.setPhone(requestApp.getPhone());

            }
            if(requestApp.getLocation()!=null){
                app.setLocation(requestApp.getLocation());

            }
            
            if(!requestApp.getPic1().equals("")){
                String fileName = "card/logo/pic1"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,requestApp.getPic1());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }
            if(!requestApp.getPic2().equals("")){
                String fileName = "card/logo/pic2"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,requestApp.getPic2());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }
            if(!requestApp.getPic3().equals("")){
                String fileName = "card/logo/pic3"+app.getId();
				imageUploadService.UploadObjectSingleOperation(fileName,requestApp.getPic3());
                app.setLogoImageUrl(bucketUrl+fileName+".png");
                groupRepository.save(group);
            }
            Group res = groupRepository.save(group);
            return ResponseEntity.ok(new BaseResponse("1", res.getApp()));

        } catch (Exception e) {
            return ResponseEntity.ok(new BaseResponse("0", e.getMessage()));

        }
    }

}

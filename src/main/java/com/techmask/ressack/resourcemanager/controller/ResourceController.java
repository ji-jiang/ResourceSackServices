package com.techmask.ressack.resourcemanager.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.core.utils.NumberUtils;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.service.ResourceService;

@RestController
@Configuration
@RequestMapping("/resource")
public class ResourceController extends BaseController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	AppConfiguration appConfiguration;

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addResource(
			HttpServletRequest request, @RequestBody Map<String, Object> resourceMap) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		
			
			resourceMap.put("userId", userSession.getUserId());
			resourceMap.put("userName", userSession.getUserName());
			
			resourceMap = resourceService.addResource(resourceMap);

			
			resultMap.put(RESULT_CODE, "SUCCESS");
			resultMap.put("resource", resourceMap);
			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;


	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/imageupload/{resourceId}")
	public Map<String, Object> uploadResourceImage(
			@RequestParam("file") MultipartFile uploadfile,@PathVariable("resourceId") String resourceId) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			
			if(NumberUtils.isNumber(resourceId)){
				resourceId = String.valueOf(NumberUtils.toLong(resourceId));
			}
			
			String filePath = appConfiguration.getResourceImageUploadPath();
			
			
			BufferedImage origImage = ImageIO.read(new ByteArrayInputStream(uploadfile.getBytes()));
            
            BufferedImage thumbnail_md = Scalr.resize(origImage, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT,
                    400, 300, Scalr.OP_ANTIALIAS);
            

            File thumbnailMdFile = new File(filePath+"R00000"+resourceId+"_md.png");
            ImageIO.write(thumbnail_md, "png", thumbnailMdFile);    
			
            resourceService.setResourceImageInd(resourceId);
			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;


	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{keywords}/{pageNo}")
	public Map<String, Object> loadAllResourceByKeywords(HttpServletRequest request, 
			@PathVariable("keywords") String keywords,
			@PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		
		requestMap.put("keywords", keywords);
		requestMap.put("pageNo", pageNo);
		
		UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		
		requestMap.put("userId", userSession.getUserId());
		requestMap.put("userName", userSession.getUserName());

		try {
			List<Resource> resources = resourceService
					.loadAllResourceByKeywords(requestMap);
			resultMap.put("resources", resources);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{category}/{subCategory}/{pageNo}")
	public Map<String, Object> loadAllResource(HttpServletRequest request, 
			@PathVariable("category") String category,
			@PathVariable("subCategory") String subCategory,
			@PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		
		requestMap.put("category", category);
		requestMap.put("subCategory", subCategory);
		requestMap.put("pageNo", pageNo);
		
		UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		
		requestMap.put("userId", userSession.getUserId());
		requestMap.put("userName", userSession.getUserName());

		try {

			

			List<Resource> resources = resourceService
					.loadAllResource(requestMap);
			resultMap.put("resources", resources);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
}

package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.busobjs.UserRole;
import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.data.PageHelper;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.NumberUtils;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.repository.ResourceRepository;

@Service
@Configuration
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	AppConfiguration appConfiguration;

	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public Resource loadResourceById(String resourceId) {
		return resourceRepository.loadResourceById(resourceId);
	}

	@Override
	public Map<String, Object> addResource(Map<String, Object> resourceMap) {

		validateAddOrUpdateResource(resourceMap,true);

		resourceRepository.addResource(resourceMap);
		long lastInserId = resourceRepository.getLastInsertId();
		resourceMap.put("id", lastInserId);

		return resourceMap;

	}
	
	@Override
	public Map<String, Object> updateResource(Map<String, Object> resourceMap) {

		validateAddOrUpdateResource(resourceMap,false);

		resourceRepository.updateResource(resourceMap);
	
		return resourceMap;

	}

	protected void validateAddOrUpdateResource(Map<String, Object> resourceMap, boolean isAddResource) {

		String userId = (String) resourceMap.get("userId");
		UserRole userRole = UserRole.getInstance((String) resourceMap.get("userRole"));
		if (StringUtils.isBlank(userId)) {
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}

		StringBuffer errorMsg = new StringBuffer();

		String title = (String) resourceMap.get("title");
		String category = (String) resourceMap.get("category");
		String subCategory = (String) resourceMap.get("subCategory");
		String desc = (String) resourceMap.get("desc");
		String origUrl = (String) resourceMap.get("origUrl");
		String downloadUrl = (String) resourceMap.get("downloadUrl");
		String downloadPassword = (String) resourceMap.get("downloadPassword");
		String tags = (String) resourceMap.get("tags");

		ValidateUtils.validateField(errorMsg, "title", title, true, 50);
		ValidateUtils.validateField(errorMsg, "category", category, true, 20);
		ValidateUtils.validateField(errorMsg, "subCategory", subCategory, true, 20);
		ValidateUtils.validateField(errorMsg, "desc", desc, true, 300);
		ValidateUtils.validateField(errorMsg, "origUrl", origUrl, true, 100);
		ValidateUtils.validateField(errorMsg, "downloadUrl", downloadUrl, false, 100);
		ValidateUtils.validateField(errorMsg, "downloadPassword", downloadPassword, false, 100);
		ValidateUtils.validateField(errorMsg, "tags", tags, false, 100);

		if (errorMsg.length() > 0) {
			throw new ValidationException(errorMsg.toString());
		}
		
		
		if(!StringUtils.isBlank(tags)){
			tags = StringUtils.replace(tags, "，",",");
			tags = StringUtils.replace(tags, ";",",");
			resourceMap.put("tags", tags);
		}

		if (!StringUtils.isBlank(origUrl) && (!StringUtils.isBlank(downloadUrl))
				&& origUrl.equalsIgnoreCase(downloadUrl) && StringUtils.isBlank(downloadPassword)) {
			downloadUrl = null;
			resourceMap.remove("downloadUrl");
		}

		if(isAddResource){
			int newCreatedCount = resourceRepository.getNewCreatedCount(resourceMap);
			int maxResouceAddCount = appConfiguration.getMaxResouceAddCount();
			int remainAddCount = maxResouceAddCount - newCreatedCount - 1;


			if (remainAddCount < 0) {
				throw new ValidationException("error.resouce.exceedAddLimit");
			} else {
				resourceMap.put("remainAddCount", remainAddCount);
			}

			int sameOrigUrlCount = resourceRepository.getSameOrigUrlCount(resourceMap);
			if (sameOrigUrlCount > 0) {
				throw new ValidationException("error.resouce.sameOrigUrl");
			}
		}else{
			String resourceId = (String)resourceMap.get("id");
			
			Resource resource = null;
			if(NumberUtils.isNumber(resourceId)){
				resourceId = String.valueOf(NumberUtils.toLong(resourceId));
				resource = loadResourceById(resourceId);
			}

			
			if(resource == null ||
					(!userRole.isAdmin() && !String.valueOf(resource.getOwnerId()).equalsIgnoreCase(userId))){
				throw new ValidationException("error.resource.notOwnResource");
			}
		}
		
		

	}

	@Override
	public List<Resource> loadAllResource(Map<String, Object> requestMap) {
		List<Resource> resources = null;
		PageHelper.preparePageQuery(requestMap);

		String category = (String) requestMap.get("category");
		String subCategory = (String) requestMap.get("subCategory");

		if (StringUtils.isBlank(category)) {
			category = "ALL";
			requestMap.put("category", category);
		}
		if (StringUtils.isBlank(subCategory)) {
			subCategory = "ALL";
			requestMap.put("subCategory", subCategory);
		}

		resources = resourceRepository.loadAllResource(requestMap);
		postProcessResources(resources, requestMap);

		return resources;
	}

	@Override
	public List<Resource> loadAllResourceByKeywords(Map<String, Object> requestMap) {
		List<Resource> resources = null;
		PageHelper.preparePageQuery(requestMap);

		String keywords = (String) requestMap.get("keywords");

		if (StringUtils.isBlank(keywords)) {
			keywords = "ALL";
			requestMap.put("keywords", keywords);
		}

		resources = resourceRepository.loadAllResource(requestMap);
		postProcessResources(resources, requestMap);

		return resources;
	}

	protected void postProcessResources(List<Resource> resources, Map<String, Object> requestMap) {

		if (resources != null) {

			ResouceLoadProcessor rlp = new ResouceLoadProcessor(requestMap, appConfiguration);

			for (int i = 0; i < resources.size(); i++) {
				Resource resource = resources.get(i);
				rlp.postProcessResource(resource);

			}
		}
	}

	@Override
	public void setResourceImageInd(String resourceId) {
		resourceRepository.setImageInd(resourceId);

	}

}

package com.techmask.ressack.profilemanager.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.data.PageHelper;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.profilemanager.domain.Profile;
import com.techmask.ressack.profilemanager.repository.ProfileRepository;
import com.techmask.ressack.usermanager.domain.User;

@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	ProfileRepository profileRepository;
	

	
	@Override
	public Profile loadProfileByUserId(String userId) {
		Profile profile = profileRepository.loadProfileByUserId(userId);
		
		return profile;
	}
	
	@Override
	public void updateProfile(Map<String, Object> profileMap) {
		int rs = profileRepository.updateProfile(profileMap);
		if(rs==0){
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
		
	}

	@Override
	public void addProfile(User user) {
		profileRepository.addProfile(user);		
	}

	@Override
	public List<Profile> loadAllContributorProfile(Map<String, Object> requestMap) {
		PageHelper.preparePageQuery(requestMap);
		
		return profileRepository.loadAllContributorProfile(requestMap);
	}

	

}

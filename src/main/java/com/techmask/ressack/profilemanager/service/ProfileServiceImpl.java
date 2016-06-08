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
import com.techmask.ressack.usermanager.service.UserService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	UserService userService;

	@Override
	public Profile loadProfileByUserId(String userId, String profileUserId) {
		Profile profile = profileRepository.loadProfileByUserId(profileUserId);
		ProfileLoadProcessor plp = new ProfileLoadProcessor(userId);
		plp.postProcessResource(profile);
		return profile;
	}

	@Override
	public void updateProfile(Map<String, Object> profileMap) {
		int rs = profileRepository.updateProfile(profileMap);

		String origUserName = (String) profileMap.get("origUserName");
		String userName = (String) profileMap.get("userName");
		String origUserEmail = (String) profileMap.get("origUserEmail");
		String email = (String) profileMap.get("email");

		if (rs == 0) {
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		} else {
			if ((userName != null && origUserName != null && !userName.equals(origUserName))
					|| (userName != null && origUserEmail == null)
					|| (email != null && origUserEmail != null && !email.equals(origUserEmail))) {
				userService.updateUserNameAndEmail(profileMap);
			}
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

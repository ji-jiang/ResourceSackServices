package com.techmask.ressack.profilemanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.profilemanager.domain.Profile;
import com.techmask.ressack.usermanager.domain.User;

public interface ProfileService {
	public Profile loadProfileByUserId(String userId, String profileUserId);
	public void addProfile(User user);
	public void updateProfile(Map<String,Object> profileMap);
	public List<Profile> loadAllContributorProfile(Map<String,Object> requestMap);
}

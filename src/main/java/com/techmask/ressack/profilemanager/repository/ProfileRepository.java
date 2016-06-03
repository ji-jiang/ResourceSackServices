package com.techmask.ressack.profilemanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.profilemanager.domain.Profile;
import com.techmask.ressack.usermanager.domain.User;

@Mapper
public interface ProfileRepository {
	public Profile loadProfileByUserId(String userId);
	public int addProfile(User user);
	public int updateProfile(Map<String,Object> profileMap);
	public List<Profile> loadAllContributorProfile(Map<String,Object> requestMap);
}

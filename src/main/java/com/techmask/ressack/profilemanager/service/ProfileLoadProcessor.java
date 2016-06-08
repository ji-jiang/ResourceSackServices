package com.techmask.ressack.profilemanager.service;

import com.techmask.ressack.profilemanager.domain.Profile;

public class ProfileLoadProcessor {
	
	private String userId;
	
	public ProfileLoadProcessor(String userId){
		this.setUserId(userId);
	}
	
	
	public void postProcessResource(Profile profile){
		if(profile!=null){
			boolean isOwnProfile = (userId!=null && profile.getUserId()!=null && userId.equals(profile.getUserId()));
			if(!isOwnProfile){
				profile.setEmail("XXXXXXXXX");
			}
		}
		
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}

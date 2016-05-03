package com.techmask.ressack.usermanager.oauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OAuthServices {
    
    @Autowired 
    private List<CustomOAuthService> oAuthServices;
    
    public CustomOAuthService getOAuthService(String type){
       
        for(int i=0;i<oAuthServices.size();i++){
        	CustomOAuthService oauthService = (CustomOAuthService)oAuthServices.get(i);
        	if(oauthService.getoAuthType().equalsIgnoreCase(type)){
        		return oAuthServices.get(i);
        	}
        }
       
        return null; 
    }
    
    public List<CustomOAuthService> getAllOAuthServices(){
        return oAuthServices;
    }

}

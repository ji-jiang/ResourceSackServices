package com.techmask.ressack.usermanager.oauth.service;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.techmask.ressack.usermanager.domain.User;

public interface CustomOAuthService extends OAuthService{
    
	String getoAuthType();
    String getAuthorizationUrl();
    User getOAuthUser(Token accessToken);

}

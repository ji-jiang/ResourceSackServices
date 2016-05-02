package com.techmask.ressack.usermanager.oauth.service;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.*;
import org.scribe.oauth.OAuth20ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.domain.UserRole;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;

public class GitHubOAuthService extends OAuth20ServiceImpl implements CustomOAuthService {

	private final DefaultApi20 api;
	private final OAuthConfig config;
	private final String authorizationUrl;
	private static final String GIT_HUB_GET_USER_URL = "https://api.github.com/user";

	public GitHubOAuthService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
		this.authorizationUrl = getAuthorizationUrl(null);
	}

	@Override
	public User getOAuthUser(Token accessToken) {
		OAuthRequest request = new OAuthRequest(Verb.GET, GIT_HUB_GET_USER_URL);
		this.signRequest(accessToken, request);
		Response response = request.send();
		
		User user = new User();
		Object result = JSON.parse(response.getBody());
		user.setOauthType(getoAuthType());
		user.setOauthId(JSONPath.eval(result, "$.id").toString());
		user.setOauthName(JSONPath.eval(result, "$.login").toString());
		user.setUserName(JSONPath.eval(result, "$.login").toString());
		user.setTokenKey(accessToken.getToken());
		user.setRole(UserRole.USER.toString());
		
		return user;
	}

	@Override
	public String getoAuthType() {
		return OAuthTypes.GITHUB;
	}

	@Override
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

}

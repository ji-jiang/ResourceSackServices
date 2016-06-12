package com.techmask.ressack.usermanager.oauth.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;

public class WeiboOAuthService extends OAuth20ServiceImpl implements CustomOAuthService {

	private final DefaultApi20 api;
	private final OAuthConfig config;
	private final String authorizationUrl;
	private static final String WEIBO_GET_TOKEN_INFO = "https://api.weibo.com/oauth2/get_token_info";
	private static final String WEIBO_GET_USER_INFO = "https://api.weibo.com/2/users/show.json";
	
	private final Log logger = LogFactory.getLog(getClass());

	public WeiboOAuthService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
		this.authorizationUrl = getAuthorizationUrl(null);
	}

	/***
	 * you can get all weibo user info from
	 * "http://open.weibo.com/wiki/2/users/show";
	 ***/

	@Override
	public User getOAuthUser(Token accessToken) {
		OAuthRequest getUidRequest = new OAuthRequest(Verb.POST, WEIBO_GET_TOKEN_INFO);
		this.signRequest(accessToken, getUidRequest);
		Response response = getUidRequest.send();
		if(logger.isDebugEnabled()){
			logger.debug("weibo get user response is: "+response.getBody());
		}
		
		Object result = JSON.parse(response.getBody());
		
		User user = new User();
		user.setTokenKey(accessToken.getToken());
		user.setOauthType(getoAuthType());
		String uid = JSONPath.eval(result, "$.uid").toString();
		user.setOauthId(uid);

		OAuthRequest getUserRequest = new OAuthRequest(Verb.GET, WEIBO_GET_USER_INFO);
		getUserRequest.addQuerystringParameter("uid", uid);
		this.signRequest(accessToken, getUserRequest);
		Response userInfoResponse = getUserRequest.send();
		if(logger.isDebugEnabled()){
			logger.debug("weibo get user userInfoResponse is: "+userInfoResponse.getBody());
		}
		Object userInfo = JSON.parse(userInfoResponse.getBody());
		user.setOauthName(JSONPath.eval(userInfo, "$.screen_name").toString());
		user.setUserName(JSONPath.eval(userInfo, "$.screen_name").toString());
		user.setHeadImgUrl(JSONPath.eval(userInfo, "$.profile_image_url").toString());

		user.setRole(UserRole.USER.name());
		return user;
	}

	@Override
	public String getoAuthType() {
		return OAuthTypes.SINA_WEIBO;
	}

	@Override
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	@Override
	public Token getAccessToken(Token requestToken, Verifier verifier) {
		OAuthRequest request = new OAuthRequest(Verb.POST, api.getAccessTokenEndpoint());
		request.addQuerystringParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
		request.addQuerystringParameter(OAuthConstants.CLIENT_SECRET, config.getApiSecret());
		request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
		request.addQuerystringParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
		if (config.hasScope())
			request.addQuerystringParameter(OAuthConstants.SCOPE, config.getScope());
		Response response = request.send();
		Object result = JSON.parse(response.getBody());
		String access_token = JSONPath.eval(result, "$.access_token").toString();
		if (!access_token.equals("")) {
			if(logger.isDebugEnabled()){
				logger.debug("weibo get user access_token is: "+access_token);
			}
			return new Token(access_token, "",response.getBody());
		}

		return null;

	}

}

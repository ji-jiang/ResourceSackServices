package com.techmask.ressack.usermanager.oauth.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuth20ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;

public class QQOAuthService extends OAuth20ServiceImpl implements CustomOAuthService {

	private final DefaultApi20 api;
	private final OAuthConfig config;
	private final String authorizationUrl;
	private static final String GET_OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me";
	private static final String GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info";
	private final Log logger = LogFactory.getLog(getClass());

	public QQOAuthService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
		this.authorizationUrl = getAuthorizationUrl(null);
	}

	
	/***
	 * you can get all info from "http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token";
	***/
	
	
	
	@Override
	public User getOAuthUser(Token accessToken) {
		
        OAuthRequest request = new OAuthRequest(Verb.GET, GET_OPEN_ID_URL);
        this.signRequest(accessToken, request);
        Response response = request.send();
  
		User user = new User();		
		user.setTokenKey(accessToken.getToken());
		user.setOauthType(getoAuthType());
		Object result = JSON.parse(response.getBody().substring(9, response.getBody().length() - 3));
		String openid=JSONPath.eval(result, "$.openid").toString();
		user.setOauthId(openid);
		if(logger.isDebugEnabled()){
			logger.debug("qq get openid  is: "+openid);
		}
		
		OAuthRequest getUserRequest = new OAuthRequest(Verb.GET, GET_USER_INFO_URL);
		getUserRequest.addQuerystringParameter("oauth_consumer_key", config.getApiKey());
		getUserRequest.addQuerystringParameter("openid", openid);
		this.signRequest(accessToken, getUserRequest);
		Response userInfoResponse = getUserRequest.send();
		if(logger.isDebugEnabled()){
			logger.debug("qq get userInfoResponse response is: "+userInfoResponse.getBody());
		}
		Object userInfo = JSON.parse(userInfoResponse.getBody());
		
		user.setOauthName(JSONPath.eval(userInfo, "$.nickname").toString());
		user.setUserName(JSONPath.eval(userInfo, "$.nickname").toString());
		user.setHeadImgUrl(JSONPath.eval(userInfo, "$.figureurl").toString());
		
		user.setRole(UserRole.USER.name());
		return user;
	}

	@Override
	public String getoAuthType() {
		return OAuthTypes.QQ;
	}

	@Override
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

}

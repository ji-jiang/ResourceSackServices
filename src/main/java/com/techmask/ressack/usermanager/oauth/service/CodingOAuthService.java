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
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;

public class CodingOAuthService extends OAuth20ServiceImpl implements CustomOAuthService {

	private final DefaultApi20 api;
	private final OAuthConfig config;
	private final String authorizationUrl;
	private static final String CODING_GET_USER_URL = "https://coding.net/api/current_user";
	
	private  final Log logger=LogFactory.getLog(CodingOAuthService.class);

	public CodingOAuthService(DefaultApi20 api, OAuthConfig config) {
		super(api, config);
		this.api = api;
		this.config = config;
		this.authorizationUrl = getAuthorizationUrl(null);
	}
	
	@Override
	  public Token getAccessToken(Token requestToken, Verifier verifier)
	  {
	    OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
	    request.addQuerystringParameter(OAuthConstants.CLIENT_ID, config.getApiKey());
	    request.addQuerystringParameter(OAuthConstants.CLIENT_SECRET, config.getApiSecret());
	    request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
	    request.addQuerystringParameter(OAuthConstants.REDIRECT_URI, config.getCallback());
	    if(config.hasScope()) request.addQuerystringParameter(OAuthConstants.SCOPE, config.getScope());
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

	
	@Override
	public User getOAuthUser(Token accessToken) {
		System.out.println(accessToken);
		OAuthRequest request = new OAuthRequest(Verb.GET, CODING_GET_USER_URL);
		this.signRequest(accessToken, request);
		Response response = request.send();

		User user = new User();
		JSONObject  data = (JSONObject) JSON.parse(response.getBody());
		Object result=data.get("data");
		
		logger.debug("coding user info:"+result);
		
		user.setOauthType(getoAuthType());
		user.setRole(UserRole.USER.name());
		
		user.setOauthId(JSONPath.eval(result, "$.id").toString());
		user.setOauthName(JSONPath.eval(result, "$.global_key").toString());
		user.setUserName(JSONPath.eval(result, "$.global_key").toString());
		//user.setEmail(JSONPath.eval(result, "$.email").toString());
		user.setHeadImgUrl(JSONPath.eval(result, "$.gravatar").toString());
		user.setTokenKey(accessToken.getToken());
		
		
		return user;
	}

	@Override
	public String getoAuthType() {
		return OAuthTypes.CODING;
	}

	@Override
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

}

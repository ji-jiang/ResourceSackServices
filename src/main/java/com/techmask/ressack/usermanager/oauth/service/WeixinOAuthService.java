package com.techmask.ressack.usermanager.oauth.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.*;
import org.scribe.oauth.OAuth20ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.domain.UserRole;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;

public class WeixinOAuthService extends OAuth20ServiceImpl implements CustomOAuthService {
    
    private final DefaultApi20 api;
    private final OAuthConfig config;
    private final String authorizationUrl;
    private static final String WIXIN_GET_USER_URL = "https://api.weixin.qq.com/sns/userinfo?";
    
    private final Log logger = LogFactory.getLog(getClass());
    
    public WeixinOAuthService(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
        this.api = api;
        this.config = config;
        this.authorizationUrl = getAuthorizationUrl(null);
    }

    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier){
      OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());
      request.addQuerystringParameter("appid", config.getApiKey());
      request.addQuerystringParameter("secret", config.getApiSecret());
      request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
      if(config.hasScope()) request.addQuerystringParameter(OAuthConstants.SCOPE, config.getScope());
      Response response = request.send();
      String responceBody = response.getBody();
      if(logger.isDebugEnabled()){
			logger.debug("weixin get getAccessToken responceBody is: "+response.getBody());
		}
      Object result = JSON.parse(responceBody);
      return new Token(JSONPath.eval(result, "$.access_token").toString(), "", responceBody);
    }

    @Override
	public User getOAuthUser(Token accessToken) {

		Object result = JSON.parse(accessToken.getRawResponse());
		 if(logger.isDebugEnabled()){
				logger.debug("weixin get getAccessToken result is: "+accessToken.getRawResponse());
			}
		OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), WIXIN_GET_USER_URL);
		request.addQuerystringParameter("openid", JSONPath.eval(result, "$.openid").toString());
		request.addQuerystringParameter("access_token", accessToken.getToken());
		Response response = request.send();
		  if(logger.isDebugEnabled()){
				logger.debug("weixin get userinfo responceBody is: "+response.getBody());
			}
		Object userResult = JSON.parse(response.getBody());
		User user = new User();
		user.setOauthType(getoAuthType());
		user.setOauthId(JSONPath.eval(userResult, "$.openid").toString());
		user.setOauthName(JSONPath.eval(userResult, "$.nickname").toString());
		user.setUserName(JSONPath.eval(userResult, "$.nickname").toString());
		user.setHeadImgUrl(JSONPath.eval(userResult, "$.headimgurl").toString());
		user.setTokenKey(accessToken.getToken());
		user.setRole(UserRole.USER.toString());

		return user;
	}
    
    @Override
    public String getoAuthType() {
        return OAuthTypes.WEIXIN;
    }

    @Override
    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

}

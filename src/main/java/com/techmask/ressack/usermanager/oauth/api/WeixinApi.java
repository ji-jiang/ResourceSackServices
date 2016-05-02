package com.techmask.ressack.usermanager.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.OAuthEncoder;

import com.techmask.ressack.usermanager.oauth.service.WeixinOAuthService;

public class WeixinApi extends DefaultApi20 {

	private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&state=%s&scope=snsapi_login#wechat_redirect";
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code";

	private final String wixinState;

	public WeixinApi(String state){
        this.wixinState = state;
    }

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()),wixinState);
	}

	@Override
	public String getAccessTokenEndpoint() {
		return ACCESS_TOKEN_URL;
	}

	@Override
	public OAuthService createService(OAuthConfig config) {
		return new WeixinOAuthService(this, config);
	}
}

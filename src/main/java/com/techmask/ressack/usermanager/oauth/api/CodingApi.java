package com.techmask.ressack.usermanager.oauth.api;


import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.oauth.OAuthService;
import org.scribe.utils.OAuthEncoder;

import com.techmask.ressack.usermanager.oauth.service.CodingOAuthService;

public class CodingApi extends DefaultApi20 {
    
    private static final String AUTHORIZE_URL = "https://coding.net/oauth_authorize.html?client_id=%s&redirect_uri=%s&response_type=code&scope=user";
    private static final String ACCESS_TOKEN_URL = "https://coding.net/api/oauth/access_token?grant_type=authorization_code";
    
    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
          return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }

    @Override
    public String getAccessTokenEndpoint() {
        return String.format(ACCESS_TOKEN_URL);
    }

	@Override
	public OAuthService createService(OAuthConfig config) {
		return new CodingOAuthService(this, config);
	}
}

package com.techmask.ressack.usermanager.oauth.config;

import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.techmask.ressack.usermanager.oauth.api.GithubApi;
import com.techmask.ressack.usermanager.oauth.api.QQApi;
import com.techmask.ressack.usermanager.oauth.api.WeiboApi;
import com.techmask.ressack.usermanager.oauth.api.WeixinApi;
import com.techmask.ressack.usermanager.oauth.service.CustomOAuthService;

@Configuration
public class OAuthConfig {

	private static final String CALLBACK_URL = "%s/oauth/%s/callback";
	// host
	@Value("${host}")
	String host;

	// weixin
	@Value("${oAuth.weixin.appId}")
	String weixinAppId;
	@Value("${oAuth.weixin.appSecret}")
	String weixinAppSecret;
	@Value("${oAuth.weixin.state}")
	String wixinState;

	// qq
	@Value("${oAuth.qq.state}")
	String qqstate;
	@Value("${oAuth.qq.appId}")
	String qqAppId;
	@Value("${oAuth.qq.appKey}")
	String qqAppKey;

	// github
	@Value("${oAuth.github.state}")
	String githubState;
	@Value("${oAuth.github.clientId}")
	String githubClientId;
	@Value("${oAuth.github.clientSecret}")
	String githubClientSecret;

	// sina
	@Value("${oAuth.sina.appKey}")
	String sinaAppKey;
	@Value("${oAuth.sina.appSecret}")
	String sinaAppSecret;

	@Bean
	public QQApi getQQApi() {
		return new QQApi(qqstate);
	}

	@Bean
	public CustomOAuthService getQQOAuthService() {
		return (CustomOAuthService) new ServiceBuilder().provider(getQQApi()).apiKey(qqAppId).apiSecret(qqAppKey)
				.callback(String.format(CALLBACK_URL, host, OAuthTypes.QQ)).build();
	}

	@Bean
	public WeixinApi getweixinApi() {
		return new WeixinApi(wixinState);
	}

	@Bean
	public CustomOAuthService getWeixinOAuthService() {
		return (CustomOAuthService) new ServiceBuilder().provider(getweixinApi()).apiKey(weixinAppId)
				.apiSecret(weixinAppSecret).scope("snsapi_login")
				.callback(String.format(CALLBACK_URL, host, OAuthTypes.WEIXIN)).build();
	}

	@Bean
	public GithubApi githubApi() {
		return new GithubApi(githubState);
	}

	@Bean
	public CustomOAuthService getGithubOAuthService() {
		return (CustomOAuthService) new ServiceBuilder().provider(githubApi()).apiKey(githubClientId)
				.apiSecret(githubClientSecret).callback(String.format(CALLBACK_URL, host, OAuthTypes.GITHUB)).build();
	}

	@Bean
	public CustomOAuthService getSinaOAuthService() {
		return (CustomOAuthService) new ServiceBuilder().provider(WeiboApi.class).apiKey(sinaAppKey)
				.apiSecret(sinaAppSecret).callback(String.format(CALLBACK_URL, host, OAuthTypes.SINA_WEIBO)).build();
	}

}

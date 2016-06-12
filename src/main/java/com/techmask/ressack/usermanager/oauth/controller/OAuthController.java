package com.techmask.ressack.usermanager.oauth.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.busobjs.BooleanFlag;
import com.techmask.ressack.core.busobjs.ResultCode;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.oauth.config.OAuthTypes;
import com.techmask.ressack.usermanager.oauth.service.CustomOAuthService;
import com.techmask.ressack.usermanager.oauth.service.OAuthServices;
import com.techmask.ressack.usermanager.service.UserService;

@RestController
public class OAuthController extends BaseController {

	public static final Log logger = LogFactory.getLog(OAuthController.class);

	@Autowired
	private OAuthServices oAuthServices;
	@Autowired
	private UserService userService;

	// index login test
	@RequestMapping(value = { "", "/login" }, method = RequestMethod.GET)
	public Map<String, Object> showLogin(Model model) {
		model.addAttribute("oAuthServices", oAuthServices.getAllOAuthServices());

		Map<String, Object> authorizationUrlList = new LinkedHashMap<String, Object>();
		authorizationUrlList.put(OAuthTypes.GITHUB,
				oAuthServices.getOAuthService(OAuthTypes.GITHUB).getAuthorizationUrl());
		authorizationUrlList.put(OAuthTypes.SINA_WEIBO,
				oAuthServices.getOAuthService(OAuthTypes.SINA_WEIBO).getAuthorizationUrl());
		authorizationUrlList.put(OAuthTypes.WEIXIN,
				oAuthServices.getOAuthService(OAuthTypes.WEIXIN).getAuthorizationUrl());
		authorizationUrlList.put(OAuthTypes.QQ, oAuthServices.getOAuthService(OAuthTypes.QQ).getAuthorizationUrl());
		
		authorizationUrlList.put(OAuthTypes.CODING, oAuthServices.getOAuthService(OAuthTypes.CODING).getAuthorizationUrl());

		return authorizationUrlList;
	}

	/*
	 * 
	 * 因token具有唯一性，可以利用token判断数据库中是否存在当前token的用户，
	 * 如果存在则直接返回数据库的用户信息，如果不存在则重新获取用户信息 ； 重新获取用户信息以后，还需要再根据用户的aouthid来判断是否是存量客户
	 * 如果是存量客户则直接更新数据库的token信息，如果是非存量客户则需要新增客户 ；
	 *
	 * 所有的access_token过期以后都需要用户重新在授权，没过期的情况下会自动验证通过。目前暂不涉及自动刷新token的功能 补充如下： -
	 * QQ的access_token有效期是30天；
	 *
	 * - weibo的access_token有效期是7天；
	 *
	 * - 微信有2个token： 1. 一个是access_token有效期是2个小时，在获取用户信息的时候使用； 2.
	 * 一个是refresh_token，其作用仅用于延续access_token的有效期，其有效期是30天 3.
	 * 
	 * 但发现为此登陆通过code得到的token都不同，所以就还是留存access_token -
	 * gitHub的access_token，目前从google的结果上看github是的token是不过期的，其设计的目的是尽量减少github的压力
	 * ，暂时未找到官方的说明文档。
	 * http://stackoverflow.com/questions/26902600/whats-the-lifetime-of-github-
	 * oauth-api-access-token
	 *
	 * 
	 * 
	 **/

	@RequestMapping(value = "/oauth/{type}/callback", method = RequestMethod.GET)
	public Map<String, Object> callBack(@RequestParam(value = "code", required = true) String code,
			@PathVariable(value = "type") String type, HttpServletRequest request) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {

			CustomOAuthService oAuthService = oAuthServices.getOAuthService(type);
			Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));
			String tokenKey = accessToken.getToken();
			User user = null;
			user = userService.loadUserByAccessTokenAndOauthType(tokenKey, type);
			
			if (user == null || BooleanFlag.getInstance(user.getReloadInd()).booleanValue()) {
				User userAuthInfo = oAuthService.getOAuthUser(accessToken);
				user = userService.loadUserByOAtuth(userAuthInfo);
				userAuthInfo.setLastLoginDate(new Date());
				if (user == null) {
					user = userService.addUser(userAuthInfo);
				} else {
					// refresh token
					user.setTokenKey(tokenKey);
					user.setLastLoginDate(new Date());
					user.setHeadImgUrl(userAuthInfo.getHeadImgUrl());
					user.setReloadInd("N");
					
					user = userService.updateUser(user);
				}
			} else {
				user.setLastLoginDate(new Date());
				user = userService.updateUser(user);
			}

			resultMap.put("tokenKey", user.getTokenKey());
			resultMap.put(RESULT_CODE, ResultCode.SUCCESS);

		} catch (ValidationException ve) {
			ve.printStackTrace();
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}

}

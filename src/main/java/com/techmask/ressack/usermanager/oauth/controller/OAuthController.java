package com.techmask.ressack.usermanager.oauth.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	public static final Logger logger = LoggerFactory.getLogger(OAuthController.class);

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

		return authorizationUrlList;
	}

	@RequestMapping(value = "/oauth/{type}/callback", method = RequestMethod.GET)
	public Map<String, Object> callBack(@RequestParam(value = "code", required = true) String code,
			@PathVariable(value = "type") String type, HttpServletRequest request) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
		
			CustomOAuthService oAuthService = oAuthServices.getOAuthService(type);
			Token accessToken = oAuthService.getAccessToken(null, new Verifier(code));

			User userAuthInfo = oAuthService.getOAuthUser(accessToken);
			User user = userService.loadUserByOAtuth(userAuthInfo);

			if (user == null) {
				user = userService.addUser(userAuthInfo);
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

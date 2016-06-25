package com.techmask.ressack.accountmanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.accountmanager.service.AccountService;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;

@RestController
@Configuration
@RequestMapping("/account")
public class AccountController extends BaseController{
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(method = RequestMethod.POST,value = "/withdrawrequest")
	public Map<String, Object> addWithdrawRequest(
			HttpServletRequest request, @RequestBody Map<String, Object> requestMap) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		
			
			requestMap.put("userId", userSession.getUserId());
			requestMap.put("userName", userSession.getUserName());
			requestMap.put("userRole", userSession.getUserRole());
			
			accountService.performWithdrawRequest(requestMap);

			
			resultMap.put(RESULT_CODE, "SUCCESS");
			resultMap.put("withdrawRequest", requestMap);
			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;


	}

}

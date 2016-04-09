package com.techmask.ressack.bookmanager.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.core.util.CryptUtil;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.service.UserService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	private final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(method = RequestMethod.POST, value = "/buy")
	public Map<String, Object> createUserAccount(
			@RequestBody Map<String, Object> chargeMap) {

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		Stripe.apiKey = "sk_test_RRmRURLnhEEkdQ2eEVRVrNP2";
		Map<String, Object> chargeParams = new HashMap<String, Object>();

		Map<String, Object> tokenMap = (Map<String, Object>) chargeMap
				.get("token");
		String email = (String) tokenMap.get("email");
		
		//check if the email account already exist in the system
		User existUser = userService.loadUserByTokenKey(email);
		if (existUser != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("The account already exists, cannot buy again, account name: "
						+ email);
			}
			response.put("errorCode", "ACCOUNT_EXIST");
		} else {

			//create a new account for the new buyer
			final User newBuyer = new User();
			newBuyer.setEmail(email);
			newBuyer.setRole(UserRole.VIP_USER.name());
			final String password = CryptUtil.generatePassword();

			
			//TODO remove this log after production deployment
			if (logger.isDebugEnabled()) {
				logger.debug("The generated password for account "+email+" is: "
						+ password);
			}
			
			
			

			userService.addUser(newBuyer);
			
			chargeParams.put("source", tokenMap.get("id")); // obtained with
															// Stripe.js
			chargeParams.put("amount", 1000);
			chargeParams.put("currency", "usd");
			
			
			//finish the charge request
			try {
				Charge.create(chargeParams);
			} catch (AuthenticationException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Charge failed because authentication failed");
				}
				response.put("errorCode", "CHARGE_AUTHENTICATION_FAILURE");
			} catch (InvalidRequestException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Charge failed because the request is invalid");
				}
				response.put("errorCode", "CHARGE_INVALID_REQUEST");
			} catch (APIConnectionException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Charge failed because the request is invalid");
				}
				response.put("errorCode", "CHARGE_INVALID_REQUEST");
			} catch (CardException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Charge failed because of card exception");
				}
				response.put("errorCode", "CHARGE_CARD_EXCEPTION");
			} catch (APIException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Charge failed because of API exception");
				}
				response.put("errorCode", "CHARGE_API_EXCEPTION");
			}
			
			if(response.containsKey("errorCode")){
				//if any error happened, delete the newly created user
				userService.invalidateUser(newBuyer.getId());
			}else{
				//if no errors, send notification email to user
				
				MimeMessagePreparator preparator = new MimeMessagePreparator() {
		            public void prepare(MimeMessage mimeMessage) throws Exception {
		                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		                message.setTo(newBuyer.getEmail());
		                message.setFrom("edison.chen@shiningsys.com"); // could be parameterized...
		                Map model = new HashMap();
		                Map userMap = new HashMap();
		                userMap.put("email", newBuyer.getEmail());
		                userMap.put("password", password);
		                model.put("user", newBuyer);
		                String text = VelocityEngineUtils.mergeTemplateIntoString(
		                        velocityEngine, "mail/registration-confirmation.vm","UTF-8", model);
		                message.setText(text, true);
		                if (logger.isDebugEnabled()) {
							logger.debug("Notification :" +text);
						}
		            }
		        };
//		        this.mailSender.send(preparator);
			}
		}

		if (response.containsKey("errorCode")) {
			response.put("result", "FAILED");
		} else {
			response.put("result", "SUCCESS");
		}

		return response;
	}
}

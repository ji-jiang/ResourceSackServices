package com.startup.bookapp.bookmanager.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.startup.bookapp.usermanager.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/buy")
	
	public Map<String, Object> createUserAccount(@RequestBody Map<String, Object> chargeMap) {

		Stripe.apiKey = "sk_test_RRmRURLnhEEkdQ2eEVRVrNP2";
		Map<String, Object> chargeParams = new HashMap<String, Object>();

		Map<String, Object> tokenMap = (Map<String, Object>) chargeMap.get("token");
		chargeParams.put("source", tokenMap.get("id")); // obtained with														// Stripe.js
		chargeParams.put("amount", 1000);
		chargeParams.put("currency", "usd");

		try {
			Charge.create(chargeParams);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		return response;
	}
}

package com.techmask.ressack.accountmanager.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techmask.ressack.accountmanager.repository.AccountRepository;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.usermanager.domain.User;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public void addAccount(User user) {
		accountRepository.addAccount(user);
	}

	@Override
	@Scheduled(fixedRate=20000)
	public void processAccountCalculate() {
		Map<String,Object> requestMap = new HashMap<String,Object>();
		requestMap.put("yearWeek",0);
		accountRepository.processAccountCalculate(requestMap);
	}

	@Override
	public void performWithdrawRequest(Map<String, Object> requestMap) {
		validatePerformWithdrawRequest(requestMap);
		accountRepository.addWithdrawRequest(requestMap);
		
	}
	
	
	protected void validatePerformWithdrawRequest(Map<String, Object> requestMap){
		StringBuffer errorMsg = new StringBuffer();

		String strApplyAmount = (String) requestMap.get("applyAmount");
		String accountInfo = (String) requestMap.get("accountInfo");


		ValidateUtils.validateField(errorMsg, "applyAmount", strApplyAmount, true, 8);
		ValidateUtils.validateField(errorMsg, "accountInfo", accountInfo, true, 200);

		if (errorMsg.length() > 0) {
			throw new ValidationException(errorMsg.toString());
		}
		
		
		if(!NumberUtils.isNumber(strApplyAmount)){
			throw new ValidationException("error.applyAmount.formatError");
		}
		
		float applyAmount = NumberUtils.createFloat(strApplyAmount).floatValue();
		if(applyAmount<50){
			throw new ValidationException("error.applyAmount.lessThanLimit");			
		}
		
		int pendingWithdrawRequestCount = accountRepository.getPendingWithdrawRequestCount(requestMap);
		if(pendingWithdrawRequestCount>0){
			throw new ValidationException("error.withdrawRequest.pendingExists");		
		}
	}

}

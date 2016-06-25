package com.techmask.ressack.accountmanager.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techmask.ressack.accountmanager.domain.Account;
import com.techmask.ressack.accountmanager.repository.AccountRepository;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.usermanager.domain.User;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public void addAccount(User user) {
		accountRepository.addAccount(user);
	}

	@Override
	@Scheduled(cron = "0 55 23 * * SUN")
	public void processAccountCalculate() {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("yearWeek", 0);
		accountRepository.processAccountCalculate(requestMap);
	}

	@Override
	public void performWithdrawRequest(Map<String, Object> requestMap) {
		Account account = accountRepository.loadAccountByUserId(requestMap);
		if (account == null) {
			throw new ValidationException("error.withdrawRequest.accountNotFound");
		}

		String accountId = account.getId();
		requestMap.put("accountId", accountId);

		validatePerformWithdrawRequest(account,requestMap);

		String accountInfo = (String) requestMap.get("accountInfo");
		if (StringUtils.isBlank(account.getAccountInfo()) || (!account.getAccountInfo().equals(accountInfo))) {
			accountRepository.updateAccountInfo(requestMap);
		}
		accountRepository.addWithdrawRequest(requestMap);

	}

	protected void validatePerformWithdrawRequest(Account account, Map<String, Object> requestMap) {
		StringBuffer errorMsg = new StringBuffer();

		String strApplyAmount = (String) requestMap.get("applyAmount");
		String accountInfo = (String) requestMap.get("accountInfo");

		ValidateUtils.validateField(errorMsg, "applyAmount", strApplyAmount, true, 8);
		ValidateUtils.validateField(errorMsg, "accountInfo", accountInfo, true, 200);

		if (errorMsg.length() > 0) {
			throw new ValidationException(errorMsg.toString());
		}

		if (!NumberUtils.isNumber(strApplyAmount)) {
			throw new ValidationException("error.applyAmount.formatError");
		}

		float applyAmount = NumberUtils.createFloat(strApplyAmount).floatValue();
		if (applyAmount < 50) {
			throw new ValidationException("error.applyAmount.lessThanLimit");
		}
		
		
		if(account.getBalance().floatValue() < applyAmount){
			throw new ValidationException("error.applyAmount.exceedBalance");
		}

		int pendingWithdrawRequestCount = accountRepository.getPendingWithdrawRequestCount(requestMap);
		if (pendingWithdrawRequestCount > 0) {
			throw new ValidationException("error.withdrawRequest.pendingExists");
		}
	}

}

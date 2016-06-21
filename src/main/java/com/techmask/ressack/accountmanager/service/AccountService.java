package com.techmask.ressack.accountmanager.service;

import java.util.Map;

import com.techmask.ressack.usermanager.domain.User;

public interface AccountService {
	public void addAccount(User user);
	public void processAccountCalculate();
	public void performWithdrawRequest(Map<String,Object> requestMap);
}

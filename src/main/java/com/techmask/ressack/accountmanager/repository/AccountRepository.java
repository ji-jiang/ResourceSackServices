package com.techmask.ressack.accountmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.accountmanager.domain.Account;
import com.techmask.ressack.usermanager.domain.User;

@Mapper
public interface AccountRepository {
	public void addAccount(User user);
	public void processAccountCalculate(Map<String,Object> requestMap);
	public void addWithdrawRequest(Map<String,Object> requestMap);
	public void updateAccountInfo(Map<String,Object> requestMap);
	public int getPendingWithdrawRequestCount(Map<String,Object> requestMap);
	public Account loadAccountByUserId(Map<String,Object> requestMap);
}

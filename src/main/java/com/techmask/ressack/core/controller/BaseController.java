package com.techmask.ressack.core.controller;

import java.util.Map;

import com.techmask.ressack.core.busobjs.ResultCode;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.log.LogUtils;


public class BaseController {
	
	protected static final String RESULT_CODE = "_resultCode";
	protected static final String ERROR_MSG = "_errMsg";
	
	
	public Map<String, Object> handleValidationExcpetion(ValidationException ve, Map<String, Object> resultMap){
		LogUtils.warn(ve.getMessage());
		resultMap.put(RESULT_CODE, ResultCode.VALIDATION_FAILED);
		resultMap.put(ERROR_MSG,ve.getMessage());
		return resultMap;
	}
	
	
	public Map<String, Object> handleException(Exception e, Map<String, Object> resultMap){
		e.printStackTrace();
		LogUtils.error(e.getMessage(),e);
		resultMap.put(RESULT_CODE, ResultCode.SYSTEM_ERROR);
		resultMap.put("ERROR_MSG",AppException.UNEXPECTED_ERROR);
		return resultMap;
	}

}

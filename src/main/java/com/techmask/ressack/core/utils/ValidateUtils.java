package com.techmask.ressack.core.utils;

public class ValidateUtils {

	public static void validateField(StringBuffer errorMsg, String fieldName, String fieldValue, boolean isRequired, int maxLenght){
		if(isRequired && StringUtils.isBlank(fieldValue)){
			if(errorMsg.length()>0){
				errorMsg.append(",");
			}
			errorMsg.append("error.").append(fieldName).append(".required");
		}
		
		if(!StringUtils.isBlank(fieldValue) && maxLenght>0 && fieldValue.length()>maxLenght){
			if(errorMsg.length()>0){
				errorMsg.append(",");
			}
			errorMsg.append("error.").append(fieldName).append(".length");
		}
	}
}

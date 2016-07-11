package com.techmask.ressack.core.codetablemanager.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techmask.ressack.core.codetablemanager.domain.Code;
import com.techmask.ressack.core.codetablemanager.service.CodeTableService;

@Component
public class CodeTableUtils {

	
	private static CodeTableService codeTableService;
	
	@Autowired
	public CodeTableUtils(CodeTableService codeTableService){
		CodeTableUtils.codeTableService = codeTableService;
	}
	
	
	public static String getCodeDesc(String fieldName, String code){
		String result = null;
		
		List<Code> codes = codeTableService.loadAllCodeByFieldName(fieldName);
		for(int i=0;i<codes.size();i++){
			Code theCode = codes.get(i);
			if(theCode.getCode().equalsIgnoreCase(code)){
				result = theCode.getCodeDesc();
				break;
			}
		}
		return result;
	}
}

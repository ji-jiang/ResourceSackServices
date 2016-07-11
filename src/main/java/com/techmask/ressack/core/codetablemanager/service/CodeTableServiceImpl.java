package com.techmask.ressack.core.codetablemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.codetablemanager.domain.Code;
import com.techmask.ressack.core.codetablemanager.domain.CodeField;
import com.techmask.ressack.core.codetablemanager.repository.CodeTableRepository;

@Service
public class CodeTableServiceImpl implements CodeTableService{
	
	@Autowired
	private CodeTableRepository codeTableRepository;

	@Override
	public CodeField loadCodeFieldByFieldName(String fieldName) {
		CodeField codeField = codeTableRepository.loadCodeFieldByFieldName(fieldName);
		return codeField;
	}

	@Override
	public List<Code> loadAllCodeByFieldName(String fieldName) {
		CodeField codeField = loadCodeFieldByFieldName(fieldName);
		
		String script = codeField.getLoadScript();
		List<Code> codes = null;
		if(script.startsWith("LOOKUP")){
			String codeType = fieldName;
			codes = codeTableRepository.loadAllCodeByCodeType(codeType);
		} 
		
		return codes;
	}
}

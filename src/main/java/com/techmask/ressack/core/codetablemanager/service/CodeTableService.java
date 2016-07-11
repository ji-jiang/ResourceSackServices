package com.techmask.ressack.core.codetablemanager.service;

import java.util.List;

import com.techmask.ressack.core.codetablemanager.domain.Code;
import com.techmask.ressack.core.codetablemanager.domain.CodeField;

public interface CodeTableService {

	public CodeField loadCodeFieldByFieldName(String fieldName);
	public List<Code> loadAllCodeByFieldName(String fieldName);
}

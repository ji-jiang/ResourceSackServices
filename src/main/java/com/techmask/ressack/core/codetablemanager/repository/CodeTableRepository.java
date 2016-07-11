package com.techmask.ressack.core.codetablemanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.core.codetablemanager.domain.Code;
import com.techmask.ressack.core.codetablemanager.domain.CodeField;

@Mapper
public interface CodeTableRepository {
	
	public CodeField loadCodeFieldByFieldName(String fieldName);
	public List<Code> loadAllCodeByCodeType(String codeType);

}

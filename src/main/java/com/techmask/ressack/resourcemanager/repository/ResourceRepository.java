package com.techmask.ressack.resourcemanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceRepository {
	

	@Results( value = { @Result(property = "id", column = "resource_id"),
			@Result(property = "downloadUrl", column = "download_url"),
			@Result(property = "downloadPassword", column = "download_password"),
			@Result(property = "paymentType", column = "payment_type"),
			@Result(property = "paymentAmount", column = "payment_amount"),
			@Result(property = "subCategory", column = "sub_category"),
			@Result(property = "createdDate", column = "created_date"),
			@Result(property = "createdBy", column = "created_by"),
			@Result(property = "updatedDate", column = "updated_date"),
			@Result(property = "updatedBy", column = "updated_by"),})
	@Select("select * from resource")
	public List<Resource> loadResource();
	
	
	@Select("select * from resource where 1=1 limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResource(Map<String,Object> requestMap);
	
	@Select("select * from resource where category=#{category} limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResourceByCategory(Map<String,Object> requestMap);

	@Insert("insert into resource (resource_name,email,role,token_key) values (#{resourceName},#{email},#{role},#{tokenKey})")
	public Resource addResource(Resource resource);

	@Select("select * from resource where resource_id=#{resourceId}")
	@ResultMap("loadResource-void")
	public Resource loadResourceById(String resourceId);

	
}

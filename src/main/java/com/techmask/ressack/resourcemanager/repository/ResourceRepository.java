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
			@Result(property = "desc", column = "description"),
			@Result(property = "origUrl", column = "orig_url"),
			@Result(property = "downloadUrl", column = "download_url"),
			@Result(property = "downloadPassword", column = "download_password"),
			@Result(property = "paymentType", column = "payment_type"),
			@Result(property = "paymentAmount", column = "payment_amount"),
			@Result(property = "ownerId", column = "owner_id"),
			@Result(property = "ownerName", column = "user_name"),
			@Result(property = "subCategory", column = "sub_category"),
			@Result(property = "createdDate", column = "created_date"),
			@Result(property = "createdBy", column = "created_by"),
			@Result(property = "updatedDate", column = "updated_date"),
			@Result(property = "updatedBy", column = "updated_by"),})
			
	@Select("select r.*,u.user_name from resource r,user u where r.owner_id=u.user_id")
	public List<Resource> loadResource();
	
	
	@Select("select r.*,u.user_name from resource r,user u where (category=#{category} or 'ALL'=#{category}) and (sub_category=#{subCategory} or 'ALL'=#{subCategory}) and r.owner_id=u.user_id limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResource(Map<String,Object> requestMap);
	
	@Select("select r.*,u.user_name from resource r,user u where ('ALL'=#{keywords} or MATCH (r.title) AGAINST(#{keywords})) and  r.owner_id=u.user_id limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResourceByKeywords(Map<String,Object> requestMap);

	@Insert("insert into resource (resource_name,email,role,token_key) values (#{resourceName},#{email},#{role},#{tokenKey})")
	public Resource addResource(Resource resource);

	@Select("select * from resource where resource_id=#{resourceId}")
	@ResultMap("loadResource-void")
	public Resource loadResourceById(String resourceId);

	
}

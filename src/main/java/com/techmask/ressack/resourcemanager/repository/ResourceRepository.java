package com.techmask.ressack.resourcemanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceRepository {
	@Results(value = { @Result(property = "id", column = "resourceId"),
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
	public List<Resource> loadAllResource();

	@Insert("insert into resource (resource_name,email,role,token_key) values (#{resourceName},#{email},#{role},#{tokenKey})")
	public Resource addResource(Resource resource);

	@Select("select * from resource where resource_id=#{resourceId}")
	@ResultMap("loadAllResource-void")
	public Resource loadResourceById(String resourceId);

	@Select("select * from resource where token_key=#{tokenKey} and status='VALID'")
	@ResultMap("loadAllResource-void")
	public Resource loadResourceByTokenKey(String tokenKey);

	@Update("update resource set token_key=#{tokenKey} where resource_id=#{id}")
	public Resource updateResource(Resource resource);

	@Delete("delete from resource where resource_id=#{id}")
	public void deleteResource(String resourceId);

	@Update("update resource set last_login_date=#{loginTime} where resource_id=#{id}")
	public void updateResourceLoginInfo(Resource resource);
}

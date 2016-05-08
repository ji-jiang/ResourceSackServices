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
	
	
	@Select("SELECT count(*) from resource where created_date>curdate() and owner_id=#{userId}")
	public int getNewCreatedCount(Map<String,Object> requestMap);
	
	@Select("SELECT LAST_INSERT_ID()")
	public long getLastInsertId();
	
	@Select("select r.*,u.user_name from resource r,user u where r.status='VALID' and (category=#{category} or 'ALL'=#{category}) and (sub_category=#{subCategory} or 'ALL'=#{subCategory}) and r.owner_id=u.user_id limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResource(Map<String,Object> requestMap);
	
	@Select("select r.*,u.user_name from resource r,user u where r.status='VALID' and ('ALL'=#{keywords} or MATCH (r.title) AGAINST(#{keywords})) and  r.owner_id=u.user_id limit #{_startRowIndex}, #{_pageSize}")
	@ResultMap("loadResource-void")
	public List<Resource> loadAllResourceByKeywords(Map<String,Object> requestMap);

	@Insert("insert into resource(title,description,category,sub_category,tags,status,orig_url,download_url,download_password,download_times,payment_type,payment_amount,owner_id,created_by,updated_by)values(#{title},#{desc},#{category},#{subCategory},#{tags},'PENDING',#{origUrl},#{downloadUrl},#{downloadPassword},0,'NA',null,#{userId},#{userName},#{userName})")
	public int addResource(Map<String,Object> resourceMap);

	@Select("select * from resource where resource_id=#{resourceId}")
	@ResultMap("loadResource-void")
	public Resource loadResourceById(String resourceId);

	
}

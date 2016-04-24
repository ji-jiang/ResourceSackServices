package com.techmask.ressack.usermanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.techmask.ressack.usermanager.domain.User;

public interface UserRepository {


	@Results(value = { @Result(property = "tokenKey", column = "token_key"),
			 @Result(property = "userName", column = "user_name"),
			 @Result(property = "id", column = "user_id"),
			 @Result(property = "role", column = "role")})
	@Select("select * from user")
	public List<User> loadAllUser();
	
	@Insert("insert into user (user_name,email,role,token_key) values (#{userName},#{email},#{role},#{tokenKey})")
	public User addUser(User user);

	@Select("select * from user where user_id=#{userId}")
	@ResultMap("loadAllUser-void")
	public User loadUserById(String userId);

	@Select("select * from user where token_key=#{tokenKey} and status='VALID'")
	@ResultMap("loadAllUser-void")
	public User loadUserByTokenKey(String tokenKey);

	@Update("update user set token_key=#{tokenKey} where user_id=#{id}")
	public User updateUser(User user);

	@Update("update user set status='INVALID' where user_id=#{id}")
	public void invalidateUser(String userId);
	
	@Update("update user set last_login_date=#{loginTime} where user_id=#{id}")
	public void updateUserLoginInfo(User user);

}

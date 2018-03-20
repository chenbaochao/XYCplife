package com.sjzxywlkj.cplife.dao;

import com.sjzxywlkj.cplife.pojo.User;

import java.util.List;

public interface UserMapper {
	int deleteByPrimaryKey(String userId);

	int insert(User record);

	User selectByPrimaryKey(String userId);

	List<User> selectAll();

	int updateByPrimaryKey(User record);

	User find1(String userId);

	User find2(String userId);

	User find3(String userId);

	Integer count(User userId);

	public List<User> selectByType(String type);

	String selectUserTypeById(User user);

	int selectUserTypeNum();

	int selectUserTypeNum1();

	int updateNameById(User user);

	List<User> selectUserByType(User user);
}
package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {
	/**
	 * 将用户添加到数据中
	 * @param user
	 */
	void addUser(User user);
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return 若没有查到，返回null
	 */
	User findUser(String username);
	/**
	 * 根据用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return 若没有查到，返回null
	 */
	User findUser(String username,String password);
	
}

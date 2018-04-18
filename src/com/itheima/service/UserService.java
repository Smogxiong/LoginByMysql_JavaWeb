package com.itheima.service;

import com.itheima.domain.User;
import com.itheima.exception.UserExistsException;

public interface UserService {
	/**
	 * 用户注册
	 * 用户名已经存在：抛出一个异常UserExistsException
	 * 在注册时需要对密码进行加密
	 * @param user
	 */
	void register(User user) throws UserExistsException;
	/**
	 * 用户登陆
	 * 注意：
	 * @param username
	 * @param password需要加密
	 * @return
	 */
	User login(String username,String password);
}

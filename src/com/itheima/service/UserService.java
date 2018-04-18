package com.itheima.service;

import com.itheima.domain.User;
import com.itheima.exception.UserExistsException;

public interface UserService {
	/**
	 * �û�ע��
	 * �û����Ѿ����ڣ��׳�һ���쳣UserExistsException
	 * ��ע��ʱ��Ҫ��������м���
	 * @param user
	 */
	void register(User user) throws UserExistsException;
	/**
	 * �û���½
	 * ע�⣺
	 * @param username
	 * @param password��Ҫ����
	 * @return
	 */
	User login(String username,String password);
}

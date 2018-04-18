package com.itheima.dao;

import com.itheima.domain.User;

public interface UserDao {
	/**
	 * ���û���ӵ�������
	 * @param user
	 */
	void addUser(User user);
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return ��û�в鵽������null
	 */
	User findUser(String username);
	/**
	 * �����û����������ѯ�û�
	 * @param username
	 * @param password
	 * @return ��û�в鵽������null
	 */
	User findUser(String username,String password);
	
}

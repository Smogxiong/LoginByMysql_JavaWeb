package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.dao.impl.UserDaoMysqlImpl;
import com.itheima.domain.User;
import com.itheima.exception.UserExistsException;
import com.itheima.service.UserService;
import com.itheima.util.MD5;

public class UserServiceImpl implements UserService {
	private UserDao dao = new UserDaoMysqlImpl();
	public User login(String username, String password) {
		return dao.findUser(username, password);
	}

	public void register(User user) throws UserExistsException {
		//���ж��û��Ƿ��Ѿ�����
		String username = user.getUsername();
		User u = dao.findUser(username);
		if(u!=null)
			throw new UserExistsException("�û����Ѿ�����");
		
		//�����ڲŽ���ע��
		user.setPassword(MD5.encode(user.getPassword()));//��������м���
		dao.addUser(user);
		
	}

}

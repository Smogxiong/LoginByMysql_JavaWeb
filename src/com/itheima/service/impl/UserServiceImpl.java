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
		//先判断用户是否已经存在
		String username = user.getUsername();
		User u = dao.findUser(username);
		if(u!=null)
			throw new UserExistsException("用户名已经存在");
		
		//不存在才进行注册
		user.setPassword(MD5.encode(user.getPassword()));//对密码进行加密
		dao.addUser(user);
		
	}

}

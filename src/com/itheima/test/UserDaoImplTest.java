package com.itheima.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;

public class UserDaoImplTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testAddUser() {
		User user = new User();
		user.setId("2");
		user.setUsername("wzhting");
		user.setPassword("sorry");
		user.setEmail("wzt@itcast.cn");
		user.setBirthday(new Date());
		dao.addUser(user);
	}

	@Test
	public void testFindUserString() {
		User user = dao.findUser("wzhting1");
		Assert.assertNull(user);
	}

	@Test
	public void testFindUserStringString() {
		User user = dao.findUser("wzhting","sorry");
		Assert.assertNotNull(user);
	}

}

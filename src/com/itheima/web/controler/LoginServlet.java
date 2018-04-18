package com.itheima.web.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {
	private UserService service = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//根据用户名和密码检测用户是否存在
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		User user = service.login(username, password);
		if(user==null){
			//不存在，转向全局消息页面，提示用户名或密码不正确
			request.setAttribute("message", "用户名或密码不正确");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//存在，把USER放到session中转向主页
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

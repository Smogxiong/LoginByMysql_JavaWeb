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
		//�����û������������û��Ƿ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		User user = service.login(username, password);
		if(user==null){
			//�����ڣ�ת��ȫ����Ϣҳ�棬��ʾ�û��������벻��ȷ
			request.setAttribute("message", "�û��������벻��ȷ");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		//���ڣ���USER�ŵ�session��ת����ҳ
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.itheima.web.controler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.itheima.domain.User;
import com.itheima.exception.UserExistsException;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.util.FillFormBean;
import com.itheima.web.formbean.UserFormBean;
//�����û�ע��
public class RegistServlet extends HttpServlet {
	private UserService service = new UserServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1����֤�û�����
			//1��1�������е����ݷ�װ��һ��FormBean��
		UserFormBean formBean = FillFormBean.toFromBean(request,UserFormBean.class);
		
		if(!formBean.validate()){
			//2����֤��ͨ������ת������ҳ�档�����û�ԭ��д������
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/regist.jsp").forward(request, response);
			return;
		}
		//3����֤ͨ�������ģ��
		try{
			User user = new User();
			ConvertUtils.register(new DateLocaleConverter(), Date.class);
			//ע��һ������ת���������String���͵�birthday��Date���͵�Birthday
			BeanUtils.copyProperties(user, formBean);
			//�����������ͺ�String����
			//4��ת���д�������ʾ��ת��һ��ȫ�ֵ���Ϣҳ��
			service.register(user);
			request.setAttribute("message", "ע��ɹ�,3���ת����ҳ��<meta http-equiv='Refresh' content='3;URL="+request.getContextPath()+"/index.jsp'");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(UserExistsException e){
			//5���û�����:UserExistsException
			//�������ݣ�����ʾ�û��û����Ѿ�����
			formBean.getErrors().put("username", "�û����Ѿ�����");
			request.setAttribute("formBean", formBean);
			request.getRequestDispatcher("/WEB-INF/pages/regist.jsp").forward(request, response); 
		}catch(Exception e){
			request.setAttribute("message", "ע��ʧ��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

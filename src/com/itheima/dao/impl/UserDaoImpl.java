package com.itheima.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.exception.DaoException;
import com.itheima.util.XmlUtil;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		try{
			Document document = XmlUtil.getDocument();
			Element root = document.getRootElement();
			root.addElement("user")
				.addAttribute("id", user.getId())
				.addAttribute("username", user.getUsername())
				.addAttribute("password", user.getPassword())
				.addAttribute("email", user.getEmail())
				.addAttribute("birthday", user.getBirthday().toLocaleString());
			XmlUtil.write2xml(document);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	public User findUser(String username) {
		try{
			String xpath = "//user[@username='"+username+"']";
			Document document = XmlUtil.getDocument();
			Element element = (Element)document.selectSingleNode(xpath);
			if(element==null)
				return null;
			User user = new User();
			user.setId(element.attributeValue("id"));
			user.setUsername(element.attributeValue("username"));
			user.setPassword(element.attributeValue("password"));
			user.setEmail(element.attributeValue("email"));
			String sbirthday = element.attributeValue("birthday");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(df.parse(sbirthday));
			return user;
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	public User findUser(String username, String password) {
		try{
			String xpath = "//user[@username='"+username+"' and @password='"+password+"']";
			Document document = XmlUtil.getDocument();
			Element element = (Element)document.selectSingleNode(xpath);
			if(element==null)
				return null;
			User user = new User();
			user.setId(element.attributeValue("id"));
			user.setUsername(element.attributeValue("username"));
			user.setPassword(element.attributeValue("password"));
			user.setEmail(element.attributeValue("email"));
			String sbirthday = element.attributeValue("birthday");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			user.setBirthday(df.parse(sbirthday));
			return user;
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}

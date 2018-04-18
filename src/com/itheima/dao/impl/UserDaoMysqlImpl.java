package com.itheima.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.exception.DaoException;
import com.itheima.util.JdbcUtil;

public class UserDaoMysqlImpl implements UserDao {

	public void addUser(User user) {
		Connection conn=null;
//		Statement stmt=null;
		PreparedStatement stmt=null;
		
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
//			stmt=conn.createStatement();
//			String sql="INSERT INTO users (username,password,email,birthday) VALUES ('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getEmail()+"','"+user.getBirthday().toLocaleString()+"');";
			String sql="INSERT INTO users (username,password,email,birthday) VALUES (?,?,?,?)";
//			
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getEmail());
			stmt.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
			stmt.executeUpdate();
		} catch (Exception e) {
			throw  new DaoException();
		}finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	public User findUser(String username) {
		
		Connection conn=null;
		PreparedStatement stmt=null;  //¿É·ÀÖ¹sql×¢Èë
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
//			stmt=conn.createStatement();
//			String sql="select *from users where username='"+username+"'";
			stmt=conn.prepareStatement("select *from users where username=?");
			stmt.setString(1, username);
			rs=stmt.executeQuery();
			if(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("id")+"");
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			else
				return null;
			} catch (Exception e) {
			throw  new DaoException();
		}finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

	public User findUser(String username, String password) {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			conn=JdbcUtil.getConnection();
//			stmt=conn.createStatement();
			//' or 1=1 username='
//			String sql="select *from users where username='"+username+"' and password='"+password+"'";
			stmt=conn.prepareStatement("select *from users where username=? and password=?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			if(rs.next()) {
				User user=new User();
				user.setId(rs.getInt("id")+"");
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setBirthday(rs.getDate("birthday"));
				return user;
			}
			else
				return null;
			} catch (Exception e) {
			throw  new DaoException();
		}finally {
			JdbcUtil.release(rs, stmt, conn);
		}
	}

}

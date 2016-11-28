package com.Lauguobin.www.dao;

import java.io.Serializable;
import java.sql.*;

import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.*;

/**
 * 
 * 这是一个专门用来处理用户存储和修改的访问数据库的类
 * @author GB菌
 *
 */
public class UserDao extends IOToSQL implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 添加一个账户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public<T> int add(T t)throws ClassNotFoundException, SQLException 
	{
		User user = (User)t;
		Connection con = DbUtil.getCon();
		String sql = "insert into user values(null , ? , ? , ?)";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setString(1, user.getUser());
		stm.setString(2, user.getPassword());
		stm.setBoolean(3, user.getIdentity());
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result; 
	}
	
	/**
	 * 更改账户密码
	 * @param pnew 新密码
	 * @param id 账户id
	 * @return 更改成功是1
	 * @throws Exception
	 */
	@Override
	public<T> int update(T t) throws ClassNotFoundException, SQLException
	{
		User user = (User)t;
		Connection con = DbUtil.getCon();
		String sql = "update user set password = ? , identity = ? where id = ?";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setString(1, user.getPassword());
		stm.setBoolean(2,user.getIdentity());
		stm.setInt(3, user.getId());
		int result = stm.executeUpdate();			
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result;
	}

	/**
	 * 传入id删除一个账户
	 */
	@Override
	public int del(int id) throws ClassNotFoundException, SQLException
	{
		Connection con = DbUtil.getCon();
		String sql = "delete from user where id = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); 				
		return result;
	}
}

package com.Lauguobin.www.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.*;

/**
 * 一个课程管理的类
 * @author GB菌
 *
 */
public class CourseDao extends IOToSQL implements Serializable
{
	private static final long serialVersionUID = -4573860917230806875L;

	@Override
	public <T>  int add(T t) throws ClassNotFoundException, SQLException
	{
		Course c = (Course)t;
		//调用dao获取连接
		Connection con = DbUtil.getCon();
		String sql = "CALL addCourse( ? , ? , ? )";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setInt(1, c.getCno());
		stm.setString(2, c.getCName());
		stm.setInt(3,c.getCcredit());
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result; 
	}
	
	@Override
	public <T> int update(T t) throws ClassNotFoundException, SQLException
	{
		Course c = (Course)t;
		//调用连接数据库
		Connection con = DbUtil.getCon();
		String sql = "CALL updateCourse(?, ? , ?)";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setString(2, c.getCName());
		stm.setInt(3,c.getCcredit());
		stm.setInt(1,c.getCno());
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result;
	}
	
	@Override
	public int del(int cno) throws ClassNotFoundException, SQLException 
	{
		Connection con = DbUtil.getCon();
		String sql = "delete from course where cno = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, cno);
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); 				// 关闭statement 和 连接
		return result;
	}
	
}

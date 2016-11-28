package com.Lauguobin.www.dao;

import java.sql.*;

import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.*;
import java.io.Serializable;

/**
 * 一个学生管理的类
 * @author GB菌
 *
 */
public class StudentDao  extends IOToSQL  implements Serializable
{
	private static final long serialVersionUID = 1L;

	/**
	 * 添加一个学生的信息
	 * @param stu
	 * @return 添加成功的个数，成功是1
	 * @throws Exception
	 */
	public<T> int add(T t) throws ClassNotFoundException, SQLException
	{
		Student stu = (Student)t;
		//调用dao获取连接
		Connection con = DbUtil.getCon();
		String sql = "CALL addStu( ? , ? , ? , ? , ? , ? )";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setInt(1, stu.getId());
		stm.setString(2, stu.getStuName());
		stm.setString(3,stu.getStuGrade());
		stm.setInt(4,stu.getStuClass());
		stm.setString(5,stu.getMobile());
		stm.setString(6,stu.getEmail());
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result; 
	}
	
	/**更新一个学生的信息
	 * 
	 * @param stu
	 * @return 更新成功的个数，成功是1
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws Exception
	 */
	public<T> int update(T t) throws ClassNotFoundException, SQLException
	{
		Student stu = (Student)t;
		//调用连接数据库
		Connection con = DbUtil.getCon();
		String sql = "update student set stuName = ? , stuGrade = ? , stuClass = ? , Mobile = ? , Email = ?  where id = ?";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setString(1, stu.getStuName());
		stm.setString(2,stu.getStuGrade());
		stm.setInt(3,stu.getStuClass());
		stm.setString(4,stu.getMobile());
		stm.setString(5,stu.getEmail());
		stm.setInt(6,stu.getId());
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result;
	}
	
	/**
	 * 这个方法可以删除一个学生
	 * 
	 * @param id
	 * @return 删除成功与否的信息
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws Exception
	 */
	public int del(int id) throws ClassNotFoundException, SQLException
	{
		Connection con = DbUtil.getCon();
		String sql = "delete from student where id = ?";
		PreparedStatement stm = con.prepareStatement(sql);
		stm.setInt(1, id);
		int result = stm.executeUpdate();
		DbUtil.close(stm, con); 				
		return result;
		}
	


}

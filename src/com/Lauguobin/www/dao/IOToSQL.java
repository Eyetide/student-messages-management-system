package com.Lauguobin.www.dao;

import java.sql.*;
import java.util.*;

import com.Lauguobin.www.util.BeanUtil;
import com.Lauguobin.www.util.DbUtil;
import com.Lauguobin.www.util.JudgeUtil;

/**
 * 一个课程管理、学生管理、用户管理的抽象类，里面有一些通用的方法
 * @author GB菌
 *
 */
public abstract class IOToSQL
{
	/**
	 * 给dao类提供增加操作的抽象方法
	 */
	public abstract <T> int add(T t)throws ClassNotFoundException, SQLException ;
	/**
	 * 给dao类提供更新操作的抽象方法
	 */
	public abstract <T> int update(T t) throws ClassNotFoundException, SQLException;
	/**
	 * 给dao类提供删除操作的抽象方法
	 */
	public abstract int del(int id) throws ClassNotFoundException, SQLException;
	
	/**
	 * 将map对象转换成具体的po对象
	 * @param className
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public<T> List<T> show(String className) throws Exception 
	{
		List<T> list = new ArrayList<T>();

		String sql = "select * from " +  className;
		Connection con = DbUtil.getCon();
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();  //返回结果
		
		//把所有的信息打包封装
		List<T>  retultList = resultSetToList(rs); 
		for(int i = 0;i < retultList.size() ; i++)
		{
			T stu = (T)BeanUtil.MapToBean(Class.forName("com.Lauguobin.www.po." + className).newInstance(),(Map<?, ?>)retultList.get(i)); 
			list.add(stu);
		}
		
		DbUtil.close(stm, con);
		return list;
	}
	
	/**
	 * 显示搜索信息的列表
	 * 
	 * @param order 传入的字符串语句
	 * @param str 传出的搜索条件
	 * @return 从数据库所有搜索出来的信息的对象的容器
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public<T> List<T> show(String order,String str,String className) throws Exception
	{
		//定义要返回的容器
		List<T> list = new ArrayList<T>();
		
		Connection con = DbUtil.getCon();
		PreparedStatement stm = con.prepareStatement(order);
		
		//判断搜索框文本域的输入，如果全是数字，择判定为搜索id，否择判定为搜索名称
		if(str.length()<10&&JudgeUtil.isInteger(str))
			stm.setInt(1, Integer.parseInt(str));
		else
			stm.setString(1, str);
		ResultSet rs = stm.executeQuery();  //返回结果
		
		//把所有的信息打包封装
		List<T>  retultList = resultSetToList(rs); 
		for(int i = 0;i < retultList.size() ; i++)
		{
			T stu = (T)BeanUtil.MapToBean(Class.forName("com.Lauguobin.www.po." + className).newInstance(),(Map<?, ?>)retultList.get(i)); 
			list.add(stu);
		}
		DbUtil.close(stm, con);
		return list;
	}
	
	/**
	 * 将resaultset中的数据包成map类
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public<T> List<T> resultSetToList(ResultSet rs) throws SQLException, ClassNotFoundException
	{
	       if(rs==null)
	       {
	           return null;
	       }

	       ResultSetMetaData md = rs.getMetaData();
	       int columnCount = md.getColumnCount();

	       List list = new ArrayList();
	       Map<String, Object> rowData;
	       while (rs.next()){
	           rowData = new HashMap<String, Object>(columnCount);
	           for (int i=1; i<=columnCount; i++)
	           {
	        		   rowData.put(md.getColumnName(i),rs.getObject(i));
	           }
	           list.add( rowData);
	       }
	     
	       return list;
   }
	
	/**
	 * 可以获得数据库一张表的列数
	 * @param tableName 传入参数 表名
	 * @return 列数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getTableList(String tableName) throws ClassNotFoundException, SQLException
	{
		Connection con = DbUtil.getCon();
		String sql = "SELECT COUNT(*) FROM information_schema.columns WHERE table_schema='db_student' AND table_name= '" + tableName+"'";
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
}

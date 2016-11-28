package com.Lauguobin.www.dao;

import java.sql.*;

import com.Lauguobin.www.po.Score;
import com.Lauguobin.www.util.DbUtil;

/**
 * 一个成绩管理的类，由于显示列表没有具体的类，所以操作有所不同
 * @author GB菌
 *
 */
public class ScoreDao
{
	/**
	 * 多表查询显示信息，由于没有具体的po类对象，所以直接返回数组
	 * @param id
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public Object[][] show(int id,String search) throws Exception 
	{
		String sql = "SELECT a.id , a.stuName , b.Cno , b.Cname , c.Score , c.Credit "
				+ "FROM student a , course b , score c WHERE c.id = a.`id`  AND c.cno = b.`Cno` AND a.id = "+ id + search;
		Connection con = DbUtil.getCon();
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();  //返回结果
		int courseCount = getCourseCount(id,search); //获取搜索结果的行数，返回给数组作为构件数组参数
		Object[][] obj = new Object[courseCount][6];
		int i = 0;
		while(rs.next())
		{
			obj[i][0] = rs.getInt(1);
			obj[i][1] = rs.getString(2);
			obj[i][2] = rs.getInt(3);
			obj[i][3] = rs.getString(4);
			obj[i][4] = rs.getInt(5);
			obj[i][5] = rs.getInt(6);
			if(i<courseCount-1)
				i++;
		}
		DbUtil.close(stm, con);
		return obj;
	}

	/**
	 * 更改成绩。
	 * @param c 成绩对象
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int alterScore(Score c) throws ClassNotFoundException, SQLException
	{
		//调用连接数据库
		Connection con = DbUtil.getCon();
		String sql = "CALL updateScore( ? , ? , ?)";
		PreparedStatement stm = con.prepareStatement(sql);  
		stm.setInt(1, c.getScore());
		stm.setInt(2,c.getId());
		stm.setInt(3,c.getCno());
		int result = stm.executeUpdate();				//用prepaered不能带参数
		DbUtil.close(stm, con); // 关闭statement 和 连接
		return result;
	}

	/**
	 * 获取所有搜索到的课程数
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private int getCourseCount(int id,String search) throws ClassNotFoundException, SQLException
	{
		Connection con = DbUtil.getCon();
		
		//将执行查询：查询结果的行数
		String sql = "SELECT COUNT(*) FROM (SELECT a.id , a.stuName , b.Cno , b.Cname , c.Score , c.Credit "
				+ "FROM student a , course b , score c WHERE c.id = a.`id`  AND c.cno = b.`Cno` AND a.id = "+ id + search+") a";
		PreparedStatement stm = con.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		rs.next();
		int n = rs.getInt(1);
		
		return n;
	}
}

package com.Lauguobin.www.service;

import java.sql.SQLException;

import com.Lauguobin.www.dao.*;
import com.Lauguobin.www.po.Score;
import com.Lauguobin.www.util.JudgeUtil;

public class ScoreService
{
	/**
	 * 获得一个id的信息，返回所有该id的成绩
	 * @param id
	 * @return
	 */
	public Object[][] getAllScore(int id)
	{
		ScoreDao sd = new ScoreDao();
		
		try
		{
			return sd.show(id,"");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 返回搜索的信息
	 * @param str 传入搜索条件
	 * @param id 学生id
	 * @return
	 */
	public Object[][] getSearch(String str,int id)
	{
		ScoreDao sd = new ScoreDao();
		
		try
		{
			if(JudgeUtil.isInteger(str))
				return sd.show(id, " AND b.`Cno` LIKE '"+str+"'");
			else
				return sd.show(id, " AND b.`Cname` LIKE '"+str+"'");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 判断输入的是不是分数，并且传入课程号和学生id进行查找放置该分数的位置
	 * @param score
	 * @param cno
	 * @param id
	 * @return
	 */
	public int isScore(String score , int cno , int id)
	{
		if(score.equals(""))
			return 4;
		else if(!JudgeUtil.isInteger(score))
			return 1;
		else if(score.length()>5||Integer.parseInt(score)>150)
			return 2;
		
		ScoreDao sd = new ScoreDao();
		Score s = new Score(Integer.parseInt(score),cno,id);
		try
		{
			sd.alterScore(s);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			return 3;
		}
		return 0;
	}
	
}

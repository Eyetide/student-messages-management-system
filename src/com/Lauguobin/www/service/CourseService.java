package com.Lauguobin.www.service;

import java.util.ArrayList;
import java.util.List;

import com.Lauguobin.www.dao.*;
import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.*;

public class CourseService extends ImformationMenager
{
	/**
	 * 这个方法对传入的课程信息提供筛查并且调用dao类进行写入
	 * @param cno
	 * @param name
	 * @param credit
	 * @return
	 */
	public int ifCourse(String cno,String name,String credit)
	{
		if((cno.equals(""))||(!JudgeUtil.isInteger(cno)))
			return 1;
		else if(cno.length()>5)
			return 3;
		else if(ifKeyClash(cno))
			return 2;

		if(name.equals("")||(!JudgeUtil.isChinese(name))||name.length()>30)
			return 4;
		else if(name.length()>20)
			return 5;
		else if( isCourseNameClash(name))
			return 8;
		
		if((credit.equals(""))||(!JudgeUtil.isInteger(credit)))
			return 6;
		else if(credit.length()>1)
			return 7;
		
		//如果通过了所有的判断，则封装对象
		int icno = Integer.parseInt(cno);
		int icre = Integer.parseInt(credit);
		Course one = new Course(icno,name,icre);

		try
		{
			//尝试添加信息
			CourseDao sd = new CourseDao();
			sd.add(one);
			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 9;
		}
	}
	
	/**
	 * 这个方法是对已有的课程进行更新操作
	 * @param cno
	 * @param name
	 * @param credit
	 * @return
	 */
	public int ifCourse(int cno,String name,String credit)
	{
		
		if(name.equals("")||(!JudgeUtil.isChinese(name))||name.length()>30)
			return 1;
		else if(name.length()>20)
			return 2;
		
		if((credit.equals(""))||(!JudgeUtil.isInteger(credit)))
			return 3;
		else if(credit.length()>1)
			return 4;
		
		//如果通过了所有的判断，则封装对象
		int icre = Integer.parseInt(credit);
		Course one = new Course(cno,name,icre);
		try
		{
			//尝试更新信息
			CourseDao sd = new CourseDao();
			sd.update(one);
			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 6;
		}
	}
	
	 /**
	  * 判断id是否冲突
	  * @param str
	  * @return
	  */
	@Override
	 public boolean ifKeyClash(String str)
	 {
		 //获取所有id
		 int[] allId = getAllKey();
		 
		 //把文本域字符串转换成id
		 int flag = Integer.parseInt(str);
		 
		 for(int i = 0; i < allId.length ; i++)
			 if(flag == allId[i])
				 return true;
		 
		 return false;
	 }
	 
	 
	/**这个方法是用来返回一个二维数组，刷新JTable用的
	 * 
	 * @return 封装好的二维数组
	 */
	 @Override
	public Object[][] getAll()
	{
		CourseDao sd = new CourseDao();
		
		List<Course> slist = new ArrayList<Course>();
		try
		{
			//得到一个含有所有课程信息的容器
			slist = sd.show("Course") ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Object info[][] = new Object[slist.size()][3];
		Course c = new Course();
		
		for(int i = 0;i<slist.size();i++)
		{
			//获取容器中的信息，并添加到二维数组中供给JTable作为参数
			c = slist.get(i);
			info[i][0] = c.getCno();	
			info[i][1] = c.getCName();
			info[i][2] = c.getCcredit();
		}
		return info;
	}
	
	/**
	 * 判断搜索框的数据并决定执行什么操作
	 * @param str 搜索框传进来的数据
	 * @return 封装好的二维数组
	 */
	public Object[][] getAll(String str) 
	{
		CourseDao cd = new CourseDao();
		List<Course> courseList = new ArrayList<Course>();
		try
		{
			//判断搜索框输入是否整数，如果是整数，传入数据库搜索id命令，否则传入名称模糊搜索命令。然后得到一个容器
			if(JudgeUtil.isInteger(str))
				courseList = cd.show("select * from course where Cno = ?",str,"Course");
			else
				courseList = cd.show("SELECT * FROM course WHERE Cname LIKE ?",str,"Course");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Object info[][] = new Object[courseList.size()][3];
		Course sm = new Course();
		
		for(int i = 0;i<courseList.size();i++)
		{
			//获取容器中的信息，并添加到二维数组中供给JTable作为参数
			sm = courseList.get(i);
			info[i][0] = sm.getCno();	
			info[i][1] = sm.getCName();
			info[i][2] = sm.getCcredit();
		}
		return info;
	}
	
	/**
	 * 获得所有的课程号
	 */
	 @Override
	protected int[] getAllKey()
	{
		CourseDao sd = new CourseDao();
		
		List<Course> slist = new ArrayList<Course>();
		try
		{
			//获取所有课程信息
			slist = sd.show("Course") ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		int info[] = new int[slist.size()];
		Course sm = new Course();
		
		//把id挑出来存入数组
		for(int i = 0;i<slist.size();i++)
		{
			sm = slist.get(i);
			info[i] = sm.getCno();	
		}
		
		//返回一个数组
		return info;
	}
	 
	 /**
	  * 判断课程号是否冲突
	  * @param name
	  * @return
	  */
	public boolean isCourseNameClash(String name)
	{
		CourseDao sd = new CourseDao();
		
		List<Course> slist = new ArrayList<Course>();
		try
		{
			//得到一个含有所有课程信息的容器
			slist = sd.show("Course") ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Course c = new Course();
		
		for(int i = 0;i<slist.size();i++)
		{
			//获取容器中的信息
			c = slist.get(i);
			if(name.equals(c.getCName()))
				return true;
		}	
		return false;
	}
}

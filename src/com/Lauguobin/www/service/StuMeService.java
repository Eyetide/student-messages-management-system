package com.Lauguobin.www.service;

import java.util.ArrayList;
import java.util.List;

import com.Lauguobin.www.dao.*;
import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.*;

public class StuMeService extends ImformationMenager
{
	/**
	 * 这一行用于判断输入的正确性并且封装数据以提供增加操作
	 * @param id
	 * @param n
	 * @param g
	 * @param c
	 * @param m
	 * @param em
	 * @return
	 */
	public int ifHaveMessage(String id ,String n ,String g ,String c ,String m,String em)
	{

		if((id.equals(""))||(!JudgeUtil.isInteger(id)))
			return 1;
		else if(id.length()>9)
			return 3;
		else if(ifKeyClash(id))
			return 2;

		
		if(n.equals("")||!JudgeUtil.isName(n)||n.length()>20)
			return 4;
		
		if(g.equals("")||g.length() > 10)
			return 5;
		else	if(!g.equals("高三")&&!g.equals("高二")&&!g.equals("高一")&&!g.equals("初三")&&!g.equals("初二")&&!g.equals("初一"))
		{
			if(!g.equals("六年级")&&!g.equals("五年级")&&!g.equals("四年级")&&!g.equals("三年级")&&!g.equals("二年级")&&!g.equals("一年级"))
				return 6;
		}
		
		if((c.equals(""))||(!JudgeUtil.isInteger(c))||(c.length()>3)||(Integer.parseInt(c)==0))
			return 7;
		else if(Integer.parseInt(c)>100)
			return 8;
		
		//联系方式可以为空，但输入了就会判断正确性
		if(!m.equals(""))
			if(!JudgeUtil.isMobileNO(m))
				return 9;

		//邮箱，同联系方式
		if(!em.equals(""))
			if(!JudgeUtil.isEmail(em))
				return 10;
		
		//如果通过了所有的判断，则封装对象
		int iid = Integer.parseInt(id);
		int ic = Integer.parseInt(c);
		Student one = new Student(iid,n,g,ic,m,em);
		
		try
		{
			//调用dao，尝试添加
			StudentDao sd = new StudentDao();
			sd.add(one);
			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 11;
		}
	}
	
	
	/**
	 * 这个 是判断数据正确性，然后封装数据再用于更新数据库
	 * @param id 学生id
	 * @param n 学生名
	 * @param g 学生年级
	 * @param c 学生班级
	 * @param m 学生电话
	 * @param em 邮箱
	 * @return
	 */

	public int ifUpMessage(int id,String n ,String g ,String c ,String m,String em)
	{

		if(n.equals("")||!JudgeUtil.isName(n)||n.length()>20)
			return 1;
		
		if(g.equals("")||g.length() > 10)
			return 2;
		else	if(!g.equals("高三")&&!g.equals("高二")&&!g.equals("高一")&&!g.equals("初三")&&!g.equals("初二")&&!g.equals("初一"))
		{
			if(!g.equals("六年级")&&!g.equals("五年级")&&!g.equals("四年级")&&!g.equals("三年级")&&!g.equals("二年级")&&!g.equals("一年级"))
				return 3;
		}
		
		if((c.equals(""))||(!JudgeUtil.isInteger(c))||c.length()>2||(Integer.parseInt(c)==0))
			return 4;
		else if(Integer.parseInt(c)>100)
			return 5;
		
		if(!m.equals("") )
			if(!JudgeUtil.isMobileNO(m))
				return 6;
		
		if(!em.equals(""))
			if(!JudgeUtil.isEmail(em))
				return 7;
		
		//如果通过了所有的判断，则封装对象
		int ic = Integer.parseInt(c);
		Student one = new Student(id,n,g,ic,m,em);
		
		try
		{
			//尝试更新信息
			StudentDao sd = new StudentDao();
			sd.update(one);
			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 8;
		}
	}
	
	/**
	 * 判断搜索框的数据并决定执行什么操作
	 * @param str 搜索框传进来的数据
	 * @return 封装好的二维数组
	 */
	public Object[][] getAll(String str) 
	{
		StudentDao sd = new StudentDao();
		List<Student> searchList = new ArrayList<Student>();
		try
		{
			//判断搜索框输入是否整数，如果是整数，传入数据库搜索id命令，否则传入名称模糊搜索命令。然后得到一个容器
			if(JudgeUtil.isInteger(str))
				searchList = sd.show("select * from student where id = ?",str,"Student");
			else
				searchList = sd.show("SELECT * FROM student WHERE stuName LIKE ?",str,"Student");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Object info[][] = new Object[searchList.size()][6];
		Student sm = new Student();
		
		for(int i = 0;i<searchList.size();i++)
		{
			//获取容器中的信息，并添加到二维数组中供给JTable作为参数
			sm = searchList.get(i);
			info[i][0] = sm.getId();	
			info[i][1] = sm.getStuName();
			info[i][2] = sm.getStuGrade();
			info[i][3] = sm.getStuClass();
			info[i][4] = sm.getMobile();
			info[i][5] = sm.getEmail();
		}
		return info;
	}
	
	 /**
	  * 判断id是否冲突
	  * @param str
	  * @return
	  */
	@Override
	 protected boolean ifKeyClash(String str)
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
	public Object[][] getAll()
	{
		StudentDao sd = new StudentDao();
		
		List<Student> slist = new ArrayList<Student>();
		try
		{
			//得到一个含有所有学生信息的容器
			slist = sd.show("Student") ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Object info[][] = new Object[slist.size()][6];
		Student sm;
		
		for(int i = 0;i<slist.size();i++)
		{
			//获取容器中的信息，并添加到二维数组中供给JTable作为参数
			sm = slist.get(i);
			info[i][0] = sm.getId();	
			info[i][1] = sm.getStuName();
			info[i][2] = sm.getStuGrade();
			info[i][3] = sm.getStuClass();
			info[i][4] = sm.getMobile();
			info[i][5] = sm.getEmail();
		}
		return info;
	}
	
	/**
	 * 获取所有学生的学号，调用了dao的东西，好像有点奇怪
	 * @return 学号数组
	 */
	protected int[] getAllKey()
	{
		StudentDao sd = new StudentDao();
		
		List<Student> slist = new ArrayList<Student>();
		try
		{
			//获取所有学生信息
			slist = sd.show("Student") ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		int info[] = new int[slist.size()];
		Student sm = new Student();
		
		//把id挑出来存入数组
		for(int i = 0;i<slist.size();i++)
		{
			sm = slist.get(i);
			info[i] = sm.getId();	
		}
		
		//返回一个数组
		return info;
	}

}

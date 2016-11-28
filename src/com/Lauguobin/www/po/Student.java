package com.Lauguobin.www.po;

import java.io.Serializable;

public class Student implements Serializable
{

	private static final long serialVersionUID = -3295220454249601933L;
	
	private int id;
	private String stuName;
	private String stuGrade;
	private int stuClass;
	private String mobile;
	private String email;
	
	
	public Student()
	{
		super();
	}
	//这个构造方法方便构造对象
	public Student(int id, String stuNname, String stuGgrade, int stuClass, String mobile, String email)
	{
		super();
		this.id = id;
		this.stuName = stuNname;
		this.stuGrade = stuGgrade;
		this.stuClass = stuClass;
		this.mobile = mobile;
		this.email = email;
	}
	

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getStuName()
	{
		return stuName;
	}
	
	public void setStuName(String stuNname)
	{
		this.stuName = stuNname;
	}
	public String getStuGrade()
	{
		return stuGrade;
	}
	public void setStuGrade(String stuGgrade)
	{
		this.stuGrade = stuGgrade;
	}
	public int getStuClass()
	{
		return stuClass;
	}
	public void setStuClass(int stuClass)
	{
		this.stuClass = stuClass;
	}
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}

}

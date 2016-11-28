package com.Lauguobin.www.service;

import java.util.*;

import com.Lauguobin.www.dao.*;
import com.Lauguobin.www.po.*;
import com.Lauguobin.www.util.JudgeUtil;
import com.Lauguobin.www.view.Login.LoginView;

public class UserService
{
	UserDao ud = new UserDao();
	/**
	 * 判断数据库里是不是已经有这个用户，用于实现修改密码
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean IfHaveUser(User user) throws Exception 
	{
		
		List<User> all= ud.show("User");
		//用重写的equals判断user类
		for(int i = 0;i < all.size();i++)
		{
			if(user.equals(all.get(i))&&(user.getIdentity()==all.get(i).getIdentity()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 吧用户名和密码封装城对象 并判断在数据库中有没有重复
	 * @param user
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean IfNewUser(User user) throws Exception
	{
		
		List<User> all= ud.show("User");

		//判断用户名
		for(int i = 0;i < all.size();i++)
		{
			if(user.getUser().equals(all.get(i).getUser()))
				return false;
		}
		return true;
	}
	
	
	/**
	 * 输入账户名和密码，输入新密码，更改密码
	 * @param user
	 * @param pnew
	 * @return
	 * @throws Exception
	 */

	public int IfAlter(User user,String pnew) throws Exception
	{
		if(pnew.length() < 6 || pnew.length() > 18)
			return 1;
		
		List<User> all= ud.show("User");
		
		for(int i = 0;i < all.size();i++)
			if(user.equals(all.get(i)))		//判断会否能在数据库找到重写后equals的对象，如果能，调用dao改变密码
			{
				user.setPassword(pnew);
				user.setId(all.get(i).getId());
				user.setIdentity(all.get(i).getIdentity());
				ud.update(user);
				return 0;
			}
		
		return 2;
	}
	
	/**
	 * 判断是不是可以添加新用户
	 * @param user 待添加的新用户数据类
	 * @return 是或否
	 * @throws Exception
	 */
	public int IfAddUserSuccess(User user) throws Exception
	{
		//此处判断是不是新用户
		if(IfNewUser(user))
		{
			//调用工具判断用户名是否合法
			if(JudgeUtil.isUser(user.getUser()))
			{
				//判断密码长度
				if(user.getPassword().length() < 6 || user.getPassword().length() > 18)
					return 1;
				else
				{
					ud.add(user);
					return 0;
				}
			}
			else
				return 2;
		}
		else
			return 3;
	}
	
	/**
	 * 输入一个密码，判断是否为当前登录账号的密码，如果是择改该账号的权限为true
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean isUpword(String password) throws Exception
	{
		if(password.equals(LoginView.USING_USER_PASSWORD))
		{
			User user = new User(LoginView.USING_USER,LoginView.USING_USER_PASSWORD);
			List<User> all= ud.show("User");
			for(int i = 0;i < all.size();i++)
				if(user.equals(all.get(i)))		
				{
					user.setId(all.get(i).getId());
					user.setIdentity(true);
					ud.update(user);
				}
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断是不是高级账号，如果不是则删除
	 * @param password
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int isDel(String password,User user) throws Exception
	{
		if(password.equals(LoginView.USING_USER_PASSWORD))
		{
			List<User> all= ud.show("User");
			for(int i = 0;i < all.size();i++)
				if(user.equals(all.get(i)))	
				{
					if(!all.get(i).getIdentity())
					{
						ud.del(all.get(i).getId());
						return 0;
					}
					else return 1;
				}
		}
		else 
			return 2;
		return 3;
	}
}

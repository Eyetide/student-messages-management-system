package com.Lauguobin.www.view.Login;

import  java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Lauguobin.www.po.*;
import com.Lauguobin.www.service.*;
import com.Lauguobin.www.util.*;
import com.Lauguobin.www.view.StudentMenagement.StudentMenageView;
/**
 * 登录界面的类
 * @author GB菌
 */
public class LoginView extends JFrame
{
	private static final long serialVersionUID = 1L;
	//当前登录用户的身份信息
	public static String USING_USER;
	public static String USING_USER_PASSWORD;
	public static boolean USER_IDENTITY;
	//总的Panel
	private JPanel buttonPanel;
	private JLabel picture;
	private JPanel all;
	private ButtonGroup group;
	private JPanel radios;
	private JRadioButton oldDriver;
	private JRadioButton newbie;
	private JPanel loginName;
	private JPanel loginPassword;
	private JLabel labelName;
	private JLabel labelPw;
	private JTextField name;
	private JPasswordField password;
	private JButton login;
	private JButton zc;
	private JButton quit;
	private JButton clean;
	private JButton change;
	
	/**
	 * main方法在这里
	 * @param args
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(
				new Runnable()
				{
					public void run()
					{
						new LoginView();
					}
				}
		);
	}
	//构造方法
	public LoginView()
	{	
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		//创建按钮并且连接
		login = new JButton("登录");
		login.addActionListener(new LoginMonitor());
		zc = new JButton("注册");
		zc.addActionListener(new LoginMonitor());
		quit = new JButton("退出");
		quit.addActionListener(new LoginMonitor());
		clean = new JButton("清除信息");
		clean.addActionListener(new LoginMonitor());
		change = new JButton("修改密码");
		change.addActionListener(new LoginMonitor());
		
		//实例化容器
		buttonPanel = new JPanel();
		loginName = new JPanel();
		loginPassword = new JPanel();
		all = new JPanel(new GridLayout(3,1));
		picture  = new JLabel(new ImageIcon("picture.jpg"));
		group =  new ButtonGroup();
		radios = new JPanel();
		labelName = new JLabel("      账号");
		name = new JTextField(13);
		name.addActionListener(new LoginMonitor());
		labelPw = new JLabel("      密码");
		password = new JPasswordField(13);
		password.addActionListener(new LoginMonitor());
		oldDriver = new JRadioButton("权限狗");
		newbie = new JRadioButton("小来宾");
		
		loginName.add(labelName);
		loginName.add(name);
		loginName.add(clean);
		loginPassword.add(labelPw);
		loginPassword.add(password);
		loginPassword.add(change);
		group.add(oldDriver);
		group.add(newbie);
		radios.add(oldDriver);
		oldDriver.addActionListener(new LoginMonitor());
		radios.add(newbie);
		newbie.addActionListener(new LoginMonitor());
		all.add(loginName);
		all.add(loginPassword);
		all.add(radios);
		buttonPanel.add(login);
		buttonPanel.add(zc);
		buttonPanel.add(quit);
		
		add(picture,"North");
		add(all);
		add(buttonPanel , "South");
		
		setTitle("小学生的圈养和管理");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(screenWidth  / 3 , screenHeight  / 5);
		setResizable(false);
		setVisible(true);
		
		pack();
	}
	
	/**
	 * 方便事件响应管理，把这个类设置成内部类
	 * @author GB菌
	 *
	 */
	class LoginMonitor  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			 UserService us = new  UserService();
			 
			 //点击登录或在密码文本域中敲击回车
			if((e.getSource() == login)||(e.getSource() == password))
			{
				if(oldDriver.isSelected())
					USER_IDENTITY = true;
				else	if(newbie.isSelected())
					USER_IDENTITY = false;
				else 
				{
					JOptionPane.showMessageDialog(null, "请选择用户类型", "输入错误", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				
				String n = name.getText(); 
				String p = new String(password.getPassword());

				password.setText("");
				User user = new User(n,p,USER_IDENTITY);
				try
				{
					if(us.IfHaveUser(user))
					{
						dispose();
						USING_USER = n;
						USING_USER_PASSWORD = p;
						new StudentMenageView();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名、密码或用户身份选择错误", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			
			//注册事件
			if(e.getSource() == zc)
			{
				new RegisterView();
			}
			
			//清除信息事件
			if(e.getSource() == clean)
			{
				name.setText("");
				password.setText("");
			}
			
			//更改密码事件
			if(e.getSource() == change)
			{
				new ChangePasswordView ();
			}
			
			//退出事件
			if(e.getSource() == quit)
			{
				int n = JOptionPane.showConfirmDialog(null, "是否退出?", "退出",JOptionPane.YES_NO_OPTION);
				if(n==0)
					System.exit(0);
			}
		}
	}
	
}

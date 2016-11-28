package com.Lauguobin.www.view.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.Lauguobin.www.po.User;
import com.Lauguobin.www.service.UserService;
import com.Lauguobin.www.util.ViewUtil;

/** 这个窗口只有在登录界面才会用到
 * 这个类用于构建注册窗口。并没有太大卵用。
 * @author GB菌
 *
 */
public class RegisterView extends JDialog
{

	private static final long serialVersionUID = 1L;
	private JPanel all;
	private JPanel buttons;
	private JPanel tips;
	private JPanel one;
	private JPanel two;
	private JPanel three;
	
	private ButtonGroup group;
	private JPanel radios;
	
	private JLabel user;
	private JLabel password;
	private JLabel affirmPassword;
	private JLabel tip1;
	private JLabel tip2;
	private JLabel tip3;
	
	private JTextField tu;
	private JPasswordField tp;
	private JPasswordField atp;
	
	private JButton yes;
	private JButton no;
	private JRadioButton oldDriver;
	private JRadioButton newbie;
	
	 RegisterView()
	 {
		all = new JPanel(new GridLayout(4,1));
		tips = new JPanel(new GridLayout(3,0));
		group =  new ButtonGroup();
		radios = new JPanel();
		one = new JPanel();
		two = new JPanel();
		three = new JPanel();
		 user = new JLabel("用户名称");
		 password = new JLabel("用户密码");
		 affirmPassword = new JLabel("确认密码");
		 tip1 = new JLabel("	　温馨提示：用户名只允许有字母、中文、数字　　");
		 tip2 = new JLabel("　　　　　　用户名允许的长度为4 - 20位　　");
		 tip3 = new JLabel("　　　　　　密码长度必须位于6 - 18位");
		tu = new JTextField(10);
		tp = new JPasswordField(10);
		tp.addActionListener(new RegisterMonitor());
		atp= new JPasswordField(10);
		atp.addActionListener(new RegisterMonitor());
		oldDriver = new JRadioButton("权限狗");
		newbie = new JRadioButton("小来宾");
		
		group.add(oldDriver);
		group.add(newbie);
		radios.add(oldDriver);
		oldDriver.addActionListener(new RegisterMonitor());
		radios.add(newbie);
		newbie.addActionListener(new RegisterMonitor());
		one.add(user);
		one.add(tu);
		two.add(password);
		two.add(tp);
		three.add(affirmPassword);
		three.add(atp);
		all.add(one);
		all.add(two);
		all.add(three);
		all.add(radios);
		tips.add(tip1);
		tips.add(tip2);
		tips.add(tip3);
		 
		 yes = new JButton("开始摇滚吧");
		 yes.addActionListener(new RegisterMonitor());
		 no = new JButton("取消");
		 no.addActionListener(new RegisterMonitor());
		 buttons = new JPanel();
		 buttons.add(yes);
		 buttons.add(no);
		 
		 add(all,"North");
		 add(tips);
		 add(buttons,"South");
		 
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("新手上路");
		setLocation(screenWidth / 3 , screenHeight  / 2);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setVisible(true);

	 }
	 
	 class RegisterMonitor  implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			UserService us = new UserService();
			
			//点击确定或者在密码文本域敲击回车
			if(e.getSource() == yes || e.getSource() == tp)
			{
				String u = tu.getText();
				String p = new String(tp.getPassword());
				String p2 = new String(atp.getPassword());
				if(!p.equals(p2))
				{	
					JOptionPane.showMessageDialog(null, "两次密码输入不一致！", "输入错误", JOptionPane.ERROR_MESSAGE);
					tp.setText("");
					atp.setText("");
					return;
				}
				boolean identity;
				if(oldDriver.isSelected())
					identity = true;
				else	if(newbie.isSelected())
					identity = false;
				else 
				{
					JOptionPane.showMessageDialog(null, "请选择用户类型", "输入错误", JOptionPane.ERROR_MESSAGE);
					return ;
				}
				try
				{
					//把数据封装成对象操作
					User user = new User(u,p,identity);
					//调用service判断并由service决定是否注册
					int flag = us.IfAddUserSuccess(user);
					switch(flag)
					{
						case 0:
						{
							JOptionPane.showMessageDialog(null, "注册成功", "信息", JOptionPane.PLAIN_MESSAGE);
							dispose();
							break;
						}
						case 1:JOptionPane.showMessageDialog(null, "密码长度必须在6 - 18位", "警告", JOptionPane.WARNING_MESSAGE);break;
						case 2:JOptionPane.showMessageDialog(null, "用户名不合法", "警告", JOptionPane.WARNING_MESSAGE);break;
						case 3:JOptionPane.showMessageDialog(null, "用户已存在", "警告", JOptionPane.WARNING_MESSAGE);break;
						default :JOptionPane.showMessageDialog(null, "未知错误", "错误", JOptionPane.WARNING_MESSAGE);break;
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			
			//点击取消
			if(e.getSource() == no)
				dispose();
		}
		 
	 }
}

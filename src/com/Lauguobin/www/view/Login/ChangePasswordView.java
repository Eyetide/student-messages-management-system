package com.Lauguobin.www.view.Login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.Lauguobin.www.po.User;
import com.Lauguobin.www.service.UserService;
import com.Lauguobin.www.util.ViewUtil;

public class ChangePasswordView extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JPanel all;
	private JPanel buttons;
	
	private JLabel user;
	private JLabel oldPassword;
	private JLabel newPassword;
	
	private JTextField tu;
	private JPasswordField top;
	private JPasswordField tnp;
	
	private JButton yes;
	private JButton no;
	
	ChangePasswordView()
	 {
		 all = new JPanel(new GridLayout(3,1));
		 user = new JLabel("　　　　　用户名");
		 oldPassword = new JLabel("　　　　　旧密码");
		 newPassword = new JLabel("　　　　　新密码");
		 tu = new JTextField(13);
		 top = new JPasswordField(13);
		 tnp = new JPasswordField(13);
		 tnp.addActionListener(new ChangePasswordMonitor());
		 
		 all.add(user);
		 all.add(tu);
		 all.add(oldPassword);
		 all.add(top);
		 all.add(newPassword);
		 all.add(tnp);
		 
		 yes = new JButton("确定修改");
		 yes.addActionListener(new ChangePasswordMonitor());
		 no = new JButton("取消");
		 no.addActionListener(new ChangePasswordMonitor());
		 buttons = new JPanel();
		 buttons.add(yes);
		 buttons.add(no);
		 
		 add(all,"North");
		 add(buttons,"South");
		 
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("密码修改");
		setLocation(screenWidth / 3 , screenHeight  / 2);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setVisible(true);
	 }
	 
	 class ChangePasswordMonitor  implements ActionListener
	 {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//点击确定或者在新密码行敲击回车响应
			if(e.getSource() == yes || e.getSource() == tnp)
			{
				UserService us = new UserService();
				
				//封装数据
				String uname =  tu.getText();
				String pold = new String(top.getPassword());
				String pnew = new String(tnp.getPassword());
				User in = new User(uname,pold);
				
				try
				{
					//调用service判断并由其决定是否执行
					int flag = us.IfAlter(in,pnew);
					switch(flag)
					{
						case 0:
						{
							JOptionPane.showMessageDialog(null, "操作成功", "标题", JOptionPane.PLAIN_MESSAGE);
							dispose();
							break;
						}
						case 1:JOptionPane.showMessageDialog(null, "新密码长度必须位于6 - 18位", "警告", JOptionPane.WARNING_MESSAGE); 	break;
						case 2:JOptionPane.showMessageDialog(null, "原账户信息验证错误", "警告", JOptionPane.WARNING_MESSAGE); 	break;
						default :JOptionPane.showMessageDialog(null, "未知错误", "警告", JOptionPane.ERROR_MESSAGE); 	break;
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == no)
				dispose();
		}
		 
	 }
}


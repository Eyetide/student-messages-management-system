package com.Lauguobin.www.view.StudentMenagement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.Lauguobin.www.service.UserService;
import com.Lauguobin.www.util.ViewUtil;
import com.Lauguobin.www.view.Login.LoginView;

/**
 * 用户升级窗口，用户再次输入自己的密码后可以升级高级账户
 * @author GB菌
 *
 */
public class UpwordUserView extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JPanel buttons;
	private JPanel imformations;
	private JPanel pack;
	private JButton yes;
	private JButton no;
	private JLabel lab;
	private JPasswordField text;
	
	public UpwordUserView()
	{
		buttons = new JPanel();
		imformations = new JPanel(new GridLayout(2, 1));
		pack = new JPanel();
		lab = new JLabel("请再次输入密码确认您的身份");
		yes = new JButton("升级权限");
		yes.addActionListener(new UpwordMonitor());
		no = new JButton("我按错了");
		no.addActionListener(new UpwordMonitor());
		text = new JPasswordField(10);
		text.addActionListener(new UpwordMonitor());
		imformations.add(lab);
		imformations.add(text);
		buttons.add(yes);
		buttons.add(no);
		pack.add(imformations);
		
		add(pack);
		add(buttons,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("权限狗会员充值");
		setLocation(screenWidth / 3 , screenHeight  / 2);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	
	class UpwordMonitor implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == no)
				dispose();
			
			/*
			 * 点击确认
			 */
			if(e.getSource() == yes||e.getSource() == text)
			{
				String p = new String(text.getPassword());
				try
				{
					if(new UserService().isUpword(p))
					{
						dispose();
						JOptionPane.showMessageDialog(null, "升级成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
						LoginView.USER_IDENTITY = true;
					}
					else
					{
						text.setText("");
						JOptionPane.showMessageDialog(null, "密码输入错误！", "输入错误", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception e1)
				{
					JOptionPane.showMessageDialog(null, "错误！", "错误", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
			
		}
		
	}
}

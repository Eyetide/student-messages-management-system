package com.Lauguobin.www.view.StudentMenagement;

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

/**
 * 删除一个用户时弹出的窗口
 * @author GB菌
 *
 */
public class DelUserView extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JPanel buttons;
	private JPanel imformations;
	private JPanel one;
	private JPanel two;
	private JPanel pack;
	private JButton yes;
	private JButton no;
	private JLabel lab;
	private JLabel user;
	private JLabel password;
	private JTextField textUser;
	private JPasswordField textPassword;
	private JPasswordField text;
	
	DelUserView()
	{
		buttons = new JPanel();
		imformations = new JPanel(new GridLayout(4, 1));
		one = new JPanel();
		two = new JPanel();
		pack = new JPanel();
		lab = new JLabel("请再次输入密码确认您的身份");
		yes = new JButton("删除来宾");
		yes.addActionListener(new DelUserMonitor());
		no = new JButton("我按错了");
		no.addActionListener(new DelUserMonitor());
		text = new JPasswordField(10);
		text.addActionListener(new DelUserMonitor());
		user = new JLabel("要删除的账户");
		password = new JLabel("该账户的密码");
		textUser = new JTextField(10);
		textPassword = new JPasswordField(10);
		
		one.add(user);
		one.add(textUser);
		two.add(password);
		two.add(textPassword);
		imformations.add(lab);
		imformations.add(text);
		imformations.add(one);
		imformations.add(two);
		buttons.add(yes);
		buttons.add(no);
		pack.add(imformations);
		
		add(pack);
		add(buttons,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("权限狗特权专区");
		setLocation(screenWidth * 3/ 7 , screenHeight *3 / 7);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	
	class DelUserMonitor implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == no)
				dispose();
			
			if(e.getSource() == yes)
			{
				String utp = new String(text.getPassword());
				String du = textUser.getText();
				String dp = new String(textPassword.getPassword());
				User u = new User(du,dp);
				try
				{
					int flag = new UserService().isDel(utp,u);
					switch(flag)
					{
						case 0:
						{
							dispose();
							JOptionPane.showMessageDialog(null, "删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
						case 1:JOptionPane.showMessageDialog(null, "不能删除高级账户！", "输入错误", JOptionPane.ERROR_MESSAGE);break;
						case 2:JOptionPane.showMessageDialog(null, "您输入的密码信息错误！", "输入错误", JOptionPane.ERROR_MESSAGE);break;
						case 3:JOptionPane.showMessageDialog(null, "选择删除的账户信息错误！", "输入错误", JOptionPane.ERROR_MESSAGE);break;
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		
	}
}

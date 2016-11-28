package com.Lauguobin.www.view.StudentMenagement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.StuMeService;
import com.Lauguobin.www.util.ViewUtil;

/**
 * 点击添加学生后出现的窗口，因为仅仅在主界面会用到
 * @author GB菌
 *
 */
public class AddStuView  extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel buttonP;
	private JPanel textP;
	private JPanel labelP;
	private JPanel allP;
	private JTable tab;
	private JButton yes;
	private JButton no;
	private JLabel id;
	private JLabel name;
	private JLabel grade;
	private JLabel cl;
	private JLabel mobile;
	private JLabel email;
	private JLabel pic;
	private JTextField tid;
	private JTextField tname;
	private JTextField tgrade;
	private JTextField tcl;
	private JTextField tmobile;
	private JTextField temail;
	
	public AddStuView(JTable tab)
	{
		buttonP = new JPanel();
		textP = new JPanel(new GridLayout(0,1));
		labelP = new JPanel(new GridLayout(0,1));
		allP = new JPanel(new GridLayout(0,2));
		pic = new JLabel(new ImageIcon("psb.jpg"));
		this.tab = tab;
		yes = new JButton("确定");
		yes.addActionListener(new ASMonitor());
		no = new JButton("取消");
		no.addActionListener(new ASMonitor());
		id = new JLabel("　　　　　　ID");
		name = new JLabel("　　　　学生姓名");
		grade = new JLabel("　　　　所在年级");
		cl =  new JLabel("　　　　所在班级");
		mobile =  new JLabel("　　　　联系方式");
		email =  new JLabel("　　　　电子邮箱");
		tid = new JTextField(15);
		tname = new JTextField(15);
		tgrade = new JTextField(15);
		tcl = new JTextField(15);
		tmobile = new JTextField(15);
		temail = new JTextField(15);
		buttonP.add(yes);
		buttonP.add(no);
		labelP.add(id);
		labelP.add(name);
		labelP.add(grade);
		labelP.add(cl);
		labelP.add(mobile);
		labelP.add(email);
		textP.add(tid);
		textP.add(tname);
		textP.add(tgrade);
		textP.add(tcl);
		textP.add(tmobile);
		textP.add(temail);
		allP.add(labelP);
		allP.add(textP);
		
		add(allP);
		add(pic,"East");
		add(buttonP,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("添加学生信息");
		setLocation(screenWidth * 2 / 5 , screenHeight / 3);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setVisible(true);

	}
	/**
	 *  添加学生窗口的时间相应处理类，内部类的内部类
	 * @author GB菌
	 *
	 */
	class ASMonitor  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			StuMeService sms = new  StuMeService();
			
			/*
			 * 点击确定添加一个新的学生
			 */
			if(e.getSource() == yes)
			{
				String id = tid.getText();
				String n = tname.getText();
				String g = tgrade.getText();
				String c = tcl.getText();
				String m = tmobile.getText();
				String em = temail.getText();
				
				//调用service判断并且添加数据进入数据库
				int flag = sms.ifHaveMessage(id , n , g , c , m , em );
				switch(flag)
				{
					case 0:
					{
						JOptionPane.showMessageDialog(null, "恭喜！成功添加学生！", "标题", JOptionPane.PLAIN_MESSAGE); 	
						dispose();
						//增加列表中的一行
						DefaultTableModel tableModel = (DefaultTableModel) tab.getModel(); 
						int iid = Integer.parseInt(id);
						int ic = Integer.parseInt(c);
						tableModel.addRow(new Object[]{iid,n,g,ic,m,em}); 
						break;
					}
					case 1:JOptionPane.showMessageDialog(null, "请输入正确的ID！", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 2:JOptionPane.showMessageDialog(null, "ID冲突，请检查ID！", "警告", JOptionPane.WARNING_MESSAGE);	break;
					case 3:JOptionPane.showMessageDialog(null, "ID不能超过200,000,000！", "警告", JOptionPane.WARNING_MESSAGE);	break;
					case 4:JOptionPane.showMessageDialog(null, "请输入正确的姓名！", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 5:JOptionPane.showMessageDialog(null, "请输入年级信息", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 6:JOptionPane.showMessageDialog(null, "请输入正确的年级信息", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 7:JOptionPane.showMessageDialog(null, "请输入正确的班级！", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 8:JOptionPane.showMessageDialog(null, "没有超过100的班级！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 9:JOptionPane.showMessageDialog(null, "请输入正确的手机号码！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 10:JOptionPane.showMessageDialog(null, "请输入正确的邮箱！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					default :JOptionPane.showMessageDialog(null, "程序异常！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
				}
			}
			
			//点击取消
			if(e.getSource() == no)
				dispose();
		}
	}
	
}

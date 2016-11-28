package com.Lauguobin.www.view.StudentMenagement;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.util.ViewUtil;
import com.Lauguobin.www.view.CourseMenagement.CourseView;
import com.Lauguobin.www.view.Login.LoginView;
import com.Lauguobin.www.view.ScoreMenagement.GradeView;
import com.Lauguobin.www.service.*;
/**
 * 这个类是学生管理的主界面窗口。里面有一些子窗口
 * @author GB菌
 *
 */
public class StudentMenageView extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel allButtons;
	private JScrollPane list;
	private JTable tab;
	private JPanel head;
	private JPanel pan;
	private JButton add;
	private JButton del;
	private JButton see;
	private JButton alter;
	private JButton search;
	private JButton upword;
	private JButton delUser;
	private JButton back;
	private JButton about;
	private JButton f5;
	private JButton exit;
	private JLabel hm;
	private JTextField se;
	/**
	 * 构造方法
	 */
	public StudentMenageView()
	{
		allButtons = new JPanel(new GridLayout(2, 0 , 10 , 3));
		allButtons.setBounds(100, 3, 600, 50);
		list = new JScrollPane();
		head = new JPanel();
		pan = new JPanel();
		add = new JButton("添加学生");
		add.addActionListener(new SMVMonitor());
		del = new JButton("删除学生");
		del.addActionListener(new SMVMonitor());
		see = new JButton("查看成绩");
		see.addActionListener(new SMVMonitor());
		alter = new JButton("更改信息");
		alter.addActionListener(new SMVMonitor());
		search = new JButton("戳我");
		search.addActionListener(new SMVMonitor());
		back = new JButton("重新登录");
		back.addActionListener(new SMVMonitor());
		about = new JButton("课程操作");
		about.addActionListener(new SMVMonitor());
		f5 = new JButton("刷新列表");
		f5.addActionListener(new SMVMonitor());
		exit = new JButton("退出程序");
		exit.addActionListener(new SMVMonitor());
		upword = new JButton("升级权限");
		upword.addActionListener(new SMVMonitor());
		delUser = new JButton("删除用户");
		delUser.addActionListener(new SMVMonitor());
		
		allButtons.add(add);
		allButtons.add(del);
		allButtons.add(see);
		allButtons.add(alter);
		allButtons.add(f5);
		allButtons.add(delUser);
		allButtons.add(upword);
		allButtons.add(about);
		allButtons.add(back);
		allButtons.add(exit);
		pan.add(allButtons);
		
		hm = new JLabel("输入id或学生姓名(不确定的地方可输入'%'或'_')");
		se = new JTextField(25);
		se.addActionListener(new SMVMonitor());
		head.add(hm);
		head.add(se);
		head.add(search);

		StuMeService sms = new StuMeService();
		Object[][] nt = sms.getAll();
		String[] title = {"ID","姓名","年级","班级","联系方式","邮箱"};
		DefaultTableModel model = new DefaultTableModel(nt, title) 
		{ 
			private static final long serialVersionUID = 1L;

				//此处设置单元格时否可以被编辑。
			  public boolean isCellEditable(int row, int column) 
			  { 
			    return false; 
			  } 
		};
		tab = new JTable(model);
		tab.getTableHeader();
		list.getViewport().add(tab); 
		
		add(head,"North");
		add(pan,"South");
		add(list);
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("猪圈");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(screenWidth / 6 , screenHeight / 6 , screenWidth * 2/ 3 , screenHeight *2 / 3);
		setResizable(false);
		setVisible(true);
		
	} 
	
	/**
	 * 主界面窗口的事件处理类，内部类。
	 * @author GB菌
	 *
	 */
	class SMVMonitor  implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			StuMeService sms = new  StuMeService();
			
			//如果点击“添加学生”按钮
			if(e.getSource() == add)
			{
				new AddStuView(tab);		//跳出添加信息窗口
			}
			
			//如果点击“删除学生”按钮
			if(e.getSource() == del)
			{
					int selectedRowIndex = tab.getSelectedRow(); 
					if(selectedRowIndex >= 0)
					{
						int n = JOptionPane.showConfirmDialog(null, "确定要删除这个可爱的学生吗?", "误操作确认",JOptionPane.YES_NO_OPTION);
						if(n==0)
							try
							{
								sms.ifSelectToDel("StudentDao",selectedRowIndex,tab);
							}
							catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1)
							{
								JOptionPane.showMessageDialog(null, "未知错误！", "警告", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "请选中一行", "警告", JOptionPane.INFORMATION_MESSAGE);
					}
			}
			
			
			//如果点击“搜索”按钮或者直接在搜索框按回车
			if(e.getSource() == search || e.getSource() == se)
			{
				String str = se.getText();
				if(str.equals(""))
				{
					JOptionPane.showMessageDialog(null, "请输入搜索条件", "警告", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					//显示搜索结果的列表
					Object info[][] = sms.getAll(str);
					String[] title = {"ID","姓名","年级","班级","联系方式","邮箱"};
					DefaultTableModel model = new DefaultTableModel(info, title) 
					{ 
						private static final long serialVersionUID = 1L;

							//此处设置单元格时否可以被编辑。
						  public boolean isCellEditable(int row, int column) 
						  { 
						    return false; 
						  } 
					};
					tab = new JTable(model);
					tab.getTableHeader();
					list.getViewport().add(tab); 
				}
			}
			
			//点击查看成绩弹出窗口
			if(e.getSource() == see)
			{
				int selectedRowIndex = tab.getSelectedRow(); 
				if(selectedRowIndex >= 0)
				{
					int as = (int)tab.getValueAt(selectedRowIndex, 0);
					new GradeView(as);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请选中一行", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			//如果点击“更新学生”按钮
			if(e.getSource() == alter)
			{
				int selectedRowIndex = tab.getSelectedRow(); 
				if(selectedRowIndex >= 0)
				{
					//调用service
					int as = (int )tab.getValueAt(selectedRowIndex, 0);
					new ChangeStuView(selectedRowIndex,as,tab,list);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请选中一行", "警告", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			//如果点击“刷新列表”按钮
			if(e.getSource() == f5)
			{
				Object[][] nt = sms.getAll();
				String[] title = {"ID","姓名","年级","班级","联系方式","邮箱"};
				DefaultTableModel model = new DefaultTableModel(nt, title) 
				{ 
					private static final long serialVersionUID = 1L;

						//此处设置单元格时否可以被编辑。
					  public boolean isCellEditable(int row, int column) 
					  { 
					    return false; 
					  } 
				};
				tab = new JTable(model);
				tab.getTableHeader();
				list.getViewport().add(tab); 
			}
			
			//权限升级
			if(e.getSource() == upword)
				if(!LoginView.USER_IDENTITY)
					new UpwordUserView();
				else
					JOptionPane.showMessageDialog(null, "您已经是权限狗", "输入错误", JOptionPane.ERROR_MESSAGE);
			
			if(e.getSource() == delUser)
				if(LoginView.USER_IDENTITY)
					new DelUserView();
				else
					JOptionPane.showMessageDialog(null, "您不是权限狗,权限不足", "输入错误", JOptionPane.ERROR_MESSAGE);
			
			//点击关于课程的响应
			if(e.getSource() == about )
					new CourseView();
			
			//如果点击“重新登录”按钮
			if(e.getSource() == back)
			{
				int n = JOptionPane.showConfirmDialog(null, "重新登录?", "操作确认",JOptionPane.YES_NO_OPTION);//i=0/1  
				if(n==0)
				{
					dispose();
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
			}
			
			if(e.getSource() == exit)
			{
				int n = JOptionPane.showConfirmDialog(null, "确实要退出程序吗?", "退出",JOptionPane.YES_NO_OPTION);
				if(n==0)
					System.exit(0);
			}
			
		}
	}
	
}
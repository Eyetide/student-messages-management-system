package com.Lauguobin.www.view.CourseMenagement;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.*;
import com.Lauguobin.www.util.*;
import com.Lauguobin.www.view.Login.LoginView;

public class CourseView extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel buttons;
	private JTable tab;
	private JScrollPane list;
	private JPanel search;
	private JTextField text;
	private JButton s;
	private JLabel label;
	private JButton f5;
	private JButton alter;
	private JButton add;
	private JButton del;
	private JButton back;
	
	public CourseView()
	{
		buttons = new JPanel();
		search = new JPanel();
		f5 = new JButton("刷新");
		f5.addActionListener(new CVMonitor());
		add = new JButton("添加课程");
		add.addActionListener(new CVMonitor());
		alter = new JButton("更改课程");
		alter.addActionListener(new CVMonitor());
		del = new JButton("删除课程");
		del.addActionListener(new CVMonitor());
		back = new JButton("返回");
		back.addActionListener(new CVMonitor());
		s = new JButton("搜索");
		s.addActionListener(new CVMonitor());
		text = new JTextField(15);
		text.addActionListener(new CVMonitor());
		list = new JScrollPane();
		label = new JLabel("搜索课程<'%'和'_'模糊>");
		
		CourseService cs = new CourseService();
		Object[][] nt = null;
		try
		{
			nt = cs.getAll();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] title = {"课程号","课程名称","学分"};
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
		
		buttons.add(add);
		buttons.add(alter);
		buttons.add(del);
		buttons.add(f5);
		buttons.add(back);
		search.add(label);
		search.add(text);
		search.add(s);
		add(search,"North");
		add(list);
		add(buttons,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("课程信息相关");
		setBounds(screenWidth / 3 , screenHeight / 4 , screenWidth / 3 , screenHeight / 3);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	
	class CVMonitor implements  ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			CourseService cs = new CourseService();
			/*
			 * 触发修改课程事件
			 */
			if(e.getSource() == alter)
			{
				int selectedRowIndex = tab.getSelectedRow(); 
				if(LoginView.USER_IDENTITY)
				{
					if(selectedRowIndex >= 0)
					{
							int cno = (int)tab.getValueAt(selectedRowIndex, 0);
							String cName = (String )tab.getValueAt(selectedRowIndex, 1);
							int cre = (int)tab.getValueAt(selectedRowIndex, 2);
							new ChangeCourseView(cno,cName,cre,tab,list);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "请选中一行", "警告", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "没有权限！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			
			/*
			 * 触发添加课程事件
			 */
			if(e.getSource() == add)
			{
				if(LoginView.USER_IDENTITY)
					new AddCourseView(tab);
				else
					JOptionPane.showMessageDialog(null, "没有权限！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			
			/*
			 * 触发删除课程事件
			 */
			if(e.getSource() == del)
			{
				int selectedRowIndex = tab.getSelectedRow(); 
				if(LoginView.USER_IDENTITY)
				{	
					if(selectedRowIndex >= 0)
					{
							int n = JOptionPane.showConfirmDialog(null, "确定要删除这门课吗?", "误操作确认",JOptionPane.YES_NO_OPTION);
							if(n==0)
								try
								{
									cs.ifSelectToDel("CourseDao",selectedRowIndex,tab);
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
				else
					JOptionPane.showMessageDialog(null, "您不是权限狗！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			
			/*
			 * 触发搜索课程事件
			 */
			if(e.getSource() == text || e.getSource() == s)
			{
				String str = text.getText();
			if(str.equals(""))
				{
					JOptionPane.showMessageDialog(null, "请输入搜索条件", "警告", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					//显示搜索结果的列表
					Object info[][] = new CourseService().getAll(str);
					String[] title = {"课程号","课程名称","学分"};
					DefaultTableModel model = new DefaultTableModel(info,title) 
					{ 
						private static final long serialVersionUID = 1L;

							//此处设置单元格时否可以被编辑。
						  public boolean isCellEditable(int row, int column) 
						  { 
						    return false; 
						  } 
					}; 
					tab.setModel(model);
					tab.getTableHeader();
					list.getViewport().add(tab); 
				}
			}
			
			if(e.getSource() == f5)
			{
				Object[][] nt = null;
				try
				{
					nt = cs.getAll();
				}
				catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String[] title = {"课程号","课程名称","学分"};
				DefaultTableModel model = new DefaultTableModel(nt,title) 
				{ 
					private static final long serialVersionUID = 1L;

						//此处设置单元格时否可以被编辑。
					  public boolean isCellEditable(int row, int column) 
					  { 
					    return false; 
					  } 
				}; 
				tab.setModel(model);
				tab.getTableHeader();
				list.getViewport().add(tab);
			}
			
			if(e.getSource() == back)
			{
				dispose();
			}
			
		}
		
	}
}


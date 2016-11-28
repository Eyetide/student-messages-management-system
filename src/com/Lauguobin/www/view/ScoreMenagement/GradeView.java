package com.Lauguobin.www.view.ScoreMenagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.*;
import com.Lauguobin.www.util.ViewUtil;
import com.Lauguobin.www.view.Login.LoginView;

/**
 * 成绩管理窗口，需要一个id来确定是谁的成绩
 * @author GB菌
 *
 */
public class GradeView extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JScrollPane list;
	private JTable tab;
	private JPanel inButton;
	private JPanel search;
	private JTextField text;
	private JButton s;
	private JLabel label;
	private JButton yes;
	private JButton change;
	private JButton f5;
	int id;
	
	public GradeView(int id)
	{
		this.id = id;
		
		yes = new JButton("返回");
		yes.addActionListener(new GVMonitor());
		f5 = new JButton("刷新");
		f5.addActionListener(new GVMonitor());
		change = new JButton("修改成绩");
		change.addActionListener(new GVMonitor());
		s = new JButton("搜索");
		s.addActionListener(new GVMonitor());
		text = new JTextField(15);
		text.addActionListener(new GVMonitor());
		list = new JScrollPane();
		label = new JLabel("搜索成绩<'%'和'_'模糊>");
		inButton = new JPanel();
		search = new JPanel();
		inButton.add(change);
		inButton.add(f5);
		inButton.add(yes);
		search.add(label);
		search.add(text);
		search.add(s);
		
		list = new JScrollPane();
		ScoreService ss = new ScoreService();
		Object[][] nt = ss.getAllScore(id);
		String[] title = {"学生ID","学生姓名","课程号","课程名称","学科成绩","获得学分"};
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
		ViewUtil.fitTableColumns(tab);
		list.getViewport().add(tab); 
		
		add(search,"North");
		add(list);
		add(inButton,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("查看成绩");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(screenWidth / 3 , screenHeight / 4 , screenWidth / 3 , screenHeight  / 3);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	
	class GVMonitor  implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			/*
			 * 返回事件
			 */
			if(e.getSource() == yes)
				dispose();
			
			/*
			 * 搜索事件
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
					Object info[][] = new ScoreService().getSearch(str,id);
					 String[] title = {"学生ID","学生姓名","课程号","课程名称","学科成绩","获得学分"};
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
					ViewUtil.fitTableColumns(tab);
					list.getViewport().add(tab); 
				}
			}
			
			/*
			 * 更改成绩事件
			 */
			if(e.getSource() == change)
			{
				if(LoginView.USER_IDENTITY)
				{
					int selectedRowIndex = tab.getSelectedRow(); 
					if(selectedRowIndex >= 0)
					{
						//调用service
						new SetScoreView(selectedRowIndex,tab,list);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "请选中一行", "警告", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "你根本就不是老司机！", "警告", JOptionPane.WARNING_MESSAGE);
			}
			
			if(e.getSource() == f5)
			{
				ScoreService ss = new ScoreService();
				Object[][] nt = ss.getAllScore(id);
				String[] title = {"学生ID","学生姓名","课程号","课程名称","学科成绩","获得学分"};
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
				ViewUtil.fitTableColumns(tab);
				list.getViewport().add(tab);
			}
		}
	}
}


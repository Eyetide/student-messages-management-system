package com.Lauguobin.www.view.CourseMenagement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.CourseService;
import com.Lauguobin.www.util.ViewUtil;

public class ChangeCourseView extends JDialog
{

	private static final long serialVersionUID = 1L;
	private JPanel message;
	private JPanel buttons;
	private JButton yes;
	private JButton no;
	private int cno; 
	private JTable tab;
	private JScrollPane list;
	private JLabel namel;
	private JLabel creditl;
	private JTextField namet;
	private JTextField creditt;
	
	ChangeCourseView(int cno,String cName,int cre,JTable tab,JScrollPane list)
	{
		message = new JPanel(new GridLayout(2,2));
		buttons = new JPanel();
		
		yes = new JButton("确定");
		yes.addActionListener(new ACVMonitor());
		no = new JButton("取消");
		no.addActionListener(new ACVMonitor());
		
		namel = new JLabel("课程名程");
		creditl = new JLabel("课程学分");
		
		this.cno = cno;
		this.tab = tab;
		this.list = list;
		namet = new JTextField(10);
		creditt = new JTextField(10);
		namet.setText(cName);
		creditt.setText(""+cre);
		
		message.add(namel);
		message.add(namet);
		message.add(creditl);
		message.add(creditt);
		buttons.add(yes);
		buttons.add(no);
		
		add(message);
		add(buttons,"South");
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("更改课程信息");
		setLocation(screenWidth * 2 / 5 , screenHeight / 3);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	
	class ACVMonitor implements ActionListener
	{
		CourseService cs = new CourseService();
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == no)
				dispose();
			
			if(e.getSource() == yes)
			{
				String name = namet.getText();
				String credit = creditt.getText();
				int flag = cs.ifCourse(cno,name,credit);
				
				switch(flag)
				{
					case 0:
					{
						JOptionPane.showMessageDialog(null, "恭喜！成功更新课程！", "标题", JOptionPane.PLAIN_MESSAGE); 
						dispose();
						Object[][] nt = cs.getAll();
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
						tab.setModel(model);
						tab.getTableHeader();
//						ViewUtil.fitTableColumns(tab);
						list.getViewport().add(tab); 
						break;
					}
					case 1:JOptionPane.showMessageDialog(null, "请输入正确的课名", "警告",JOptionPane.WARNING_MESSAGE); break;
					case 2:JOptionPane.showMessageDialog(null, "课程名称过长", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 3:JOptionPane.showMessageDialog(null, "请输入正确学分信息", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 4:JOptionPane.showMessageDialog(null, "没有大于10学分的学科", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 5:JOptionPane.showMessageDialog(null, "该课程已经存在！", "警告",JOptionPane.WARNING_MESSAGE);break;
					default :JOptionPane.showMessageDialog(null, "未知错误！", "标题", JOptionPane.ERROR_MESSAGE); break;
				}
			}
		}
	}
}

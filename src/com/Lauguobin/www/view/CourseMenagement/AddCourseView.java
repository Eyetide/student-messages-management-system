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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.CourseService;
import com.Lauguobin.www.util.ViewUtil;

/**
 * 增加课程的窗口
 * @author GB菌
 *
 */
public class AddCourseView extends JDialog
{

	private static final long serialVersionUID = 1L;
	
	private JPanel message;
	private JPanel buttons;
	
	private JButton yes;
	private JButton no;
	
	private JLabel conl;
	private JLabel namel;
	private JLabel creditl;
	
	private JTextField cont;
	private JTextField namet;
	private JTextField creditt;
	private JTable tab;
	
	
	AddCourseView(JTable tab)
	{
		this.tab = tab;
		message = new JPanel(new GridLayout(3,2));
		buttons = new JPanel();
		
		yes = new JButton("确定");
		yes.addActionListener(new ACVMonitor());
		no = new JButton("取消");
		no.addActionListener(new ACVMonitor());
		
		conl = new JLabel("课程号");
		namel = new JLabel("课程名程");
		creditl = new JLabel("课程学分");
		
		cont = new JTextField(5);
		namet = new JTextField(5);
		creditt = new JTextField(5);
		
		message.add(conl);
		message.add(cont);
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
		
		setTitle("添加课程信息");
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
			
			//如果点击确定
			if(e.getSource() == yes)
			{
				String cno = cont.getText();
				String name = namet.getText();
				String credit = creditt.getText();
				int flag = cs.ifCourse(cno,name,credit);
				
				switch(flag)
				{
					case 0:
					{
						JOptionPane.showMessageDialog(null, "恭喜！成功添加课程！", "标题", JOptionPane.PLAIN_MESSAGE); 
						dispose();
						//增加列表中的一行
						DefaultTableModel tableModel = (DefaultTableModel) tab.getModel(); 
						tableModel.addRow(new Object[]{cno,name,credit}); 
						break;
					}
					case 1:JOptionPane.showMessageDialog(null, "请输入正确的课程号", "信息",JOptionPane.PLAIN_MESSAGE); break;
					case 2:JOptionPane.showMessageDialog(null, "该课程号已存在", "警告",JOptionPane.WARNING_MESSAGE); break;
					case 3:JOptionPane.showMessageDialog(null, "课程号不大于20,000", "警告",JOptionPane.WARNING_MESSAGE); break;
					case 4:JOptionPane.showMessageDialog(null, "请输入正确的课名", "警告",JOptionPane.WARNING_MESSAGE); break;
					case 5:JOptionPane.showMessageDialog(null, "课程名称过长", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 6:JOptionPane.showMessageDialog(null, "请输入正确学分信息", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 7:JOptionPane.showMessageDialog(null, "没有大于10学分的学科", "警告",JOptionPane.WARNING_MESSAGE);break;
					case 8:JOptionPane.showMessageDialog(null, "该课程已经存在！", "警告",JOptionPane.WARNING_MESSAGE);break;
					default :JOptionPane.showMessageDialog(null, "未知错误！", "标题", JOptionPane.ERROR_MESSAGE); 
				}
			}
		}
	}
}

package com.Lauguobin.www.view.ScoreMenagement;

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

import com.Lauguobin.www.service.ScoreService;
import com.Lauguobin.www.util.ViewUtil;

/**
 * 设置成绩的窗口，每次设置成绩以后需要刷新一次列表
 * @author GB菌
 *
 */
public class SetScoreView extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel text;
	private JPanel buttons;
	private JButton yes;
	private JButton no;
	private JTextField t;
	private JTable tab;
	private JScrollPane list;
	private JLabel cn;
	private int selectedRowIndex;
	
	public SetScoreView(int selectedRowIndex,JTable tab,JScrollPane list)
	{
		this.selectedRowIndex = selectedRowIndex;
		this.tab = tab;
		this.list = list;
		
		text = new JPanel();
		buttons = new JPanel();
		cn = new JLabel((String)tab.getValueAt(selectedRowIndex, 3));
		yes = new JButton("修改");
		yes.addActionListener(new SSVMonitor());
		no = new JButton("取消");
		no.addActionListener(new SSVMonitor());
		t = new JTextField(7);
		t.setText(tab.getValueAt(selectedRowIndex, 4).toString());
		t.addActionListener(new SSVMonitor());
		
		text.add(cn);
		text.add(t);
		buttons.add(yes);
		buttons.add(no);
		
		add(text,"North");
		add(buttons);
		
		int screenHeight = ViewUtil.geScreenHeight();
		int screenWidth = ViewUtil.geScreenWidth();
		
		setTitle("成绩修改");
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(screenWidth * 3 / 7 , screenHeight * 3 / 7);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	
	class SSVMonitor implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			ScoreService ss = new ScoreService();
			/*
			 * 响应修改成绩操作并刷新列表
			 */
			if(e.getSource() == t || e.getSource() == yes)
			{
				String score = t.getText();
				int id = (int)tab.getValueAt(selectedRowIndex, 0);
				int cno = (int)tab.getValueAt(selectedRowIndex, 2);
				int n = ss.isScore(score, cno, id);
				switch(n)
				{
					case 0:
					{
						dispose();
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
						tab.setModel(model);
						tab.getTableHeader();
						ViewUtil.fitTableColumns(tab);
						list.getViewport().add(tab); 
						JOptionPane.showMessageDialog(null, "修改成绩成功！");break;
					}
					case 1:JOptionPane.showMessageDialog(null, "请输入合法的成绩！", "标题",JOptionPane.WARNING_MESSAGE);break;
					case 2:JOptionPane.showMessageDialog(null, "成绩不能超过150！", "标题",JOptionPane.WARNING_MESSAGE);break;
					case 3:JOptionPane.showMessageDialog(null, "未知错误！", "标题",JOptionPane.ERROR_MESSAGE);break;
					case 4:JOptionPane.showMessageDialog(null, "成绩不能为空！", "标题",JOptionPane.WARNING_MESSAGE);break;
				}
			}
			
			if(e.getSource() == no)
				dispose();
			
		}
		
	}
}
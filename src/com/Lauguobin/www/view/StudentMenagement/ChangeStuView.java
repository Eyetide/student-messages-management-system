package com.Lauguobin.www.view.StudentMenagement;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.service.StuMeService;
import com.Lauguobin.www.util.ViewUtil;

/**
 * 点击更改信息弹出的窗口
 * 更改信息时，窗口里的文本域已经有原来的信息。
 * 为了方便原来信息传递进这里，我把它设成主窗口的内部类，同样是因为只有主界面会用这个类而且数据细节处理方便
 * @author GB菌
 *
 */
public class ChangeStuView  extends JDialog
{
	private static final long serialVersionUID = 1L;
	
	private JPanel buttonP;
	private JPanel textP;
	private JPanel labelP;
	private JPanel allP;
	private JButton yes;
	private JButton no;
	private JLabel name;
	private JLabel grade;
	private JLabel cl;
	private JLabel mobile;
	private JLabel email;
	private JLabel pic;
	private int pointId; 
	private JTable tab;
	private JScrollPane list;
	private JTextField tname;
	private JTextField tgrade;
	private JTextField tcl;
	private JTextField tmobile;
	private JTextField temail;
	
	ChangeStuView(int selectedRowIndex , int id,JTable tab,JScrollPane list)
	{
		buttonP = new JPanel();
		textP = new JPanel(new GridLayout(0,1));
		labelP = new JPanel(new GridLayout(0,1));
		allP = new JPanel(new GridLayout(0,2));
		yes = new JButton("确定");
		yes.addActionListener(new CSMonitor());
		no = new JButton("取消");
		no.addActionListener(new CSMonitor());
		pointId = id;
		this.tab = tab;
		this.list = list;
		
		name = new JLabel("　　　　学生姓名");
		grade = new JLabel("　　　　所在年级");
		cl =  new JLabel("　　　　所在班级");
		mobile =  new JLabel("　　　　联系方式");
		email =  new JLabel("　　　　电子邮箱");
		pic = new JLabel(new ImageIcon("psb.jpg"));

		tname = new JTextField(15);
		tgrade = new JTextField(15);
		tcl = new JTextField(15);
		tmobile = new JTextField(15);
		temail = new JTextField(15);
		DefaultTableModel tableModel = (DefaultTableModel) tab.getModel(); 
		
		// 取那一行的数据,不用重新输入其他信息 
		tname.setText((String) tableModel.getValueAt(selectedRowIndex, 1)); 
		tgrade.setText((String) tableModel.getValueAt(selectedRowIndex, 2)); 
		tcl.setText(String.valueOf(tableModel.getValueAt(selectedRowIndex, 3))); 
		tmobile.setText((String) tableModel.getValueAt(selectedRowIndex, 4)); 
		temail.setText((String) tableModel.getValueAt(selectedRowIndex, 5)); 
		
		buttonP.add(yes);
		buttonP.add(no);
		labelP.add(name);
		labelP.add(grade);
		labelP.add(cl);
		labelP.add(mobile);
		labelP.add(email);
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
		
		setTitle("修改学生信息");
		setLocation(screenWidth * 2 / 5 , screenHeight / 3);
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	/**
	 * 更改学生信息事件响应窗口。
	 */
	class CSMonitor  implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			StuMeService sms = new  StuMeService();
			
			if(e.getSource() == yes)
			{
				String n = tname.getText();
				String g = tgrade.getText();
				String c = tcl.getText();
				String m = tmobile.getText();
				String em = temail.getText();
				
				//调用service判断并且添加数据进入数据库
				int flag = sms.ifUpMessage(pointId,n , g , c , m , em );
				switch(flag)
				{
					case 0:
					{
						JOptionPane.showMessageDialog(null, "更新学生成功！", "标题", JOptionPane.PLAIN_MESSAGE);
						dispose();
						
						//刷新列表的代码，封装成方法后也能刷新，但是再选中一行后不能再进行行操作，原因不明 = =
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
						tab.setModel(model);
						tab.getTableHeader();
						list.getViewport().add(tab); 
						break;
					}
					case 1:JOptionPane.showMessageDialog(null, "请输入正确的姓名", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 2:JOptionPane.showMessageDialog(null, "请输入年级信息", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 3:JOptionPane.showMessageDialog(null, "请输入正确的年级信息", "错误", JOptionPane.ERROR_MESSAGE);	break;
					case 4:JOptionPane.showMessageDialog(null, "请输入正确的班级信息", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 5:JOptionPane.showMessageDialog(null, "没有超过100的班级！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 6:JOptionPane.showMessageDialog(null, "请输入正确的联系信息", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					case 7:JOptionPane.showMessageDialog(null, "请输入正确的邮箱信息", "错误", JOptionPane.ERROR_MESSAGE); 	break;
					default :JOptionPane.showMessageDialog(null, "程序异常！", "错误", JOptionPane.ERROR_MESSAGE); 	break;
				}

			}
			
			//点击取消
			if(e.getSource() == no)
				dispose();
		}
	}	
}

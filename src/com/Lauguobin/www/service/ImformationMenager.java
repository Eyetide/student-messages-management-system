package com.Lauguobin.www.service;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.Lauguobin.www.dao.IOToSQL;

/**
 * 一个给有需要刷新列表、删除更新列表数据、搜索列表的判断操作提供方法的类
 * @author GB菌
 *
 */
public abstract class ImformationMenager
{
	/**这个方法是用来返回一个二维数组，主要用于刷新JTable
	 * 
	 * @return 封装好的二维数组
	 */
	public abstract Object[][] getAll();
	/**
	 * 获得所有索引
	 * @return
	 */
	protected abstract int[] getAllKey();
	 /**
	  * 判断索引是否冲突
	  * @param str
	  * @return
	  */
	protected abstract boolean ifKeyClash(String str);
	
	/**
	 * 如果从JTable中选择了一行，点击删除可以执行此操作
	 * @param className
	 * @param selectedRowIndex
	 * @param tab
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public  void ifSelectToDel(String className,int selectedRowIndex,JTable tab) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		IOToSQL io = (IOToSQL) Class.forName("com.Lauguobin.www.dao." + className).newInstance();
		
		int cno = (int )tab.getValueAt(selectedRowIndex, 0);
		try
		{
			//传入cno，试图在数据库删除
			io.del(cno);
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		DefaultTableModel model = (DefaultTableModel) tab.getModel(); 
		// 删除行
		model.removeRow(selectedRowIndex); 
	}	
	
}

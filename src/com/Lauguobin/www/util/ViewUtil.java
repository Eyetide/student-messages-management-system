package com.Lauguobin.www.util;

import java.awt.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.*;

public class ViewUtil
{
	
	/**一个可以让表格窗口自己适应的方法<网上直接抄的 ..>
	 * 
	 * @param myTable
	 */
	public static void fitTableColumns(JTable myTable)
	{
		  JTableHeader header = myTable.getTableHeader();
		     int rowCount = myTable.getRowCount();

		     Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();
		     while(columns.hasMoreElements()){
		         TableColumn column = (TableColumn)columns.nextElement();
		         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
		         int width = (int)myTable.getTableHeader().getDefaultRenderer()
		                 .getTableCellRendererComponent(myTable, column.getIdentifier()
		                         , false, false, -1, col).getPreferredSize().getWidth();
		         for(int row = 0; row<rowCount; row++){
		             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
		               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
		             width = Math.max(width, preferedWidth);
		         }
		         header.setResizingColumn(column); // 此行很重要
		         column.setWidth(width+myTable.getIntercellSpacing().width);
		     }
	}
	
	
	/**
	 * 	这一段获取屏幕高度
	 * @return 屏幕高度
	 */
	public static int geScreenHeight()
	{
		//这一段获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		return screenSize.height;
	}
	
	/**
	 * 	这一段获取屏幕宽度
	 * @return 屏幕宽度
	 */
	public static int geScreenWidth()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		return screenSize.width;
	}
}

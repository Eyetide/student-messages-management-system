package com.Lauguobin.www.util;

import java.sql.*;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtil
{
	private static final DbUtil instance = new DbUtil();
	private static ComboPooledDataSource cpds = new ComboPooledDataSource(true);    
	
	private DbUtil() { }
	
	public static DbUtil getInstance() {  
		return instance;
    }  

	/**
	 * 连接数据库
	 * @author GB菌
	 *
	 */
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		try {
			return cpds.getConnection();
			} catch (SQLException e) {
			e.printStackTrace();
			}
			return null;
	}
	
	
	/**关闭数据库连接
	 * 
	 * @param stm 
	 * @param con 
	 * @throws SQLException
	 */
	public static void close(PreparedStatement stm,Connection con) {
		try {
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

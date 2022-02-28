package com.lazy.common.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils extends LogsInit{
	static Logger logger = Logger.getLogger(DbUtils.class);
	private static ComboPooledDataSource dataSource;
	
	static {
		dataSource=new ComboPooledDataSource();
	}
	
	public static Connection getConnection(String ip,String db) {
		Connection connection=null;
		dataSource.setJdbcUrl("jdbc:mysql://"+ip+":3306/"+db+"?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
		try {
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
		dataSource.setUser(getUser());
		dataSource.setPassword(getPassword());
		dataSource.setInitialPoolSize(3);
		dataSource.setMinPoolSize(10);
		dataSource.setMaxStatements(200);
		dataSource.setMaxPoolSize(200);
		//链接失败后重新试30次。
		dataSource.setAcquireRetryAttempts(30);
		//最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。
		dataSource.setMaxIdleTime(30);
		//链接用完了自动增量3个
		dataSource.setAcquireIncrement(3);
		try {
			connection= dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();  // 快速异常捕获 Alt + shift + z 
				rs = null;   // 建议垃圾回收期回收资源
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		logger.info("close database....");
	}
	
	public static String getUser() {
		return AutoCommonUtil.readConfig("database.properties","username");
	}
	public static String getPassword() {
		return AutoCommonUtil.readConfig("database.properties","password");
	}
	
	

}

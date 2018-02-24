package com.SOB.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseConnManager {

	//JDBCֱ����ʽ����������web.xml�ļ��С�
	public static Connection getConnection(String driver, String url,
			String user, String pwd) {

		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return con;
	}
//JDBCֱ����ʽ����������dbproperty.properties�ļ��С�
	public static Connection getConnection_2(){
		
		Connection con = null;
		try {
//			FileInputStream fis = new FileInputStream("D:\\apache-tomcat-6.0.14\\webapps\\TingTaoBookStore0808\\WEB-INF\\classes\\databaseProperty.properties");
//			Properties pro = new Properties();
//			pro.load(fis);
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/SOB?useUnicode=true&characterEncoding=utf-8";
			String user = "root";
			String pwd = "1234";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}
		return con;
	}
//����tomcat����Դ�����ݿ����ӳط�ʽ
	public static Connection getConnection(){
		Connection con = null;
		try{
			InitialContext initConx = new InitialContext();
			Context cxt = (Context)initConx.lookup("java:comp/env");
			DataSource ds = (DataSource)cxt.lookup("jdbc/proMIS");
			con = ds.getConnection();
		}catch(NamingException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �ر����Ӷ���con
	 */
	public static void closeConn(Connection con){
		
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * �ر���������pst
	 */
	public static void closePreparedStatement(PreparedStatement pst){
		
		if(pst != null){
			try{
				pst.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �رս��������rs
	 */
	public static void closeResultSet(ResultSet rs){
		
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ȫ���رյķ���conn,pst,rs
	 */
	public static void closeAll(Connection con,PreparedStatement pst,ResultSet rs){
		
		if(rs != null && pst != null & rs != null){
			try{
				rs.close();
				pst.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ͬʱ�ر�conn,pst
	 */
	public static void closeCon_Pst(Connection con,PreparedStatement pst){
		
		if(pst != null && con != null){
			try{
				pst.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}

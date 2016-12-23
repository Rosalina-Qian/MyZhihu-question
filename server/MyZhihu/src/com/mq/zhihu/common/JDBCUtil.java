package com.mq.zhihu.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	//连接数据库地址语句
	private static String url = "jdbc:mysql://localhost:3306/zhihudb";
//	private static String url = "jdbc:sqlserver://123.207.18.20:1433;databaseName=ZhiHuNewDb";
	
	//数据库应用名
	private static String user = "root";
//	private static String user = "sa";
	
	//数据库密码
	private static String pwd = "";
//	private static String pwd = "432@uestc";
	
	//连接对象
	Connection con = null;
	
	//预处理语句对象
	PreparedStatement ps = null;
	
	//结果集对象
	ResultSet rs = null;
	
	
	/**
	 * 获取连接对象的方法
	 */
	public Connection getCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			// TODO: handle exception
//			System.out.println("bad job!");
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭所有连接
	 */
	public void closeAll(){
		try {
			if(con != null){
				con.close();
			}
			if(ps != null){
				ps.close();
			}
		if(rs != null){
				rs.close();
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * 增删改通用方法
	 * @param sql
	 * @param obj
	 * @return
	 */
	public int update(String sql, Object...obj){
		int result = 0;   
		con = getCon();
		try {
			ps = con.prepareStatement(sql);
			if(obj != null){
				for(int i = 0; i < obj.length; i++){
						ps.setObject(i + 1, obj[i]);
				}
			}result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询所有数据
	 * @param sql
	 * @param objects
	 * @return
	 */
	public ResultSet query(String sql, Object...objects){
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			for(int i = 0; i < objects.length; i++){
				ps.setObject(i + 1, objects[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
}

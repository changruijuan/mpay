package com.mi.pay.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

/**
 * Created by Ruijuan on 6/5/18.
 */
public class DBConnectTest {
	public void test1() {
		Statement stmt = null;
		Connection conn = null;
		try {
			//1.驱动注册程序  --内部执行了RegisterDriver
			Class.forName("com.mysql.jdbc.Driver");

			//2.获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pay?useUnicode=true&amp;characterEncoding=utf-8", "root", "315416.com");

			//3.创建Statement
			stmt = conn.createStatement();

			//4.准备sql
			String sql = "CREATE TABLE student(id INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),gender VARCHAR(2))";

			//5.发送sql语句，执行sql语句,得到返回结果
			int count = stmt.executeUpdate(sql);

			//6.输出
			System.out.println("影响了" + count + "行！");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			//7.关闭连接(顺序:后打开的先关闭)
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
		}
	}

	public static void main(String[] argv) {
		DBConnectTest dbConnectTest = new DBConnectTest();
		dbConnectTest.test1();
	}
}
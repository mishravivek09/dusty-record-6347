package com.dusty_record_6347.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection provideconnection() {
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/project";
		
		try {
			conn=DriverManager.getConnection(url, "root", "vivek");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
}

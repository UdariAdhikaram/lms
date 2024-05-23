package com.lms.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	
	public static Connection connect() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/leaenhub?useSSL=false","root","");
			System.out.println("Connection successfull" + conn);
			
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.err.println("Connection Failed");
		}
		
		
		
		return conn;
	}
}


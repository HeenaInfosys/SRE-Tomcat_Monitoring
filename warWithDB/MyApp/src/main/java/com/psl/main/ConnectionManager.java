package com.psl.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	static Connection conn;

	public static Connection getConnection() {
		try {
			// String url = "jdbc:mysql://10.222.78.115:3306";
			String url = "jdbc:mysql://10.152.102.104:3306";

			// String dbName ="mysql"; // here "mysql" is the name of Database.
			String dbName = "test";
			String uname = "root";
			String pwd = "root";

			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection(url, uname, pwd);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnectionForPostgreSQL() {

		try {
			String dbName = "reviewdb";
			String uname = "ciadmin";
			String pwd = "ciadmin";
                        System.out.println("Connection");
			String url = "jdbc:postgresql://localhost:5432/" + dbName;
                   
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, uname, pwd);
                        System.out.println("Connection established");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return conn;

	}

}

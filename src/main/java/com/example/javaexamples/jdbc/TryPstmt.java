package com.example.javaexamples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TryPstmt {
	public static void main(String[] args) throws Exception {
		String dbURL = "jdbc:oracle:thin:@dbhost:1521:orcl";
		String dbUser = "test";
		String dbPassword = "test";

		// Connect to the database
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		
		PreparedStatement ps = con
				.prepareStatement("select login from users where first_name='hello'");
		ResultSet rs = ps.executeQuery();
		
		String s = "";
		if (rs.next()) {
			s = rs.getString("login");
		}

		
		System.out.println(s);
		
		ps.close();
		con.close();
		System.exit(0);
	}
}

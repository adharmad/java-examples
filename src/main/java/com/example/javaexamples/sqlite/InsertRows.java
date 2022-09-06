package com.example.javaexamples.sqlite;

import java.sql.*;

public class InsertRows {
    public static void main(String[] args) {
        String dbName = "test.db";
        String url = "jdbc:sqlite:" + dbName;

        String sql = "INSERT INTO USERS(id, login, first_name, last_name, ssn, age) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i=0 ; i<10 ; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, "gandalf"+i);
                pstmt.setString(3, "gandalf_first"+i);
                pstmt.setString(4, "gandalf_last"+i);
                pstmt.setString(5, ""+(i * 111111111));
                pstmt.setInt(6, i+10);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

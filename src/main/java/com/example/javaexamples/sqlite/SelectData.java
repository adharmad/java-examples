package com.example.javaexamples.sqlite;

import java.sql.*;

public class SelectData {
    public static void main(String[] args) {
        String dbName = "test.db";
        String url = "jdbc:sqlite:" + dbName;

        String sql = "select * from users";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                StringBuffer sbuf = new StringBuffer();
                for (int i=0 ; i<rsmd.getColumnCount() ; i++) {
                    String columnName = rsmd.getColumnName(i+1);
                    String columnValue = rs.getString(columnName);

                    sbuf.append(columnName + " --> " + columnValue + "\n");
                }
                sbuf.append("===================================");
                System.out.println(sbuf.toString());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

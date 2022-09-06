package com.example.javaexamples.sqlite;

import java.sql.*;

public class CreateTable {
    public static void main(String[] args) {
        String dbName = "test.db";
        String url = "jdbc:sqlite:" + dbName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS USERS (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	login text NOT NULL,\n"
                + "	first_name text,\n"
                + "	last_name text,\n"
                + "	ssn text,\n"
                + "	age number\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

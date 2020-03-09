package com.example.javaexamples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetUsersFromDB {
    public static void main(String[] args)
    {
        String username = "sa";
        String password = "sa";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@host:1521:sid";
        String schemaName = "sa";
        Connection con;

        try 
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            String queryString = "SELECT * sa.USERS";
            ResultSet rs = stmt.executeQuery(queryString);
            
            while (rs.next())
            {
                String userName = rs.getString("USERID");
                
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                String xlpassword = rs.getString("PASSWORD");
                String manager = rs.getString("MANAGERNAME");
                String role = rs.getString("XLROLE");
                String xltype = rs.getString("EMPTYPE");
                String location = rs.getString("LOCATION");
                
                System.out.println(userName + " " + firstName + " " + lastName 
                        + " " + xlpassword + " " + manager + " " + role 
                        + " " + xltype + " " + location);
                
            }
        }
        catch (Exception exception1) 
        {
            System.out.println("Exception: " + exception1);
        }
        
    }
}

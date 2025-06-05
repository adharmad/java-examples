package com.example.javaexamples.hsqldb;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.sql.*;
import java.util.UUID;

public class Hsqldb2 {
    public static void main(String[] args) throws Exception {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:" + uuid, "sa", "");
        String createTable = """
                create table t(mypk integer IDENTITY PRIMARY KEY, id integer, data varchar(100));
                """;

        System.out.println("Creating table");
        try (Statement statement = c.createStatement()) {
            statement.executeUpdate(createTable);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        System.out.println("");
        System.out.println("=============================================");
        System.out.println("");

        try {
            String sql = "insert into t (id, data) values (?, ?)";
            PreparedStatement stmt = c.prepareStatement(sql);

            for (int i=0 ; i<10000 ; i++) {
                int randId = RandomUtils.nextInt(1, 1001);
                String data = RandomStringUtils.randomAlphabetic(50);
                System.out.println(String.format("inserting %s and %s into t", randId, data));

                stmt.setInt(1, randId);
                stmt.setString(2, data);
                stmt.addBatch();
            }
            stmt.executeBatch();

            c.commit();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        System.out.println("");
        System.out.println("=============================================");
        System.out.println("");

        try (Statement statement = c.createStatement()) {
            String sql = "select mypk, id, data from t order by id";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println(String.format("%s - fetched %s and %s", rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }

            rs.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        System.out.println("");
        System.out.println("=============================================");
        System.out.println("");

        c.close();
    }
}

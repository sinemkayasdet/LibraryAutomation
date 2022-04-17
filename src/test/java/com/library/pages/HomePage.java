package com.library.pages;

import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class HomePage {

    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;


    public void printID() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(ConfigurationReader.getProperty("url"),
                    ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {

                System.out.println(rs.getString("id"));
            }
            rs.previous();
        } catch (SQLException e) {
            System.out.println("Exception occurred " + e.getMessage());
            e.printStackTrace();
        }

    }


    public void verifyUniquId() throws SQLException {
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        DB_Util.runQuery("select id from users");
        int expectedSize = DB_Util.getRowCount();
        DB_Util.runQuery("Select distinct id from users");
        int actualSize = DB_Util.getRowCount();
        Assert.assertEquals(expectedSize, actualSize);
    }

    public void printCloumnName() throws SQLException {
        Connection conn = DriverManager.getConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount =rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.println("rsmd.getColumnName(" +i +") = " + rsmd.getColumnName(i));
        }
    }
}




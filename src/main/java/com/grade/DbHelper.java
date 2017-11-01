package com.grade;

import org.junit.Test;

import javax.activation.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.*;
import java.util.Hashtable;

/**
 * Created by Administrator on 2017/2/23.
 */
public class DbHelper
{
    static String driver = "org.postgresql.Driver";
    static String url = "jdbc:postgresql://localhost:5432/gradereport";
    static String name = "postgres";
    static String password = "1234";
    static Statement myStatement = null;
    Connection conn;

    public  void OpenDb() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(url,name,password);
        System.out.println(" Connection Database Succeed===========");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DbHelper dbhelper = new DbHelper();
        dbhelper.OpenDb();
    }

    public void closeDb(){
        try {
            if (myStatement != null) {
                myStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            myStatement = conn.createStatement();
        }
        catch (SQLException ex1) {
        }
        ResultSet rs = null;
        try {
            rs = myStatement.executeQuery(sql);
            return rs;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public int updateQuery(String sql) {
        int rs;
        try {
            myStatement = conn.createStatement();
            rs = myStatement.executeUpdate(sql);
            return rs;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return -1;
        }
    }

}

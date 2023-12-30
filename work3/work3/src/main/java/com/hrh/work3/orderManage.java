package com.hrh.work3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class orderManage {
    private static String driver="com.mysql.jdbc.Driver";;
    private static String url="jdbc:mysql://localhost:3306/work3?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private static String username="root";
    private static String password="T516822";


    public static Connection getConnection() throws SQLException {
        Connection connection=null;
        try{
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    public static void release(Connection conn, Statement st, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

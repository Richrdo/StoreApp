package com.example.shopstore.data;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectMysql{
    private static String url="jdbc:mysql://47.106.177.200:3306/store?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    private static  String name="ceaser";
    private static String psw="Q201023aaa";


    private static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection(url,name,psw);

        }catch (ClassNotFoundException | SQLException e){
            Log.i("ConnectMysql","连接失败");
            e.printStackTrace();
        }
        return conn;
    }


}

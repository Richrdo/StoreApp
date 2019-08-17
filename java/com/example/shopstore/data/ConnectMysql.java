//package com.example.shopstore.data;
//
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.shopstore.Control.MyApplication;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//public class ConnectMysql{
//    private  String url="jdbc:mysql://47.106.177.200:3306/store?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
//    private   String name="ceaser";
//    private  String psw="Q201023aaa";
//    private  Connection conn;
//
//    public  void openConnection(){
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn= DriverManager.getConnection(url,name,psw);
//
//        }catch (ClassNotFoundException | SQLException e){
//            Log.i("ConnectMysql","连接成功");
//            e.printStackTrace();
//        }
//    }
//
//    public void query(){
//        try{
//            PreparedStatement pst=conn.prepareStatement("user store");
//            ResultSet rst=pst.executeQuery("select name from user where id=1");
//            while (rst.next()){
//                String s=String.valueOf(rst.getObject(1));
//                Toast.makeText(MyApplication.getContext(),s,Toast.LENGTH_LONG).show();
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public  void closeConnection(){
//        try{
//            conn.close();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//}

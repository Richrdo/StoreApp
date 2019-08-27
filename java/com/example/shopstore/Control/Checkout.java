package com.example.shopstore.Control;

import android.util.Log;

import com.example.shopstore.data.User;
import com.example.shopstore.view.LoginPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Checkout {
    public static String submit(String ori_url,String content){
        String result=null;
        try{
            URL url=new URL(ori_url);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type","application/json");
            connection.addRequestProperty("Connection","Keep-Alive");
            connection.setDoOutput(true);
            OutputStream os=connection.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            os.close();
            if (connection.getResponseCode()==200){
                result=LoginPane.streamToString(connection.getInputStream());
                Log.e("MYTAG", "提交成功");
            }else{
                Log.e("MYTAG", "提交失败" );
            }
        }catch (IOException e){
            Log.e("MYTAG", "提交信息失败" );
            e.printStackTrace();
        }
        return result;
    }

    public static InputStream getUserInfoStream(String ori_url){
        InputStream inputStream=null;
        try{
            URL url=new URL(ori_url);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Content-Type","application/json");

            connection.connect();
            if (connection.getResponseCode()==200){
                inputStream=connection.getInputStream();
            }
        }catch (IOException e){
            Log.e("MYTAG", "获取用户信息失败!MESSAGE="+e.getMessage() );
        }
        return  inputStream;
    }

    public static User getUserInfo(String ori_url){
        User user=new User();
        InputStream inputStream=getUserInfoStream(ori_url);
        Log.e("MYTAG", "获取用户信息成功" );
        String result= LoginPane.streamToString(inputStream);
        Log.e("MYTAG", "用户信息为, "+result );
        try{
            JSONObject jsonObject=new JSONObject(result);
            user.setName(jsonObject.getString("name"));
            user.setPhone(jsonObject.getLong("phone"));
            user.setSex(jsonObject.getString("sex"));
        }catch (JSONException e){
            Log.e("MYTAG", "转换json失败， "+e.getMessage() );
        }
        return user;
    }

}

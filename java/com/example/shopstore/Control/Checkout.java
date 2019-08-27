package com.example.shopstore.Control;

import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Checkout {
    public static void submit(String ori_url,String content){
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
                Log.e("MYTAG", "提交成功");
            }else{
                Log.e("MYTAG", "提交失败" );
            }
        }catch (IOException e){
            Log.e("MYTAG", "提交信息失败" );
            e.printStackTrace();
        }
    }

}

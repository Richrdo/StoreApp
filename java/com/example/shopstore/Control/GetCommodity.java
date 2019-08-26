package com.example.shopstore.Control;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.shopstore.data.Commodity;
import com.example.shopstore.data.StoreData;
import com.example.shopstore.view.LoginPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetCommodity {

    public static InputStream doGet(String ori_url, String contentType){
        InputStream inputStream=null;
        try{
            URL url=new URL(ori_url);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("Content-Type",contentType);

            connection.connect();
            if (connection.getResponseCode()==200){
                Log.e("MYTAG", "获取数据成功" );
                inputStream=connection.getInputStream();
            }else{
                Log.e("MYTAG", "连接失败,Message="+connection.getResponseCode());
            }
        }catch (MalformedURLException e){
            Log.e("MYTAG", "MalformedURLException,MESSAGE="+e.getMessage() );
        }catch (IOException e){
            Log.e("MYTAG", "IOEXCEPITON ,MESSAGE="+e.getMessage() );
            e.printStackTrace();
        }
        return inputStream;
    }

    //初始化商品
    public static List<Commodity> initCommodity(String ori_url){
        List<Commodity> commodities=new ArrayList<>();
        Commodity commodity;
        String result= LoginPane.streamToString(doGet(ori_url,"application/json"));
        try{
            JSONArray jsonArray=new JSONArray(result);
            if (jsonArray.length()>0){
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject json=jsonArray.getJSONObject(i);
                    commodity=new Commodity();
                    commodity.setCommodityName(json.getString("name"));
                    commodity.setId(json.getInt("id"));
                    commodity.setPrice(json.getDouble("price"));
                    commodity.setType(json.getString("type"));
                    commodity.setImagePath(json.getString("imagePath"));
                    commodity.setGroundDate(json.getString("news_date"));
                    commodity.setDescribe(json.getString("describe"));
                    commodities.add(commodity);
                }
            }
        }catch(JSONException e){
            Log.e("MYTAG", " json转换失败,MESSAGE="+e.getMessage() );
        }
        Log.e("MYTAG", "Commodity对象列表已取得，commodities.size="+commodities.size() );
        return commodities;
    }

    public static void downloadImage(Commodity commodity, Context context){
        String picPath=null;
        picPath=commodity.getImagePath();
        try{
            picPath="http://47.106.177.200:8080/store/image?path="+picPath;
            InputStream inputStream=doGet(picPath,"application/json");
            String savePath=context.getFilesDir()+"/goods_image/";
            File file=new File(savePath+commodity.getId()+".png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            Log.e("MYTAG", "bitmap获取成功" );
            StoreData.imageMap.put(commodity.getId(),bitmap);
            FileOutputStream outputStream=new FileOutputStream(file);
            Log.e("MYTAG", "file存储成功" );
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.close();

        }catch (IOException e){
            Log.e("MYTAG", "读取图片失败，MESSAGE="+e.getMessage() );
            e.printStackTrace();
        }

    }

    public static  void getImage(Context context,List<Commodity> commodities){
        String picPath=context.getFilesDir()+"/goods_image/";
        try{
            File file=new File(picPath);
            if (!file.exists()){
                file.mkdir();
                Log.e(" MYTAG", "文件创建成功  " );
                for (Commodity commodity:commodities){
                    downloadImage(commodity ,context);
                }
            }else{
                for (Commodity commodity:commodities){
                    file=new File(picPath+commodity.getId()+".png");
                    if (!file.exists()){
                        Log.e("MYTAG", "开始获取网络图片"+file.toString() );
                        downloadImage(commodity,context);
                    }else{
                        Log.e("MYTAG", "开始获取本地图片"+file.toString() );
                        FileInputStream stream=new FileInputStream(file);
                        Bitmap bitmap=BitmapFactory.decodeStream(stream);
                        StoreData.imageMap.put(commodity.getId(),bitmap);
                    }
                }
            }
        }catch (FileNotFoundException e){
            Log.e("mytag", "文件找不到 ，MESSAGE="+e.getMessage() );
        }
    }

}

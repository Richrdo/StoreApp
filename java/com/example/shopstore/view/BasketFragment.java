package com.example.shopstore.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.shopstore.Control.BasketCommodityAdapter;
import com.example.shopstore.Control.Checkout;
import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;
import com.example.shopstore.data.OrderBean;
import com.example.shopstore.data.StoreData;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

public class BasketFragment extends Fragment {
    private ListView listView;
    private CheckBox select_all;
    private TextView sum_price;
    private Button btn_checkout;
    private Activity mActivity;

    SharedPreferences sharedPreferences;

    public static BasketCommodityAdapter basketCommodityAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.e("MYTAG", " 开始初始化适配器,StoreData.size="+StoreData.commodityBasket.size() );
        basketCommodityAdapter=new BasketCommodityAdapter(mActivity, StoreData.commodityBasket);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_basket,container,false);
        listView=view.findViewById(R.id.basket_list);
        select_all=view.findViewById(R.id.ck_select_all);
        sum_price=view.findViewById(R.id.tv_sum);
        btn_checkout=view.findViewById(R.id.btn_checkout);

        initBasket();

        return view;
    }

    private void initBasket(){
        Log.e("MYTAG", "正在createView" );
        listView.setAdapter(basketCommodityAdapter);
        sharedPreferences=mActivity.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit_order(StoreData.commodityBasket);
            }
        });
    }

    public  void submit_order(List<Commodity> commodities){
        OrderBean orderBean=new OrderBean(Long.valueOf(sharedPreferences.getString("PHONE_NUMBER","").trim()),commodities);
        Gson gson=new Gson();
        String json=gson.toJson(orderBean);
        String ori_url="http://47.106.177.200:8080/store/basket";
        Thread submit=new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("MYTAG", "开始提交json数据，json="+json );
                Checkout.submit(ori_url,json);
            }
        });
        try{
            submit.start();
            submit.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        StoreData.commodityBasket.clear();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                basketCommodityAdapter.notifyDataSetChanged();
                Toast.makeText(mActivity,"订单已提交！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.example.shopstore.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopstore.data.StoreData;
import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CommodityDetail extends AppCompatActivity implements View.OnClickListener {
    private Commodity commodity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detail);

        commodity=(Commodity)getIntent().getSerializableExtra("current_commodity");

        Button chat=(Button)findViewById(R.id.btn_chat);
        Button add_to_basket=(Button)findViewById(R.id.btn_add_to_basket);
        Button submit_order=(Button)findViewById(R.id.btn_submit_order);

        ImageView imageView=(ImageView) findViewById(R.id.detail_image);
        TextView describe=(TextView)findViewById(R.id.detail_describe);

        imageView.setImageBitmap(StoreData.imageMap.get(commodity.getId()));
        describe.setText(commodity.getDescribe());

        add_to_basket.setOnClickListener(this);
        submit_order.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_to_basket:{
                Log.e("MYTAG", "点击了加入购物车" );
                StoreData.commodityBasket.add(commodity);
                Log.e("MYTAG", "商品"+commodity.getCommodityName()+"已加入购物车" );
                this.finish();
            };break;
            case R.id.btn_submit_order:{
                List<Commodity> commodities=new ArrayList<>();
                commodities.add(commodity);
                HomePage.basketFragment.submit_order(commodities);
                this.finish();
            };break;
        }
    }
}

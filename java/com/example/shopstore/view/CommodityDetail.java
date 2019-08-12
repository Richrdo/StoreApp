package com.example.shopstore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;

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

        imageView.setImageResource(commodity.getImageId());
        describe.setText(commodity.getDescribe());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //TODO 给下方按钮设置事件
        }
    }
}

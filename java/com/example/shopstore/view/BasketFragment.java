package com.example.shopstore.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.shopstore.Control.BasketCommodityAdapter;
import com.example.shopstore.R;
import com.example.shopstore.data.StoreData;

public class BasketFragment extends Fragment {
    private ListView listView;
    private CheckBox select_all;
    private TextView sum_price;
    private Button btn_checkout;
    private Activity mActivity;

    public BasketCommodityAdapter basketCommodityAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
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
        Log.e("MYTAG", "开始初始化适配器" );
        basketCommodityAdapter=new BasketCommodityAdapter(mActivity, StoreData.commodityBasket);
        Log.e("MYTAG", "开始配置适配器" );
        listView.setAdapter(basketCommodityAdapter);
        Log.e("MYTAG", "basket适配器配置完成" );
    }
}

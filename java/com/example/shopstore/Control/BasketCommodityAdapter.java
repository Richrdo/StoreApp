package com.example.shopstore.Control;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;
import com.example.shopstore.data.StoreData;

import java.util.List;

public class BasketCommodityAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Commodity> mCommodities;

    public BasketCommodityAdapter(Context context, List<Commodity> commodities){
        this.inflater=LayoutInflater.from(context);
        mCommodities=commodities;
    }

    @Override
    public int getCount() {
        return mCommodities.size();
    }

    @Override
    public Object getItem(int i) {
        return mCommodities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View commodityView=inflater.inflate(R.layout.commodity_basket,null);
        Commodity commodity=mCommodities.get(i);

        ImageView imageView=commodityView.findViewById(R.id.basket_commodity_image);
        TextView commodity_describe=commodityView.findViewById(R.id.basket_commodity_describe);
        TextView commodity_price=commodityView.findViewById(R.id.basket_commodity_prices);

        if (commodity.getDescribe()!=null&&commodity.getPrice()>0&& StoreData.imageMap.get(commodity.getId())!=null){
            imageView.setImageBitmap(StoreData.imageMap.get(commodity.getId()));
            commodity_describe.setText(commodity.getDescribe());
            commodity_price.setText("$"+commodity.getPrice());
        }else{
            Log.e("MYTAG", "适配器空指针异常" );
        }


        return commodityView;
    }
}

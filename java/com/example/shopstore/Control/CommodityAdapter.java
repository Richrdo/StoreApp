package com.example.shopstore.Control;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopstore.data.Commodity;
import com.example.shopstore.R;

import java.util.List;

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder>{

    private List<Commodity> mCommodities;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView commodityImage;
        TextView commodityName;
        TextView commodityDescribe;

        public ViewHolder(View view) {
            super(view);
            commodityImage=(ImageView)view.findViewById(R.id.commodity_image_view);
            commodityName=(TextView)view.findViewById(R.id.commodity_name);
            commodityDescribe=(TextView)view.findViewById(R.id.commodity_describe);
        }
    }


    public CommodityAdapter(List<Commodity> commodities){
        mCommodities=commodities;
    }

    /*
     *onCreateViewHolder创建ViewHolder实例
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.commodity_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return  holder;
    }

    //对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Commodity commodity=mCommodities.get(position);
        holder.commodityImage.setImageResource(commodity.getImageId());
        holder.commodityName.setText(commodity.getCommodityName());
        holder.commodityDescribe.setText(commodity.getDescribe());
    }

    @Override
    public int getItemCount(){
        return mCommodities.size();
    }
}

package com.example.shopstore.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.shopstore.Control.CommodityAdapter;
import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private FragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments;
    private List<Commodity> commodities=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        initCommodities();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_store_page,container,false);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.review_sp);

        //第一个参数用于指定布局的列数，第二个参数用于指定布局的排列方向，VERTICAL表示布局纵向排列
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        CommodityAdapter adapter=new CommodityAdapter(this.getContext(),commodities);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initCommodities(){
        Commodity colthes_1=new Commodity("PANTS九分裤",R.drawable.clothes_1,"弹性舒适九分裤，时尚潮流，穿着贴身");
        Commodity colthes_2=new Commodity("小哈尼T恤",R.drawable.clothes_2,"纯棉男生学生纯白2019新款");
        Commodity life_1=new Commodity("纸约卫生纸",R.drawable.life_1,"30包抽纸卫生纸沾水不破");
        Commodity life_2=new Commodity("电动牙刷",R.drawable.life_2,"小米电动牙刷，电力强劲阻尼防滑深度清洁");
        Commodity food_1=new Commodity("BLING纯净水",R.drawable.food_1,"BLING纯净水玻璃瓶装高端大气上档次");
        Commodity food_2=new Commodity("康师傅饮品",R.drawable.food_2,"康师傅饮品1L装六种不同口味");
        commodities.add(colthes_1);
        commodities.add(colthes_2);
        commodities.add(food_1);
        commodities.add(food_2);
        commodities.add(life_1);
        commodities.add(life_2);

    }

}

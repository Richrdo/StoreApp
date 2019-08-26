package com.example.shopstore.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.shopstore.Control.CommodityAdapter;
import com.example.shopstore.Control.GetCommodity;
import com.example.shopstore.R;
import com.example.shopstore.data.Commodity;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener{
    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private FragmentPagerAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Fragment> mFragments;
    private List<Commodity> commodities=new ArrayList<>();

    private Activity mActivity;
    private CommodityAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(Activity)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.activity_store_page,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.review_sp);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        //第一个参数用于指定布局的列数，第二个参数用于指定布局的排列方向，VERTICAL表示布局纵向排列
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter=new CommodityAdapter(mActivity,commodities);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initCommodities(){
        commodities= GetCommodity.initCommodity("http://47.106.177.200:8080/store/get_commodity");
        GetCommodity.getImage(mActivity,commodities);
    }

    @Override
    public void onRefresh() {

        Log.e("MYTAG", "响应下拉刷新事件 ");
        Thread refresh = new Thread(new Runnable() {
            @Override
            public void run() {
                //从网络获取Commodity数据
                initCommodities();
                Log.e("MYTAG", "获取commodity数据完成");
                //配置适配器
                adapter = new CommodityAdapter(mActivity, commodities);
                Log.e("MYTAG", "适配器配置完成" );
                //加载适配器
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("MYTAG", "开始更改适配器" );
                        recyclerView.setAdapter(adapter);
                        Log.e("MYTAG", "更改适配器完成" );
                    }
                });
            }
        });
        refresh.start();
        try {
            refresh.join();
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Log.e("MYTAG", "fail in refresh ");
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}

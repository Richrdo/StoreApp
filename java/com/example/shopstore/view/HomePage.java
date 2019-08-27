package com.example.shopstore.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.shopstore.Control.MyFragmentPagerAdapter;
import com.example.shopstore.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;

    private List<Fragment> mFragments;
    private FragmentPagerAdapter mAdapter;

    private static StoreFragment storeFragment=new StoreFragment();
    public static BasketFragment basketFragment=new BasketFragment();
    public static UserFragment userFragment=new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initView();
    }

    private void initView(){
        //find view
        mViewPager=findViewById(R.id.fragment_vp);
        mTabRadioGroup=findViewById(R.id.tabs_rg);

        //init fragment
        mFragments=new ArrayList<>(4);
        mFragments.add(storeFragment);
        mFragments.add(new MessageFragment());
        mFragments.add(basketFragment);
        mFragments.add(userFragment);

        //init view pager
        mAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        //register listener
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mTabRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mViewPager.removeOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton=(RadioButton)mTabRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onRestart(){
        super.onRestart();
        Log.e("MYTAG", "回到主线程" );
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("MYTAG", "开始唤醒适配器" );
                Log.e("mytag", "basketFragment="+basketFragment.toString() );
                Log.e("mytag", "basketCommodityAdapter="+basketFragment.basketCommodityAdapter.toString() );
                basketFragment.basketCommodityAdapter.notifyDataSetChanged();
                Log.e("MYTAG", "适配器成功唤醒" );
            }
        });
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            for (int i=0;i<radioGroup.getChildCount();i++){
                if (radioGroup.getChildAt(i).getId()==checkedId){
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    };
}

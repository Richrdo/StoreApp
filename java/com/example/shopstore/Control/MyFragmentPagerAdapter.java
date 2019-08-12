package com.example.shopstore.Control;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.mList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mList==null?null:this.mList.get(position);
    }

    @Override
    public int getCount() {
        return this.mList==null?0:this.mList.size();
    }
}

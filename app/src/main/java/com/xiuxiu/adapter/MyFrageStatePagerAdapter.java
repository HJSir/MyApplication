package com.xiuxiu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by jian on 2016/12/5.
 */


public class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fm;
    public MyFrageStatePagerAdapter(FragmentManager fm,List<Fragment> fragmentList)
    {
        super(fm);
        this.fragmentList=fragmentList;

    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

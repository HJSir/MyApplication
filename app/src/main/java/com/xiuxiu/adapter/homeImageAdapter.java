package com.xiuxiu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by jian on 2016/12/5.
 */

public class homeImageAdapter extends PagerAdapter {
    private ArrayList<SimpleDraweeView> viewlist;

    public homeImageAdapter(ArrayList<SimpleDraweeView> viewlist) {
        this.viewlist = viewlist;
    }

    @Override
    //需要滑動的VIEW的个数
    public int getCount() {
        //设置成最大，使用户看不到边界
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    //destroyItem（）：从当前container中删除指定位置（position）的View; 由于在下面instantiateItem设置了所以这里不实现 否则有BUG
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        //Warning：不要在这里调用removeView
    }

    @Override
    //instantiateItem()：做了两件事，第一：将当前视图添加到container中，第二：返回当前View
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
        position %= viewlist.size();
        //简单的求模会出现问题：考虑用户向左滑的情形，则position可能会出现负值。所以我们需要对负值再处理一次，使其落在正确的区间内
        if (position < 0) {
            position = viewlist.size() + position;
        }
        SimpleDraweeView view = viewlist.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        //add listeners here if necessary
        return view;
    }
}

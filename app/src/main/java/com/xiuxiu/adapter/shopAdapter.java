package com.xiuxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiuxiu.entity.shopBean;
import com.xiuxiu.fragment.HomeFragment;

import java.util.List;

/**
 * Created by jian on 2016/12/7.
 */

public class shopAdapter extends BaseAdapter {
private List<shopBean> mlist;
    private LayoutInflater mInflater;

//    public shopAdapter(Context context, List<shopBean> list) {
//        mlist=list;
//        mInflater=LayoutInflater.from(context);
//    }

    public shopAdapter(Context context, List<shopBean> list) {
        mlist=list;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView =mInflater.inflate(R.layout.listview_shop,null);
            viewHolder.shop_image= (SimpleDraweeView) convertView.findViewById(R.id.image_shop);
            viewHolder.shop_name= (TextView) convertView.findViewById(R.id.name_shop);
            viewHolder.shop_trade= (TextView) convertView.findViewById(R.id.trade_shop);
            viewHolder.shop_distance= (TextView) convertView.findViewById(R.id.distance_shop);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        shopBean bean=mlist.get(position);
        viewHolder.shop_distance.setText(bean.distance_shop);
        viewHolder.shop_name.setText(bean.name_shop);
        viewHolder.shop_trade.setText(bean.trade_shop);
        viewHolder.shop_image.setImageURI(bean.Image_shop);
        return convertView;
    }
    class ViewHolder{
       private SimpleDraweeView shop_image;
        private TextView shop_name;
        private TextView shop_distance;
        private TextView shop_trade;
    }
}

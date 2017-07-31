package com.xiuxiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.R;
import com.xiuxiu.entity.orderbean;


import java.util.List;


/**
 * Created by jian on 2017/7/13.
 */

public class OrderWaitAdapter extends BaseAdapter {

    private List<orderbean> mlist;
    private LayoutInflater mInflater;
    Context context;
    public OrderWaitAdapter(Context context, List<orderbean> list) {
        mlist=list;
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return  mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null){
            viewholder = new Viewholder();
            convertView = mInflater.inflate(R.layout.order_wait_listview,null);
            viewholder.shopname = (TextView) convertView.findViewById(R.id.lv_wait_shopname);
            viewholder.content= (TextView) convertView.findViewById(R.id.lv_wait_content);
            viewholder.orderstatues= (TextView) convertView.findViewById(R.id.lv_wait_statues);
            viewholder.place= (TextView) convertView.findViewById(R.id.lv_wait_place);
            viewholder.price= (TextView) convertView.findViewById(R.id.lv_wait_price);
            viewholder.shopimage= (ImageView) convertView.findViewById(R.id.lv_wait_image);
            viewholder.bt_cancle = (Button) convertView.findViewById(R.id.lv_wait_cancle);
            convertView.setTag(viewholder);

        }else{
            viewholder = (Viewholder) convertView.getTag();
        }
        orderbean orderbean = mlist.get(position);
        viewholder.shopname.setText(orderbean.getShopname());
        viewholder.content.setText(orderbean.getContent());
        viewholder.orderstatues.setText(orderbean.getOrderstatues());
        viewholder.place.setText(orderbean.getPlace());
        viewholder.price.setText(orderbean.getPrice());
        viewholder.shopimage.setImageResource(R.mipmap.ic_launcher);
        viewholder.bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"取消成功",Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }

    class Viewholder{
        private TextView shopname;
        private TextView orderstatues;
        private TextView place;
        private TextView content;
        private TextView price;
        private ImageView shopimage;
        private Button bt_cancle;


    }
}

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

public class OrderDoAdapter extends BaseAdapter {
    private List<orderbean> mlist;
    private LayoutInflater mInflater;
    Context context;
    public OrderDoAdapter(Context context, List<orderbean> list) {
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
        OrderDoAdapter.Viewholder viewholder;
        if(convertView==null){
            viewholder = new OrderDoAdapter.Viewholder();
            convertView = mInflater.inflate(R.layout.order_do_listview,null);
            viewholder.shopname = (TextView) convertView.findViewById(R.id.lv_do_shopname);
            viewholder.content= (TextView) convertView.findViewById(R.id.lv_do_content);
            viewholder.orderstatues= (TextView) convertView.findViewById(R.id.lv_do_statues);
            viewholder.place= (TextView) convertView.findViewById(R.id.lv_do_place);
            viewholder.price= (TextView) convertView.findViewById(R.id.lv_do_price);
            viewholder.shopimage= (ImageView) convertView.findViewById(R.id.lv_do_image);
            viewholder.bt_checkplan = (Button) convertView.findViewById(R.id.lv_do_checkplan);
            viewholder.bt_surefinish = (Button) convertView.findViewById(R.id.lv_do_surefinish);
            convertView.setTag(viewholder);

        }else{
            viewholder = (OrderDoAdapter.Viewholder) convertView.getTag();
        }
        orderbean orderbean = mlist.get(position);
        viewholder.shopname.setText(orderbean.getShopname());
        viewholder.content.setText(orderbean.getContent());
        viewholder.orderstatues.setText(orderbean.getOrderstatues());
        viewholder.place.setText(orderbean.getPlace());
        viewholder.price.setText(orderbean.getPrice());
        viewholder.shopimage.setImageResource(R.mipmap.ic_launcher);
        viewholder.bt_surefinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"确认完工",Toast.LENGTH_SHORT).show();
            }
        });
        viewholder.bt_checkplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"进度不明",Toast.LENGTH_SHORT).show();

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
        private Button bt_checkplan;
        private Button bt_surefinish;

    }

}

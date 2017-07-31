package com.xiuxiu.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.R;
import com.xiuxiu.entity.professorBean;
import com.xiuxiu.entity.shopBean;

import java.util.List;

public class professorAdapter extends BaseAdapter {
    private List<professorBean> mlist;
    private LayoutInflater mInflater;
    public professorAdapter(Context context, List<professorBean> list) {
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
            convertView =mInflater.inflate(R.layout.listview_professor,null);

            viewHolder.prof_name= (TextView) convertView.findViewById(R.id.tv_professorName);
            viewHolder.prof_time= (TextView) convertView.findViewById(R.id.tv_professorTime);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        professorBean bean=mlist.get(position);
        viewHolder.prof_name.setText(bean.getProfessorName());
        viewHolder.prof_time.setText(bean.getProfessorTime());

        return convertView;
    }

    class ViewHolder{
//        private ImageView shop_image;
        private TextView prof_name;
        private TextView prof_time;

    }


}

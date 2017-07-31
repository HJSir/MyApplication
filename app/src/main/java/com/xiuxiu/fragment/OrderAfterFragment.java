package com.xiuxiu.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.R;
import com.xiuxiu.adapter.OrderAfterAdapter;
import com.xiuxiu.entity.orderbean;

import java.util.ArrayList;
import java.util.List;


public class OrderAfterFragment extends Fragment {


    View view;
    List<orderbean> mList;
    orderbean mOrderbean;
    ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_order_after, container, false);

        mListView= (ListView) view.findViewById(R.id.lv_order_after);

        if(mList==null){
            mList=new ArrayList<orderbean>();
            mOrderbean = new orderbean();
            mOrderbean.setContent("机电维修");
            mOrderbean.setOrderstatues("售后处理");
            mOrderbean.setPlace("吉首大学张家界校区");
            mOrderbean.setShopname("吉首大学维修中心");
            mList.add(mOrderbean);
        }
        mListView.setAdapter(new OrderAfterAdapter(getActivity(),mList));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

}

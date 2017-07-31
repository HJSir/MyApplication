package com.xiuxiu.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import com.R;
import com.xiuxiu.adapter.OrderDoAdapter;
import com.xiuxiu.entity.orderbean;

import java.util.ArrayList;
import java.util.List;


public class OrderDoFragment extends Fragment {



    View view;
    List<orderbean> mList;
    orderbean mOrderbean;
    ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_order_do, container, false);

        mListView= (ListView) view.findViewById(R.id.lv_order_do);

        if(mList==null){
            mList=new ArrayList<orderbean>();
            mOrderbean = new orderbean();
            mOrderbean.setContent("机电维修");
            mOrderbean.setOrderstatues("正在维修");
            mOrderbean.setPlace("吉首大学张家界校区");
            mOrderbean.setShopname("吉首大学维修中心");
            mList.add(mOrderbean);
        }
        mListView.setAdapter(new OrderDoAdapter(getActivity(),mList));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }


}



package com.xiuxiu.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.R;
import com.xiuxiu.adapter.OrderFinishAdapter;
import com.xiuxiu.entity.orderbean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFinishFragment extends Fragment {

    View view;
    List<orderbean> mList;
    orderbean mOrderbean;
    ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_order_finish, container, false);

        mListView= (ListView) view.findViewById(R.id.lv_order_finish);

        if(mList==null){
            mList=new ArrayList<orderbean>();
            mOrderbean = new orderbean();
            mOrderbean.setContent("底盘维修");
            mOrderbean.setOrderstatues("完成订单");
            mOrderbean.setPlace("大庸桥街道办事处");
            mOrderbean.setShopname("大庸桥维修中心");
            mList.add(mOrderbean);
        }
        mListView.setAdapter(new OrderFinishAdapter(getActivity(),mList));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

}

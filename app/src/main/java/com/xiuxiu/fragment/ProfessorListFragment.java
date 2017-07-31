package com.xiuxiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.R;
import com.xiuxiu.activity.ProfessorDtailActivity;
import com.xiuxiu.adapter.professorAdapter;
import com.xiuxiu.entity.professorBean;

import java.util.ArrayList;
import java.util.List;


public class ProfessorListFragment extends Fragment {
    private View currentView;
    private ListView mListView;
    private List<professorBean> professorBeanList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_professor_list, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        if(professorBeanList==null) {
            professorBeanList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                professorBeanList.add(new professorBean("小情人","3年"));

            }
            mListView = (ListView) currentView.findViewById(R.id.lv_professor);
            mListView.setAdapter(new professorAdapter(getActivity(), professorBeanList));
            mListView.setOnItemClickListener(new ItemClickEvent());
        }
        return currentView;
    }
    private final class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        //这里需要注意的是第三个参数arg2，这是代表单击第几个选项
        public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                long arg3) {
            //通过单击事件，获得单击选项的内容
            //  String text = listView.getItemAtPosition(arg2);
            Intent intent = new Intent();
            intent.setClass(getActivity(),ProfessorDtailActivity.class);
            startActivity(intent);

        }
    }
}



package com.xiuxiu.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.R;
import com.hyphenate.chat.EMClient;
import com.xiuxiu.activity.LoginActivity;




public class MineSetFragment extends Fragment implements View.OnClickListener {
private TableRow bt_logout;

    private View currentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_mine_set, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        if(bt_logout==null){
        bt_logout= (TableRow) currentView.findViewById(R.id.mineSet_logout);
        bt_logout.setOnClickListener(this);


        }


        return currentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.mineSet_logout:
                SharedPreferences preference=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preference.edit();
//                editor.remove("Stag");
//                editor.remove("ID");
//                editor.remove("WORD");
                EMClient.getInstance().logout(true);
                editor.clear();

                editor.commit();
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;



        }
    }
}

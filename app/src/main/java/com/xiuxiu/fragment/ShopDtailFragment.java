package com.xiuxiu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.R;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;


public class ShopDtailFragment extends Fragment {
   private View currentView;
    LinearLayout bt_chat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_shop_dtail, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        bt_chat= (LinearLayout) currentView.findViewById(R.id.bt_chatwithshop);
        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //new出EaseChatFragment或其子类的实例
                EaseChatFragment chatFragment = new EaseChatFragment();
                //传入参数
                Bundle args = new Bundle();
//                args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                args.putString(EaseConstant.EXTRA_USER_ID, "qqq");
                chatFragment.setArguments(args);
                getFragmentManager().beginTransaction().add(R.id.detail_shop, chatFragment).commit();

            }
        });

        return currentView;
    }

}

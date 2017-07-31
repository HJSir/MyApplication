package com.xiuxiu.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

public class ChatActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


                EaseChatFragment chatFragment = new EaseChatFragment();

        Intent intent=getIntent();
                Bundle args = new Bundle();
//                args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                args.putString(EaseConstant.EXTRA_USER_ID,intent.getStringExtra(EaseConstant.EXTRA_USER_ID));
                chatFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_chat, chatFragment).commit();



    }
}

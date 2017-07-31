package com.xiuxiu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import com.R;


import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.xiuxiu.fragment.ProfessorListFragment;

public class AskProfessorActivity extends FragmentActivity {

    private ProfessorListFragment mProfessorListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_professor);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mProfessorListFragment==null)
        mProfessorListFragment = new ProfessorListFragment();
        transaction.replace(R.id.activity_ask_professor, mProfessorListFragment);
        transaction.commit();

//        Intent intent= new Intent(this, ChatActivity.class);
//        startActivity(intent);
//        finish();
    }
}

package com.xiuxiu.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.R;
import com.xiuxiu.fragment.MineSetFragment;

public class MineSetActivity extends FragmentActivity {
private MineSetFragment minsetFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_set);
        setDefalutMineSetFragment();
    }

    private void setDefalutMineSetFragment() {
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        minsetFragment =new MineSetFragment();
        transaction.replace(R.id.activity_mine_set,minsetFragment);
        transaction.commit();
    }
}

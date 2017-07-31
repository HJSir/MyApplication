package com.xiuxiu.activity;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.R;
import com.xiuxiu.fragment.ShopDtailFragment;

public class ShopDtailActivity extends FragmentActivity {
private ShopDtailFragment shopdetaifragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_dtail);
        init();
    }

    private void init() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        shopdetaifragment= new ShopDtailFragment();
        transaction.replace(R.id.activity_shop_dtail,shopdetaifragment);
        transaction.commit();
    }
}

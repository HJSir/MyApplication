package com.xiuxiu.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.MainActivity;
//import com.example.jian.myapplication.R;
import com.R;
import com.xiuxiu.fragment.HomeMateDetailFragment;
import com.xiuxiu.fragment.MateTipFragment;

import android.os.Handler;
import java.util.logging.LogRecord;

public class MateActivity extends FragmentActivity implements HomeMateDetailFragment.FChooseDetailClicklistener, MateTipFragment.MateTip {
    private HomeMateDetailFragment mDetailFragment;
    private MateTipFragment matetip;
    private Handler handler;
    private Runnable progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate);


        //步骤一：添加一个FragmentTransaction的实例
                        FragmentManager fragmentManager =getSupportFragmentManager();
                         FragmentTransaction transaction = fragmentManager.beginTransaction();

                         //步骤二：用add()方法加上Fragment的对象rightFragment
                        mDetailFragment = new HomeMateDetailFragment();
                         transaction.replace(R.id.id_detail, mDetailFragment);

                         //步骤三：调用commit()方法使得FragmentTransaction实例的改变生效
                         transaction.commit();

    }

//@Override
//public void onDestroy(){
//    handler.removeCallbacks(progress);
//}

    @Override
    public void onChooseDetail() {
        Toast toast= Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT);
        //显示toast信息
        toast.show();super.onPause();
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        matetip = new MateTipFragment();
        transaction.replace(R.id.id_detail, matetip);
        transaction.commit();

    }

    @Override
    public void tip() {

        handler=new Handler();

        progress=new Runnable() {
            @Override
            public void run() {
                finish();
                handler.removeCallbacks(progress);
            }
        };
        handler.postDelayed(progress, 3000);

    }

}

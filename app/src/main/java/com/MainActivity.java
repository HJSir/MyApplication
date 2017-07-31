package com;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import com.example.jian.myapplication.R;
import com.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.xiuxiu.activity.AskProfessorActivity;
import com.xiuxiu.activity.ChatActivity;
import com.xiuxiu.activity.LoginActivity;
import com.xiuxiu.activity.MateActivity;
import com.xiuxiu.activity.ProfessorDtailActivity;
import com.xiuxiu.activity.QueryActivity;

import com.xiuxiu.fragment.HomeFragment;
import com.xiuxiu.fragment.LoginFragment;
import com.xiuxiu.fragment.LoginTipFragment;
import com.xiuxiu.fragment.MineFragment;
import com.xiuxiu.fragment.NearFragment;
import com.xiuxiu.util.PercentLinearLayout;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.bmob.v3.Bmob;


public class MainActivity extends FragmentActivity implements View.OnClickListener,HomeFragment.setHomeClick {
  private ImageView Inear;
    private ImageView Ihome;
    private ImageView Imine;
    private ImageView Ichat;
    private HomeFragment fhome;
    private NearFragment fnear;

    private MineFragment fmine;
    private  PercentLinearLayout mTabnear;
    private PercentLinearLayout mTabcontent;
    private PercentLinearLayout mTabmine;
    private PercentLinearLayout mTabchat;
    private String[] Permissions={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    ViewPager mViewPager;
    List<Fragment> fragmentList;
    private EaseConversationListFragment conversationListFragment;
    private static final int REQUECT_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(this);
        Bmob.initialize(this, "79478323569af01e52ff2efbea00be68");

                MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE, Permissions);


        setContentView(R.layout.activity_main);

//        初始化控件、声明事件
        if(mTabmine==null) {
            init();
            state();
        }

    }

/////////////////////////////////////////////////////////////////////////////////////////
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
{

    MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
   Log.i("tag","grantResults:"+grantResults + "requestCod:"+requestCode);
}


    @PermissionGrant(REQUECT_CODE)
    public void requestSdcardSuccess()
    {
        Toast.makeText(this, "成功授权！", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE)
    public void requestSdcardFailed()
    {
        Toast.makeText(this, "授权失败！部分功能将无法使用！", Toast.LENGTH_SHORT).show();
    }
    /////////////////////////
    //初始化
public void init() {

    mTabcontent = (PercentLinearLayout)findViewById(R.id.button_1);
    mTabnear = (PercentLinearLayout) findViewById(R.id.button_2);
    mTabchat= (PercentLinearLayout) findViewById(R.id.button_3);
    mTabmine= (PercentLinearLayout) findViewById(R.id.button_4);
    Ichat= (ImageView) findViewById(R.id.image_chat);
    Ihome= (ImageView) findViewById(R.id.image_home);
    Imine= (ImageView) findViewById(R.id.image_mine);
    Inear= (ImageView) findViewById(R.id.image_near);
//    mViewPager=(ViewPager) findViewById(R.id.id_content);
//    fragmentList=new ArrayList<Fragment>();

    fhome = new HomeFragment();
    fnear = new NearFragment();

    fmine = new MineFragment();
//    fragmentList.add(fhome);
//    fragmentList.add(fnear);
//    fragmentList.add(fchat);
//    fragmentList.add(fmine);


}
    private void setSelectedMenu(ImageView selected){
        if(selected == Ichat)
            Ichat.setImageResource(R.mipmap.chat_1);
        else
            Ichat.setImageResource(R.mipmap.chat_2);

        if(selected == Ihome)
            Ihome.setImageResource(R.mipmap.home_1);
        else
            Ihome.setImageResource(R.mipmap.home_2);
        if(selected == Imine)
            Imine.setImageResource(R.mipmap.mine_1);
        else
            Imine.setImageResource(R.mipmap.mine_2);
        if(selected == Inear)
            Inear.setImageResource(R.mipmap.near_1);
        else
            Inear.setImageResource(R.mipmap.near_2);
    }


//    声明
    public void state(){
        mTabnear.setOnClickListener(this);
        mTabcontent.setOnClickListener(this);
        mTabchat.setOnClickListener(this);
        mTabmine.setOnClickListener(this);
//        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager(),fragmentList));
        getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fhome).commit();

    }


    @Override
    public void onClick(View v) {



        switch (v.getId())
        {
            case R.id.button_1:
                setSelectedMenu(Ihome);
                getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fhome).commit();
                break;
            case R.id.button_2:
                setSelectedMenu(Inear);
                getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fnear).commit();
                //new出EaseChatFragment或其子类的实例
//                EaseChatFragment chatFragment = new EaseChatFragment();
//                //传入参数
//                Bundle args = new Bundle();
//                //args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
//                args.putString(EaseConstant.EXTRA_USER_ID, "t2");
//                chatFragment.setArguments(args);
//                getSupportFragmentManager().beginTransaction().add(R.id.id_content, chatFragment).commit();
                break;
            case R.id.button_3:
                setSelectedMenu(Ichat);
                SharedPreferences preferences= getSharedPreferences("user",Context.MODE_PRIVATE);
                if(preferences.getBoolean("Stag",false)==false){ //聊天若是没有登录 则先提示登录
                    LoginTipFragment tipFragment=new LoginTipFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.id_content,tipFragment ).commit();
                }else {

                    conversationListFragment = new EaseConversationListFragment();
                    conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

                        @Override
                        public void onListItemClicked(EMConversation conversation) {
                            startActivity(new Intent(MainActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));

                        }


                    });

                    getSupportFragmentManager().beginTransaction().replace(R.id.id_content, conversationListFragment).commit();
                }
                break;
            case R.id.button_4:
                setSelectedMenu(Imine);
                getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fmine).commit();
                break;
        }

    }

    @Override
    public void setHomeOnClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
//            匹配
            case R.id.button_pp:
                intent.setClass(this, MateActivity.class);
                startActivity(intent);
                break;
//            配件查询
            case R.id.button_pj:
                intent.setClass(this, QueryActivity.class);
                startActivity(intent);
                break;
            //自选
            case R.id.button_zx:
                setSelectedMenu(Inear);
                getSupportFragmentManager().beginTransaction().replace(R.id.id_content, fnear).commit();
                break;
            //chat 聊天
            case R.id.button_ct:
                intent.setClass(this, AskProfessorActivity.class);
                startActivity(intent);
                break;
        }
    }
}


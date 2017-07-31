package com.xiuxiu.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.R;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.xiuxiu.entity.userBean;
import com.xiuxiu.fragment.EditUserInfoFragment;
import com.xiuxiu.fragment.LoginFragment;

import com.xiuxiu.fragment.RegistDtailFragment;
import com.xiuxiu.fragment.UserDtailFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static com.xiuxiu.fragment.MineFragment.IsChange;

public class LoginActivity extends FragmentActivity implements LoginFragment.LoginClickListener ,LoginFragment.RegistClickListener,UserDtailFragment.setMineEditClick,RegistDtailFragment.RegistJump{
    String Sname,Sword;


    private ProgressDialog mDialog;
    private UserDtailFragment userdetail;
    private LoginFragment mLoginFragment;
    private RegistDtailFragment RegistFragment;
    private EditUserInfoFragment mEditUserInfoFragment;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /// 因为handler中有耗时任务 使用严苛模式 而我又没开一个线程
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        //
        InitLoginFragment();

//       Bundle bundle = this.getIntent().getExtras();
//        if (bundle != null) {
//            String str = bundle.getString("tag");
//            if (str == "1") {
//                Toast.makeText(this, "地址：" + bundle.getString("tag"), Toast.LENGTH_SHORT).show();
//            }
//        }
    }
    public void InitLoginFragment(){
        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(preferences.getBoolean("Stag",false)) {

            userdetail = new UserDtailFragment();
            transaction.replace(R.id.id_Login, userdetail);
            transaction.commit();
        }else{
            mLoginFragment = new LoginFragment();
            transaction.replace(R.id.id_Login, mLoginFragment);
            transaction.commit();
        }
     }
    public void LoginEASE(String ID,String pw){


//        Toast.makeText(LoginActivity.this, "ID"+preferences.getString("Sname", null)+"pw"+preferences.getString("Eword", null), Toast.LENGTH_SHORT).show();
        EMClient.getInstance().login(ID,pw,new EMCallBack() {//回调
            @Override
            public void onSuccess() {

                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }
            @Override
            public void onError(final int i, final String s) {
                Log.d("main", "登录失败 Error code:" + i + ", message:" + s);
            }
        });


    }

    public void checkUser(){


        SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
        String username =preferences.getString("Sname", null);
        BmobQuery<userBean> query = new BmobQuery<userBean>();

        query.addWhereEqualTo("user_id", username);

//执行查询方法

        query.findObjects(new FindListener<userBean>() {
            @Override
            public void done(List<userBean> object, BmobException e) {
                if(e==null){
                    SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
                     String username =preferences.getString("ID", null);
                     String pw=preferences.getString("WORD", null);
                    int tag=0;
                    for (userBean userbean : object) {
                      if((userbean.getUser_id().equals(username))&&(userbean.getUser_password().equals(pw))){
                          SharedPreferences.Editor editor=preferences.edit();
                          editor.putString("image",userbean.getUser_image());
                          editor.putString("age",userbean.getUser_age());
                          editor.putString("phoneNumber",userbean.getUser_phonenumber());
                          editor.putString("sex",userbean.getUser_sex());
                          editor.putString("name",userbean.getUser_name());
                          editor.putBoolean("Stag",true);
                          editor.commit();
                          LoginEASE(userbean.getUser_id(),userbean.getUser_chatPW());
                          tag=1;
                      }
                    }
                    if(tag==0){
                        Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }

        });

    }
    @Override
    public void onLogin() {
        checkUser();

    }

    @Override
    public void onRegist() {
        SMSSDK.initSDK(this, "1a02cfb2afca0", "64677de12ae65903516875a5e8a7c506");
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
// 解析注册结果

                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                     phone = (String) phoneMap.get("phone");

                    FragmentManager fragmentManager =getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    RegistFragment = new RegistDtailFragment();
                    Bundle args = new Bundle();
                    args.putString("phone", phone);
                    RegistFragment.setArguments(args);
                    transaction.replace(R.id.id_Login, RegistFragment);
                    transaction. commitAllowingStateLoss();
// 提交用户信息（此方法可以不调用）
//                     registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }

    @Override
    public void setMineEditOnClick(View v) {
        mEditUserInfoFragment = new EditUserInfoFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.id_Login, mEditUserInfoFragment);
        FragmentTransaction ft2 =  getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.id_Login, mEditUserInfoFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();
    }


    @Override
    public void onJump() {
        registEASE();
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mLoginFragment = new LoginFragment();
        transaction.replace(R.id.id_Login, mLoginFragment);
        transaction. commitAllowingStateLoss();
    }
    public void registEASE(){
//                new Thread(new Runnable() {
//           @Override
//           public void run() {


    Log.d("main", "注册环信成功！");
               String id =null;
               SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
               String username =preferences.getString("Sname", id);
    ///****
    BmobQuery<userBean> query = new BmobQuery<userBean>();
//查询数据  获取该数据的objectid
    query.addWhereEqualTo("user_id", username);


//执行查询方法
    query.findObjects(new FindListener<userBean>() {
        @Override
        public void done(List<userBean> object, BmobException e) {
            if(e==null){
                //随机一个密码
                int max=20000;
                int min=1;
                Random random = new Random();
                final String PW = String.valueOf(random.nextInt(max)%(max-min+1) + min);
                String id =null;
                final SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
                String username =preferences.getString("Sname", id);
                try {
                    EMClient.getInstance().createAccount(username, PW);
                } catch (HyphenateException e1) {
                    Log.i("tag","注册失败");
                    e1.printStackTrace();
                }
                for (userBean gameScore : object) {


                    userBean userbean = new userBean();
                    userbean.setUser_chatPW(PW);
                    userbean.update( gameScore.getObjectId(), new UpdateListener() {

                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.i("tag","保存成功 密码是："+PW);
                                SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("Eword",PW);

                            }else{

                            }
                        }
                    });



                }
            }else{
                Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
            }
        }
    });
    //***



//            }
//        }).start();

    }
}

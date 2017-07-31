package com.xiuxiu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.MainActivity;
import com.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiuxiu.activity.LoginActivity;
import com.xiuxiu.activity.MateActivity;
import com.xiuxiu.activity.MineSetActivity;
import com.xiuxiu.activity.OrderActivity;
import com.xiuxiu.util.PercentLinearLayout;


public class MineFragment extends Fragment implements View.OnClickListener{
    public static int IsChange=0;
private TextView username;
private SimpleDraweeView user_image;
private  View currentView;
    private PercentLinearLayout bt_wait;
    private PercentLinearLayout bt_do;
    private PercentLinearLayout bt_finish;
    private PercentLinearLayout bt_after;
    private PercentLinearLayout bt_setting;

    public void onResume() {
        super.onResume();
        Login();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_mine, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        //设置头像
        if(bt_setting==null) {
            bt_setting = (PercentLinearLayout) currentView.findViewById(R.id.mine_setting);
            bt_wait = (PercentLinearLayout) currentView.findViewById(R.id.mine_wait);
            bt_do = (PercentLinearLayout) currentView.findViewById(R.id.mine_do);
            bt_after = (PercentLinearLayout) currentView.findViewById(R.id.mine_after);
            bt_finish = (PercentLinearLayout) currentView.findViewById(R.id.mine_finish);
            user_image = (SimpleDraweeView) currentView.findViewById(R.id.user_image);
            bt_setting.setOnClickListener(this);
            bt_wait.setOnClickListener(this);
            bt_do.setOnClickListener(this);
            bt_after.setOnClickListener(this);
            bt_finish.setOnClickListener(this);
            user_image.setOnClickListener(this);
        }
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(preferences.getString("image",null)==null){
            user_image.setImageURI("http://img1.imgtn.bdimg.com/it/u=2496618007,3190450108&fm=26&gp=0.jpg");
        }else{
            Log.i("Tag","url:"+preferences.getString("image",null));
        user_image.setImageURI(preferences.getString("image",null));
        }


        return currentView;
    }
    //保存登录后的文件 ****暂时每次都做一次保存 以后再进行改进

    public void Login(){
        username= (TextView) currentView.findViewById(R.id.user_name);
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String name=preferences.getString("name","点击登录");
//        String ID=preferences.getString("ID", "点击登录");
        String Sword=preferences.getString("WORD", "0");
        username.setText(name);
        System.out.println("name"+name+"Sword"+Sword+"ISLogin"+preferences.getBoolean("Stag",false));
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.user_image:
                intent.setClass(getActivity(), LoginActivity.class);
                SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

            startActivity(intent);
                break;
            case R.id.mine_setting:
                intent.setClass(getActivity(), MineSetActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_after:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",4);
                startActivity(intent);
                break;
            case R.id.mine_do:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",2);
                startActivity(intent);
                break;
            case R.id.mine_finish:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",3);
                startActivity(intent);
                break;
            case R.id.mine_wait:
                intent.setClass(getActivity(), OrderActivity.class);
                intent.putExtra("num",1);
                startActivity(intent);
                break;
        }
    }
}

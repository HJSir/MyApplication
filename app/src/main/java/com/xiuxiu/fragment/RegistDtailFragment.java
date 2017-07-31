package com.xiuxiu.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MainActivity;
import com.R;
import com.xiuxiu.activity.LoginActivity;
import com.xiuxiu.activity.PhotoChooseActivity;
import com.xiuxiu.entity.shopBean;
import com.xiuxiu.entity.userBean;

import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class RegistDtailFragment extends Fragment implements View.OnClickListener{
    private Button zc;
    private EditText regID;
    private EditText regPASSWORD;
  private EditText regSEX;
    private EditText regNAME;
    private View currentView;
public interface RegistJump{
    void onJump();
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_regist_dtail, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        if(zc==null){
            regID = (EditText) currentView.findViewById(R.id.reg_id);
            regPASSWORD= (EditText) currentView.findViewById(R.id.reg_password);
            regNAME= (EditText) currentView.findViewById(R.id.reg_name);
            regSEX= (EditText) currentView.findViewById(R.id.reg_sex);
            regSEX.setOnClickListener(this);
        zc= (Button) currentView.findViewById(R.id.login_detail_zc);
        zc.setOnClickListener(this);}
//        regPASSWORD.setOnClickListener(this);
//        regID.setOnClickListener(this);
        return currentView;
    }
    public int Alertdialog(String title, final String[] type)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle(title);
//        //    指定下拉列表的显示数据
//        final String[] cities = {"广州", "上海", "北京", "香港", "澳门"};
        //    设置一个下拉的列表选择项
        builder.setItems(type, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                    //男
                    if(which==0)
                        regSEX.setText("男");
                    else
                        regSEX.setText("女");


                // Toast.makeText(getActivity(), "选择的为：" +tag[0]+ type[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
        return 0;
    }
public void checkIsRegist(){
    BmobQuery<userBean> query = new BmobQuery<userBean>();
//     String TemID= String.valueOf(regID.getText());
//    final int[] isRegist = new int[1];
//    isRegist[0]=5;
    query.addWhereEqualTo("user_id", String.valueOf(regID.getText()));

    query.findObjects(new FindListener<userBean>() {
        @Override
        public void done(List<userBean> object, BmobException e) {
            if(e==null){
                Log.i("bmob","查询成功"+object.size());
                 if (object.size()==0)
                 {
                     saveinfo(String.valueOf(regID.getText()),String.valueOf(regPASSWORD.getText()),getArguments().getString("phone"),String.valueOf(regNAME.getText()),String.valueOf(regSEX.getText()));
                     //检测无登录记录可进行注册
//                     isRegist[0] =1;
                 }else{
                     //有人已注册此ID 提示重新输入ID
//                     isRegist[0] =0;
                     Toast.makeText(getActivity(), "此账号已有人注册，请重新输入", Toast.LENGTH_SHORT).show();
                 }
            }else{
                Log.i("bmob","失败："+e.toString());
            }
        }
    });

}
public void saveinfo(String id,String pw,String phnum,String name,String sex){
    userBean user = new userBean();
//注意：不能调用gameScore.setObjectId("")方法
  user.setUser_id(id);
    user.setUser_password(pw);
    user.setUser_phonenumber(phnum);
    user.setUser_sex(sex);
    user.setUser_name(name);

    user.save(new SaveListener() {

        @Override
        public void done(Object o, BmobException e) {
            if(e==null){
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
//                editor.putBoolean("Stag",Stag);
                editor.putString("Sname", String.valueOf(regID.getText()));
                editor.putString("Sword", String.valueOf(regPASSWORD.getText()));
                editor.commit();
                if (getActivity() instanceof RegistDtailFragment.RegistJump)
                {

                    ((RegistDtailFragment.RegistJump) getActivity()).onJump();
                }
            }else{
                Log.i("log","注册失败");
                Toast.makeText(getActivity(), "注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }




    });

}

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login_detail_zc){
            //判断不为空
            if(regID.getText()!=null&&regPASSWORD.getText()!=null)
               checkIsRegist();
        }
        if(v.getId()==R.id.reg_sex){
            final String[] type2 = {"男", "女"};
            Alertdialog("请选择性别",type2);
        }
    }
}

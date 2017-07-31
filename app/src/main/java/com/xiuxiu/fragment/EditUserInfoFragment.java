package com.xiuxiu.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiuxiu.activity.LoginActivity;
import com.xiuxiu.activity.PhotoChooseActivity;

import org.w3c.dom.Text;


public class EditUserInfoFragment extends Fragment implements View.OnClickListener {
    private View currentView;
    private SimpleDraweeView user_image_edit;
    private Button bt_save;
    private EditText ed_sex;
    private EditText ed_name;
    public interface setEditUserInfoFragmentClick{
        void setEditUserInfoFragmentOnClick(View v);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_edit_user_info, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        bt_save= (Button) currentView.findViewById(R.id.mine_userinfo_save);
        ed_name= (EditText) currentView.findViewById(R.id.mine_userimfo_editname);
        ed_sex= (EditText) currentView.findViewById(R.id.mine_userimfo_editsex);
        user_image_edit= (SimpleDraweeView) currentView.findViewById(R.id.user_image_edit);
        // 光标设置右边 优化用户体验

//        ed_name.setSelection(String.valueOf(ed_name.getText()).length());

        bt_save.setOnClickListener(this);
//        tx_name.setOnClickListener(this);
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        ed_name.setText(preferences.getString("name",null));
        ed_sex.setText(preferences.getString("sex",null));
        user_image_edit.setImageURI(preferences.getString("image",null));
        user_image_edit.setOnClickListener(this);
        ed_sex.setOnClickListener(this);
        return currentView;
    }


    @Override
    public void onClick(View v) {
//        if (getActivity() instanceof EditUserInfoFragment.setEditUserInfoFragmentClick)
//        {
//
//            (( EditUserInfoFragment.setEditUserInfoFragmentClick) getActivity()).setEditUserInfoFragmentOnClick(v);
//        }
        switch (v.getId()){
            case R.id.mine_userinfo_save:
                //得到数据存储后台
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                //调用回退栈 回退上一个fragment 通过sharepreference传值
                // 暂时未做处理
                getFragmentManager().popBackStack();
                break;
//               case R.id.mine_userimfo_editname:
//
//                   break;

            case R.id.mine_userimfo_editsex:
                final String[] type2 = {"男", "女"};
                Alertdialog("请选择性别",type2,0);


                break;
            case R.id.user_image_edit:
                final String[] type1 = {"图库", "拍照"};
                Alertdialog("请选择图片",type1,1);
                break;


        }

    }


    public int Alertdialog(String title, final String[] type, final int tag)
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
                if(tag==0){
                    //男
                    if(which==0)
                        ed_sex.setText("男");
                    else
                        ed_sex.setText("女");
                }
                if(tag==1){
                    Intent intent = new Intent(getActivity(),PhotoChooseActivity.class);
                    intent.putExtra("mEmail", which);
                    startActivity(intent);

                }
                // Toast.makeText(getActivity(), "选择的为：" +tag[0]+ type[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
        return 0;
    }

    @Override
    public void onResume() {
        super.onResume();
        //从Share中取出地址并显示。
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String url=preferences.getString("Surl",null);
        if (url!=null){
            Toast.makeText(getActivity(), "图片地址："+preferences.getString("Surl",null), Toast.LENGTH_SHORT).show();
            user_image_edit.setImageURI(url);
            Toast.makeText(getActivity(),"目前使用在线数据库无法存储图片，所以无法修改头像！",Toast.LENGTH_SHORT).show();
        }

    }
}

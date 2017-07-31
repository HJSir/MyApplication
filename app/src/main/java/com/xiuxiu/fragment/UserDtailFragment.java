package com.xiuxiu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiuxiu.entity.userBean;

import org.w3c.dom.Text;


public class UserDtailFragment extends Fragment implements View.OnClickListener {
View currentView;
    private Button bt_edit;
    private TextView tv_age; //暂时未作处理
    private TextView tv_sex;
    private TextView tv_num;
    private TextView tv_name;
    private TextView tv_id;
    private SimpleDraweeView user_image;
//    private userBean user=new userBean();
    public interface setMineEditClick{
        void setMineEditOnClick(View v);

    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_user_dtail, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }

        if(bt_edit==null){
            bt_edit= (Button) currentView.findViewById(R.id.mine_userinfo_edit);
            tv_sex= (TextView) currentView.findViewById(R.id.mine_userimfo_readsex);
            tv_name= (TextView) currentView.findViewById(R.id.mine_userimfo_readname);
            tv_num= (TextView) currentView.findViewById(R.id.mine_userimfo_readtel);
            tv_id= (TextView) currentView.findViewById(R.id.mine_userimfo_readid);
            user_image = (SimpleDraweeView) currentView.findViewById(R.id.user_image);
            bt_edit.setOnClickListener(this);
        }
        SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        user.setUser_name(preferences.getString("name",null));
//        user.setUser_phonenumber(preferences.getString("phoneNumber",null));
//        user.setUser_image(preferences.getString("image",null));
//        user.setUser_age(preferences.getString("age",null)); //暂时未作处理
//        user.setUser_id(preferences.getString("ID",null));
//        user.setUser_sex(preferences.getString("sex",null));

       tv_name.setText(preferences.getString("name",null));
        tv_num.setText(preferences.getString("phoneNumber",null));
        tv_id.setText(preferences.getString("ID",null));
        tv_sex.setText(preferences.getString("sex",null));
        user_image.setImageURI(preferences.getString("image",null));
        return currentView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.mine_userinfo_edit){
            if (getActivity() instanceof UserDtailFragment.setMineEditClick)
            {

                (( UserDtailFragment.setMineEditClick) getActivity()).setMineEditOnClick(v);
            }

        }
    }
}

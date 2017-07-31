package com.xiuxiu.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.R;
public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button login_bt;
    private Button login_zc;
    private EditText password;
    private EditText username;
    private RegistDtailFragment RGfragment;
    String Sname,Sword;
private View currentView;
    public interface LoginClickListener{
        void onLogin();
    }
    public interface RegistClickListener{

        void onRegist();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_login, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        if (username==null){
        username = (EditText) currentView.findViewById(R.id.username);
        password = (EditText) currentView.findViewById(R.id.password);
        login_bt = (Button) currentView.findViewById(R.id.login_bt);
        login_zc = (Button)currentView.findViewById(R.id.login_zc);
        login_bt.setOnClickListener(this);
        login_zc.setOnClickListener(this);}
        return currentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_bt:
            //此处不应该直接存入sharepreference
                boolean Stag = false; //标记登录状态
                Sname=username.getText().toString();
                Sword=password.getText().toString();
                SharedPreferences preferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("Stag",Stag);
                editor.putString("ID", Sname);
                editor.putString("WORD", Sword);
                editor.commit();
                if (getActivity() instanceof LoginClickListener )
                {
                    ((LoginClickListener) getActivity()).onLogin();
                }
                break;
            case R.id.login_zc:
                if(getActivity() instanceof RegistClickListener )
                {
                    ((RegistClickListener) getActivity()).onRegist();
                }
                break;
        }
    }
}

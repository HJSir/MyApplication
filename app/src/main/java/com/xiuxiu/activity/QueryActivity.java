package com.xiuxiu.activity;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.R;

public class QueryActivity extends FragmentActivity implements View.OnClickListener{
private Button btnResult;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qurey);
        btnResult= (Button) findViewById(R.id.btn_queryResult);
        tvResult= (TextView) findViewById(R.id.tv_queryresult);
        btnResult.setOnClickListener(this);
        tvResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_queryResult:
                tvResult.setText("抱歉，您搜索的配件暂时没有相关信息哦~");
                break;


        }
    }
}

package com.xiuxiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.R;
import com.xiuxiu.fragment.OrderAfterFragment;
import com.xiuxiu.fragment.OrderDoFragment;
import com.xiuxiu.fragment.OrderFinishFragment;
import com.xiuxiu.fragment.OrderWaitFragment;


public class OrderActivity extends Activity implements View.OnClickListener {
Button bt_wait;
    Button bt_finish;
    Button bt_after;
    Button bt_do;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        bt_wait= (Button) findViewById(R.id.order_wait);
        bt_after= (Button) findViewById(R.id.order_after);
        bt_finish= (Button) findViewById(R.id.order_finish);
        bt_do= (Button) findViewById(R.id.order_do);
        bt_wait.setOnClickListener(this);
        bt_after.setOnClickListener(this);
        bt_finish.setOnClickListener(this);
        bt_do.setOnClickListener(this);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num",1);
        switch (num){
            case 1:
                changecolor(1);

                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderWaitFragment()).commit();
                break;
            case 2:
                changecolor(2);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderDoFragment()).commit();
                break;
            case 3:
                changecolor(3);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderFinishFragment()).commit();
                break;
            case 4:
                changecolor(4);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderAfterFragment()).commit();
                break;

        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            // 后期优化
            case R.id.order_after:
                changecolor(4);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderAfterFragment()).commit();
                break;
            case R.id.order_do:
                changecolor(2);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderDoFragment()).commit();
                break;
            case R.id.order_finish:
                changecolor(3);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderFinishFragment()).commit();
                break;
            case R.id.order_wait:
                changecolor(1);
                getFragmentManager().beginTransaction().replace(R.id.content_order, new OrderWaitFragment()).commit();
                break;
            default:
                break;
        }

    }
    void changecolor(int num){

        if(num==1){
            bt_wait.setTextColor(getResources().getColor(R.color.orange));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==2)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.orange));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==3)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.orange));
            bt_after.setTextColor(getResources().getColor(R.color.black));
        }
        if(num==4)
        {
            bt_wait.setTextColor(getResources().getColor(R.color.black));
            bt_do.setTextColor(getResources().getColor(R.color.black));
            bt_finish.setTextColor(getResources().getColor(R.color.black));
            bt_after.setTextColor(getResources().getColor(R.color.orange));
        }


    }
}

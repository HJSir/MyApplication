package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.example.myapplication.R;
public class MainActivity extends FragmentActivity implements View.OnClickListener {
String s="sw key";
	tst  mTst = new tst();
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		new Thread(new Runnable() {
			@Override
			public void run() {
				mTst.tst("hhhh");
				mTst.Sysout();

			}
		}).start();

	}

	@Override
	public void onClick(View v) {

	}
}

package com.xiuxiu.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.R;

import com.xiuxiu.adapter.MyFrageStatePagerAdapter;
import com.xiuxiu.fragment.ProfessorCommentFragment;
import com.xiuxiu.fragment.ProfessorIntelligentFragment;

import static android.R.attr.offset;

public class ProfessorDtailActivity extends FragmentActivity {
   private ProfessorCommentFragment mProfessorCommentFragment;
    private ProfessorIntelligentFragment mIntelligentFragment;
    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1, textView2;
    private List<Fragment> Fragmentlist;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_dtail);
        InitImageView();
        InitTextView();
        InitViewPager();
    }

    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vp_professor);
        Fragmentlist = new ArrayList<Fragment>();
       mIntelligentFragment = new ProfessorIntelligentFragment();
        mProfessorCommentFragment= new ProfessorCommentFragment();

        Fragmentlist.add(mProfessorCommentFragment);
        Fragmentlist.add(mIntelligentFragment);

        viewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager(),Fragmentlist));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     */

    private void InitTextView() {
        textView1 = (TextView) findViewById(R.id.tv_comment);
        textView2 = (TextView) findViewById(R.id.tv_intell);


        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));

    }

    /**
     * 2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     * 3
     */

    private void InitImageView() {
        imageView = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.redbar).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }


 class MyOnClickListener implements OnClickListener{
    private int index=0;
    public MyOnClickListener(int i){
        index=i;
    }
    public void onClick(View v) {
        viewPager.setCurrentItem(index);
    }

}


public class MyOnPageChangeListener implements OnPageChangeListener{

    int one = offset*2+ bmpW;// 页卡1 -> 页卡2 偏移量

    public void onPageScrollStateChanged(int arg0) {


    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {


    }

    public void onPageSelected(int arg0) {

        Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
        currIndex = arg0;
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        imageView.startAnimation(animation);

    }

}


}

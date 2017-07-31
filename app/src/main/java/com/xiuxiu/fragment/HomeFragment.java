package com.xiuxiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiuxiu.activity.ShopDtailActivity;
import com.xiuxiu.adapter.homeImageAdapter;
import com.xiuxiu.adapter.shopAdapter;
import com.xiuxiu.entity.shopBean;
import com.xiuxiu.util.ImageHandler;
import com.xiuxiu.util.PercentLinearLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private View currentView;
    private PercentLinearLayout pp_btn;
    private PercentLinearLayout zx_btn;
    private PercentLinearLayout ct_btn;
    private PercentLinearLayout pj_btn;
    private ListView listView;
    private List<shopBean> shopBeanList;
    private static final String LOG_TAG = "HomeFragment";
    public ImageHandler handler = new ImageHandler(new WeakReference<HomeFragment>(this));
    public ViewPager viewPager;
public interface setHomeClick{
    void setHomeOnClick(View v);

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(currentView==null)
        {
        currentView = inflater.inflate(R.layout.fragment_home, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        if(shopBeanList==null) {
            shopBeanList = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//            shopBeanList.add(new shopBean(R.drawable.s, "皇家店铺", 5 * i + "", 60 * i + ""));
//
//            }
            BmobQuery<shopBean> query = new BmobQuery<shopBean>();

            query.addWhereEqualTo("hot_shop", true);
//返回50条数据，如果不加上这条语句，默认返回10条数据
//            query.setLimit(50);
//执行查询方法
            query.findObjects(new FindListener<shopBean>() {
                @Override
                public void done(List<shopBean> object, BmobException e) {
                    if(e==null){
//                        toast("查询成功：共"+object.size()+"条数据。");
                        Log.i("bmob","查询成功："+object.size()+"条数据。");
                        for (shopBean shopbean : object) {

                            shopbean.getObjectId();
                            Log.i("bmob","成功："+ shopbean.getName_shop());
                            shopBeanList.add(new shopBean(shopbean.getImage_shop(),shopbean.getDistance_shop()+"km",shopbean.getTrade_shop(),shopbean.getHot_shop(),shopbean.getId_shop(), shopbean.getName_shop()));
                        }
                    }else{
                        Log.i("bmob","失败："+e.toString());
                    }
                }
            });
            listView = (ListView) currentView.findViewById(R.id.home_listview);
            listView.setAdapter(new shopAdapter(getActivity(), shopBeanList));
            listView.setOnItemClickListener(new ItemClickEvent());
        }

        if (pp_btn==null){
          pp_btn = (PercentLinearLayout) currentView.findViewById(R.id.button_pp);
          zx_btn = (PercentLinearLayout) currentView.findViewById(R.id.button_zx);
          ct_btn = (PercentLinearLayout) currentView.findViewById(R.id.button_ct);
          pj_btn = (PercentLinearLayout) currentView.findViewById(R.id.button_pj);
          pp_btn.setOnClickListener(this);
          zx_btn.setOnClickListener(this);
          ct_btn.setOnClickListener(this);
          pj_btn.setOnClickListener(this);}
if(viewPager==null) {
    //------------------------
    //初始化iewPager的内容
    viewPager = (ViewPager) currentView.findViewById(R.id.viewpager);
    // 初始化三个图片控件
    SimpleDraweeView view1 = (SimpleDraweeView) inflater.inflate(R.layout.item, null);
    SimpleDraweeView view2 = (SimpleDraweeView) inflater.inflate(R.layout.item, null);
    SimpleDraweeView view3 = (SimpleDraweeView) inflater.inflate(R.layout.item, null);
    // 图片铺满屏幕
    view1.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
    view2.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
    view3.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
//        将图片资源放入
    view1.setImageURI("http://pic.5tu.cn/uploads/allimg/1510/172336134300.jpg");
    view2.setImageURI("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3441891011,151525812&fm=23&gp=0.jpg");
    view3.setImageURI("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=962606754,3543991090&fm=23&gp=0.jpg");

    ArrayList<SimpleDraweeView> views = new ArrayList<SimpleDraweeView>();
    views.add(view1);
    views.add(view2);
    views.add(view3);
    viewPager.setAdapter(new homeImageAdapter(views));
    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

        //配合Adapter的currentItem字段进行设置。
        @Override
        public void onPageSelected(int arg0) {
            handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        //覆写该方法实现轮播效果的暂停和恢复
        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }
        }
    });
    viewPager.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
    //开始轮播效果
    handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
}
        return currentView;
    }
    private final class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        //这里需要注意的是第三个参数arg2，这是代表单击第几个选项
        public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                long arg3) {
            //通过单击事件，获得单击选项的内容
           //  String text = listView.getItemAtPosition(arg2);
            Intent intent = new Intent();
            intent.setClass(getActivity(),ShopDtailActivity.class);
            startActivity(intent);

        }
    }
    @Override
    public void onClick(View v) {
        if (getActivity() instanceof HomeFragment.setHomeClick)
        {

            (( HomeFragment.setHomeClick) getActivity()).setHomeOnClick(v);
        }


    }
}

// -----------------------

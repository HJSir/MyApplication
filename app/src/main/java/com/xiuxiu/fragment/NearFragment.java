package com.xiuxiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.R;
import com.xiuxiu.activity.ShopDtailActivity;
import com.xiuxiu.adapter.MenuListAdapter;
import com.xiuxiu.adapter.shopAdapter;
import com.xiuxiu.entity.shopBean;
import com.xiuxiu.util.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class NearFragment extends Fragment{
    private View currentView;
    //菜单标题
    private ListView contentView;
    private String headers[] = {"城市", "类型", "排序"};
    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    List<shopBean> shopBeanList;
    private MenuListAdapter mMenuAdapter1;
    private MenuListAdapter mMenuAdapter2;
    private MenuListAdapter mMenuAdapter3;

    private DropDownMenu mDropDownMenu;

    private String citys[] = {"定位所在地"};

    private String type[] = {"不限", "维修", "保养", "装饰", "洗车打蜡"};

    private String sort[] = {"不限", "距离最近", "好评优先","销量优先"};

    private List<View> popupViews = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(currentView==null)
        {
            currentView= inflater.inflate(R.layout.fragment_near, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        initView();

        return currentView;
    }
    private final class ItemClickEvent implements AdapterView.OnItemClickListener {
        @Override
        //这里需要注意的是第三个参数arg2，这是代表单击第几个选项
        public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                long arg3) {
            //通过单击事件，获得单击选项的内容
            //  String text = contentView.getItemAtPosition(arg2);
            Intent intent = new Intent();
            intent.setClass(getActivity(),ShopDtailActivity.class);
            startActivity(intent);

        }
    }
    private void initView() {
if(mDropDownMenu==null){
        mDropDownMenu = (DropDownMenu) currentView.findViewById(R.id.dropDownMenu);


        //init menu listview

        //这里是每个下拉菜单之后的布局,目前只是简单的listview作为展示
        if (listView1 == null) {

            listView1 = new ListView(getActivity());
            listView2 = new ListView(getActivity());
            listView3 = new ListView(getActivity());


        listView1.setDividerHeight(0);
        listView2.setDividerHeight(0);
        listView3.setDividerHeight(0);

            mMenuAdapter1 = new MenuListAdapter(getActivity(), Arrays.asList(citys));
            mMenuAdapter2 = new MenuListAdapter(getActivity(), Arrays.asList(type));
            mMenuAdapter3 = new MenuListAdapter(getActivity(), Arrays.asList(sort));

        listView1.setAdapter(mMenuAdapter1);
        listView2.setAdapter(mMenuAdapter2);
        listView3.setAdapter(mMenuAdapter3);

        popupViews.add(listView1);
        popupViews.add(listView2);
        popupViews.add(listView3);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(type[position]);
                mDropDownMenu.closeMenu();
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                mDropDownMenu.setTabText(sort[position]);
                mDropDownMenu.closeMenu();
            }
        });

    }


      //这里添加 内容显示区域,可以是任何布局
//      可以是任何布局  ListView contentView = new ListView(getActivity());

    if(shopBeanList==null) {
        shopBeanList = new ArrayList<>();

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
        contentView = new ListView(getActivity());
        contentView.setAdapter(new shopAdapter(getActivity(), shopBeanList));
        contentView.setOnItemClickListener(new ItemClickEvent());
    }





        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews,contentView );}

    }


}

package com.xiuxiu.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.R;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xiuxiu.util.MyLocationListener;


public class HomeMateDetailFragment extends Fragment implements View.OnClickListener {
    private Button bt_tj;
    private TextView mlocat;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 1) {
                Log.i("tags", msg.obj + "");
                mlocat.setText(msg.obj + ""); //定位信息更新ui
            }
        }
    };

    private View currentView;
    public LocationClient mLocationClient = null;
    public MyLocationListener myListener = new MyLocationListener(handler);

    public interface FChooseDetailClicklistener {
        void onChooseDetail();
    }

    private void initLocation() { //初始化定位
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

//        int span=1000;
        option.setScanSpan(0);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (currentView == null) {
            currentView = inflater.inflate(R.layout.fragment_home__mate__detail, container, false);

        }
        ViewGroup parent = (ViewGroup) currentView.getParent();
        if (parent != null) {
            parent.removeView(currentView);
        }
        mlocat = (TextView) currentView.findViewById(R.id.tv_locat);
        bt_tj = (Button) currentView.findViewById(R.id.tj_detail);
        bt_tj.setOnClickListener(this);
        mLocationClient = new LocationClient(getContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        initLocation();
        mLocationClient.start();
        return currentView;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.tj_detail:
//                Toast toast=Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT);
////显示toast信息
//                toast.show();
//                break;
//        }
        switch (v.getId()) {
            case R.id.tj_detail:

                if (getActivity() instanceof FChooseDetailClicklistener) {

                    ((FChooseDetailClicklistener) getActivity()).onChooseDetail();
                }
                break;
        }

    }
}

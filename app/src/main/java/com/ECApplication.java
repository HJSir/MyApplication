package com;

import android.app.Application;
        import android.app.ActivityManager;
        import android.app.Application;
        import android.content.Context;
        import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;

import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.chat.EMOptions;


import java.util.Iterator;
        import java.util.List;

/**
 * Created by lz on 2016/4/16.
 * 项目的 Application类，做一些项目的初始化操作，比如sdk的初始化等
 */
public class ECApplication extends Application {

    // 上下文菜单
    private Context mContext;

    // 记录是否已经初始化
    private boolean isInit = false;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mContext = this;

        // 初始化环信SDK
        initEasemob();
    }

    /**
     *
     */

    private void initEasemob() {
        EMOptions options = new EMOptions();

        // 默认添加好友时，是不需要验证的，改成需要验证,true:自动验证,false,手动验证
        options.setAcceptInvitationAlways(true);
        //初始化
        EaseUI.getInstance().init(this, options);
    }



    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */

}

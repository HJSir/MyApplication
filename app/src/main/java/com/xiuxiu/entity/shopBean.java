package com.xiuxiu.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by jian on 2016/12/7.
 */

public class shopBean extends BmobObject {
    public String Image_shop;
    public String name_shop;
    public String id_shop;

    public shopBean(String image_shop, String distance_shop, String trade_shop, Boolean hot_shop, String id_shop, String name_shop) {
        Image_shop = image_shop;
        this.distance_shop = distance_shop;
        this.trade_shop = trade_shop;
        this.hot_shop = hot_shop;
        this.id_shop = id_shop;
        this.name_shop = name_shop;
    }

    public String getImage_shop() {
        return Image_shop;
    }

    public void setImage_shop(String image_shop) {
        Image_shop = image_shop;
    }

    public String getId_shop() {
        return id_shop;
    }

    public void setId_shop(String id_shop) {
        this.id_shop = id_shop;
    }

    public Boolean getHot_shop() {
        return hot_shop;
    }

    public void setHot_shop(Boolean hot_shop) {
        this.hot_shop = hot_shop;
    }

    public Boolean hot_shop;
//    public String credit_shop;

    public String getTrade_shop() {
        return trade_shop;
    }

    public void setTrade_shop(String trade_shop) {
        this.trade_shop = trade_shop;
    }

    public String getDistance_shop() {
        return distance_shop;
    }

    public void setDistance_shop(String distance_shop) {
        this.distance_shop = distance_shop;
    }

    public String getName_shop() {
        return name_shop;
    }

    public void setName_shop(String name_shop) {
        this.name_shop = name_shop;
    }



    public String trade_shop;
    public String distance_shop;
//int credit_shop,

}

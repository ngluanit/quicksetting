package com.example.settingapp.tiles;

import android.content.Intent;

public class ItemApp {
    String tv_itemapp;
    int img_itemapp;

    public ItemApp(String tv_itemapp, int img_itemapp) {
        this.tv_itemapp = tv_itemapp;
        this.img_itemapp = img_itemapp;
    }

    public String getTv_itemapp() {
        return tv_itemapp;
    }

    public void setTv_itemapp(String tv_itemapp) {
        this.tv_itemapp = tv_itemapp;
    }

    public int getImg_itemapp() {
        return img_itemapp;
    }

    public void setImg_itemapp(int img_itemapp) {
        this.img_itemapp = img_itemapp;
    }
}

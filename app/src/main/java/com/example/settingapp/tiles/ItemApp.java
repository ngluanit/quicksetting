package com.example.settingapp.tiles;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class ItemApp {
    String tv_itemapp;
    Drawable icon;

    public ItemApp(String tv_itemapp, Drawable icon) {
        this.tv_itemapp = tv_itemapp;
        this.icon = icon;
    }

    public String getTv_itemapp() {
        return tv_itemapp;
    }

    public void setTv_itemapp(String tv_itemapp) {
        this.tv_itemapp = tv_itemapp;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}

package com.example.blacklist;

import android.graphics.drawable.Drawable;

public class ItemBlacklist {
    String tv_app_bl;
    Drawable img_app_bl;

    public ItemBlacklist(String tv_app_bl, Drawable img_app_bl) {
        this.tv_app_bl = tv_app_bl;
        this.img_app_bl = img_app_bl;
    }

    public String getTv_app_bl() {
        return tv_app_bl;
    }

    public void setTv_app_bl(String tv_app_bl) {
        this.tv_app_bl = tv_app_bl;
    }

    public Drawable getImg_app_bl() {
        return img_app_bl;
    }

    public void setImg_app_bl(Drawable img_app_bl) {
        this.img_app_bl = img_app_bl;
    }
}

package com.example.miui_ify;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.legacy.app.FragmentPagerAdapter;

import com.example.settingapp.util.SharePref;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public static Context mContext;
    int num;
    int du;
    public static int numpager ;
    private Context context;
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new IconSettingFragment();
    }

    @Override
    public int getCount() {
        num = SharePref.getIntPref(mContext, "total_tile") / (SharePref.getIntPref(mContext, "number_column") * SharePref.getIntPref(mContext, "number_row"));
        du = SharePref.getIntPref(mContext, "total_tile") % (SharePref.getIntPref(mContext, "number_column") * SharePref.getIntPref(mContext, "number_row"));
        if (du != 0) {
            numpager = num + 1;
            return numpager;
        } else {
            numpager = num;
            return num;
        }

    }
}

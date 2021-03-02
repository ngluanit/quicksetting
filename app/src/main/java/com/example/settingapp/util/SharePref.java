package com.example.settingapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    public static void setIntPref(Context context,String key,int value){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public static int getIntPref(Context context,String key){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        return pref.getInt(key,1);
    }
    public static void setStringPref(Context context,String key,String value){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getStringPref(Context context,String key){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        return pref.getString(key,"");
    }
    public static void setBooleanPref(Context context,String key,boolean value){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public static boolean getBooleanPref(Context context,String key){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", 0);
        return pref.getBoolean(key,false);
    }
}

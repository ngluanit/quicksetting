package com.example.blacklist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miui_ify.MainActivity;
import com.example.settingapp.R;
import com.example.settingapp.extra.ExtraActivity;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class BlacklistActivity extends AppCompatActivity {

    ImageView seclect_all;

    Button btn_ok,btn_cancel;

    RecyclerView rcv_blacklist;
    BlacklistAdapter blacklistAdapter;
    ArrayList<ItemBlacklist> itemBlacklists;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blacklist);


        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white


        seclect_all = findViewById(R.id.seclect_all);
        rcv_blacklist = findViewById(R.id.rcv_blacklist);
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);

        itemBlacklists = getSystemApps();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcv_blacklist.setLayoutManager(layoutManager);

        rcv_blacklist.setAdapter(blacklistAdapter);
        blacklistAdapter.notifyDataSetChanged();
        seclect_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1(BlacklistActivity.this);
            }
        });

    }

    private void showDialog1(Activity activity) {
        final Dialog dialog = new Dialog(BlacklistActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_app_blacklist);
        final Button btn_ok = dialog.findViewById(R.id.btn_ok);
        final Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                blacklistAdapter.selecteAll();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public ArrayList<ItemBlacklist> getSystemApps() {
        PackageManager pm = getPackageManager();
        ArrayList<ItemBlacklist> itemBlacklists = new ArrayList();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p))) {
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                itemBlacklists.add(new ItemBlacklist(appName, icon));
            }

        }
        blacklistAdapter = new BlacklistAdapter(BlacklistActivity.this,itemBlacklists);
        return itemBlacklists;
    }
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return (pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}
package com.example.settingapp.layout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miui_ify.MainActivity;
import com.example.settingapp.R;
import com.example.settingapp.systemIcons.SystemIcons;
import com.example.settingapp.util.SharePref;

public class Layout_Activity extends AppCompatActivity {

    private boolean imgshow_footerShown= true;
    private boolean imgHideTextShown= true;
    private boolean imgAlarm_footerShown= true;
    private boolean imgturncropShown= true;
    private boolean img_24_clockShown= true;


    ImageView imgBack;
    SeekBar seekbar_number_rows,seekbar_number_columns,seekbar_number_small_columns,seekbar_panel_radius,seekbar_panel_padding,seekbar_tilesize;
    TextView tvnumber_row,tv_number_columns,tv_number_small_columns,tv_panel_radius,tv_panel_padding,tv_tilesize;
    ImageView imgshow_footer,imgHideText,imgAlarm_footer,imgturncrop,img_24_clock;
    RelativeLayout rlsystemicon;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white
        imgBack = findViewById(R.id.imgBack);
        seekbar_number_rows = findViewById(R.id.seekbar_number_rows);
        seekbar_number_rows.setProgress(SharePref.getIntPref(this,"number_row"));
        seekbar_number_columns = findViewById(R.id.seekbar_number_columns);
        seekbar_number_columns.setProgress(SharePref.getIntPref(this,"number_column"));
        seekbar_number_small_columns = findViewById(R.id.seekbar_number_small_columns);
        seekbar_number_small_columns.setProgress(SharePref.getIntPref(this,"number_small_column"));
        seekbar_panel_radius = findViewById(R.id.seekbar_panel_radius);
        seekbar_panel_radius.setProgress(SharePref.getIntPref(this,"panel_radius"));
        seekbar_panel_padding = findViewById(R.id.seekbar_panel_padding);
        seekbar_panel_padding.setProgress(SharePref.getIntPref(this,"panel_padding"));
        seekbar_tilesize = findViewById(R.id.seekbar_tilesize);
        seekbar_tilesize.setProgress(SharePref.getIntPref(this,"tile_size"));
        tvnumber_row = findViewById(R.id.tv_number_row);
        tvnumber_row.setText(String.valueOf(SharePref.getIntPref(this,"number_row")));
        tv_number_columns = findViewById(R.id.tv_number_columns);
        tv_number_columns.setText(String.valueOf(SharePref.getIntPref(this,"number_column")));
        tv_number_small_columns = findViewById(R.id.tv_number_small_columns);
        tv_number_small_columns.setText(String.valueOf(SharePref.getIntPref(this,"number_small_column")));
        tv_panel_radius = findViewById(R.id.tv_panel_radius);
        tv_panel_radius.setText(String.valueOf(SharePref.getIntPref(this,"panel_radius")));
        tv_panel_padding = findViewById(R.id.tv_panel_padding);
        tv_panel_padding.setText(String.valueOf(SharePref.getIntPref(this,"panel_padding")));
        tv_tilesize = findViewById(R.id.tv_tilesize);
        tv_tilesize.setText(String.valueOf(SharePref.getIntPref(this,"tile_size")));
        rlsystemicon = findViewById(R.id.rlsystemicon);
        imgshow_footer = findViewById(R.id.imgshow_footer);
        imgHideText = findViewById(R.id.imgHideText);
        imgAlarm_footer = findViewById(R.id.imgAlarm_footer);
        imgturncrop = findViewById(R.id.imgturncrop);
        img_24_clock = findViewById(R.id.img_24_clock);

        rlsystemicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Layout_Activity.this, SystemIcons.class));
                finish();
            }
        });

        imgshow_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgshow_footer != null) && (imgshow_footerShown)){
                    imgshow_footer.setImageResource(R.drawable.ic_switch_on);
                    imgshow_footerShown = false;
                }
                else {
                    if (imgshow_footer != null) imgshow_footer.setImageResource(R.drawable.ic_switch_off);
                    imgshow_footerShown = true;
                }
            }
        });

        imgHideText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgHideText != null) && (imgHideTextShown)){
                    imgHideText.setImageResource(R.drawable.ic_switch_on);
                    imgHideTextShown = false;
                }
                else {
                    if (imgHideText != null) imgHideText.setImageResource(R.drawable.ic_switch_off);
                    imgHideTextShown = true;
                }
            }
        });

        imgAlarm_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgAlarm_footer != null) && (imgAlarm_footerShown)){
                    imgAlarm_footer.setImageResource(R.drawable.ic_switch_on);
                    imgAlarm_footerShown = false;
                }
                else {
                    if (imgAlarm_footer != null) imgAlarm_footer.setImageResource(R.drawable.ic_switch_off);
                    imgAlarm_footerShown = true;
                }
            }
        });

        imgturncrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((imgturncrop != null) && (imgturncropShown)){
                    imgturncrop.setImageResource(R.drawable.ic_switch_on);
                    imgturncropShown = false;
                }
                else {
                    if (imgturncrop != null) imgturncrop.setImageResource(R.drawable.ic_switch_off);
                    imgturncropShown = true;
                }
            }
        });

        img_24_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((img_24_clock != null) && (img_24_clockShown)){
                    img_24_clock.setImageResource(R.drawable.ic_switch_on);
                    img_24_clockShown = false;
                }
                else {
                    if (img_24_clock != null) img_24_clock.setImageResource(R.drawable.ic_switch_off);
                    img_24_clockShown = true;
                }
            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onBackPressed();
            }
        });

        seekbar_number_rows.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvnumber_row.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"number_row",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekbar_number_columns.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_number_columns.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"number_column",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekbar_number_small_columns.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_number_small_columns.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"number_small_column",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "start", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
        seekbar_panel_radius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_panel_radius.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"panel_radius",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "start", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
        seekbar_panel_padding.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_panel_padding.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"panel_padding",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "start", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
        seekbar_tilesize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_tilesize.setText(""+progress);
                SharePref.setIntPref(getApplicationContext(),"tile_size",progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "start", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Layout_Activity.this, "stop", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Layout_Activity.this, MainActivity.class));
        finish();
    }
}
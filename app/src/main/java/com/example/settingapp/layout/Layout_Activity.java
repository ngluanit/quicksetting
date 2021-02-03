package com.example.settingapp.layout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.settingapp.R;

public class Layout_Activity extends AppCompatActivity {

    SeekBar seekbar_number_rows,seekbar_number_columns,seekbar_number_small_columns,seekbar_panel_radius,seekbar_panel_padding,seekbar_tilesize;
    TextView tvnumber_row,tv_number_columns,tv_number_small_columns,tv_panel_radius,tv_panel_padding,tv_tilesize;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_);
        Window window = getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(Color.parseColor("#f0f0f0"));// set status background white

        seekbar_number_rows = findViewById(R.id.seekbar_number_rows);
        seekbar_number_columns = findViewById(R.id.seekbar_number_columns);
        seekbar_number_small_columns = findViewById(R.id.seekbar_number_small_columns);
        seekbar_panel_radius = findViewById(R.id.seekbar_panel_radius);
        seekbar_panel_padding = findViewById(R.id.seekbar_panel_padding);
        seekbar_tilesize = findViewById(R.id.seekbar_tilesize);
        tvnumber_row = findViewById(R.id.tv_number_row);
        tv_number_columns = findViewById(R.id.tv_number_columns);
        tv_number_small_columns = findViewById(R.id.tv_number_small_columns);
        tv_panel_radius = findViewById(R.id.tv_panel_radius);
        tv_panel_padding = findViewById(R.id.tv_panel_padding);
        tv_tilesize = findViewById(R.id.tv_tilesize);



        seekbar_number_rows.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvnumber_row.setText(""+progress);
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
        seekbar_number_columns.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_number_columns.setText(""+progress);
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
        seekbar_number_small_columns.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_number_small_columns.setText(""+progress);
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
}
package com.example.settingapp.util;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import com.example.settingapp.R;

import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class QSIntentService extends TileService {

    private static final String SERVICE_STATUS_FLAG = "serviceStatus";
    private static final String PREFERENCES_KEY = "com.google.android_quick_settings";

    public QSIntentService() {
    }

    @Override
    public void onClick() {

        updateTile();

        boolean isCurrentlyLocked = this.isLocked();
        System.out.println("mzm1123123123");
        if (!isCurrentlyLocked) {

            Resources resources = getApplication().getResources();

            Tile tile = getQsTile();
            String tileLabel = tile.getLabel().toString();
            String tileState = (tile.getState() == Tile.STATE_ACTIVE) ?
                    resources.getString(R.string.service_active) :
                    resources.getString(R.string.service_inactive);

//            Intent intent = new Intent(getApplicationContext(),
//                    ResultActivity.class);
//
//            intent.putExtra(ResultActivity.RESULT_ACTIVITY_NAME_KEY,
//                    tileLabel);
//            intent.putExtra(ResultActivity.RESULT_ACTIVITY_INFO_KEY,
//                    tileState);
//
//            startActivityAndCollapse(intent);
        }

    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        Tile tile = getQsTile();
        System.out.println("mzm1123123123"+tile.getLabel());
        tile.updateTile();
//        tile.setIcon(Icon.createWithResource(this,
//                R.drawable.ic_title_started));
//        tile.setLabel(getString(R.string.tile_label));
//        tile.setContentDescription(
//                getString(R.string.tile_content_description);
//        tile.setState(Tile.STATE_ACTIVE);
//        tile.updateTile()
    }

    private void updateTile() {

        Tile tile = this.getQsTile();
        boolean isActive = getServiceStatus();

        Icon newIcon;
        String newLabel;
        int newState;

        if (isActive) {

            newLabel = String.format(Locale.US,
                    "%s %s",
                    getString(R.string.tile_label),
                    getString(R.string.service_active));

           // newIcon = Icon.createWithResource(getApplicationContext(), ic_);

            newState = Tile.STATE_ACTIVE;

        } else {
            newLabel = String.format(Locale.US,
                    "%s %s",
                    getString(R.string.tile_label),
                    getString(R.string.service_inactive));

            newIcon =
                    Icon.createWithResource(getApplicationContext(),
                            android.R.drawable.ic_dialog_alert);

            newState = Tile.STATE_INACTIVE;
        }

        tile.setLabel(newLabel);
        //tile.setIcon(newIcon);
        tile.setState(newState);

        tile.updateTile();
    }

    private boolean getServiceStatus() {

        SharedPreferences prefs =
                getApplicationContext()
                        .getSharedPreferences(PREFERENCES_KEY,
                                MODE_PRIVATE);

        boolean isActive = prefs.getBoolean(SERVICE_STATUS_FLAG, false);
        isActive = !isActive;

        prefs.edit().putBoolean(SERVICE_STATUS_FLAG, isActive).apply();

        return isActive;
    }
}

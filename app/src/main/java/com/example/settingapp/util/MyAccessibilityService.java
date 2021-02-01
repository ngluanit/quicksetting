package com.example.settingapp.util;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;

import java.util.List;

public class MyAccessibilityService extends AccessibilityService {


    public MyAccessibilityService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        try {
            AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
            //Inspect app elements if ready
            if (rootInActiveWindow != null) {
                //Search bar is covered with textview which need to be clicked
                List<AccessibilityNodeInfo> searchBarIdle =
                        rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/search_box_idle_text");
                if (searchBarIdle.size() > 0) {
                    AccessibilityNodeInfo searchBar = searchBarIdle.get(0);
                    searchBar.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
                //Check is search bar is visible
                List<AccessibilityNodeInfo> searchBars =
                        rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/search_box_text_input");
                if (searchBars.size() > 0) {
                    AccessibilityNodeInfo searchBar = searchBars.get(0);
                    //Check is searchbar have the required text, if not set the text
                    if (searchBar.getText() == null || !searchBar.getText().toString().equalsIgnoreCase("facebook")) {
                        Bundle args = new Bundle();
                        args.putString(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "facebook");
                        searchBar.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args);
                    } else {
                        //There is no way to press Enter to perform search, so find corresponding suggestion and click
                        List<AccessibilityNodeInfo> searchSuggestions = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/suggest_text");
                        for (AccessibilityNodeInfo suggestion : searchSuggestions) {
                            if (suggestion.getText().toString().equals("Facebook")) {
                                //We found textview, but its not clickable, so we should perform the click on the parent
                                AccessibilityNodeInfo clickableParent = suggestion.getParent();
                                clickableParent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {

    }
}

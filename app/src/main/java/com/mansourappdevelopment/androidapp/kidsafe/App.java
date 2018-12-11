package com.mansourappdevelopment.androidapp.kidsafe;

import android.graphics.drawable.Drawable;

public class App {
    private String mAppName;
    private Drawable mAppIcon;

    public App(String mAppName, Drawable mAppIcon) {
        this.mAppIcon = mAppIcon;
        this.mAppName = mAppName;
    }

    public String getmAppName() {
        return mAppName;
    }

    public Drawable getmAppIcon() {
        return mAppIcon;
    }
}

package com.example.jirehcordova.tccs_clockin;

import android.app.Application;

import com.github.orangegangsters.lollipin.lib.managers.LockManager;
/**
 * Created by oliviergoutay on 1/14/15.
 * Library and Template used by jireh cordova
 */
public class CustomApplication extends Application {

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate() {
        super.onCreate();

        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);
        lockManager.getAppLock().setLogoId(R.drawable.security_lock);
    }
}

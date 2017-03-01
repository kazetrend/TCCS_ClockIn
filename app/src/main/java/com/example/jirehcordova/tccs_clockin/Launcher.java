package com.example.jirehcordova.tccs_clockin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.PinActivity;

/**
 * Created by Jireh Cordova on 18/02/2017.
 */

public class Launcher extends PinActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        boolean firstRun = settings.getBoolean("firstRun", true);

        if (firstRun) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("firstRun", false);
            editor.commit();
            Intent i = new Intent(Launcher.this, registeractivity.class);
            startActivity(i);
        } else {
            Intent a = new Intent(Launcher.this, MainActivity.class);
            Toast.makeText(this, "inmain", Toast.LENGTH_LONG).show();
            startActivity(a);
            finish();
        }
    }
}

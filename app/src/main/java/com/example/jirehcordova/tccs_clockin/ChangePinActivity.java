package com.example.jirehcordova.tccs_clockin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;

import static android.content.ContentValues.TAG;

public class ChangePinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        Intent intent = new Intent(this, CustomPinActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            Log.d(TAG, "Pin enabled");
            Intent intent = new Intent(ChangePinActivity.this, MainActivity.class);
            startActivity(intent);
        }else{
            Log.d(TAG, "fail");
        }
    }
}

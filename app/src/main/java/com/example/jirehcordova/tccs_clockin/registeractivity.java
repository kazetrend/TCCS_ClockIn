package com.example.jirehcordova.tccs_clockin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.managers.LockManager;


import java.util.HashMap;
import java.util.Map;




public class registeractivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        final EditText username = (EditText)findViewById(R.id.usename);
        final EditText email = (EditText)findViewById(R.id.email);
        Button bregister = (Button)findViewById(R.id.submit);
        postgreshelper client = new postgreshelper();

        client.execute();
        bregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String usernamee = username.getText().toString();
                String emaile = email.getText().toString();

                Intent intent = new Intent(registeractivity.this, MainActivity.class);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("AppPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("header", usernamee);
                editor.commit();

                Map vals = new HashMap();
                vals.put("username", usernamee);
                vals.put("email", emaile);
                startActivity(intent);
            }
        });

    }

}


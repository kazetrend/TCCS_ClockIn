package com.example.jirehcordova.tccs_clockin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jirehcordova.tccs_clockin.model.User;
import com.github.orangegangsters.lollipin.lib.PinActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.jirehcordova.tccs_clockin.R.id.welcomemsg;

public class MainActivity extends PinActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_ENABLE = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
        startActivityForResult(intent, REQUEST_CODE_ENABLE);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.button_change_pin).setOnClickListener(this);
        this.findViewById(R.id.button_unlock_pin).setOnClickListener(this);
        this.findViewById(R.id.button_compat_locked).setOnClickListener(this);
        this.findViewById(R.id.button_not_locked).setOnClickListener(this);
        TextView welcome = (TextView)findViewById(welcomemsg);

        SharedPreferences prefs = getSharedPreferences("AppPref", Context.MODE_PRIVATE);
        String name = prefs.getString("header", "");

        welcome.setText("Welcome, " +name+"!");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
        switch (v.getId()) {
            case R.id.button_change_pin:
                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
                startActivity(intent);
                break;
            case R.id.button_unlock_pin:
                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                startActivity(intent);
                break;
            case R.id.button_compat_locked:
                Toast.makeText(this, "Clocked In", Toast.LENGTH_LONG).show();
                /*Intent intent2 = new Intent(MainActivity.this, LockedCompatActivity.class);
                startActivity(intent2);*/
                break;
            case R.id.button_not_locked:
                Toast.makeText(this, "Clocked Out", Toast.LENGTH_LONG).show();
                /*Intent intent3 = new Intent(MainActivity.this, NotLockedActivity.class);
                startActivity(intent3);*/
        }
    }

    private void loginRequest() {
        JSONObject body = new JSONObject();
        try {
            body.put("email", "test@test.com");
            body.put("pin", "encrypted-sha1-sha256-pin");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://10.0.0.2:8080/api/login", body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // response from the server
                // {"id" : 1, "firstname": "ako", "lastname": "ikaw", "email": "test@testing.com", "hasFirstLogin": false}
                Gson gson = new Gson();

                User user = gson.fromJson(response.toString(), User.class);

                user.getFirstname();

                if (!user.isHasFirstLogin()) {
                    // prompt user to change pin and send again new pin to server
                } else {
                    // enter into the app
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              // internal server errors and 400s
                error.printStackTrace();
            }
        });

        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }
}

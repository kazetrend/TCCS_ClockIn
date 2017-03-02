package com.example.jirehcordova.tccs_clockin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

import static com.example.jirehcordova.tccs_clockin.R.id.pinSetUp;
import static com.example.jirehcordova.tccs_clockin.R.id.welcomemsg;
import static com.github.orangegangsters.lollipin.lib.managers.AppLock.ENABLE_PINLOCK;

public class MainActivity extends PinActivity implements View.OnClickListener {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String KEY_LOGGED_IN = "isloggedin";
    Button btn;
    private static final int REQUEST_CODE_ENABLE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcome = (TextView)findViewById(welcomemsg);
        btn = (Button)findViewById(R.id.changeling);
        btn.setText("CLOCK IN");
        btn.setOnClickListener(this);

        /*Button pinSet = (Button)findViewById(R.id.pinSetUp);
        final LinearLayout zz = (LinearLayout) findViewById(R.id.line);
        pinSet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View button){
                zz.removeView(button);
                Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
                startActivityForResult(intent, REQUEST_CODE_ENABLE);
            }
        });*/

        prefs = getSharedPreferences("name", Context.MODE_PRIVATE);

        editor = prefs.edit();
        String name = prefs.getString("name", "");
        welcome.setText("Welcome, " +name+"!");

        if(prefs.getBoolean(KEY_LOGGED_IN, false)){
            btn.setText("CLOCK OUT");
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
        switch (v.getId()) {
            case R.id.changeling:
                Toast.makeText(this, "Here is the final button which changes face depending on login/logout case ver 1", Toast.LENGTH_LONG).show();
                if(prefs.getBoolean(KEY_LOGGED_IN, false)){
                    editor.putBoolean(KEY_LOGGED_IN, false);
                    btn.setText("CLOCK IN");
                }
                else{
                    editor.putBoolean(KEY_LOGGED_IN, true);
                    btn.setText("CLOCK OUT");
                }
                editor.commit();
                break;
        }
    }

    /*private void loginRequest() {
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

    private void showMyDialog(String content){
       *//* Resources res = getResources();
        CustomDialog.Builder builder = new CustomDialog.Builder(this,
                res.getString(R.string.activity_dialog_title),
                res.getString(R.string.activity_dialog_accept));
        builder.content(content);
        builder.negativeText(res.getString(R.string.activity_dialog_decline));
     //   builder.

        //Set theme
        builder.darkTheme(false);
        builder.typeface(Typeface.SANS_SERIF);
        builder.positiveColor(res.getColor(R.color.light_blue_500)); // int res, or int colorRes parameter versions available as well.
        builder.negativeColor(res.getColor(R.color.light_blue_500));
        builder.rightToLeft(false); // Enables right to left positioning for languages that may require so.
        builder.titleAlignment(BaseDialog.Alignment.CENTER);
        builder.buttonAlignment(BaseDialog.Alignment.CENTER);

        //Set text sizes
        builder.titleTextSize((int) res.getDimension(R.dimen.activity_dialog_title_size));
        builder.contentTextSize((int) res.getDimension(R.dimen.activity_dialog_content_size));
        builder.buttonTextSize((int) res.getDimension(R.dimen.activity_dialog_positive_button_size));
        //builder.buttonTextSize(int) 6sp);
        res.getDimension(R.dimen.activity_dialog_negative_button_size);

        //Build the dialog.
        CustomDialog customDialog = builder.build();
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customDialog.setClickListener(new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelClick() {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        // Show the dialog.
        customDialog.show();*//*
    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_ENABLE:
                Toast.makeText(this, "PinCode enabled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

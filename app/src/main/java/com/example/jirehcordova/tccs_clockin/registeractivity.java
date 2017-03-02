package com.example.jirehcordova.tccs_clockin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.jirehcordova.tccs_clockin.model.User;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jirehcordova.tccs_clockin.model.User;

public class registeractivity extends AppCompatActivity {

    private static final String JSON_OBJECT_REQUEST_URL = "http://10.0.0.2:8080/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        final EditText pin = (EditText)findViewById(R.id.Pin);
        final EditText email = (EditText)findViewById(R.id.email);
        Button bregister = (Button)findViewById(R.id.submit);

       // client.execute();
        bregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginRequest(email.getText().toString(), pin.getText().toString());

            }
        });
    }

    private void loginRequest(String email, String pin) {
        final MaterialDialog dialog = new MaterialDialog.Builder(this).content("Logging In. Please wait a moment.").progress(true, 0).cancelable(false).build();
        dialog.show();

        JSONObject body = new JSONObject();
        try{
        body.put("email", email);
        body.put("pinCode", pin);}
        catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "http://192.168.0.104:8080/api/v1/login", body, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Log.d("response", response.toString());
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);
                String namee = user.getFirstname();
                String eemail = user.getemail();
                Boolean firstlogin = user.getisFirstlogin();
                SharedPreferences prefes = PreferenceManager.getDefaultSharedPreferences(registeractivity.this);
                SharedPreferences.Editor editor = prefes.edit();
                editor.putString("name", namee);
                editor.commit();
               /* if (firstlogin==true) {
                    // prompt user to change pin and send again new pin to server
                   // Intent intent = new Intent(registeractivity.this, ChangePinActivity.class);
                    startActivity(intent);
                } else {*/
                    /*String hPin = user.getHashedPin();
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(registeractivity.this);
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putString("PASSCODE", hPin);
                    editor1.commit();*/
                    // enter into the app
                    Intent intent = new Intent(registeractivity.this, MainActivity.class);
                    startActivity(intent);
                //}
            }

            /*@Override
            public void onResponse(JSONObject response) {
                // response from the server
                // {"id" : 1, "firstname": "ako", "lastname": "ikaw", "email": "test@testing.com", "hasFirstLogin": false}
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                VolleyLog.v("Response: ", response.toString());
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);
                String namee = user.getFirstname();
                String eemail = user.getemail();
                SharedPreferences prefes = PreferenceManager.getDefaultSharedPreferences(registeractivity.this);
                SharedPreferences.Editor editor = prefes.edit();
                editor.putString("name", namee);
                editor.commit();
                if (user.isHasFirstLogin()) {
                    // prompt user to change pin and send again new pin to server
                    Intent intent = new Intent(registeractivity.this, ChangePinActivity.class);
                    startActivity(intent);
                } else {
                    String hPin = user.getHashedPin();
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(registeractivity.this);
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putString("PASSCODE", hPin);
                    editor1.commit();
                    // enter into the app

                }
            }*/
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


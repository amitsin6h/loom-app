package com.genesisdigisec.loom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ahmadrosid.library.FloatingLabelEditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener {



    private FloatingLabelEditText ftemail;
    private FloatingLabelEditText ftpassword;

    private Button btLogin;

    private String email;
    private String password;


    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ftemail = (FloatingLabelEditText) findViewById(R.id.ftemail);
        ftpassword = (FloatingLabelEditText) findViewById(R.id.ftpassword);
        btLogin = (Button) findViewById(R.id.btlogin);

        btLogin.setOnClickListener(this);

    }



    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(LoginDetail.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(LoginDetail.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }




    private void userLogin() {
        email = ftemail.getText().toString().trim();
        password = ftpassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginDetail.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals(LoginDetail.LOGIN_SUCCESS)){
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(LoginDetail.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(LoginDetail.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(LoginDetail.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //intent.putExtra("key_email", "test");
                            startActivity(intent);

                        }else{
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(LoginDetail.EMAIL,email);
                map.put(LoginDetail.PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

   /* private void openProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USER_EMAIL, email);
        startActivity(intent);
    }*/






    @Override
    public void onClick(View v) {
        if(v == btLogin){
            userLogin();
        }
    }


}

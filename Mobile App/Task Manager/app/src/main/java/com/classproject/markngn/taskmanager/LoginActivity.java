package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.security.NetworkSecurityPolicy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText usrname, pword;
    private TextView forgetPass, newAccount;
    private Button login;
    private static final String URL = "http://m4rks.site/LAMPAPI/Applogin.php";
    public static int UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usrname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        forgetPass = (TextView)findViewById(R.id.passReset);
        newAccount = (TextView)findViewById(R.id.register);
        login = (Button)findViewById(R.id.LoginBT);
       // NetworkSecurityPolicy.getInstance().;

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login () {
        Map<String, String> map = new HashMap<>();
        map.put("uName", usrname.getText().toString().trim());
        map.put("pWord", Sha1.encryptPassword(pword.getText().toString().trim()));
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String userName = response.getString("UserName");
                    if(!userName.isEmpty()) {
                        UserID = response.getInt("UserID");
                        System.err.println("UserID = " + UserID);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else{
                        ErrorActivity.message = "Wrong Username or Password";
                        ErrorActivity.errorClass = LoginActivity.class;
                        startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
                    }
                } catch (JSONException e) {
                    ErrorActivity.message = "JSONException: " + e.getMessage();
                    ErrorActivity.errorClass = LoginActivity.class;
                    startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorActivity.message = error.getMessage();
                ErrorActivity.errorClass = LoginActivity.class;
                startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("UserName", usrname.getText().toString().trim());
                hashMap.put("Password", pword.getText().toString().trim());
                return hashMap;
            }
        };

        RequestSingleton.getInstance(this).addToRequestQueue(request);
    }

}

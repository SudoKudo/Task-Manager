package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText usrname, pword;
    private TextView forgetPass, newAccount;
    private Button login;
    private RequestQueue requestQueue;
    private static final String URL = "http:///m4rks.site/LAMPAPI/Applogin.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usrname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        forgetPass = (TextView)findViewById(R.id.passReset);
        newAccount = (TextView)findViewById(R.id.register);
        login = (Button)findViewById(R.id.LoginBT);

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

        ;

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start main activity without login API
                //Login();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });
    }

    private void Login (){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequestrequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("UserName", usrname.getText().toString().trim());
                hashMap.put("Password", pword.getText().toString().trim());
                return hashMap;
            }
        };

        requestQueue.add(request);

    }


}

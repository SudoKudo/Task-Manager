package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.security.NetworkSecurityPolicy;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Fname, Lname, Uname, Pass, Cpass, Email;
    private Button regButton;
    private TextView goBack;
    private static final String URL = "http://m4rks.site/LAMPAPI/Register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        System.err.println("cleartext = " + NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted());
        UIview();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    Register();
                }
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }

    private void UIview (){
        Fname = (EditText)findViewById(R.id.regFirstname);
        Lname = (EditText)findViewById(R.id.regLastname);
        Uname = (EditText)findViewById(R.id.regUsername);
        Pass = (EditText)findViewById(R.id.regPassword);
        Cpass = (EditText)findViewById(R.id.regCPass);
        Email = (EditText)findViewById(R.id.regEmail);
        regButton = (Button)findViewById(R.id.regButton);
        goBack = (TextView)findViewById(R.id.regBack);
    }

    private Boolean validateInput(){
        Boolean result = false;
         String first = Fname.getText().toString();
         String last = Lname.getText().toString();
         String usrname = Uname.getText().toString();
         String pass = Pass.getText().toString();
         String veripass = Cpass.getText().toString();
         String email = Email.getText().toString();

         if(first.isEmpty() && last.isEmpty() && usrname.isEmpty() && pass.isEmpty() && veripass.isEmpty() &&
                 email.isEmpty()){
             Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
         }

         if (!(pass.matches(veripass))) {
             Toast.makeText(this, "Make sure you enter the same password.", Toast.LENGTH_SHORT).show();
             Cpass.setError("Password don't match!");
         }
         else
             result = true;

        return result;
    }
    private void Register(){
        StringRequest request = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        ErrorActivity.message = error.getMessage();
                        ErrorActivity.errorClass = LoginActivity.class;
                        startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                 Map<String,String> map = new HashMap<>();
                map.put("uName", Uname.getText().toString().trim());
                map.put("pWord", Sha1.encryptPassword(Pass.getText().toString().trim()));
                map.put("email", Email.getText().toString().trim());
                map.put("firstname", Fname.getText().toString().trim());
                map.put("lastName", Lname.getText().toString().trim());

                return map;
            }
        };

        RequestSingleton.getInstance(this).addToRequestQueue(request);
    }
}

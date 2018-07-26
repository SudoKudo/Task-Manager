package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.classproject.markngn.taskmanager.LoginActivity.UserID;

public class task_view extends AppCompatActivity {

    private TextView task_name, task_description, task_priority, task_duration;
    private static final String URL = "http://m4rks.site/LAMPAPI/RetrieveTaskID.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        task_name = findViewById(R.id.task_view_name);
        task_description = findViewById(R.id.task_view_description);
        task_priority = findViewById(R.id.task_view_priority);
        task_duration = findViewById(R.id.task_view_duration);


        final String date = "bananas";
        final String userID = "test";

        Map<String, String> map = new HashMap<>();
        map.put("UserId", "" + userID);
        map.put("Date", "" + date);
        ;
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response.get("Title"));
                    System.err.println("Title = " + response.getString("Title"));

                } catch (JSONException e) {
                    e.printStackTrace();
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
                hashMap.put("UserId", "" + userID);
                hashMap.put("Date", "" + date);
                return hashMap;
            }
        };
        RequestSingleton.getInstance(this).addToRequestQueue(request);


    }
}

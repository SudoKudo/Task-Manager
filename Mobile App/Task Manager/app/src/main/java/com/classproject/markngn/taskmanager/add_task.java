package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class add_task extends AppCompatActivity {

    private EditText task_name, task_description, task_priority, task_duration;
    private Button addButton, backButton;

    private static final String URL = "http://m4rks.site/LAMPAPI/addTask.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initiate fields
        task_name = findViewById(R.id.task_name);
        task_description = findViewById(R.id.task_description);
        task_priority = findViewById(R.id.task_priority);
        task_duration = findViewById(R.id.task_duration);

        // What happens when the back button is clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(add_task.this, MainActivity.class));
            }
        });

        // What happens when add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Pull text from fields
                String taskName = task_name.getText().toString().trim();
                String taskDescription = task_description.getText().toString().trim();
                String taskPriority = task_priority.getText().toString().trim();
                String taskDuration = task_duration.getText().toString().trim();

                // Run the add Task function
                addTask();
            }
        }); // Add button click listener end
    } // onCreate end

    // Add the task via the API to the database
    private void addTask() {
        // Create JSON string
        Map<String, String> map = new HashMap<>();
        map.put("Title", task_name.getText().toString().trim());
        map.put("Description", task_description.getText().toString().trim());
        map.put("Duration", task_duration.getText().toString().trim());
        map.put("Priority", task_priority.getText().toString().trim());

        // Create JSON object and send to API
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, obj,

                // Check for error response
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.err.println("response = " + response.toString());
                        startActivity(new Intent(getApplicationContext(), add_task.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ErrorActivity.message = error.getMessage();
                ErrorActivity.errorClass = add_task.class;
                startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
            }
        });

        RequestSingleton.getInstance(this).addToRequestQueue(request);
    } // addTask end

}

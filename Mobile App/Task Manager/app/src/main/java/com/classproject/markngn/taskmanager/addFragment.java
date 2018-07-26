package com.classproject.markngn.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.classproject.markngn.taskmanager.LoginActivity.UserID;


public class addFragment extends Fragment {

    private EditText task_name, task_description, task_priority, task_duration;
    private Button addButton, backButton;

    private static final String URL = "http://m4rks.site/LAMPAPI/AddTask.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        // Initiate fields
        task_name =  view.findViewById(R.id.task_name);
        task_description = view.findViewById(R.id.task_description);
        task_priority = view.findViewById(R.id.task_priority);
        task_duration = view.findViewById(R.id.task_duration);
        addButton = view.findViewById(R.id.add_button);
        backButton = view.findViewById(R.id.back_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new mainFragment());
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public String FDate() {
        Date now = new Date();
        final SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-dd");
        return formatedDate.format(now);
    }

    // Add the task via the API to the database
    private void addTask() {

        // Pull text from fields
        String taskName = task_name.getText().toString().trim();
        String taskDescription = task_description.getText().toString().trim();
        String taskPriority = task_priority.getText().toString().trim();
        String taskDuration = task_duration.getText().toString().trim();
        String currentDate = FDate();

        // Create JSON string
        Map<String, String> map = new HashMap<>();
        map.put("userID", "" + UserID);
        map.put("title", taskName);
        map.put("description", taskDescription);
        map.put("dateCreated", currentDate);
        map.put("duration", taskDuration);
        map.put("startTime", currentDate);
        map.put("priority", taskPriority);
        map.put("date", currentDate);
        map.put("completion", "False");

        // Create JSON object and send to API
        JSONObject obj = new JSONObject(map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, obj,

                // Check for error response
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.err.println("response = " + response.toString());

                        //startActivity(new Intent(getApplicationContext(), add_task.class));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ErrorActivity.message = error.getMessage();
                //ErrorActivity.errorClass = add_task.class;
                //startActivity(new Intent(getApplicationContext(), ErrorActivity.class));
            }
        });

        //need to find new add method
        //RequestSingleton.getInstance(this).addToRequestQueue(request);
    } // addTask end

}

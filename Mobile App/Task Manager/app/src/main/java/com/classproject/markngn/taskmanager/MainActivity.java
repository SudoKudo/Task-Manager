package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.classproject.markngn.taskmanager.LoginActivity.UserID;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private static final String URL = "http://m4rks.site/LAMPAPI/RetrieveTaskID.php";
    //private static final String URL2 = "http://m4rks.site/LAMPAPI/RetrieveTaskID.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_account);
        }


        try {
            GrabTasksIDs(Fdate());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profileFragment()).commit();
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
            case R.id.nav_setting:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new settingFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public String FDate(int daysToAdd){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        final SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-DD");
        cal.add(Calendar.DATE, daysToAdd);
        return formatedDate.format(cal.getTime());
    }
    public String Fdate(){
        Date now = new Date();
        final SimpleDateFormat formatedDate = new SimpleDateFormat("yyyy-MM-DD");
        return formatedDate.format(now);
    }
    public void GrabTasksIDs(String fdate) throws JSONException {
        Map<String, String> map = new HashMap<>();
        map.put("UserID", "" + UserID);
        map.put("Date", fdate);
        JSONArray obj = new JSONArray(map);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, URL, obj, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i<response.length();i++) {
                        System.out.println(response.get(i).toString());
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
            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("UserID", "" + UserID);
                hashMap.put("Date", fdate);
                return hashMap;
            }*/
        };
        RequestSingleton.getInstance(this).addToRequestQueue(request);

    }
}


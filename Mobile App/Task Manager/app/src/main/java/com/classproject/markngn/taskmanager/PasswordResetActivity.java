package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button sendLink;
    private TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        resetEmail = (EditText)findViewById(R.id.ResetEmail);
        sendLink = (Button)findViewById(R.id.SendButton);
        goBack = (TextView)findViewById(R.id.resetBack);

        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PasswordResetActivity.this, LoginActivity.class));
            }
        });
    }
}

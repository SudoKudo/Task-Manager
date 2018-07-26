package com.classproject.markngn.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PassResetActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button sendLink, Reset;
    private TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_reset);

        resetEmail = (EditText)findViewById(R.id.ResetEmail);
        sendLink = (Button)findViewById(R.id.SendButton);
        goBack = (TextView)findViewById(R.id.resetBack);
        Reset = (Button)findViewById(R.id.resetButton);

        sendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendMail();
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reSet();
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassResetActivity.this, LoginActivity.class));
            }
        });

    }

    public void sendMail(){
        //mail reset code to reset password

    }

    public void reSet(){

    }



}

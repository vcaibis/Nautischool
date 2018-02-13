package com.example.arjun.vhf_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //EditText username = (EditText)findViewById(R.id.edit_text_username);
    //EditText password = (EditText)findViewById(R.id.edit_text_password);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void login(View view) {
        //if (username.getText().toString().equals("Tom") && password.getText().toString().equals("123")) {

            Toast.makeText(LoginActivity.this,"Username and password is correct",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, mainActivity.class));
        //} else {
            //wrong password
           // Toast.makeText(LoginActivity.this,"Username and password is NOT correct",
          //          Toast.LENGTH_LONG).show();
            //password.setText("");
        //}
    }
    public void cancel(View view) {

    }
}

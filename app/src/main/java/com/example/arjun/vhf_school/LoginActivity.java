package com.example.arjun.vhf_school;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

import com.example.arjun.vhf_school.xmpp.LocalBinder;
import com.example.arjun.vhf_school.xmpp.MyService;

public class LoginActivity extends AppCompatActivity {
    //EditText username = (EditText)findViewById(R.id.edit_text_username);
    //EditText password = (EditText)findViewById(R.id.edit_text_password);
    private static final String TAG = "MainActivity";
    private boolean mBounded;
    private MyService mService;
    private final ServiceConnection mConnection = new ServiceConnection() {

        @SuppressWarnings("unchecked")
        @Override
        public void onServiceConnected(final ComponentName name,
                                       final IBinder service) {
            mService = ((LocalBinder<MyService>) service).getService();
            mBounded = true;
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            mService = null;
            mBounded = false;
            Log.d(TAG, "onServiceDisconnected");
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        doBindService();


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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    void doBindService() {
        bindService(new Intent(this, MyService.class), mConnection,
                Context.BIND_AUTO_CREATE);
    }

    void doUnbindService() {
        if (mConnection != null) {
            unbindService(mConnection);
        }
    }

    public MyService getmService() {
        return mService;
    }

}

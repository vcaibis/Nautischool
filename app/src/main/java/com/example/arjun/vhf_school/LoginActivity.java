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
import com.example.arjun.vhf_school.xmpp.SettingsPrefs;

public class LoginActivity extends AppCompatActivity {
    EditText username ;
    EditText password ;
    private static final String TAG = "LoginActivity";
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

        //Define variables
        username = (EditText)findViewById(R.id.edit_text_username);
        password = (EditText)findViewById(R.id.edit_text_password);

        SettingsPrefs settings = new SettingsPrefs(this);
        username.setText(settings.getUser());
        password.setText(settings.getPassword());

    }

    /**
     * Function to log the user
     * @param view
     */
    public void login(View view) {

       if(testLogin()){
           Toast.makeText(LoginActivity.this,"Username and password is correct",
                   Toast.LENGTH_LONG).show();
           startActivity(new Intent(LoginActivity.this, mainActivity.class));
        }else {
           Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
       }
    }

    private boolean testLogin() {
        String sUsername = username.getText().toString();
        String sPassword = password.getText().toString();

        if(sUsername.isEmpty()){
            username.setError("Enter a username");
            return false;
        }
        if(sPassword.isEmpty()){
            password.setError("Enter a password");
            return false;
        }
        SettingsPrefs settings = new SettingsPrefs(this);
        settings.setUser(sUsername);
        settings.setPassword(sPassword);
        settings.save();
        // lunch the service
        doBindService();
        return true;
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

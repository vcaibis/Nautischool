package ch.hevs.nautischool;

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

import ch.hevs.nautischool.xmpp.LocalBinder;
import ch.hevs.nautischool.xmpp.MyService;
import ch.hevs.nautischool.xmpp.MyXMPP;
import ch.hevs.nautischool.xmpp.SettingsPrefs;

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
        username = findViewById(R.id.edit_text_username);
        password = findViewById(R.id.edit_text_password);

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
           startActivity(new Intent(LoginActivity.this, mainActivity.class));
       }
    }

    /**
     * Function to test the EditText values
     * And Start the BindService
     * @return false if empty
     */
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
        //Set the preferences
        SettingsPrefs settings = new SettingsPrefs(this);
        settings.setUser(sUsername);
        settings.setPassword(sPassword);
        settings.save();

        // launch the service
        doBindService();

        return true;
    }

    public void cancel(View view) {
        doUnbindService();
        MyXMPP.instance = null;
        MyXMPP.instanceCreated = false;

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

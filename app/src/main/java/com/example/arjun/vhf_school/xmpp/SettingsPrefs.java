package com.example.arjun.vhf_school.xmpp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by GCI on 15.02.2018.
 */

public class SettingsPrefs {
    public static final String PREFS_NAME = "MyPrefsXMPP";

    private Context context;
    private String server;
    private String user;
    private String password;

    public SettingsPrefs(Context context) {
        this.context = context;
        getMySharedPreferences();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void getMySharedPreferences(){
        SharedPreferences settings =  context.getSharedPreferences(PREFS_NAME, 0);
        server = settings.getString("server","85.1.228.64");
        user = settings.getString("user","user2");
        password = settings.getString("password","1234");
    }

    public void save() {

    }
}

package com.example.arjun.vhf_school;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.arjun.vhf_school.xmpp.MyXMPP;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

public class mainActivity extends AppCompatActivity {
//    static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sw360);

        //Disable native home barre and notification barre for win place
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        /*Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 360)
        {
            setContentView(R.layout.activity_main_sw360);
        }
        if (config.smallestScreenWidthDp >= 380)
        {
            setContentView(R.layout.activity_main_sw380);
        }
        if (config.smallestScreenWidthDp >= 600)
        {
            setContentView(R.layout.activity_main_sw600);
        }
        if (config.smallestScreenWidthDp >= 720)
        {
            setContentView(R.layout.activity_main_sw720);
        }
        else
        {
            setContentView(R.layout.activity_main);
        }*/

    }
    //test
    public void ClickNumPad1(View view) {

        MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(MyXMPP.connection);
        // Create a MultiUserChat using an XMPPConnection for a room
        MultiUserChat muc2 = manager.getMultiUserChat(MyXMPP.WATERPLACE);
        try {
            muc2.sendMessage("Button 1 clicked by "+MyXMPP.connection.getUser());
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (SmackException.NotConnectedException e) {
            e.printStackTrace();
        }

    }

    public void ClickNumPad2(View view) {
    }

    public void ClickNumPad3(View view) {
    }

    public void ClickNumPad4(View view) {
    }

    public void ClickNumPad5(View view) {
    }

    public void ClickNumPad6(View view) {
    }

    public void ClickNumPad7(View view) {
    }

    public void ClickNumPad8(View view) {
    }

    public void ClickNumPad9(View view) {
    }

    public void ClickNumPadCancel(View view) {
    }

    public void ClickNumPad0(View view) {
    }

    public void ClickNumPadEnter(View view) {
    }

    public void ClickDistressButton(View view) {
    }

    public void ClickDualWatchButton(View view) {
    }

    public void ClickButtonChannel_16(View view) {
    }

    public void ClickChangeLight(View view) {
    }

    public void btnMenu_onClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void btnSettings_onClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}

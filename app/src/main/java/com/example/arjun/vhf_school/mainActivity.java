package com.example.arjun.vhf_school;

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
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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

 }

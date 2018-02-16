package com.example.arjun.vhf_school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.arjun.vhf_school.xmpp.MyXMPP;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

public class mainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int cpt = 0;
        while(!(MyXMPP.connected && MyXMPP.isJoined) && cpt < 10000000){
            cpt++;
        }
        if(!(MyXMPP.connected && MyXMPP.isJoined)){
            Toast.makeText(this, "mainActivity : Login Failed", Toast.LENGTH_LONG).show();
            startActivity(new Intent(mainActivity.this, LoginActivity.class));
        }


        setContentView(R.layout.activity_main);

    }
    //test
    public void ClickNumPad1(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "this is button 1", Toast.LENGTH_LONG);
        toast.show();
        MultiUserChatManager manager = MultiUserChatManager.getInstanceFor(MyXMPP.connection);

        // Create a MultiUserChat using an XMPPConnection for a room
        MultiUserChat muc2 = manager.getMultiUserChat(MyXMPP.WATERPLACE);
        try {
            muc2.sendMessage("Coucou. Je suis "+MyXMPP.connection.getUser());
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

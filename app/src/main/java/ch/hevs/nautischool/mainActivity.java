package ch.hevs.nautischool;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.ScreenLabels;

public class mainActivity extends AppCompatActivity {
//    static Context context;
     MachineContext radio = new MachineContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void radioToScreen(View view) {
        ScreenLabels sc = radio.getScreenLabels();
        TextView right1 = (TextView)findViewById(R.id.right1);
        TextView right2 = (TextView)findViewById(R.id.right2);
        TextView right3 = (TextView)findViewById(R.id.right3);
        TextView right4 = (TextView)findViewById(R.id.right4);

        right1.setText(sc.right1);
        right2.setText(sc.right2);
        right3.setText(sc.right3);
        right4.setText(sc.right4);

        TextView mid1 = (TextView)findViewById(R.id.mid1);
        TextView mid2 = (TextView)findViewById(R.id.mid2);
        TextView mid3 = (TextView)findViewById(R.id.mid3);
        TextView mid4 = (TextView)findViewById(R.id.mid4);

        mid1.setText(sc.mid1);
        mid2.setText(sc.mid2);
        mid3.setText(sc.mid3);
        mid4.setText(sc.mid4);

        TextView left1 = (TextView)findViewById(R.id.left1);
        TextView left2 = (TextView)findViewById(R.id.left2);
        TextView left3 = (TextView)findViewById(R.id.left3);
        TextView left4 = (TextView)findViewById(R.id.left4);

        left1.setText(sc.left1);
        left2.setText(sc.left2);
        left3.setText(sc.left3);
        left4.setText(sc.left4);

        TextView message1 = (TextView)findViewById(R.id.message1);
        TextView message2 = (TextView)findViewById(R.id.message2);
        TextView message3 = (TextView)findViewById(R.id.message3);

        message1.setText(sc.message1);
        message2.setText(sc.message2);
        message3.setText(sc.message3);



        /*radio.getCurrentState();
        radio.getMachineData();
        radio.getTimer();*/
    }
    public void sk1(View view) {
        radio.softkey(1, false);
        radioToScreen(view);
    }
    //test
    public void ClickNumPad1(View view) {
        radio.volume(10);
        radioToScreen(view);

    }

    public void ClickNumPad2(View view) {
        RelativeLayout bigC = findViewById(R.id.bigchannel);
        RelativeLayout smallC = findViewById(R.id.smallChan);

        bigC.setVisibility(View.GONE);
        smallC.setVisibility(View.VISIBLE);

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
        radio.sixteenButtonPressed();

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

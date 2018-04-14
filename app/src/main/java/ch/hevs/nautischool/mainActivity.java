package ch.hevs.nautischool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
        TextView bigChan = (TextView)findViewById(R.id.bigChan);
        TextView smallChan = (TextView)findViewById(R.id.smallChan);

        bigChan.setText(sc.bigChan);
        smallChan.setText(sc.smallChan);

        TextView right1 = (TextView)findViewById(R.id.right1);
        TextView right2 = (TextView)findViewById(R.id.right2);
        TextView right3 = (TextView)findViewById(R.id.right3);
        TextView right4 = (TextView)findViewById(R.id.right4);

        right1.setText(sc.right1);
        right2.setText(sc.right2);
        right3.setText(sc.right3);
        right4.setText(sc.right4);
//.
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



//        radio.getCurrentState();
//        radio.getMachineData();
//        radio.getTimer();
    }
    public void sk1(View view) {
        radio.softkey(1, false);
        radioToScreen(view);
    }
    public void sk2(View view) {
        radio.softkey(2, false);
        radioToScreen(view);
    }
    public void sk3(View view) {
        radio.softkey(3, false);
        radioToScreen(view);
    }
    public void sk4(View view) {
        radio.softkey(4, false);
        radioToScreen(view);
    }

    public void ClickNumPad1(View view) {
        radio.alphanumeric(1);
        radioToScreen(view);

    }

    public void ClickNumPad2(View view) {
        radio.alphanumeric(2);
        radioToScreen(view);

    }

    public void ClickNumPad3(View view) {
        radio.alphanumeric(3);
        radioToScreen(view);

    }

    public void ClickNumPad4(View view) {
        radio.alphanumeric(4);
        radioToScreen(view);

    }

    public void ClickNumPad5(View view) {
        radio.alphanumeric(5);
        radioToScreen(view);

    }

    public void ClickNumPad6(View view) {
        radio.alphanumeric(6);
        radioToScreen(view);

    }

    public void ClickNumPad7(View view) {
        radio.alphanumeric(7);
        radioToScreen(view);

    }

    public void ClickNumPad8(View view) {
        radio.alphanumeric(8);
        radioToScreen(view);

    }

    public void ClickNumPad9(View view) {
        radio.alphanumeric(9);
        radioToScreen(view);

    }

    public void ClickNumPadCancel(View view) {
        radio.cancel();
        radioToScreen(view);

    }

    public void ClickNumPad0(View view) {
        radio.alphanumeric(0);
        radioToScreen(view);

    }

    public void ClickNumPadEnter(View view) {
        radio.enter();
        radioToScreen(view);

    }

    public void ClickDistressButton(View view) {
        radio.distressButtonPressed();
        radioToScreen(view);

    }

    public void ClickDualWatchButton(View view) {
        radio.dualWatchButtonPressed();
        radioToScreen(view);

    }

    public void ClickButtonChannel_16(View view) {
        radio.sixteenButtonPressed();
        radioToScreen(view);

    }

    public void ClickChangeLight(View view) {
        radio.lightButtonPressed();
        radioToScreen(view);

    }

    public void btnMenu_onClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void btnSettings_onClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    public void onClickVolume(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogViewVolume = inflater.inflate(R.layout.volume_layout,null);
        builder.setView(dialogViewVolume);

        final SeekBar sbVolume = (SeekBar)dialogViewVolume.findViewById(R.id.seekBarVolume);
        sbVolume.setMax(100);

        final TextView tv_Volume  = (TextView)dialogViewVolume.findViewById(R.id.resultV);
        int oldVolume = radio.getMachineData().volume;


        tv_Volume.setText(oldVolume + " %");
        sbVolume.setProgress(oldVolume);

        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int newVolume, boolean b) {

                tv_Volume.setText(newVolume + " %");

                radio.volume(newVolume);
                radioToScreen(view);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final AlertDialog dialog = builder.create();

        dialog.show();
    }



    public void onClickSquelch(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogViewSquelch = inflater.inflate(R.layout.squelch_layout,null);
        builder.setView(dialogViewSquelch);

        final SeekBar sbSquelch = (SeekBar)dialogViewSquelch.findViewById(R.id.seekBarSquelch);
        sbSquelch.setMax(100);

        final TextView tv_Squelch  = (TextView)dialogViewSquelch.findViewById(R.id.resultS);
        int oldSquelch = radio.getMachineData().squelch;

        tv_Squelch.setText(oldSquelch + " %");
        sbSquelch.setProgress(oldSquelch);

        sbSquelch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int newSquelch, boolean b) {

                tv_Squelch.setText(newSquelch + " %");

                radio.squelch(newSquelch);
                radioToScreen(view);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();

        dialog.show();

    }



}

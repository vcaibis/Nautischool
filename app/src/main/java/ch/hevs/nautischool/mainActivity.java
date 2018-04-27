package ch.hevs.nautischool;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;

import java.util.Timer;
import java.util.TimerTask;

import ch.hevs.nautischool.machine.MachineContext;
import ch.hevs.nautischool.machine.ScreenLabels;

import static ch.hevs.nautischool.R.drawable.led_layout_off;

public class mainActivity extends AppCompatActivity {
    //    static Context context;
    MachineContext radio = new MachineContext();
    private DrawLine mDrawLine;
    private ImageView line;
    private Timer timer;
    private TimerTask task;

    TextView right1;
    Button sk1;
    TextView right2;
    Button sk2;
    TextView right3;
    Button sk3;
    TextView right4;
    Button sk4;
    Button distressBouton;
    private final String TAG = "ICI";
    int mOrientation;

    /**
     *
     * @param savedInstanceState
     * mDrawLine.getViewTreeObserver():
     *
     */
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

        mDrawLine = (DrawLine) findViewById(R.id.drawLine);


        right1 = findViewById(R.id.right1);
        sk1 = findViewById(R.id.sk1);
        right2 = findViewById(R.id.right2);
        sk2 = findViewById(R.id.sk2);
        right3 = findViewById(R.id.right3);
        sk3 = findViewById(R.id.sk3);
        right4 = findViewById(R.id.right4);
        sk4 = findViewById(R.id.sk4);
        distressBouton = findViewById(R.id.DistressButton);

        /**
         * Get sk 1-4 button and right 1-4 label position and draw line into imageview
         */
        mDrawLine.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mDrawLine.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                int imgWidth = mDrawLine.getWidth();

                PointF pointA = new PointF(0, right1.getBottom() + 20);
                PointF pointB = new PointF(imgWidth, (sk1.getY() + sk1.getHeight() / 2) + 20);

                PointF pointC = new PointF(0, (right2.getBottom()) + 20);
                PointF pointD = new PointF(imgWidth, (sk2.getY() + sk2.getHeight() / 2) + 20);

                PointF pointE = new PointF(0, (right3.getBottom()) + 20);
                PointF pointF = new PointF(imgWidth, (sk3.getY() + sk3.getHeight() / 2) + 20);

                PointF pointG = new PointF(0, (right4.getBottom()) + 20);
                PointF pointH = new PointF(imgWidth, (sk4.getY() + sk4.getHeight() / 2) + 20);

                mDrawLine.setPointA(pointA);
                mDrawLine.setPointB(pointB);
                mDrawLine.setPointC(pointC);
                mDrawLine.setPointD(pointD);
                mDrawLine.setPointE(pointE);
                mDrawLine.setPointF(pointF);
                mDrawLine.setPointG(pointG);
                mDrawLine.setPointH(pointH);
                mDrawLine.draw();
            }
        });


        startTimer();

        final Button txt = (Button) findViewById(R.id.distSwipeButton);

        txt.setOnTouchListener(new OnSwipeTouchListener(this) {


        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
            Timer t = new Timer();
            Button btn = (Button) findViewById(R.id.DistressButton);
            btn.setVisibility(View.VISIBLE);
            txt.setVisibility(View.INVISIBLE);

        }


    });


    }

    public void sk1(View view) {
        radio.softkey(1, false);
        radioToScreen();
        sk1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                radio.softkey(1, true);
                radioToScreen();
                return true;
            }
        });
    }

    public void sk2(View view) {
        radio.softkey(2, false);
        radioToScreen();
        sk2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                radio.softkey(2, true);
                radioToScreen();
                return true;
            }
        });
    }

    public void sk3(View view) {
        radio.softkey(3, false);
        radioToScreen();
        sk3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                radio.softkey(3, true);
                radioToScreen();
                return true;
            }
        });
    }

    public void sk4(View view) {
        radio.softkey(4, false);
        radioToScreen();
        radio.softkey(4, true);
        sk4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
                radio.softkey(4, true);
                radioToScreen();
                return true;
            }
        });

    }

    public void ClickNumPad1(View view) {
        radio.alphanumeric(1);
        radioToScreen();

    }

    public void ClickNumPad2(View view) {
        radio.alphanumeric(2);
        radioToScreen();

    }

    public void ClickNumPad3(View view) {
        radio.alphanumeric(3);
        radioToScreen();

    }

    public void ClickNumPad4(View view) {
        radio.alphanumeric(4);
        radioToScreen();

    }

    public void ClickNumPad5(View view) {
        radio.alphanumeric(5);
        radioToScreen();

    }

    public void ClickNumPad6(View view) {
        radio.alphanumeric(6);
        radioToScreen();

    }

    public void ClickNumPad7(View view) {
        radio.alphanumeric(7);
        radioToScreen();

    }

    public void ClickNumPad8(View view) {
        radio.alphanumeric(8);
        radioToScreen();

    }

    public void ClickNumPad9(View view) {
        radio.alphanumeric(9);
        radioToScreen();

    }

    public void ClickNumPadCancel(View view) {
        radio.cancel();
        radioToScreen();

    }

    public void ClickNumPad0(View view) {
        radio.alphanumeric(0);
        radioToScreen();

    }

    public void ClickNumPadEnter(View view) {
        radio.enter();
        radioToScreen();

    }

    public void ClickDistressButton(View view) {
        radio.distress(false);
        radioToScreen();
        distressBouton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                radio.distress(true);
                radioToScreen();
                return true;
            }
        });
    }

    public void ClickDualWatchButton(View view) {
        radio.dualWatchButtonPressed();
        radioToScreen();

    }

    public void ClickButtonChannel_16(View view) {
        radio.sixteenButtonPressed();
        radioToScreen();

    }


    public void btnMenu_onClick(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void btnSettings_onClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * method, that changes the volume value
     * max 100
     * @param view
     */
    public void onClickVolume(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogViewVolume = inflater.inflate(R.layout.volume_layout, null);
        builder.setView(dialogViewVolume);

        final SeekBar sbVolume = (SeekBar) dialogViewVolume.findViewById(R.id.seekBarVolume);
        sbVolume.setMax(100);

        final TextView tv_Volume = (TextView) dialogViewVolume.findViewById(R.id.resultV);
        int oldVolume = radio.getMachineData().volume;


        tv_Volume.setText(oldVolume + " %");
        sbVolume.setProgress(oldVolume);

        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int newVolume, boolean b) {

                tv_Volume.setText(newVolume + " %");

                radio.volume(newVolume);
                radioToScreen();
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

    /**
     * method, that changes the Squelch level
     * @param view
     * max 100
     */
    public void onClickSquelch(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.this);

        LayoutInflater inflater = getLayoutInflater();
        View dialogViewSquelch = inflater.inflate(R.layout.squelch_layout, null);
        builder.setView(dialogViewSquelch);

        final SeekBar sbSquelch = (SeekBar) dialogViewSquelch.findViewById(R.id.seekBarSquelch);
        sbSquelch.setMax(100);

        final TextView tv_Squelch = (TextView) dialogViewSquelch.findViewById(R.id.resultS);
        int oldSquelch = radio.getMachineData().squelch;

        tv_Squelch.setText(oldSquelch + " %");
        sbSquelch.setProgress(oldSquelch);

        sbSquelch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int newSquelch, boolean b) {

                tv_Squelch.setText(newSquelch + " %");

                radio.squelch(newSquelch);
                radioToScreen();
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

    public void startTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        radioToScreen();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(task, 0, 250);
    }



    public void radioToScreen() {

        RelativeLayout lscreen = (RelativeLayout) findViewById(R.id.displayScreen);

        //if the screen is on we turn it off
        if (radio.getMachineData().screenColor == true) {
            int colorOff = getResources().getColor(R.color.colorScreenOff);
            lscreen.setBackgroundColor(colorOff);

        }
        //else we turn it on
        else {
            int colorOn = getResources().getColor(R.color.colorScreenOn);
            lscreen.setBackgroundColor(colorOn);
        }


        ScreenLabels sc = radio.getScreenLabels();
        TextView bigChan = (TextView) findViewById(R.id.bigChan);
        TextView smallChan = (TextView) findViewById(R.id.smallChan);

        bigChan.setText(sc.bigChan);
        smallChan.setText(sc.smallChan);

        TextView right1 = (TextView) findViewById(R.id.right1);
        TextView right2 = (TextView) findViewById(R.id.right2);
        TextView right3 = (TextView) findViewById(R.id.right3);
        TextView right4 = (TextView) findViewById(R.id.right4);

        right1.setText(sc.right1);
        right2.setText(sc.right2);
        right3.setText(sc.right3);
        right4.setText(sc.right4);
//.
        TextView mid1 = (TextView) findViewById(R.id.mid1);
        TextView mid2 = (TextView) findViewById(R.id.mid2);
        TextView mid3 = (TextView) findViewById(R.id.mid3);
        TextView mid4 = (TextView) findViewById(R.id.mid4);

        mid1.setText(sc.mid1);
        mid2.setText(sc.mid2);
        mid3.setText(sc.mid3);
        mid4.setText(sc.mid4);

        TextView left1 = (TextView) findViewById(R.id.left1);
        TextView left2 = (TextView) findViewById(R.id.left2);
        TextView left3 = (TextView) findViewById(R.id.left3);
        TextView left4 = (TextView) findViewById(R.id.left4);

        left1.setText(sc.left1);
        left2.setText(sc.left2);
        left3.setText(sc.left3);
        left4.setText(sc.left4);

        TextView message1 = (TextView) findViewById(R.id.message1);
        TextView message2 = (TextView) findViewById(R.id.message2);
        TextView message3 = (TextView) findViewById(R.id.message3);

        message1.setText(sc.message1);
        message2.setText(sc.message2);
        message3.setText(sc.message3);

    }

    /**
     * @param view
     */
    public void ClickButton1_25(View view) {
        //radio;
        radioToScreen();

    }

    /**
     * method, that changes the creen color ON/OFF
     * @param view
     */

    public void ClickChangeLight(View view) {
        radio.light();
        radioToScreen();
    }
}

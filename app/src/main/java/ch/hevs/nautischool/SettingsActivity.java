package ch.hevs.nautischool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {


    CheckBox cB_Ship_Station, cB_Exams, cB_Tuto, cB_Expert_MRCC;
    CheckBox cB_Credit, cB_Help, cB_Educational_Links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Set the Checkbox with wiews
        cB_Ship_Station = findViewById(R.id.cB_Ship_Station);
        cB_Exams = findViewById(R.id.cB_Exams);
        cB_Tuto = findViewById(R.id.cB_Tuto);
        cB_Expert_MRCC = findViewById(R.id.cB_Expert_MRCC);
        cB_Credit = findViewById(R.id.cB_Credit);
        cB_Help = findViewById(R.id.cB_Help);
        cB_Educational_Links = findViewById(R.id.cB_Educational_Links);

    }

    /**
     * Method to send back to the main menu
     * @param view
     */
    public void btnbackMain_onClick(View view) {
        startActivity(new Intent(SettingsActivity.this, mainActivity.class));

    }

    /**
     * Method to login
     * Redirect to the login Page
     * @param view
     */
    public void btn_login_onClick(View view) {
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
    }

    public void btnbackMain_onClick2(View view) {
        startActivity(new Intent(SettingsActivity.this, mainActivity.class));

    }

    /**
     * Method to recover the CheckBox clicked for Modes
     * @param view
     */
    public void onModesClicked(View view) {
        boolean checked = ((CheckBox)view).isChecked();
        String str="";

        switch (view.getId()){
            case R.id.cB_Ship_Station:
                str = "Ship Station Mode Selected";
                cB_Exams.setChecked(false);
                cB_Tuto.setChecked(false);
                cB_Expert_MRCC.setChecked(false);
                break;
            case R.id.cB_Exams:
                str = "Exam Mode Selected";
                cB_Ship_Station.setChecked(false);
                cB_Tuto.setChecked(false);
                cB_Expert_MRCC.setChecked(false);
                break;
            case R.id.cB_Tuto:
                str = "Tuto Mode Selected";
                cB_Ship_Station.setChecked(false);
                cB_Exams.setChecked(false);
                cB_Expert_MRCC.setChecked(false);
                break;
            case R.id.cB_Expert_MRCC:
                str = "Expert MRCC Mode Selected";
                cB_Ship_Station.setChecked(false);
                cB_Exams.setChecked(false);
                cB_Tuto.setChecked(false);
                break;


        }
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    /**
     * Method to recover the CheckBox clicked for Various Settings
     * @param view
     */
    public void onVariousSettingsClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str = "";

        switch (view.getId()) {
            case R.id.cB_Credit:
                str = "Credit";
                cB_Help.setChecked(false);
                cB_Educational_Links.setChecked(false);
                break;
            case R.id.cB_Help:
                str = "Help";
                cB_Credit.setChecked(false);
                cB_Educational_Links.setChecked(false);
                break;
            case R.id.cB_Educational_Links:
                str = "Educational Links";
                cB_Credit.setChecked(false);
                cB_Help.setChecked(false);
                break;
        }
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

}

package com.example.arjun.vhf_school;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends ListActivity {
         String[] listModes = {
                "Ship Station",
                "Exams",
                "Tutorial",
                "Expert MRCC"
        };

    String[] listvariousSettings = {
            "Credit",
            "Help",
            "Educational links"
    };



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);

            // -- Display mode of the ListView

            ListView firstListView = getListView();

            setListAdapter(new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_checked,listModes));

            firstListView.setChoiceMode(firstListView.CHOICE_MODE_SINGLE);


             ListView settingsSecondList = (ListView) findViewById(R.id.listSecond);
             settingsSecondList.setAdapter( new ArrayAdapter<String>(this,
                     R.layout.single_row, R.id.textViewSettingsSecond, listvariousSettings ));


        }




        public void onListItemClick(ListView firstListView, View v,int position,long id) {
            CheckedTextView item = (CheckedTextView) v;
            Toast.makeText(this, listModes[position] + " checked : " +
                    item.isChecked(), Toast.LENGTH_SHORT).show();
        }

        public void btnbackMain_onClick(View view) {
            startActivity(new Intent(SettingsActivity.this, mainActivity.class));
            //Intent intent = new Intent(this, mainActivity.class);
            //startActivity(intent);

        }


    public void btn_login_onClick(View view) {
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
    }

    public void btnbackMain_onClick2(View view) {
        startActivity(new Intent(SettingsActivity.this, mainActivity.class));
        //Intent intent = new Intent(this, mainActivity.class);
        //startActivity(intent);
    }
}

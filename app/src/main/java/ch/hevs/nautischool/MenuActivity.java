package ch.hevs.nautischool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    String[] dcsMessageslist = {
            "Distress alert (CH16)",
            "Routine alert (CH08)",
            "Group alert (CH06)"
    };

    String[] vocalMessageslist = {
            "Safety (CH16)",
            "Routine (CH08)"//5
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ListView menuFirstList = (ListView) findViewById(R.id.list_DSC_messages);
        menuFirstList.setAdapter( new ArrayAdapter<String>(this,
                R.layout.single_row_menu, R.id.text_view_message, dcsMessageslist ));


        ListView menuSecondList = (ListView) findViewById(R.id.list_vocal_messages);
        menuSecondList.setAdapter( new ArrayAdapter<String>(this, R.layout.single_row_menu_vocal, R.id.text_view_messages_vocal, vocalMessageslist));


    }



    public void btnbackMain_onClick(View view) {
        startActivity(new Intent(MenuActivity.this, mainActivity.class));

    }

    public void btnbackMain_onClick1(View view) {
        startActivity(new Intent(MenuActivity.this, mainActivity.class));
        //
        //Intent intent = new Intent(this, mainActivity.class);
        //startActivity(intent);
    }
}

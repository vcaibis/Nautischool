package ch.hevs.nautischool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MenuActivity extends AppCompatActivity {

    String[] dcsMessageslist = {
            "Distress alert (CH16)",
            "Routine alert (CH08)",
            "Group alert (CH06)"
    };

    String[] vocalMessageslist = {
            "Safety (CH16)",
            "Routine (CH08)",
            "Group (CH12)",
            "Group (CH05)"};

    public static void justifyListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter adapter = listView.getAdapter();

        if (adapter == null) {
            return;
        }
        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        ListView menuFirstList = findViewById(R.id.list_DSC_messages);
        menuFirstList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.single_row_menu, R.id.text_view_message, dcsMessageslist));
        Utility.setListViewHeightBasedOnChildren(menuFirstList);


        ListView menuSecondList = findViewById(R.id.list_vocal_messages);
        menuSecondList.setAdapter(new ArrayAdapter<String>(this, R.layout.single_row_menu_vocal, R.id.text_view_messages_vocal, vocalMessageslist));

        Utility.setListViewHeightBasedOnChildren(menuSecondList);
    }

    public void btnbackMain_onClick(View view) {
        startActivity(new Intent(MenuActivity.this, mainActivity.class));

    }

    public void btnbackMain_onClick1(View view) {
        startActivity(new Intent(MenuActivity.this, mainActivity.class));

        //Intent intent = new Intent(this, mainActivity.class);
        //startActivity(intent);
    }


}

package ca.bcit.ass2.lam_lai;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] continents = new String[]{"Africa", "Americas", "Asia", "Europe", "Oceania", "Polar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create the adapter that will allow listview to work
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, continents);

        //Set the adapter to the listview object
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView list, final View view,
                                int position, long id) {
        Intent intent = new Intent(MainActivity.this, Countries.class);

        //Passes on the continent to the next activity
        intent.putExtra("continent", continents[position]);

        startActivity(intent);
    }
}


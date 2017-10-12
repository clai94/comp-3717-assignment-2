package ca.bcit.ass2.lam_lai;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Countries extends ListActivity {
    String[] countries = new String[]{"Canada"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create the adapter that will allow listview to work
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countries);

        //Set the adapter to the listview object
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView list, final View view,
                                int position, long id) {
        Intent intent = new Intent(Countries.this, CountryDetails.class);

       // intent.putExtra("countries", countries[position]);

        startActivity(intent);
    }
}

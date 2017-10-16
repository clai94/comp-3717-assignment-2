package ca.bcit.ass2.calvinlai_anguslam;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The Main activity for the app. Displays a list of regions.
 *
 * @author Angus Lam, Calvin Lai
 * @version 1.0
 */
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

    /**
     * Selects the continent.
     *
     * @param list the list view
     * @param view the view
     * @param position the position of the item on the list
     * @param id the id of the list item
     */
    public void onListItemClick(ListView list, final View view,
                                int position, long id) {
        Intent intent = new Intent(MainActivity.this, CountryActivity.class);

        //Passes on the continent to the next activity
        intent.putExtra("continent", continents[position]);

        startActivity(intent);
    }
}


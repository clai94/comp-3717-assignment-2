package ca.bcit.ass2.calvinlai_anguslam;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Displays a list of countries based on the selected region from the MainActiviy.
 *
 * @author Angus Lam, Calvin Lai
 * @version 1.0
 */
public class CountryActivity extends ListActivity {
    ArrayList<String> countries = new ArrayList<String>();
    String apiURL;
    JSONArray countryJsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetCountries().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetCountries extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh     = new HttpHandler();
            Intent      intent = getIntent();

            // Append countries to the end of the api URL that would search by region
            apiURL = "https://restcountries.eu/rest/v2/region/"
                    + intent.getStringExtra("continent").toLowerCase();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(apiURL);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array
                    countryJsonArray = new JSONArray(jsonStr);

                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        // Get the JSON object from the JSON array
                        JSONObject c = countryJsonArray.getJSONObject(i);

                        // Add the name of the country to the arraylist of countries
                        countries.add(c.getString("name"));
                    }
                } catch (final JSONException e) {}
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Create adapter here
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CountryActivity.this,
                    android.R.layout.simple_list_item_1, countries);

            // Attach the adapter to a ListView
            setListAdapter(adapter);
        }
    }

    /**
     * Sends country information over to the CountryDetailActivity on click.
     *
     * @param list the list view item
     * @param view the view being pressed
     * @param position the position of the item on the list
     * @param id the id of the item
     */
    public void onListItemClick(ListView list, final View view,
                                int position, long id) {
        Intent intent = new Intent(CountryActivity.this, CountryDetailActivity.class);

        // Pass on the country to the next activity
        try {
            JSONObject c = countryJsonArray.getJSONObject(position);
            JSONArray b = c.getJSONArray("borders");
            String borders = "";
            for (int i = 0 ; i < b.length(); i ++) {
                borders += b.get(i) + " ";
            }
            intent.putExtra("name", c.getString("name"));
            intent.putExtra("capital", c.getString("capital"));
            intent.putExtra("region", c.getString("region"));
            intent.putExtra("population", c.getString("population"));
            intent.putExtra("area", c.getString("area"));
            intent.putExtra("borders", borders);
            intent.putExtra("flag", c.getString("flag"));
            startActivity(intent);
        } catch (JSONException e) {

        }
    }
}


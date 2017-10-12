package ca.bcit.ass2.lam_lai;

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

public class Countries extends ListActivity {
    ArrayList<String> countries = new ArrayList<String>();

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
            String apiURL = "https://restcountries.eu/rest/v2/region/"
                    + intent.getStringExtra("continent").toLowerCase();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(apiURL);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Countries.this,
                    android.R.layout.simple_list_item_1, countries);

            // Attach the adapter to a ListView
            setListAdapter(adapter);
        }
    }

    public void onListItemClick(ListView list, final View view,
                                int position, long id) {
        Intent intent = new Intent(Countries.this, CountryDetails.class);

        // Pass on the country to the next activity
        intent.putExtra("countries", countries.get(position));

        startActivity(intent);
    }
}


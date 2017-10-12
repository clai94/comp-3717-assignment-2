package ca.bcit.ass2.lam_lai;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        TextView  name       = (TextView) findViewById(R.id.name);
        TextView  capital    = (TextView) findViewById(R.id.capital);
        TextView  region     = (TextView) findViewById(R.id.region);
        TextView  population = (TextView) findViewById(R.id.population);
        TextView  area       = (TextView) findViewById(R.id.area);
        TextView  borders    = (TextView) findViewById(R.id.borders);
        ImageView flag       = (ImageView) findViewById(R.id.flag);

        name.setText("Canada");
        capital.setText("capital");
        region.setText("region");
        population.setText("populations");
        area.setText("area");
        borders.setText("borders");
        //flag.setImageResource(someObject.getImageResourceId());
    }
}

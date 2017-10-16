package ca.bcit.ass2.calvinlai_anguslam;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.widget.TextView;

/**
 * Displays country specific details chosen from the CountryActivity.
 *
 * @author Angus Lam, Calvin Lai
 * @version 1.0
 */
public class CountryDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Intent      intent = getIntent();
        HttpHandler sh     = new HttpHandler();

        String name_        = intent.getStringExtra("name");
        String capital_     = intent.getStringExtra("capital");
        String region_      = intent.getStringExtra("region");
        String populations_ = intent.getStringExtra("population");
        String area_        = intent.getStringExtra("area");
        String borders_     = intent.getStringExtra("borders");
        String flag_        = intent.getStringExtra("flag");

        TextView  name       = (TextView) findViewById(R.id.name);
        TextView  capital    = (TextView) findViewById(R.id.capital);
        TextView  region     = (TextView) findViewById(R.id.region);
        TextView  population = (TextView) findViewById(R.id.population);
        TextView  area       = (TextView) findViewById(R.id.area);
        TextView  borders    = (TextView) findViewById(R.id.borders);
        WebView flag         = (WebView) findViewById(R.id.flag);

        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);

        name.setText(getString(R.string.name) + " " +  name_);
        capital.setText(getString(R.string.capital) + " " + capital_);
        region.setText(getString(R.string.region) + " " +region_);
        population.setText(getString(R.string.population) + " " +populations_);
        area.setText(getString(R.string.area) + " " +area_);
        borders.setText(getString(R.string.borders) + " " +borders_);
        flag.loadUrl(flag_);
    }
}

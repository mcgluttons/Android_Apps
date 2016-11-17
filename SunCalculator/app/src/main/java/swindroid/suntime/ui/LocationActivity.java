package swindroid.suntime.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swindroid.suntime.R;

public class LocationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        final ArrayList<String> listItems = new ArrayList<>();
        final ArrayList<Double> latitudes = new ArrayList<>();
        final ArrayList<Double> longitudes = new ArrayList<>();
        final ArrayList<String> timeZones = new ArrayList<>();

        ListView locationList = (ListView)findViewById(R.id.locationList);

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent locationData = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("location", listItems.get(position));
                bundle.putDouble("latitude", latitudes.get(position));
                bundle.putDouble("longitude", longitudes.get(position));
                bundle.putString("timeZone", timeZones.get(position));

                locationData.putExtras(bundle);
                setResult(RESULT_OK, locationData);
                finish();
            }
        });

        try {

            String[] lineData;
            BufferedReader readFile = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.au_locations)));
            String line;

            while((line = readFile.readLine()) != null) {
                lineData = line.split(",");
                listItems.add(lineData[0]);
                latitudes.add(Double.parseDouble(lineData[1]));
                longitudes.add(Double.parseDouble(lineData[2]));
                timeZones.add(lineData[3]);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
            locationList.setAdapter(adapter);

        } catch (Exception e){

        }

    }


}

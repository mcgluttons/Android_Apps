package swindroid.suntime.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import swindroid.suntime.R;

public class LocationActivity extends AppCompatActivity {


    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayList<Double> latitudes = new ArrayList<>();
    private ArrayList<Double> longitudes = new ArrayList<>();
    private ArrayList<String> timeZones = new ArrayList<>();
    private String fileName = "au_locations.txt";
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ImageButton actionButton = (ImageButton)findViewById(R.id.fab_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewLocationActivity = new Intent(LocationActivity.this, NewLocationActivity.class);
                startActivityForResult(startNewLocationActivity, 1);
            }
        });

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


        File file = getBaseContext().getFileStreamPath(fileName);

        if(!file.exists()) {
            // read from raw and write to file
            createFile();
        }

        extractData();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        locationList.setAdapter(adapter);

    }

    public ArrayList<String[]> getFileContents(String fileName) {
        try {
            String line;
            String[] temp;
            ArrayList<String[]> lineData = new ArrayList<>();
            Log.d("READ_FILE", "lineData size: " + lineData.size());

            BufferedReader readFile = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

            while((line = readFile.readLine()) != null) {
                Log.d("READ_FILE", "line value: " + line);
                temp = line.split(",");
                lineData.add(temp);
                Log.d("READ_FILE", "lineData incrementing size: " + lineData.size());
            }
            readFile.close();
            Log.d("READ_FILE", "lineData final size: " + lineData.size());
            return lineData;
        } catch (Exception e) {
            Log.d("READ_FILE", "Can't read from file");
            e.printStackTrace();
        }
        return null;
    }

    public void extractData() {
        Log.d("CONTENTS", "opening existing file from internal storage");
        ArrayList<String[]> contents = getFileContents(fileName);
        Log.d("CONTENTS", "Contents size: " + contents.size());
        for (int i = 0; i < contents.size(); i++) {
            Log.d("CONTENTS", contents.get(i)[0]);
            Log.d("CONTENTS", contents.get(i)[1]);
            Log.d("CONTENTS", contents.get(i)[2]);
            Log.d("CONTENTS", contents.get(i)[3]);
            listItems.add(contents.get(i)[0]);
            latitudes.add(Double.parseDouble(contents.get(i)[1]));
            longitudes.add(Double.parseDouble(contents.get(i)[2]));
            timeZones.add(contents.get(i)[3]);
        }
    }

    public void createFile() {
        try {
            Log.d("FILE_CREATION", "Creating new file from raw data");
            Log.d("FILE_CREATION", getFileStreamPath(fileName).getAbsolutePath());
            File newFile = new File(getFilesDir(), fileName);
            BufferedReader readFile = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.au_locations)));
            String line;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE)));

            while((line = readFile.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            readFile.close();
        } catch (Exception e) {
            Log.d("FILE_CREATION", "File could not be created");
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        extractData();
        adapter.notifyDataSetChanged();
    }
}

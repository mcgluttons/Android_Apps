package swindroid.suntime.ui;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import swindroid.suntime.R;

public class NewLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);

        final EditText locationEditText = (EditText)findViewById(R.id.locationValue);
        final EditText latitudeEditText = (EditText)findViewById(R.id.latitudeValue);
        final EditText longitudeEditText = (EditText)findViewById(R.id.longitudeValue);
        final EditText timezoneEditText = (EditText)findViewById(R.id.timezoneValue);

        Button button = (Button)findViewById(R.id.newLocationButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = locationEditText.getText().toString();
                String latitude = latitudeEditText.getText().toString();
                String longitude = longitudeEditText.getText().toString();
                String timezone = timezoneEditText.getText().toString();

                try {
                    String fileName = "au_locations.txt";

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, MODE_APPEND)));

                    bw.append(location);
                    bw.append(",");
                    bw.append(latitude);
                    bw.append(",");
                    bw.append(longitude);
                    bw.append(",");
                    bw.append(timezone);
                    bw.newLine();
                    bw.close();
                } catch (Exception e){
                    Log.d("PUPPY", "File output incorrect");
                    e.printStackTrace();
                }
                Intent goBack = new Intent();
                setResult(RESULT_OK, goBack);
                finish();
            }
        });
    }
}

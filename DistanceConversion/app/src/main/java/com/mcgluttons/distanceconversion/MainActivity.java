package com.mcgluttons.distanceconversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView conversionTextView;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI() {
        Button button = (Button)findViewById(R.id.button);
        conversionTextView = (TextView)findViewById(R.id.conversionValue);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double convertedValue = convertToMetric();
                if (convertedValue != -1) {
                    if (checkBox.isChecked()){
                        conversionTextView.setText(Double.toString(convertedValue/100).concat(" " + getString(R.string.m)));
                    } else {
                        conversionTextView.setText(Double.toString(convertedValue).concat(getString(R.string.cm)));
                    }
                }
            }
        });
    }

    private double convertToMetric() {
        double centimeter_value;
        EditText miles_text = (EditText)findViewById(R.id.milesValue);
        EditText feet_text = (EditText)findViewById(R.id.feetValue);
        EditText inches_text = (EditText)findViewById(R.id.inchesValue);

        if (!miles_text.getText().toString().isEmpty()) {
            double miles = Double.parseDouble(miles_text.getText().toString());
            centimeter_value = miles * 5280 * 12 * 2.54;
            return centimeter_value;
        } else if (!feet_text.getText().toString().isEmpty()) {
            double feet = Double.parseDouble(feet_text.getText().toString());
            centimeter_value = feet * 12 * 2.54;
            return centimeter_value;
        } else if (!inches_text.getText().toString().isEmpty()) {
            double inches = Double.parseDouble(inches_text.getText().toString());
            centimeter_value = inches * 2.54;
            return centimeter_value;
        }
        return -1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("converted_value", conversionTextView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        conversionTextView.setText(savedInstanceState.getString("converted_value"));
        super.onRestoreInstanceState(savedInstanceState);
    }
}

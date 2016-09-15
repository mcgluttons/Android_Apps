package com.mcgluttons.conversion;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DistActivity extends AppCompatActivity {

    //private RadioGroup radioGroup;
    private RadioButton milesRadioButton;
    private RadioButton feetRadioButton;
    private RadioButton inchesRadioButton;
    private EditText inputEditText;
    private CheckBox unitsCheckBox;
    private TextView outputTextView;
    private Button convertButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dist);

        initialiseUI();
    }

    private void initialiseUI() {

        milesRadioButton = (RadioButton)findViewById(R.id.milesRadioButton);
        feetRadioButton = (RadioButton)findViewById(R.id.feetRadioButton);
        inchesRadioButton = (RadioButton)findViewById(R.id.inchesRadioButton);
        inputEditText = (EditText)findViewById(R.id.editText);
        unitsCheckBox = (CheckBox)findViewById(R.id.checkBox);
        outputTextView = (TextView)findViewById(R.id.outputTextView);
        convertButton = (Button)findViewById(R.id.button);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputEditText.getText().toString().isEmpty()) {
                    Converter convert = new Converter(Double.parseDouble(inputEditText.getText().toString()));
                    double output_value;
                    String units;
                    // conversion of distances to centimeters
                    if (milesRadioButton.isChecked()) {
                        output_value = convert.covertMiles();
                        units = "miles";
                    } else if (feetRadioButton.isChecked()) {
                        output_value = convert.convertFeet();
                        units = "feet";
                    } else {
                        output_value = convert.convertInches();
                        units = "inches";
                    }
                    if (unitsCheckBox.isChecked()) {
                        output_value /= 100;
                        outputTextView.setText(inputEditText.getText().toString() + " " + units + " is " + Double.toString(output_value) + " meters");
                    } else {
                        outputTextView.setText(inputEditText.getText().toString() + " " + units + " is " + Double.toString(output_value) + " centimeters");
                    }

                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("output", outputTextView.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        outputTextView.setText(savedInstanceState.getString("output"));
    }
}

package com.mcgluttons.conversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    private TextView output;
    private RadioGroup radioGroup;
    private RadioButton cRadioButton;
    private RadioButton fRadioButton;
    private EditText inputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        initialiseUI();
    }

    private void initialiseUI() {
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        cRadioButton = (RadioButton)findViewById(R.id.cRadioButton);
        fRadioButton = (RadioButton)findViewById(R.id.fRadioButton);
        inputValue = (EditText)findViewById(R.id.editText);
        output = (TextView)findViewById(R.id.outputTextView);
        Button convertButton = (Button)findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputValue.getText().toString().isEmpty()) {
                    Converter convert = new Converter(Double.parseDouble(inputValue.getText().toString()));
                    if (cRadioButton.isChecked()) {
                        setOutputText(convert.convertToCelsius());
                    } else if (fRadioButton.isChecked()) {
                        setOutputText(convert.convertToFahrenheit());
                    }
                }
            }
        });
    }

    private void setOutputText(double output_value) {
        // something degrees in
        //String string = Double.toString(output_value);
        if (fRadioButton.isChecked()) {
            output.setText(inputValue.getText().toString() + "\u2103 is " + String.format("%.2f", output_value) + "\u2109");
        } else if (cRadioButton.isChecked()) {
            output.setText(inputValue.getText().toString() + "\u2109 is " + String.format("%.2f", output_value) + "\u2103");
        }
    }
}

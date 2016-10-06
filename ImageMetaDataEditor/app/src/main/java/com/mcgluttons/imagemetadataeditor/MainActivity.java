package com.mcgluttons.imagemetadataeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView image1_name;
    private TextView image1_date;

    private TextView image2_name;
    private TextView image2_date;

    private TextView image3_name;
    private TextView image3_date;

    private TextView image4_name;
    private TextView image4_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intialiseUI(savedInstanceState);
    }

    private void intialiseUI(Bundle savedInstanceState) {
        ImageButton image1 = (ImageButton)findViewById(R.id.image1);
        image1_name = (TextView)findViewById(R.id.image1_name);
        image1_date = (TextView)findViewById(R.id.image1_date);

        ImageButton image2 = (ImageButton)findViewById(R.id.image1);
        image2_name = (TextView)findViewById(R.id.image2_name);
        image2_date = (TextView)findViewById(R.id.image2_date);

        ImageButton image3 = (ImageButton)findViewById(R.id.image1);
        image3_name = (TextView)findViewById(R.id.image3_name);
        image3_date = (TextView)findViewById(R.id.image3_date);

        ImageButton image4 = (ImageButton)findViewById(R.id.image1);
        image4_name = (TextView)findViewById(R.id.image4_name);
        image4_date = (TextView)findViewById(R.id.image4_date);

        if(savedInstanceState == null) {
            initialiseImageData();
        } else {
            getPreviousTextValues(savedInstanceState);
        }

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void initialiseImageData() {
        image1_name.setText(R.string.initial_image1_name);
        image1_date.setText(R.string.initial_image1_date);
        image2_name.setText(R.string.initial_image2_name);
        image2_date.setText(R.string.initial_image2_date);
        image3_name.setText(R.string.initial_image3_name);
        image3_date.setText(R.string.initial_image3_date);
        image4_name.setText(R.string.initial_image4_name);
        image4_date.setText(R.string.initial_image4_date);
    }

    void getPreviousTextValues(Bundle savedInstanceState) {
        image1_name.setText("");
        image1_date.setText("");
        image2_name.setText("");
        image2_date.setText("");
        image3_name.setText("");
        image3_date.setText("");
        image4_name.setText("");
        image4_date.setText("");
    }

    protected void onSaveInstanceState(Bundle state) {

    }
}

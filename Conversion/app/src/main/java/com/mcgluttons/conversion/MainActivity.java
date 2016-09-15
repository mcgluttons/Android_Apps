package com.mcgluttons.conversion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI() {
        Button tempButton = (Button)findViewById(R.id.tempButton);
        Button distButton = (Button)findViewById(R.id.distButton);

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call TempActivity
                Intent startTempActivity = new Intent(MainActivity.this, TempActivity.class);
                startActivity(startTempActivity);
            }
        });

        distButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call DistActivity
                Intent startDistActivity = new Intent(MainActivity.this, DistActivity.class);
                startActivity(startDistActivity);
            }
        });
    }
}

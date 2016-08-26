package com.mcgluttons.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("puppy", "I ate dumplings for lunch");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

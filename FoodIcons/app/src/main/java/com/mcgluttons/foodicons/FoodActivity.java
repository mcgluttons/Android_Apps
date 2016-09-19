package com.mcgluttons.foodicons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        initialiseUI();
    }

    private void initialiseUI() {
        ImageView foodImageView = (ImageView)findViewById(R.id.foodImageView);
        TextView descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);

        Bundle bundle = getIntent().getExtras();
        foodImageView.setImageResource(bundle.getInt("drawable"));
        descriptionTextView.setText(getString(bundle.getInt("description")));
    }
}

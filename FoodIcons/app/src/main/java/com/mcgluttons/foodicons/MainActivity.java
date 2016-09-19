package com.mcgluttons.foodicons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI() {
        ImageButton baconButton = (ImageButton)findViewById(R.id.baconButton);
        ImageButton pancakeButton = (ImageButton)findViewById(R.id.pancakeButton);
        ImageButton chickenButton = (ImageButton)findViewById(R.id.chickenButton);
        ImageButton burgerButton = (ImageButton)findViewById(R.id.burgerButton);

        baconButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFood(R.string.bacon_desc, R.drawable.bacon);
            }
        });

        pancakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFood(R.string.pancake_desc, R.drawable.pancake);
            }
        });

        chickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFood(R.string.chicken_desc, R.drawable.chicken);
            }
        });

        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFood(R.string.burger_desc, R.drawable.hamburgers);
            }
        });
    }

    private void showFood(int description, int drawable) {
        Bundle bundle = new Bundle();
        bundle.putInt("description", description);
        bundle.putInt("drawable", drawable);

        Intent showFoodActivity = new Intent(this, FoodActivity.class);
        showFoodActivity.putExtras(bundle);
        startActivity(showFoodActivity);
    }
}

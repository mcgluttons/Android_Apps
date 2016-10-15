package com.mcgluttons.imagemetadataeditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText dateInput;
    private EditText URLInput;
    private EditText keywordInput;
    private EditText emailInput;
    private RatingBar ratingInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initialiseUI();
    }

    private void initialiseUI() {
        nameInput = (EditText)findViewById(R.id.nameInput);
        dateInput = (EditText)findViewById(R.id.dateInput);
        URLInput = (EditText)findViewById(R.id.URLInput);
        keywordInput = (EditText)findViewById(R.id.keywordsInput);
        emailInput = (EditText)findViewById(R.id.emailInput);
        ratingInput = (RatingBar)findViewById(R.id.ratingInput);

        ArrayList<Image> imageMetaData = getIntent().getParcelableArrayListExtra("IMAGE_DATA");
        Image image = imageMetaData.get(0);

        nameInput.setText(image.getName());
        dateInput.setText(image.getDate());
        URLInput.setText(image.getSourceURL());
        keywordInput.setText(image.getSourceURL());
        emailInput.setText(image.getEmail());
        ratingInput.setRating(image.getRating());
    }

    
}

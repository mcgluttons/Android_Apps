package com.mcgluttons.imagemetadataeditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    private Image image;
    private int imageID;

    private String name;
    private String date;
    private String url;
    private String keywords;
    private String email;
    private Float rating;
    private boolean sharing;

    private EditText nameInput;
    private EditText dateInput;
    private EditText URLInput;
    private EditText keywordInput;
    private EditText emailInput;
    private RatingBar ratingInput;
    private ToggleButton sharingInput;

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
        sharingInput = (ToggleButton)findViewById(R.id.shareButton);

        ArrayList<Image> imageMetaData = getIntent().getParcelableArrayListExtra("IMAGE_DATA");
        Image imageData = imageMetaData.get(0);
        imageID = imageData.getID();

        nameInput.setText(imageData.getName());
        dateInput.setText(imageData.getDate());
        URLInput.setText(imageData.getSourceURL());
        keywordInput.setText(imageData.getSourceURL());
        emailInput.setText(imageData.getEmail());
        ratingInput.setRating(imageData.getRating());
        sharingInput.setChecked(imageData.isSharing());

        nameInput.setOnFocusChangeListener(fcl);
        dateInput.setOnFocusChangeListener(fcl);
        URLInput.setOnFocusChangeListener(fcl);
        keywordInput.setOnFocusChangeListener(fcl);
        emailInput.setOnFocusChangeListener(fcl);
        ratingInput.setOnFocusChangeListener(fcl);
        sharingInput.setOnFocusChangeListener(fcl);
    }

    OnFocusChangeListener fcl = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            storeImageData();
        }
    };

    @Override
    public void onBackPressed() {
        storeImageData();
        Intent reply = new Intent();
        ArrayList<Image> imageList = new ArrayList<Image>();
        imageList.add(image);
        reply.putParcelableArrayListExtra("IMAGE_DATA", imageList);
        setResult(RESULT_OK, reply);

        super.onBackPressed();
    }

    void storeImageData() {
        getEditTextValues();
        if (image != null) {
            image.updateImageData(name, date, url, keywords, email, rating, sharing);
        } else {
            image = new Image(name, date, url, keywords, email, rating, sharing);
            image.setID(imageID);
        }
    }

    void getEditTextValues() {
        name = nameInput.getText().toString();
        date = dateInput.getText().toString();
        url = URLInput.getText().toString();
        keywords = keywordInput.getText().toString();
        email = emailInput.getText().toString();
        rating = ratingInput.getRating();
        sharing = sharingInput.isChecked();
    }
}

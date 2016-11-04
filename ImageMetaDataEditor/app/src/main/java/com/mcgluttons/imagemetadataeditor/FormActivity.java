package com.mcgluttons.imagemetadataeditor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    private Image image;
    private int imageID;

    private String name;
    private String date;
    Calendar cal = Calendar.getInstance();
    private int year;
    private int month;
    private int day;
    private String url;
    private String keywords;
    private String email;
    private Float rating;
    private boolean sharing;

    private TextView nameLabel;
    private EditText nameInput;
    private TextView dateLabel;
    private EditText dateInput;
    private TextView urlLabel;
    private EditText URLInput;
    private TextView keywordLabel;
    private EditText keywordInput;
    private TextView emailLabel;
    private EditText emailInput;
    private TextView ratingLabel;
    private RatingBar ratingInput;
    private ToggleButton sharingInput;
    private DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initialiseUI();
    }

    private void initialiseUI() {

        nameLabel = (TextView)findViewById(R.id.nameLabel);
        nameInput = (EditText)findViewById(R.id.nameInput);
        dateLabel = (TextView)findViewById(R.id.dateLabel);
        dateInput = (EditText)findViewById(R.id.dateInput);
        urlLabel = (TextView)findViewById(R.id.URLLabel);
        URLInput = (EditText)findViewById(R.id.URLInput);
        keywordLabel = (TextView)findViewById(R.id.keywordsLabel);
        keywordInput = (EditText)findViewById(R.id.keywordsInput);
        emailLabel = (TextView)findViewById(R.id.emailLabel);
        emailInput = (EditText)findViewById(R.id.emailInput);
        ratingLabel = (TextView)findViewById(R.id.ratingLabel);
        ratingInput = (RatingBar)findViewById(R.id.ratingInput);
        sharingInput = (ToggleButton)findViewById(R.id.shareButton);
        datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateInput.setText(Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

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

        String textSize = getIntent().getExtras().getString("text");
        Log.d("PUPPY", "textSize: " + textSize);
        if (textSize.equals("small")) {
            Log.d("PUPPY", "sending small id");
            setFormLabelStyle(R.style.FormLabelSmall);
        } else if (textSize.equals("large")) {
            Log.d("PUPPY", "sending large id");
            setFormLabelStyle(R.style.FormLabelLarge);
        } else {
            Log.d("PUPPY", "sending reg id");
            setFormLabelStyle(R.style.FormLabelNormal);
        }

        nameInput.setOnFocusChangeListener(fcl);
        dateInput.setOnFocusChangeListener(fcl);
        URLInput.setOnFocusChangeListener(fcl);
        keywordInput.setOnFocusChangeListener(fcl);
        emailInput.setOnFocusChangeListener(fcl);
        ratingInput.setOnFocusChangeListener(fcl);
        sharingInput.setOnFocusChangeListener(fcl);
        dateInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                datePicker.show();
            }
        });
    }

    private void setFormLabelStyle(int textStyleID) {
        Log.d("PUPPY", "textStyleID: " + textStyleID);
        nameLabel.setTextAppearance(textStyleID);
        dateLabel.setTextAppearance(textStyleID);
        urlLabel.setTextAppearance(textStyleID);
        keywordLabel.setTextAppearance(textStyleID);
        emailLabel.setTextAppearance(textStyleID);
        ratingLabel.setTextAppearance(textStyleID);
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

package com.mcgluttons.imagemetadataeditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView image1_name;
    private TextView image1_date;
    private TextView image2_name;
    private TextView image2_date;
    private TextView image3_name;
    private TextView image3_date;
    private TextView image4_name;
    private TextView image4_date;

    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI(savedInstanceState);
    }

    private void initialiseUI(Bundle savedInstanceState) {
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.image1);
        image1_name = (TextView)findViewById(R.id.image1_name);
        image1_date = (TextView)findViewById(R.id.image1_date);

        ImageButton imageButton2 = (ImageButton)findViewById(R.id.image1);
        image2_name = (TextView)findViewById(R.id.image2_name);
        image2_date = (TextView)findViewById(R.id.image2_date);

        ImageButton imageButton3 = (ImageButton)findViewById(R.id.image1);
        image3_name = (TextView)findViewById(R.id.image3_name);
        image3_date = (TextView)findViewById(R.id.image3_date);

        ImageButton imageButton4 = (ImageButton)findViewById(R.id.image1);
        image4_name = (TextView)findViewById(R.id.image4_name);
        image4_date = (TextView)findViewById(R.id.image4_date);

        if(savedInstanceState == null) {
            initialiseImageData();
        }

        // name source keyword date email rating
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image1, "image1");
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image2, "image2");
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image3, "image3");
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image4, "image4");
            }
        });
    }

    void initialiseImageData() {
        image1 = new Image("Image 1", "6/10/2016");
        image2 = new Image("Image 2", "6/10/2016");
        image3 = new Image("Image 3", "6/10/2016");
        image4 = new Image("Image 4", "6/10/2016");

        image1_name.setText(image1.getName());
        image1_date.setText(image1.getDate());
        image2_name.setText(image2.getName());
        image2_date.setText(image2.getDate());
        image3_name.setText(image3.getName());
        image3_date.setText(image3.getDate());
        image4_name.setText(image4.getName());
        image4_date.setText(image4.getDate());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("image1_name", image1_name.getText().toString());
        outState.putString("image1_date", image1_date.getText().toString());
        outState.putString("image2_name", image2_name.getText().toString());
        outState.putString("image2_date", image2_date.getText().toString());
        outState.putString("image3_name", image3_name.getText().toString());
        outState.putString("image3_date", image3_date.getText().toString());
        outState.putString("image4_name", image4_name.getText().toString());
        outState.putString("image4_date", image4_date.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        image1_name.setText(savedInstanceState.getString("image1_name"));
        image1_date.setText(savedInstanceState.getString("image1_date"));
        image2_name.setText(savedInstanceState.getString("image2_name"));
        image2_date.setText(savedInstanceState.getString("image2_date"));
        image3_name.setText(savedInstanceState.getString("image3_name"));
        image3_date.setText(savedInstanceState.getString("image3_date"));
        image4_name.setText(savedInstanceState.getString("image4_name"));
        image4_date.setText(savedInstanceState.getString("image4_date"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void showImageMetadata(Image image, String id) {
        // name source keyword date email rating
        Intent showFormActivity = new Intent(getApplicationContext(), FormActivity.class);
        ArrayList<Image> imageToEdit = new ArrayList<Image>();
        imageToEdit.add(image);
        showFormActivity.putParcelableArrayListExtra("IMAGE_DATA", imageToEdit);
        startActivityForResult(showFormActivity, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (data != null) {
                ArrayList<Image> imageMetaData = data.getParcelableArrayListExtra("IMAGE_DATA");
                Image toModify = imageMetaData.get(0);
                image4.getName(), image4.getSourceURL(), image4.getKeywords(), image4.getDate(), image4.getEmail(), image1.getRating();
            }
        }
    }
}

package com.mcgluttons.imagemetadataeditor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
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

    private RadioButton small;
    private RadioButton regular;
    private RadioButton large;

    private String textSize;

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

        ImageButton imageButton2 = (ImageButton)findViewById(R.id.image2);
        image2_name = (TextView)findViewById(R.id.image2_name);
        image2_date = (TextView)findViewById(R.id.image2_date);

        ImageButton imageButton3 = (ImageButton)findViewById(R.id.image3);
        image3_name = (TextView)findViewById(R.id.image3_name);
        image3_date = (TextView)findViewById(R.id.image3_date);

        ImageButton imageButton4 = (ImageButton)findViewById(R.id.image4);
        image4_name = (TextView)findViewById(R.id.image4_name);
        image4_date = (TextView)findViewById(R.id.image4_date);

        small = (RadioButton)findViewById(R.id.smallText);
        regular = (RadioButton)findViewById(R.id.normalText);
        large = (RadioButton)findViewById(R.id.largeText);

        if(savedInstanceState == null) {
            initialiseImageData();
        }

        // name source keyword date email rating
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image1);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image2);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image3);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageMetadata(image4);
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
        outState.putParcelable("image1", image1);
        outState.putParcelable("image2", image2);
        outState.putParcelable("image3", image3);
        outState.putParcelable("image4", image4);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        image1 = savedInstanceState.getParcelable("image1");
        image2 = savedInstanceState.getParcelable("image2");
        image3 = savedInstanceState.getParcelable("image3");
        image4 = savedInstanceState.getParcelable("image4");
        image1_name.setText(image1.getName());
        image1_date.setText(image1.getDate());
        image2_name.setText(image2.getName());
        image2_date.setText(image2.getDate());
        image3_name.setText(image3.getName());
        image3_date.setText(image3.getDate());
        image4_name.setText(image4.getName());
        image4_date.setText(image4.getDate());
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void showImageMetadata(Image image) {
        // name source keyword date email rating
        if (small.isChecked()) {
            textSize = "small";
        } else if (large.isChecked()) {
            textSize = "large";
        } else {
            textSize = "regular";
        }

        Intent showFormActivity = new Intent(getApplicationContext(), FormActivity.class);
        ArrayList<Image> imageToEdit = new ArrayList<Image>();
        Bundle bundle = new Bundle();
        bundle.putString("text", textSize);
        imageToEdit.add(image);
        Log.d("PUPPY", "Sending image with ID " + Integer.toString(image.getID()));
        showFormActivity.putParcelableArrayListExtra("IMAGE_DATA", imageToEdit);
        showFormActivity.putExtras(bundle);
        startActivityForResult(showFormActivity, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (data != null) {
                ArrayList<Image> imageMetaData = data.getParcelableArrayListExtra("IMAGE_DATA");
                Image result = imageMetaData.get(0);
                Log.d("PUPPY", "Image received with ID " + Integer.toString(result.getID()));
                if (result.getID() == 0) {
                    image1.cloneImage(result);
                } else if (result.getID() == 1) {
                    image2.cloneImage(result);
                } else if (result.getID() == 2) {
                    image3.cloneImage(result);
                } else if (result.getID() == 3) {
                    image4.cloneImage(result);
                } else {
                    // do nothing
                }
                updateDataDisplayed();
            }
        }
    }

    private void updateDataDisplayed() {
        image1_name.setText(image1.getName());
        image1_date.setText(image1.getDate());
        image2_name.setText(image2.getName());
        image2_date.setText(image2.getDate());
        image3_name.setText(image3.getName());
        image3_date.setText(image3.getDate());
        image4_name.setText(image4.getName());
        image4_date.setText(image4.getDate());
    }


}

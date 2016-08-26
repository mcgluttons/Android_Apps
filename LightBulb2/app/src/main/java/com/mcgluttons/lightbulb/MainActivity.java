package com.mcgluttons.lightbulb;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean onOff = false;
    private ImageView bulbImageView;
    private TextView bulbTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI() {
        // find views and assign listeners based on the orientation of the screen
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bulbTextView = (TextView)findViewById(R.id.bulbStatusTextView);
            bulbTextView.setOnTouchListener(bulbTouchListener);
            //bulbTextView.setOnLongClickListener(bulbLongClickListener);
        } else {
            bulbImageView = (ImageView)findViewById(R.id.bulbImageView);
            bulbImageView.setOnTouchListener(bulbTouchListener);
            //bulbImageView.setOnLongClickListener(bulbLongClickListener);
        }

        /*bulbImageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    changeBulbImage();
                    return true;
                }
                return false;
            }
        });*/
    }

    // anonymous inner class declared outside listener assignment statement
    private OnTouchListener bulbTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            // specify which action to change image on
            // if not specified with onTouchListener both press and release will trigger image change
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                swapViewResource();
                // returning true tells the function the touch event has been handled
                return true;
            }
            return false;
        }
    };

    private OnLongClickListener bulbLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            swapViewResource();
            return true;
        }
    };

    private void changeBulbText(Boolean bulbStatus) {
        if (bulbStatus) {
            bulbTextView.setText(getString(R.string.bulb_on));
            Log.d("ActionOccurring", "Turned light on");
            onOff = true;
        } else {
            bulbTextView.setText(getString(R.string.bulb_off));
            Log.d("ActionOccurring", "Turned light off");
            onOff = false;
        }
    }

    private void changeBulbImage(Boolean bulbStatus) {
        if (bulbStatus) {
            bulbImageView.setImageResource(R.drawable.on);
            Log.d("ActionOccurring", "Turned light on");
            onOff = true;
        } else {
            bulbImageView.setImageResource(R.drawable.off);
            Log.d("ActionOccurring", "Turned light off");
            onOff = false;
        }
    }

    private void swapViewResource() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (onOff) {
                changeBulbText(false);
            } else {
                changeBulbText(true);
            }
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (onOff) {
                changeBulbImage(false);
            } else {
                changeBulbImage(true);
            }
        }
    }

    private void updateViewResource() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (onOff) {
                changeBulbText(true);
            } else {
                changeBulbText(false);
            }
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if (onOff) {
                changeBulbImage(true);
            } else {
                changeBulbImage(false);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("onOff", onOff);
        if (onOff) {
            Log.d("state", "Saving state as on");
        } else {
            Log.d("state", "Saving state as off");
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onOff = (boolean)savedInstanceState.get("onOff");
            if (onOff) {
                Log.d("state", "Reinitialising state as on");

            } else {
                Log.d("state", "Reinitialising state as off");
            }
            updateViewResource();
        }
    }
}

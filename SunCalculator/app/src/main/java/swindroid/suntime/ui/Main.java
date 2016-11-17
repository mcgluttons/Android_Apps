package swindroid.suntime.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import swindroid.suntime.R;
import swindroid.suntime.calc.AstronomicalCalendar;
import swindroid.suntime.calc.GeoLocation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;

public class Main extends Activity
{

    private String location = "Melbourne";
    private double latitude = -37.50;
    private double longitude = 145.01;
    private String timeZone = TimeZone.getDefault().getID();
    private int lastUsedYear;
    private int lastUsedMonth;
    private int lastUsedDay;
    private TextView locationTextView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeUI();
    }

	private void initializeUI()
	{

		DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
		Calendar cal = Calendar.getInstance();
        lastUsedYear = cal.get(Calendar.YEAR);
        lastUsedMonth = cal.get(Calendar.MONTH);
        lastUsedDay = cal.get(Calendar.DAY_OF_MONTH);
		dp.init(lastUsedYear,lastUsedMonth,lastUsedDay,dateChangeHandler); // setup initial values and reg. handler
		updateTime(lastUsedYear, lastUsedMonth, lastUsedDay);

		locationTextView = (TextView)findViewById(R.id.locationTV);
        locationTextView.setText(location + ", AU");
        locationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeLocation = new Intent(Main.this, LocationActivity.class);
                startActivityForResult(changeLocation, 0);
            }
        });
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Bundle geoInfo = data.getExtras();
            location = geoInfo.getString("location");
            latitude = geoInfo.getDouble("latitude");
            longitude = geoInfo.getDouble("longitude");
            timeZone = geoInfo.getString("timeZone");

            locationTextView.setText(location + ", AU");

            updateTime(lastUsedYear, lastUsedMonth, lastUsedDay);
        }
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth)
	{
        Log.d("PUPPY", "timeZone: " + timeZone);
		TimeZone tz = TimeZone.getTimeZone(timeZone);
		GeoLocation geolocation = new GeoLocation(location, latitude, latitude, tz);
		AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
		ac.getCalendar().set(year, monthOfYear, dayOfMonth);
		Date srise = ac.getSunrise();
		Date sset = ac.getSunset();
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		TextView sunriseTV = (TextView) findViewById(R.id.sunriseTimeTV);
		TextView sunsetTV = (TextView) findViewById(R.id.sunsetTimeTV);
		Log.d("SUNRISE Unformatted", srise+"");
		
		sunriseTV.setText(sdf.format(srise));
		sunsetTV.setText(sdf.format(sset));		
	}
	
	OnDateChangedListener dateChangeHandler = new OnDateChangedListener()
	{
		public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
		{
            lastUsedYear = year;
            lastUsedMonth = monthOfYear;
            lastUsedDay = dayOfMonth;
			updateTime(lastUsedYear, lastUsedMonth, lastUsedDay);
		}	
	};
	
}
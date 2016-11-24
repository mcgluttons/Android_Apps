package com.mcgluttons.bookratings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    private void initialiseUI() {
        createData();
        ListView list = (ListView)findViewById(R.id.bookList);
        BookAdapter ba = new BookAdapter(this, bookList);
        list.setAdapter(ba);
    }



    private void createData() {
        bookList.add(new Book("Birthday Surprise", 2, R.drawable.ic_cake_black_48dp));
        bookList.add(new Book("The Worst Day", 4, R.drawable.ic_mood_bad_black_48dp));
        bookList.add(new Book("Liberty Bell: A Symbol of American Freedom", 1, R.drawable.ic_notifications_none_black_48dp));
        bookList.add(new Book("Graduating from Swinburne: A Living Nightmare", 3, R.drawable.ic_school_black_48dp));
        bookList.add(new Book("My Worst Dining Experiences", 1, R.drawable.ic_sentiment_dissatisfied_black_48dp));
        bookList.add(new Book("Controlling Your Emotions", 5, R.drawable.ic_sentiment_neutral_black_48dp));
        bookList.add(new Book("Best Cafes in Melbourne", 5, R.drawable.ic_sentiment_satisfied_black_48dp));
        bookList.add(new Book("All About Deathbeds", 2, R.drawable.ic_sentiment_very_dissatisfied_black_48dp));
        bookList.add(new Book("Is too Much Happiness Good for You?", 1, R.drawable.ic_sentiment_very_satisfied_black_48dp));
        bookList.add(new Book("How to Start Bushfires for Dummies", 0, R.drawable.ic_whatshot_black_48dp));
    }
}

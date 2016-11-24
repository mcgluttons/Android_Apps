package com.mcgluttons.bookratings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Justin Wolf on 24/11/2016.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_row, parent, false);
        }

        ImageView bookIcon = (ImageView)convertView.findViewById(R.id.bookIcon);
        TextView bookTitle = (TextView)convertView.findViewById(R.id.bookTitle);
        RatingBar bookRating = (RatingBar)convertView.findViewById(R.id.bookRating);

        bookIcon.setImageResource(book.getIconID());
        bookTitle.setText(book.getTitle());
        bookRating.setRating(book.getRating());

        return convertView;
    }
}

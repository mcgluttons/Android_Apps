package com.mcgluttons.bookratings;

/**
 * Created by Justin Wolf on 24/11/2016.
 */

public class Book {

    private String title;

    public String getTitle() {
        return title;
    }

    private float rating;

    public float getRating() {
        return rating;
    }

    private int iconID;

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getIconID() {
        return iconID;
    }

    public Book(String title, float rating, int iconID) {
        this.title = title;
        this.rating = rating;
        this.iconID = iconID;
    }
}

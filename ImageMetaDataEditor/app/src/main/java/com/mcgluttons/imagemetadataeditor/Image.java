package com.mcgluttons.imagemetadataeditor;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Justin Wolf on 7/10/2016.
 */

public class Image implements Parcelable {

    private static int counter = 0;

    private int ID;

    public int getID() {
        return ID;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String sourceURL;

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Image(String name, String date) {
        this.ID = counter++;
        this.name = name;
        this.date = date;
    }

    private Image(Parcel parcel) {
        // name source keyword date email rating
        name = parcel.readString();
        sourceURL = parcel.readString();
        keywords = parcel.readString();
        date = parcel.readString();
        email = parcel.readString();
        rating = parcel.readFloat();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        @Override
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // name source keyword date email rating
        parcel.writeString(name);
        parcel.writeString(sourceURL);
        parcel.writeString(keywords);
        parcel.writeString(date);
        parcel.writeString(email);
        parcel.writeFloat(rating);
    }

    void cloneImage(Image modified) {
        this.setName(modified.getName());
        this.setDate(modified.getDate());
        this.setEmail(modified.getEmail());
        this.setKeywords(modified.getKeywords());
        this.setSourceURL(modified.getSourceURL());
        this.setRating(modified.getRating());
    }
}

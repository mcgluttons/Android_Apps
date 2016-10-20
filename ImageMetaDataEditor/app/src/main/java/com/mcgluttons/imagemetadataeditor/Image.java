package com.mcgluttons.imagemetadataeditor;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


/**
 * Created by Justin Wolf on 7/10/2016.
 */

public class Image implements Parcelable {

    private static int counter = 0;

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    private boolean sharing;

    public boolean isSharing() {
        return sharing;
    }

    public void setSharing(boolean sharing) {
        this.sharing = sharing;
    }

    public Image(String name, String date) {
        Log.d("PUPPY", "Image created with ID " + Integer.toString(counter));
        this.ID = counter++;
        this.setName(name);
        this.setDate(date);
    }

    public Image(String name, String date, String url, String keywords, String email, Float rating, boolean sharing) {
        this.setName(name);
        this.setDate(date);
        this.setSourceURL(url);
        this.setKeywords(keywords);
        this.setEmail(email);
        this.setRating(rating);
        this.setSharing(sharing);
    }

    public void updateImageData(String name, String date, String url, String keywords, String email, Float rating, boolean sharing) {
        this.setName(name);
        this.setDate(date);
        this.setSourceURL(url);
        this.setKeywords(keywords);
        this.setEmail(email);
        this.setRating(rating);
        this.setSharing(sharing);
    }

    private Image(Parcel parcel) {
        // name source keyword date email rating
        ID = parcel.readInt();
        name = parcel.readString();
        sourceURL = parcel.readString();
        keywords = parcel.readString();
        date = parcel.readString();
        email = parcel.readString();
        rating = parcel.readFloat();
        boolean[] boolArray = new boolean[1];
        parcel.readBooleanArray(boolArray);
        sharing = boolArray[0];
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
        parcel.writeInt(ID);
        parcel.writeString(name);
        parcel.writeString(sourceURL);
        parcel.writeString(keywords);
        parcel.writeString(date);
        parcel.writeString(email);
        parcel.writeFloat(rating);
        parcel.writeBooleanArray(new boolean[] {sharing});
    }

    void cloneImage(Image modified) {
        this.setName(modified.getName());
        this.setDate(modified.getDate());
        this.setEmail(modified.getEmail());
        this.setKeywords(modified.getKeywords());
        this.setSourceURL(modified.getSourceURL());
        this.setSourceURL(modified.getSourceURL());
        this.setRating(modified.getRating());
        this.setSharing(modified.isSharing());
    }
}

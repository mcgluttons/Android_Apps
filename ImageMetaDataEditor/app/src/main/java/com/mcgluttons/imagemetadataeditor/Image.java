package com.mcgluttons.imagemetadataeditor;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Justin Wolf on 7/10/2016.
 */

public class Image implements Parcelable {

    private String name;

    public String getName() {
        return name;
    }

    private String sourceURL;

    public String getSourceURL() {
        return sourceURL;
    }

    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    private String date;

    public String getDate() {
        return date;
    }

    private int rating;

    public int getRating() {
        return rating;
    }

    public Image(String name, String date) {
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
        rating = parcel.readInt();
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
        parcel.writeInt(rating);
    }
}

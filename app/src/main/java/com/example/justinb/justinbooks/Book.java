package com.example.justinb.justinbooks;

/**
 * Created by jbuha on 6/19/2017.
 */

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mImageURL;
    private double mRating;

    public Book(String mTitle, String mAuthor, double mRating) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mRating = mRating;
    }

    public Book(String mTitle, String mAuthor, double mRating, String mImageURL) {
        this(mTitle, mAuthor, mRating);
        this.mImageURL = mImageURL;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public double getmRating() {
        return mRating;

    }

    public String getmImageURL() {
        return mImageURL;
    }
}

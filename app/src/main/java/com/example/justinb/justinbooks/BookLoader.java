package com.example.justinb.justinbooks;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by jbuha on 6/19/2017.
 */

public class BookLoader extends android.support.v4.content.AsyncTaskLoader<List<Book>> {

    private String bookURL;

    public BookLoader(Context context, String url) {
        super(context);

        bookURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (bookURL == null) {
            Log.e(BookLoader.class.getName(), "book URL is null");
            return null;
        }
        List<Book> books = QueryUtils.fetchBookData(bookURL);

        return books;

    }
}

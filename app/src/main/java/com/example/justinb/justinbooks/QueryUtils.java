package com.example.justinb.justinbooks;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbuha on 6/19/2017.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {

    }

    public static List<Book> fetchBookData(String url) {
        String JSONResponse = null;
        URL officialQueryURL = convertToURL(url);

        try {
            JSONResponse = makeHTTPRequest(officialQueryURL);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Cannot make HTTP request");
        }


        List<Book> books = extractBooks(JSONResponse);

        return books;

    }

    private static List<Book> extractBooks(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            Log.e(LOG_TAG, "Text Utils is Empty");
            return null;
        }

        ArrayList<Book> books = new ArrayList<Book>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray items = root.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject book = items.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");
                JSONArray authors = volumeInfo.getJSONArray("authors");
                String author = authors.getString(0);
                double rating = 0;
                if (volumeInfo.has("averageRating")) {
                    rating = volumeInfo.getDouble("averageRating");
                }

                books.add(new Book(title, author, rating));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;

    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            Log.e(LOG_TAG, "url is null");
            return jsonResponse;
        }


        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = inputStreamReader(inputStream);
            } else {
                Log.i(QueryUtils.class.getName(), "Not ok");
            }
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Could not create a connection");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }


        return jsonResponse;
    }

    private static String inputStreamReader(InputStream inputStream) throws IOException {
        StringBuilder data = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                data.append(line);
                line = reader.readLine();
            }
        }


        return data.toString();
    }

    private static URL convertToURL(String converturl) {
        URL url = null;
        if (converturl != null) {
            try {
                url = new URL(converturl);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
        return url;
    }

}

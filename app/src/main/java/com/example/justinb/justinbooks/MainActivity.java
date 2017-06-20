package com.example.justinb.justinbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> bookListItems = new ArrayList<String>();
        bookListItems.add("Harry Potter");


        ArrayAdapter<String> booksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookListItems);

        ListView booksListView = (ListView) findViewById(R.id.books_list_view);

        booksListView.setAdapter(booksAdapter);

    }
}

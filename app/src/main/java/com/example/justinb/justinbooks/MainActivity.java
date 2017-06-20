package com.example.justinb.justinbooks;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private final String EXAMPLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?minResults=10&q=harry+potter";
    private BookAdapter booksAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView booksListView = (ListView) findViewById(R.id.books_list_view);
        booksAdapter = new BookAdapter(this, new ArrayList<Book>());
        booksListView.setAdapter(booksAdapter);
        getSupportLoaderManager().initLoader(1, null, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, EXAMPLE_BOOKS_API);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        booksAdapter.clear();

        if (data != null && !data.isEmpty()) {
            booksAdapter.addAll(data);
        } else {
            Log.e(MainActivity.class.getName(), "Empty list");
        }

    }


    @Override
    public void onLoaderReset(Loader loader) {
        booksAdapter.clear();
    }
}

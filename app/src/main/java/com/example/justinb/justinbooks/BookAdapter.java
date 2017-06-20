package com.example.justinb.justinbooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jbuha on 6/19/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_information_list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.book_title);
        title.setText(book.getmTitle());

        TextView author = (TextView) convertView.findViewById(R.id.book_author);
        author.setText(book.getmAuthor());

        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating_bar);
        ratingBar.setRating((float) book.getmRating());

        return convertView;

    }
}

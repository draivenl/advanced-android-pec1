package com.draiven.pec1application;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.draiven.pec1application.model.BookModel;

public class BookDetailActivity extends AppCompatActivity {

    private BookModel.BookItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
//        TextView textId = findViewById(R.id.book_detail_id);
//        mItem = BookContent.ITEM_MAP.get(getIntent().getStringExtra("item_id"));
//        textId.setText(getIntent().getStringExtra("item_id"));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(BookDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(BookDetailFragment.ARG_ITEM_ID, 0));
            BookDetailFragment fragment = new BookDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_detail_container, fragment)
                    .commit();
        }

    }
}
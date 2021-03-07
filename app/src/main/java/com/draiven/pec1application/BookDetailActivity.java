package com.draiven.pec1application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.draiven.pec1application.model.BookContent;

public class BookDetailActivity extends AppCompatActivity {

    private BookContent.BookItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        TextView textId = findViewById(R.id.book_detail_id);
        mItem = BookContent.ITEM_MAP.get(getIntent().getStringExtra("item_id"));
        textId.setText(getIntent().getStringExtra("item_id"));

    }
}
package com.draiven.pec1application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        loadList();
    }

    private void loadList() {
        ArrayList<String> bookList = new ArrayList<>();
        bookList.add("1 Item1");
        bookList.add("2 Item2");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);

        ListView listView = (ListView) findViewById(R.id.book_list_view);
        listView.setAdapter(adapter);
    }
}
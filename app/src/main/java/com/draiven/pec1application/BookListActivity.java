package com.draiven.pec1application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.draiven.pec1application.model.BookModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        if (findViewById(R.id.book_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        recyclerView = findViewById(R.id.book_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        // loadList();
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_list, menu);
        return true;
    }

    // Collections.sort

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.sortTitle:

                Collections.sort(BookModel.ITEMS, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                            BookModel.BookItem book1 = (BookModel.BookItem) o1;
                            BookModel.BookItem book2 = (BookModel.BookItem) o2;
                        return book1.titulo.compareTo(book2.titulo);
                    }
                });
                recyclerView.getAdapter().notifyDataSetChanged();
                //adapter.notifyDatasetChanged()
                return true;
            case R.id.sortAuthor:
                Collections.sort(BookModel.ITEMS, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        BookModel.BookItem book1 = (BookModel.BookItem) o1;
                        BookModel.BookItem book2 = (BookModel.BookItem) o2;
                        return book1.autor.compareTo(book2.autor);
                    }
                });
                recyclerView.getAdapter().notifyDataSetChanged();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new BookRecyclerViewAdapter(this, BookModel.ITEMS, mTwoPane));
    }

//    private void loadList() {
//        final ArrayList<String> bookList = new ArrayList<>();
//
//        for (BookModel.BookItem bookItem:
//        BookModel.ITEMS){
//            bookList.add(bookItem.titulo);
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
//        final BookListActivity mParentActivity = this;
//        final ListView listView = findViewById(R.id.book_list_view);
//        listView.setAdapter(adapter);
//
//        // ListView Item Click Listener
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                BookModel.BookItem item = BookModel.ITEMS.get(position);
//                if (!mTwoPane){
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, BookDetailActivity.class);
//                    intent.putExtra(BookDetailFragment.ARG_ITEM_ID, item.identificador);
//
//                    context.startActivity(intent);
//                } else {
//                    Bundle arguments = new Bundle();
//                    arguments.putInt(BookDetailFragment.ARG_ITEM_ID, item.identificador);
//                    BookDetailFragment fragment = new BookDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.book_detail_container, fragment)
//                            .commit();
//                }
//
//
//            }
//
//        });
//    }

    public static class BookRecyclerViewAdapter
            extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

        private final BookListActivity mParentActivity;
        private final List<BookModel.BookItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookModel.BookItem item = (BookModel.BookItem) view.getTag();
                // BookModel.BookItem item = BookModel.ITEMS.get(position);
                if (!mTwoPane){
                    Context context = view.getContext();
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra(BookDetailFragment.ARG_ITEM_ID, item.identificador);

                    context.startActivity(intent);
                } else {
                    Bundle arguments = new Bundle();
                    arguments.putInt(BookDetailFragment.ARG_ITEM_ID, item.identificador);
                    BookDetailFragment fragment = new BookDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.book_detail_container, fragment)
                            .commit();
                }
            }
        };

        BookRecyclerViewAdapter(BookListActivity parent,
                                List<BookModel.BookItem> items,
                                boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = null;
            if (viewType == ODD) {
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content_odd, parent, false);
            } else {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_list_content, parent, false);
            }

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mTitleView.setText(String.valueOf(mValues.get(position).titulo));
            holder.mAuthorView.setText(mValues.get(position).autor);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mTitleView;
            final TextView mAuthorView;

            ViewHolder(View view) {
                super(view);
                mTitleView = (TextView) view.findViewById(R.id.id_title);
                mAuthorView = (TextView) view.findViewById(R.id.author);
            }
        }
        private final static int EVEN = 0;
        private final static int ODD = 1;
        @Override
        public int getItemViewType(int position) {
            return position%2==0 ? EVEN : ODD;
        }

    }


}
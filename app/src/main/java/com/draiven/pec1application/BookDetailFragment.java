package com.draiven.pec1application;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.draiven.pec1application.model.BookModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.DateFormat;


public class BookDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private BookModel.BookItem mItem;

    public BookDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = BookModel.ITEM_MAP.get(String.valueOf(getArguments().getInt(ARG_ITEM_ID)));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.titulo);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            android.text.format.DateFormat df = new android.text.format.DateFormat();
            // ((TextView) rootView.findViewById(R.id.book_title)).setText(mItem.titulo);
            ((TextView) rootView.findViewById(R.id.book_author)).setText(mItem.autor);
            ((TextView) rootView.findViewById(R.id.book_date)).setText(df.format("dd/MM/yyyy", mItem.fechaPublicacion));
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.descripcion);
        }
        return rootView;
    }
}
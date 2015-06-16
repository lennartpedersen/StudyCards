package com.example.tom_fire.studycards2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lennart Pedersen on 16-06-2015.
 */
public class ScreenSlidePageFragment extends Fragment{
    public static final String ARG_PAGE = "page";

    private int mPageNumber;

    public static android.support.v4.app.Fragment create(int pageNumber) {
        android.support.v4.app.Fragment fragment = new android.support.v4.app.Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public ScreenSlidePageFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.card,container,false);
        return rootView;
    }

    public int getPageNumber () { return mPageNumber;}
}

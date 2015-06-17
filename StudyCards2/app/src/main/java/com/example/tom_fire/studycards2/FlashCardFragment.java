package com.example.tom_fire.studycards2;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lennart Pedersen on 16-06-2015.
 */
public class FlashCardFragment extends Fragment {
    private TextView mTextView;
    final public DataManagement dataManagement = new DataManagement();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateQuestion();
        Log.d("as", "onActivityCreated front");
        Log.d("as",dataManagement.getQuestions().get(0));
        Log.d("as",dataManagement.getQuestions().get(1));
        Log.d("as",dataManagement.getQuestions().get(2));
    }
    void updateQuestion() {
        Log.d("as","updateQuestion");
        mTextView = (TextView) getView().findViewById(R.id.card_view);
        mTextView.setText(dataManagement.getQuestions().get(dataManagement.getmCounter()));
        Log.d("as", ""+dataManagement.getmCounter());
        Log.d("as",dataManagement.getQuestions().get(dataManagement.getmCounter()));
    }


}

package com.example.tom_fire.studycards2;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lennart Pedersen on 17-06-2015.
 */
public class DataManagement {
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    private int mCounter = 0;

    public DataManagement() {
        addQuestion("isdhfoj");
        addAnswer("iajofi");
        addQuestion("isdsd1fshfoj");
        addAnswer("iajosdgfi");
        addQuestion("i2sdsasdhfoj");
        addAnswer("iajofwer32i");
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void addQuestion(String q) {
        questions.add(q);
    }

    public void addAnswer(String a) {
        answers.add(a);
    }

    public void incCounter() {
        if(mCounter < questions.size()-1) {
            mCounter++;
            Log.d("as","mCounter = " + mCounter);
        }
    }

    public void decCounter() {
        if(mCounter > 0) {
            mCounter--;
        }
    }

    public int getmCounter() {
        return mCounter;
    }
}

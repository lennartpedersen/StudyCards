package com.example.tom_fire.studycards2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lennart Pedersen on 15-06-2015.
 */
public class CustomDialog extends Dialog implements android.view.View.OnClickListener {

    public Button confirm;
    public EditText text = (EditText) findViewById(R.id.dialogtextview);

    public CustomDialog(Activity act) {
        super(act);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Category");
        setContentView(R.layout.custom_dialog);
    }

    @Override
    public void onClick(View v) {

    }
}

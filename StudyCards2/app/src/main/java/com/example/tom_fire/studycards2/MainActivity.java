package com.example.tom_fire.studycards2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.GestureDetector.*;


public class MainActivity extends ActionBarActivity {

    public ArrayList<String> categories = new ArrayList<String>();
    private FlashCardFragment mFlashCardFragment;
    private FlashCardFragmentBack mFlashCardBackFragment;
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlashCardFragment = new FlashCardFragment();
        mFlashCardBackFragment = new FlashCardFragmentBack();

        FragmentManager frag = getFragmentManager();
        FragmentTransaction fragtrac = frag.beginTransaction();
        fragtrac.add(R.id.fragment, mFlashCardFragment);
        fragtrac.commit();

        final View view = (View) findViewById(R.id.fragment);
        view.setOnTouchListener(new OnSwipeListener(getApplicationContext()) {
            long speed = 100;
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                ObjectAnimator slideUd = ObjectAnimator.ofFloat(view, "x", -view.getWidth()).setDuration(speed);
                ObjectAnimator usynlig = ObjectAnimator.ofFloat(view, "alpha", 0f).setDuration(1);
                ObjectAnimator reset = ObjectAnimator.ofFloat(view, "x", view.getWidth()).setDuration(1);
                ObjectAnimator synlig = ObjectAnimator.ofFloat(view, "alpha", 1f).setDuration(1);
                ObjectAnimator slideInd = ObjectAnimator.ofFloat(view, "x", 0).setDuration(speed);
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(slideUd, usynlig, reset, synlig, slideInd);
                if(mShowingBack) {
                    flipCard();
                }
                set.start();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                ObjectAnimator slideUd = ObjectAnimator.ofFloat(view, "x", view.getWidth()).setDuration(speed);
                ObjectAnimator usynlig = ObjectAnimator.ofFloat(view, "alpha", 0f).setDuration(1);
                ObjectAnimator reset = ObjectAnimator.ofFloat(view, "x", -view.getWidth()).setDuration(1);
                ObjectAnimator synlig = ObjectAnimator.ofFloat(view, "alpha", 1f).setDuration(1);
                ObjectAnimator slideInd = ObjectAnimator.ofFloat(view, "x", 0).setDuration(speed);
                AnimatorSet set = new AnimatorSet();
                set.playSequentially(slideUd, usynlig, reset, synlig, slideInd);
                if(mShowingBack) {
                    flipCard();
                }
                set.start();
            }

            @Override
            public void onTap() {
                flipCard();
            }
        });

        final Button addCategory = (Button) findViewById(R.id.add_category);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"knappen virker",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("New category:");
                alertDialog.setMessage("Enter name of new category");

                final EditText input = new EditText(MainActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categories.add(input.getText().toString());
                        mFlashCardFragment.updateQuestion(input.getText().toString());
                        Toast.makeText(getApplicationContext(), input.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }

    public void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }
        mShowingBack = true;
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.card_flip_hoejre_ind, R.animator.card_flip_hoejre_ud,
                        R.animator.card_flip_venstre_ind, R.animator.card_flip_venstre_ud)
                .replace(R.id.fragment, mFlashCardBackFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

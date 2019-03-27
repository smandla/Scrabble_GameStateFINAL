package com.example.scrabble_gamestate.scrabble;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ScrabbleController implements View.OnTouchListener, View.OnClickListener {
    private ImageButton playButton = null;
    private ImageButton quitButton = null;
    private ImageButton skipButton = null;
    private ImageButton dictionaryButton = null;
    private ImageButton shuffleButton = null;
    private ImageButton exchangeButton = null;
    private TextView ourScore = null;
    private TextView opponentScore = null;
    private Spinner menu = null;
    private ImageView theirProfile = null;
    private ImageView ourProfile = null;

    public ScrabbleController(TextView ourPlayerScore, TextView theirPlayerScore ) {
        ourScore = ourPlayerScore;
        opponentScore = theirPlayerScore;
        /*theirProfile = theirProfPic;
        ourProfile = ourProfPic; */

    }
    @Override
    public void onClick(View button) {


    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int xTouch = (int)event.getX();
        int yTouch = (int)event.getY();



        return true;
    }
}

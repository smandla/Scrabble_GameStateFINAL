package com.example.scrabble_gamestate.scrabble;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.game.LocalGame;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ScrabbleMainActivity extends GameMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView ourScore = findViewById(R.id.playerScore);
        TextView opponentScore = findViewById(R.id.opponentScore);

        ScrabbleController theController = new ScrabbleController(ourScore, opponentScore);

        ImageButton swapTileButton = findViewById(R.id.swapTileButtton);
        swapTileButton.setOnClickListener(theController);

        ImageButton skipButton = findViewById(R.id.passImageButton);
        skipButton.setOnClickListener(theController);

        ImageButton shuffleTileButton = findViewById(R.id.shuffleImageButton);
        shuffleTileButton.setOnClickListener(theController);

        ImageButton dictionaryButton = findViewById(R.id.dictionaryButton);
        dictionaryButton.setOnClickListener(theController);

        ImageButton playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(theController);



    }

    public GameConfig createDefaultConfig(){

    }

    public LocalGame createLocalGame(){
        return new ScrabbleLocalGame();
    }

}

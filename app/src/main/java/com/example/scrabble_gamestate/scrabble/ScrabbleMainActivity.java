package com.example.scrabble_gamestate.scrabble;

import android.os.Bundle;

import com.example.scrabble_gamestate.R;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ScrabbleMainActivity extends GameMainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public GameConfig createDefaultConfig(){

    }

    public LocalGame createLocalGame(){
        return new ScrabbleLocalGame();
    }

}

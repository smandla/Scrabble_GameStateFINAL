package com.example.scrabble_gamestate.scrabble;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.ProxyGame;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.game.GamePlayer;

/**
 * Rules of the game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 */
public class ScrabbleRules extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrabble_rules_layout);
    }
}
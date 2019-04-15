package com.example.scrabble_gamestate.scrabble;

import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.SurfaceView;
import android.widget.Switch;

import com.example.scrabble_gamestate.game.GameComputerPlayer;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.infoMsg.NotYourTurnInfo;
import com.example.scrabble_gamestate.game.util.Tickable;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.scrabble.ScrabbleGameState;
import com.example.scrabble_gamestate.scrabble.ScrabbleLocalGame;


import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A computer-version of a counter-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2013*/
public class ScrabbleDumbComputerPlayer extends GameComputerPlayer implements Tickable {

    ArrayList<Character> letters;
    String alreadyPlayedLetter = null;
    Tile alreadyPlayedTile = null;
    boolean foundWord = false;
    int player2 = playerNum;

    // String word = read in line from dumbDictionary.txt
    String word = " ";
    ScrabbleGameState latestState;

/**
     * Constructor for objects of class CounterComputerPlayer1
     *
     * @param name the player's name*/


    public ScrabbleDumbComputerPlayer(String name) {
        // invoke superclass constructor
        super(name);

        // start the timer, ticking 20 times per second
        //getTimer().setInterval(50);
        //getTimer().start();
    }


/**
     * callback method--game's state has changed
     *
     * @param info the information (presumably containing the game's state)
     */

    @Override
    protected void receiveInfo(GameInfo info) {
        if (!(info instanceof ScrabbleGameState)) {
            return;

        }
        if(info instanceof NotYourTurnInfo){
            return;
        }
//        else {
//
           this.latestState = (ScrabbleGameState) info;
            if(latestState.getTurn()==this.playerNum) {

                //skip turn half the time
                /**if(Math.random() >= .5) {
                    SkipTurnAction skip = new SkipTurnAction(this);
                    game.sendAction(skip);//skips so we can test if we can play multiple words
                }
                else {*/
                    findLocation();
                //}


            }

    }

    public void findLocation(){

        Tile alreadyPlayedLetter = null;
        int wordLength = 0; //not counting already played tile
        String potentialWord;

        //iterate through board, starting with second col from left
        for(int col = 1; col < 14; col++){
            for (int row = 0; row < 15; row++) {


                if(latestState.getBoard()[col][row] != null){
                    alreadyPlayedLetter = latestState.getBoard()[col][row];

                    //check through subsequent rows to see how long of a word we can make
                    int offset = 1;

                    if(latestState.getBoard()[col][row -1] != null){
                        continue;
                    }

                    while(row + offset < 14 && latestState.getBoard()[col][row + offset] == null &&
                            latestState.getBoard()[col - 1][row + offset] == null &&
                            latestState.getBoard()[col + 1][row + offset] == null &&
                            latestState.getBoard()[col][(row + offset) + 1] == null){
                        wordLength++;
                        offset++; //keep incrementing so we don't keep checking the same thing
                    }

                    potentialWord = determineWord(wordLength, alreadyPlayedLetter);

                    if(potentialWord != null && alreadyPlayedLetter != null){
                        computerPlaceTiles(potentialWord, alreadyPlayedLetter);
                        return;
                    }
                    else{
                        break;
                    }

                }


            }
        }

        SkipTurnAction skip = new SkipTurnAction(this);
        game.sendAction(skip);
    }

    public String determineWord(int length, Tile alreadyPlayed){
        HashSet<String> dictionary = latestState.getDictionary();

        Iterator<String> itr = dictionary.iterator();

        String testWord;
        boolean inHand;
        boolean legalWord;

        while(itr.hasNext()){
            testWord = itr.next();

            legalWord = true;

            //make sure that the first letters match
            if(testWord.length() <= length + 1 && testWord.length() > 0){
                Log.i("dumbAI", testWord);
                Log.i("tile",Character.toString(alreadyPlayed.getTileLetter()));
                if(testWord.charAt(0) == alreadyPlayed.getTileLetter()){

                    //start at the second letter of the word, since the first is already played
                    for(int i = 1; i < testWord.length(); i++){

                        ArrayList<Tile> computerHand = latestState.getHand2();

                        inHand = false;
                        //iterate through hand, looking to see if that tile's letter matches the
                        //current letter in the string
                        for (Tile t: computerHand) {
                            if(testWord.charAt(i) == t.getTileLetter()){
                                inHand = true;

                            }
                        }

                        //can't find letter, so can't make work
                        if(inHand == false){
                            legalWord = false;
                        }
                    }
                }
                else{
                    legalWord = false;
                }
            }
            else{
                legalWord = false;
            }
            //found a word
            if(legalWord == true){
                return testWord;
            }
        }

        return null;

    }

    public void computerPlaceTiles(String toPlay, Tile alreadyPlayedTile){


        //for each letter in toPlay, look for that letter in the computer's hand
        //place that word in location of alreadyPlayedTile
        for(int i = 1; i < toPlay.length(); i++){

            ArrayList<Tile> computerHand = latestState.getHand2();

            //iterate through hand, looking to see if that tile's letter matches the current letter
            //in the string
            for (Tile t: computerHand) {
                if(toPlay.charAt(i) == t.getTileLetter()){
                    PlaceTileAction placeTileAction = new PlaceTileAction(this,
                            alreadyPlayedTile.getxCoord(), alreadyPlayedTile.getyCoord() + i, t);
                    game.sendAction(placeTileAction);
                    break;
                }
            }
        }

        //play the word
        PlayWordAction play = new PlayWordAction(this);
        game.sendAction(play);
    }
}




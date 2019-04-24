package com.example.scrabble_gamestate.scrabble;

import android.util.Log;

import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.infoMsg.NotYourTurnInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *Smart computer player for the Scrabble game.
 * The computer only plays words vertically, and only starting with the letter it finds, instead of
 * trying to make words with a found letter in any place in a them.
 *
 *  @author Sydney Wells
 *  @author Sarah Bunger
 *  @author Kavya Mandla
 *  @author Meredith Marcinko
 *  @version February 2019
 */
public class ScrabbleSmartComputerPlayer extends ScrabbleDumbComputerPlayer {
    ArrayList<Character> letters;
    String alreadyPlayedLetter = null;
    Tile alreadyPlayedTile = null;
    boolean foundWord = false;
    int player2 = playerNum;

    String word = " ";
    ScrabbleGameState latestState;

    /**
     * Constructor
     *
     * @param name the player's name
     */
    public ScrabbleSmartComputerPlayer(String name) {
        // invoke superclass constructor
        super(name);

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

        this.latestState = (ScrabbleGameState) info;
        if(latestState.getTurn()==this.playerNum) {
            findLocation();
        }

    }

    /**
     * A helper function that finds a viable location to play a vertical word
     */
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


    /**
     * Helper method for determining a word for the AI to play, based on the letters it has and
     * the letter it has chosen to build a word off of, as well as the amount of space where letters
     * can legally be placed.
     *
     * @param length  the length we found for a word
     * @param alreadyPlayed the tile that was already played, which we will try to build a word off
     * @return the word to play
     */
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

    /** helper function to make the computer place the tiles for the word it found, then play the
     * word
     *
     * @param toPlay  the word we are attempting to play
     * @param alreadyPlayedTile  the tile we are playing off of
     */
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
        PlayWordActionComputer play = new PlayWordActionComputer(this);
        game.sendAction(play);
    }
}



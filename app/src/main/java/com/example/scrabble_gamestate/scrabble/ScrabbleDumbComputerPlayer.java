package com.example.scrabble_gamestate.scrabble;

import android.util.Log;
import com.example.scrabble_gamestate.game.GameComputerPlayer;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.infoMsg.NotYourTurnInfo;
import com.example.scrabble_gamestate.game.util.Tickable;
import com.example.scrabble_gamestate.game.Tile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A Dumb AI that plays words 50% of the time and skips its turn the other 50%.
 * The computer only plays words vertically, and only starting with the letter it finds, instead of
 * trying to make words with a found letter in any place in a them.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 * */
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
     * Constructor
     *
     * @param name the player's name
     */
    public ScrabbleDumbComputerPlayer(String name) {
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

            //skip turn half the time
            if(Math.random() >= .5) {
                SkipTurnAction skip = new SkipTurnAction(this);
                game.sendAction(skip);
            }
            else {
                findLocation();
            }

        }
    }


    /**
     * A helper function that finds a viable location to play a vertical word
     */
    public void findLocation(){

        /**
         * External Citation
         *
         * Date: 13 April 2019
         * Problem: Struggled to understand how to implement such a complex algorithm; would get
         * caught in an infinite loop of not knowing how to gather the right information at the
         * right time.
         * Resource: Jason Twigg, tutor
         * Solution: Jason walked us through a basic idea of what the code would look like, and
         * assisted with further questions for implementation.
         */

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

                    //we won't play words at the edges of the board
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

        /**
         * External Citation
         * Date: 14 April 2019
         * Problem: AI would simply stop playing words after a certain point.
         * Resource: Dr. Tribelhorn
         * Solution: Various additions of if/else statements for determining if a legal word
         * actually exists.
         */

        HashSet<String> dictionary = latestState.getDictionary();

        Iterator<String> itr = dictionary.iterator();

        String testWord;
        boolean inHand;
        boolean legalWord;

        while(itr.hasNext()){
            testWord = itr.next();

            legalWord = true;

            //make sure we don't try to play something longer than we have room for
            //also, limit the length of words that can be played since it's "dumb"
            if(testWord.length() <= length + 1 && testWord.length() > 0 && testWord.length() <= 4){

                //make sure that the first letters match
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
        PlayWordAction play = new PlayWordAction(this);
        game.sendAction(play);
    }
}




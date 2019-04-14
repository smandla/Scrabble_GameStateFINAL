package com.example.scrabble_gamestate.game.infoMsg;

import com.example.scrabble_gamestate.scrabble.ScrabbleGameState;


/**
 * The a message from the game to a player that the move just attempted
 * was illegal. If the word they want to play is not a word
 *
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class IllegalMoveInfo extends GameInfo {

    private ScrabbleGameState gameState;

    private int turnId;

    // to satisfy Serializable interface
    private static final long serialVersionUID = 7165334825841353190L;

    protected String checkifValid() {
        if (gameState.playWord(turnId) != true) {
            return "Your word is not a word.";
        } else {
            return null;
        }
    }

}


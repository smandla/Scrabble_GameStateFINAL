package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.LocalGame;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;
import android.util.Log;

/**
 *
 */
public class ScrabbleLocalGame extends LocalGame {

    // When a scrabble game is played. Both players are trying to use up all their tiles and get
    // most points based on the words they play
    public static final int TARGET_MAGNITUDE = 10;

    // the game's state
    private ScrabbleGameState gameState;

    /**
     * can this player move
     *
     * @return
     * 		true if the index param is the same as the turnID in the game state and false if not
     */
    @Override
    protected boolean canMove(int playerIdx) {
        if(playerIdx != gameState.getTurn())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * This ctor should be called when a new scrabble game is started
     */
    public ScrabbleLocalGame() {
        // initialize the game state, with all default start values
        this.gameState = new ScrabbleGameState();
    }

    /**
     * Makes a move on behalf of a player
     *
     * @param action The move that the player has sent to the game
     * @return Indicates if move was legal or not
     */
    @Override
    protected boolean makeMove(GameAction action) {

        if (action instanceof CheckDictionaryAction) {

            // cast so that we Java knows it's a CheckDictionaryAction
            CheckDictionaryAction cma = (CheckDictionaryAction) action;

            // Update the counter values based upon the action
            int result = gameState.getCounter() + (cma.isPlus() ? 1 : -1);
            gameState.setCounter(result);

            // denote that this was a legal/successful move
            return true;
        }
        else {
            // denote that this was an illegal move
            return false;
        }
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // this is a perfect-information game, so we'll make a
        // complete copy of the state to send to the player
        p.sendInfo(new CounterState(gameState));

    }//sendUpdatedSate

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {

        // get the value of the counter
        int counterVal = this.gameState.getCounter();

        if (counterVal >= TARGET_MAGNITUDE) {
            // counter has reached target magnitude, so return message that
            // player 0 has won.
            return playerNames[0]+" has won.";
        }
        else if (counterVal <= -TARGET_MAGNITUDE) {
            // counter has reached negative of target magnitude; if there
            // is a second player, return message that this player has won,
            // otherwise that the first player has lost
            if (playerNames.length >= 2) {
                return playerNames[1]+" has won.";
            }
            else {
                return playerNames[0]+" has lost.";
            }
        }
        else {
            // game is still between the two limit: return null, as the game
            // is not yet over
            return null;
        }
    }

}// class ScrabbleLocalGame

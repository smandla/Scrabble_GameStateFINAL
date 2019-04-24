package com.example.scrabble_gamestate.scrabble;

import android.media.MediaPlayer;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.LocalGame;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

import java.util.HashSet;

/**
 * Class is the Local Game of the Scrabble Game.
 * All the information that is being played at the moment
 * will be passed through here.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */

public class ScrabbleLocalGame extends LocalGame {

    // the game's state
    private ScrabbleGameState gameState;
    private Tile selectedTile;
    private int turnId;

    private MediaPlayer mediaPlayer;

    /**
     * constructor
     */
    public ScrabbleLocalGame() {
        // initialize the game state, with all default start values
        this.gameState = new ScrabbleGameState();
    }

    /**
     * can this player move
     *
     * @return
     * 		true if the index param is the same as the turnID in the game state and false if not
     */
    @Override
    public boolean canMove(int playerIdx) {
        if(gameState.getTurn() == playerIdx)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //setter method
    public void setDictionary(HashSet<String> sentDict){
        gameState.setDictionary(sentDict);
    }

    public void setHumanDictionary(HashSet<String> sentDict){
        gameState.setHumanDictionary(sentDict);
    }



    /**
     * Makes a move on behalf of a player, calls the boolean method from ScrabbleGameState
     *
     * @param action The move that the player has sent to the game
     * @return Indicates if move was legal or not
     */
    @Override
    protected boolean makeMove(GameAction action) {

        //determine what type of action it is and call the appropriate method
        if(action instanceof ExchangeTileAction){
            gameState.exchangeTile(gameState.getTurn(), ((ExchangeTileAction) action).getPos());
            return true;
        }
        else if( action instanceof PlayWordAction)
        {
            gameState.playWord(gameState.getTurn());
            return true;
        }
        else if( action instanceof ShuffleTileAction)
        {
            gameState.shuffleTiles(gameState.getTurn());
            return true;
        }
        else if( action instanceof SkipTurnAction)
        {
            gameState.skipTurn(gameState.getTurn());
            return true;
        }

        else if(action instanceof PlaceTileAction){
            gameState.placeTile(gameState.getTurn(), ((PlaceTileAction) action).getX(),
                    ((PlaceTileAction) action).getY(), ((PlaceTileAction) action).getTile());
            return true;
        }
        else if( action instanceof RecallTilesAction)
        {
            gameState.recallTiles(gameState.getTurn());
            return true;
        }
        else if( action instanceof QuitGameAction)
        {
            gameState.quitGame();
            return true;
        }
        else if( action instanceof PlayWordActionComputer)
        {
            gameState.playWordComputer(gameState.getTurn());
            return true;
        }
        //else if
        return true;
    }//makeMove

    /**
     * sends the updated state to the given player and makes a copy of the appropriate hand,
     * and places dummy tiles in the other player's hand to prevent cheating
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //if there is no state to send, ignore
        if (gameState == null) {
            gameState = new ScrabbleGameState();
        }

        ScrabbleGameState stateForPlayer = new ScrabbleGameState(gameState); // copy of state

        stateForPlayer.preventCheating();

        // send the modified copy of the state to the player
        p.sendInfo(stateForPlayer);

    }//sendUpdatedSate

    /**
     * Check if the word is valid. Return a string that tells
     * the player that their word is invalid. If the word is valid,
     * return null
     *
     * @return
     * 		a message that tells the player if the word is invalid, or null if the
     * 		word is valid
     */

    protected String checkifValid() {

        if(gameState.playWord(turnId) != true) {
            return "Your word is not a word.";
        }
        else {
             return null;
        }
    }

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
        if(gameState.getTileBag().size() == 0) {

            //even if the tile bag is empty, we can still play if both people have tiles in hand
            if (gameState.getHand1().size() > 0 && gameState.getHand2().size() > 0) {
                //both players still have tiles in their hands
                return null;
            }
            else {
                //at least one of the players has run out of tiles

                //whichever player has a larger score wins
                if (gameState.getPlayerZeroScore() > gameState.getPlayerOneScore()) {
                    return playerNames[0] + " has won.";
                }
                else if (gameState.getPlayerZeroScore() < gameState.getPlayerOneScore()) {
                    return playerNames[1] + " has won.";
                }
                else {
                    //account for the unusual case in which they have the same score
                    return "Tie!";
                }
            }
        }

        return null;
    }

}// class ScrabbleLocalGame

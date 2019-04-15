package com.example.scrabble_gamestate.scrabble;

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
 * @author Kavva Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */

public class ScrabbleLocalGame extends LocalGame {

    // the game's state
    private ScrabbleGameState gameState;
    private Tile selectedTile;
    private int turnId;

    /**
     * This actor should be called when a new scrabble game is started
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

    public void setDictionary(HashSet<String> sentDict){
        gameState.setDictionary(sentDict);
    }



    /**
     * Makes a move on behalf of a player, calls the boolean method from ScrabbleGameState
     *
     * @param action The move that the player has sent to the game
     * @return Indicates if move was legal or not
     */
    @Override
    protected boolean makeMove(GameAction action) {

        if (action instanceof CheckDictionaryAction) {

            // cast so that Java knows it's a CheckDictionaryAction
            CheckDictionaryAction cda = (CheckDictionaryAction) action;
            gameState.checkDictionary(gameState.getTurn());

            //TODO after alpha
            // Update the counter values based upon the action
            //int result = gameState.getCounter() + (cma.isPlus() ? 1 : -1);
            //gameState.setCounter(result);

            // denote that this was a legal/successful move
            return true;
        }
        else if(action instanceof ExchangeTileAction){
            gameState.exchangeTile(gameState.getTurn(), gameState.getPositionInHand());
            //TODO after alpha
            return false;
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
        //else if
        return true; //placeholder
        //TODO make recall tiles button and method
    }//makeMove

    /**
     * sends the updated state to the given player and makes a copy of the appropriate hand,
     * and nulls out all the cards except the top card
     * in the middle deck, since that's the only one they can "see"
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // if there is no state to send, ignore
        if (gameState == null) {
            gameState = new ScrabbleGameState();
        }

        //TODO implement how to copy without letting players know each others
        //aka make a tiles that aren't real tiles and fill the opponents hand with as many of those
        //as they had of real tiles

        // make a copy of the state; null out all cards except for the
        // top card in the middle deck
        ScrabbleGameState stateForPlayer = new ScrabbleGameState(gameState); // copy of state
        //TODO read this method
        //stateForPlayer.preventCheating(); // put nulls except for visible card

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

        if(gameState.getHand1().size() > 0 && gameState.getHand2().size() > 0){
            //both players still have tiles in their hands
            //TODO figure out way to track # consecutive skips (for after alpha)

            /*if(player1 skipped multiple times in a row){
                return playerNames[1]+" has won."; //player 1 forfeits
            }
            else if(player2 skipped multiple times in a row){
                return playerNames[0]+" has won."; //player 2 forfeits
            }
            else{
                return null; //nobody has forfeited yet and tiles remain
            }*/
            return null; //placeholder
        }
        else{
            //at least one of the players has run out of tiles

            //whichever player has a larger score wins
            if(gameState.getPlayerZeroScore() > gameState.getPlayerOneScore()){
                return playerNames[0]+" has won.";
            }
            else if (gameState.getPlayerZeroScore() < gameState.getPlayerOneScore()){
                return playerNames[1]+" has won.";
            }
            else{
                //account for the unusual case in which they have the same score
                return "Tie!";
            }
        }
    }

}// class ScrabbleLocalGame

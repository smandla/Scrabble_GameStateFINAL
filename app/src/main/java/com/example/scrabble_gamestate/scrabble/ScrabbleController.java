package com.example.scrabble_gamestate.scrabble;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.Tile;
/**
 * This is the Controller class which has eventhandling
 * methods for buttons and other touch events on the GUI
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavva Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */

public class ScrabbleController implements View.OnTouchListener, View.OnClickListener
{

    private TextView ourScore;
    private TextView opponentScore;
    private TextView playerTurn;


    //menu with quit and save buttons
    private Spinner menu = null;
    private ImageView theirProfile = null;
    private ImageView ourProfile = null;
    //the gamestate that we are changing/making decisions based on
    private ScrabbleGameState ourGameState;
    //the image button in our hand which we've touched
    private View selectedView = null;
    private GameHumanPlayer ourPlayer = null;
    private Game ourGame;

    /**
     * constructor
     * @param ourPlayerScore
     * 		the score for our player
     * @param theirPlayerScore
     *
     * @param whichPlayerTurn
     *
     * @param theGameState
     *      the state of the current game
     * @param theGame
     *      the current game being played
     * @param  aPlayer
     *      the human player we want to send stuff to
     */
    public ScrabbleController(TextView ourPlayerScore,TextView theirPlayerScore,
                              TextView whichPlayerTurn,
                              ScrabbleGameState theGameState,
                              Game theGame, GameHumanPlayer aPlayer ) {
        ourScore = ourPlayerScore;
        opponentScore = theirPlayerScore;
        playerTurn = whichPlayerTurn;
        ourGameState = theGameState;
        ourGame = theGame;
        ourPlayer = aPlayer;

        /*theirProfile = theirProfPic;
        ourProfile = ourProfPic; */

    }//ScrabbleController

    //setter for the game state
    public void setUpdatedState(ScrabbleGameState state) {
        ourGameState = state;
    }

    public void setGame(Game local) {
        ourGame = local;
    }//setter for the game

    /**
     * OnClick method that sets a view as selected or sends an action associated with a button
     * @param button
     * 		the button the player has pressed
     */
    @Override
    public void onClick(View button) {

        if(ourGameState == null || ourGame == null){
            return;
        }

        switch (button.getId()) {

            //determine if any of the tiles have been touched
            case R.id.tileOneButton:

                /*if(selectedView.getId() == R.id.swapTileButtton){
                    ExchangeTileAction swapTile = new ExchangeTileAction(ourPlayer, 0);
                    ourGame.sendAction(swapTile);
                }*/

                selectedView = button;
                break;

            case R.id.tileTwoButton:


                selectedView = button;
                break;

            case R.id.tileThreeButton:

                selectedView = button;
                break;

            case R.id.tileFourButton:

                selectedView = button;
                break;

            case R.id.tileFiveButton:


                selectedView = button;
                break;

            case R.id.tileSixButton:

                selectedView = button;
                break;

            case R.id.tileSevenButton:

                selectedView = button;
                break;

            //the next cases check if its a certain action type and then send that action
            case R.id.playButton:

                PlayWordAction playAction = new PlayWordAction(ourPlayer);
                ourGame.sendAction(playAction);

                break;

            case R.id.recallTiles:

                RecallTilesAction recall = new RecallTilesAction(ourPlayer);
                ourGame.sendAction(recall);
                break;

            case R.id.passImageButton:

                SkipTurnAction skipAction = new SkipTurnAction(ourPlayer);
                ourGame.sendAction(skipAction);
                break;

            case R.id.swapTileButtton:

                selectedView = button;

                break;


            case R.id.shuffleImageButton:

                ShuffleTileAction shuffleAction = new ShuffleTileAction(ourPlayer);
                ourGame.sendAction(shuffleAction);
                break;

            case R.id.dictionaryButton:

                CheckDictionaryAction dictionaryAction = new CheckDictionaryAction(ourPlayer);
                ourGame.sendAction(dictionaryAction);
                break;

            default:
                break;

        }
    }//OnClick

    /**
     * constructor
     * @param v
     * 		the view which has been touched
     * @param event
     *      the motionEvent that occurs when you touch a view
     */
    @Override
    public boolean onTouch (View v, MotionEvent event){
        //if nothing's there, return
        if(ourGameState == null || ourGame == null){
            return true;
        }
        //finds out where is being touched
        int xTouch = (int) event.getX();
        int yTouch = (int) event.getY();
            //if we havent already touched a hand tile, return
        if(selectedView == null || v == null){
            return true;
        }

            Log.i("controller", "surface view " + v);

        //figuring out which tile to place
        Tile t = null;
        switch (selectedView.getId()) {

            //must say getHand1
            case R.id.tileOneButton:
                t = ourGameState.getHand1().get(0);
                break;

            case R.id.tileTwoButton:
                t = ourGameState.getHand1().get(1);
                break;

            case R.id.tileThreeButton:
                t = ourGameState.getHand1().get(2);
                break;

            case R.id.tileFourButton:
                t = ourGameState.getHand1().get(3);
                break;

            case R.id.tileFiveButton:
                t = ourGameState.getHand1().get(4);
                break;

            case R.id.tileSixButton:
                t = ourGameState.getHand1().get(5);
                break;

                case R.id.tileSevenButton:
                    t = ourGameState.getHand1().get(6);
                    break;

            default:
                break;
        }

        //based on location of touch, round to nearest multiple of cell size and put tile there
        xTouch = xTouch/ScrabbleSurfaceView.TILE_WIDTH_AND_HEIGHT;
        yTouch = yTouch/ScrabbleSurfaceView.TILE_WIDTH_AND_HEIGHT;

        //tell the game we want to place a tile
        PlaceTileAction placeTileAction = new PlaceTileAction(ourPlayer, xTouch, yTouch, t);
        ourGame.sendAction(placeTileAction);

        this.selectedView = null;//resets selected tile button
        return true;
    }//OnTouch
}

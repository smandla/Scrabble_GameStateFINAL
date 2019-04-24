package com.example.scrabble_gamestate.scrabble;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.Tile;
/**
 *Controller for the Scrabble Game.
 *
 *  @author Sydney Wells
 *  @author Sarah Bunger
 *  @author Kavya Mandla
 *  @author Meredith Marcinko
 *  @version February 2019
 */
public class ScrabbleController implements View.OnTouchListener, View.OnClickListener
{
    //textviews for scores and turns
    private TextView ourScore;
    private TextView opponentScore;
    private TextView playerTurn;

    //imageviews for profiles
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
     * @param ourPlayerScore  the score for our player
     * @param theirPlayerScore  the opponent's score
     * @param whichPlayerTurn  the text view indicating whose turn it is
     * @param theGameState  the state of the current game
     * @param theGame  the current game being played
     * @param  aPlayer the human player we want to send stuff to
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

    }//ScrabbleController

    //setter for the game state
    public void setUpdatedState(ScrabbleGameState state) {
        ourGameState = state;
    }

    //setter for the game
    public void setGame(Game local) {
        ourGame = local;
    }

    /**
     * OnClick method that sets a view as selected or sends an action associated with a button
     *
     * @param button  the button the player has pressed
     *
     * @return true to indicate we handled the event
     */
    @Override
    public void onClick(View button) {

        if(ourGameState == null || ourGame == null){
            return;
        }

        switch (button.getId()) {

            //determine if any of the tiles have been touched
            case R.id.tileOneButton:

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

                if(selectedView != null)
                {
                    int index = 0;
                    //check which button has been selected and set that button's place in the
                    //array to the index
                    switch( selectedView.getId())
                    {
                        case R.id.tileOneButton:
                            index = 0;
                            break;

                        case R.id.tileTwoButton:
                            index = 1;
                            break;

                        case R.id.tileThreeButton:
                            index = 2;
                            break;

                        case R.id.tileFourButton:
                            index = 3;
                            break;

                        case R.id.tileFiveButton:
                            index = 4;
                            break;

                        case R.id.tileSixButton:
                            index = 5;
                            break;

                        case R.id.tileSevenButton:
                            index = 6;
                            break;

                        default:
                            break;
                    }
                    ExchangeTileAction swapTile = new ExchangeTileAction(ourPlayer,index);
                    ourGame.sendAction(swapTile);
                }
                break;

            case R.id.shuffleImageButton:

                ShuffleTileAction shuffleAction = new ShuffleTileAction(ourPlayer);
                ourGame.sendAction(shuffleAction);
                break;

            case R.id.QuitGame:
                QuitGameAction quitGameAction = new QuitGameAction(ourPlayer);
                ourGame.sendAction(quitGameAction);
                break;

            default:
                break;

        }
    }//OnClick

    /**
     * constructor
     * @param v  the view which has been touched
     * @param event  the motionEvent that occurs when you touch a view
     *
     * @return true to indicate that we handled the event
     */
    @Override
    public boolean onTouch (View v, MotionEvent event){
        //if empty, return true
        if(ourGameState == null || ourGame == null){
            return true;
        }
        //finds out where is being touched
        int xTouch = (int) event.getX();
        int yTouch = (int) event.getY();

        //if we haven't already touched a hand tile, return
        if(selectedView == null || v == null){
            return true;
        }

        //figuring out which tile to place
        Tile t = null;
        switch (selectedView.getId()) {

            //must say getHand1
            case R.id.tileOneButton:
                t = ourGameState.getHandCurrent().get(0);
                break;

            case R.id.tileTwoButton:
                t = ourGameState.getHandCurrent().get(1);
                break;

            case R.id.tileThreeButton:
                t = ourGameState.getHandCurrent().get(2);
                break;

            case R.id.tileFourButton:
                t = ourGameState.getHandCurrent().get(3);
                break;

            case R.id.tileFiveButton:
                t = ourGameState.getHandCurrent().get(4);
                break;

            case R.id.tileSixButton:
                t = ourGameState.getHandCurrent().get(5);
                break;

            case R.id.tileSevenButton:
                 t = ourGameState.getHandCurrent().get(6);
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

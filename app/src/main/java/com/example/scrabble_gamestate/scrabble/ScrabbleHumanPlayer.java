package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.Board;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;

import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 *
 */
public class ScrabbleHumanPlayer extends GameHumanPlayer {

    /* instance variables */

    // The TextView the displays the current counter value
    private TextView counterValueTextView;

    private TextView ourScore;
    private TextView opponentScore;

    private ScrabbleController theController;

    private ImageButton swapTileButton;
    private ImageButton skipButton;
    private ImageButton shuffleTileButton;
    private ImageButton dictionaryButton;
    private ImageButton playButton;


    private ImageButton tileOneButton;
    private ImageButton tileTwoButton;
    private ImageButton tileThreeButton;
    private ImageButton tileFourButton;
    private ImageButton tileFiveButton;
    private ImageButton tileSixButton;
    private ImageButton tileSevenButton;
    // the most recent game state, as given to us by the ScrabbleLocalGame
    private ScrabbleGameState state;

    // the android activity that we are running
    private GameMainActivity myActivity;

    private Board surface;

    //TODO for after alpha, deal with the unusual case where players can't get rid of their letters
    //bool skipped  start it out as false; everytime they skip, check to see if true (if so, forfeit); pop up yes/no
    //dialog asking if they actually want to forfeit, then send a quitgameaction instead

    /**
     * constructor
     * @param name
     * 		the player's name
     */
    public ScrabbleHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * sets the counter value in the text view
     */
    protected void updateDisplay() {

        // set the text in the appropriate widget
        //counterValueTextView.setText("" + state.getCounter());
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a ScrabbleGameState message
        if (!(info instanceof ScrabbleGameState)) return;

        // update our state; then update the display
        this.state = (ScrabbleGameState) info;
        updateDisplay();
    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        ourScore = activity.findViewById(R.id.playerScore);
        opponentScore = activity.findViewById(R.id.opponentScore);

        theController = new ScrabbleController(ourScore, opponentScore, state);

        //sets the listeners for the gameplay buttons
        swapTileButton = activity.findViewById(R.id.swapTileButtton);
        swapTileButton.setOnClickListener(theController);

        skipButton = activity.findViewById(R.id.passImageButton);
        skipButton.setOnClickListener(theController);

        shuffleTileButton = activity.findViewById(R.id.shuffleImageButton);
        shuffleTileButton.setOnClickListener(theController);

        dictionaryButton = activity.findViewById(R.id.dictionaryButton);
        dictionaryButton.setOnClickListener(theController);

        playButton = activity.findViewById(R.id.playButton);
        playButton.setOnClickListener(theController);

        //sets the listeners for the player hand tiles for drag and drop
        tileOneButton = activity.findViewById(R.id.tileOneButton);
        tileOneButton.setOnDragListener(theController);

        tileTwoButton = activity.findViewById(R.id.tileTwoButton);
        tileTwoButton.setOnDragListener(theController);

        tileThreeButton = activity.findViewById(R.id.tileThreeButton);
        tileThreeButton.setOnDragListener(theController);

        tileFourButton = activity.findViewById(R.id.tileFourButton);
        tileFourButton.setOnDragListener(theController);

        tileFiveButton = activity.findViewById(R.id.tileFiveButton);
        tileFiveButton.setOnDragListener(theController);

        tileSixButton = activity.findViewById(R.id.tileSixButton);
        tileSixButton.setOnDragListener(theController);

        tileSevenButton = activity.findViewById(R.id.tileSevenButton);
        tileSevenButton.setOnDragListener(theController);

        // remember the field that we update to display the counter's value
        /*this.counterValueTextView =
                (TextView) activity.findViewById(R.id.counterValueTextView);*/

        surface = myActivity.findViewById(R.id.surfaceView);
        //setContentView(R.layout.activity_main);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
    }

}// class CounterHumanPlayer



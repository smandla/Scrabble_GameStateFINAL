package com.example.scrabble_gamestate.scrabble;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.GameMainActivity;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.LocalGame;

/**
 *This is the human player for the Scrabble Game. It makes sure the current player gets the info
 * that they need to see, and updates the GUI for that player in accordance with the actions the
 * human player takes.
 *
 *  @author Sydney Wells
 *  @author Sarah Bunger
 *  @author Kavya Mandla
 *  @author Meredith Marcinko
 *  @version February 2019
 */
public class ScrabbleHumanPlayer extends GameHumanPlayer {

    /* instance variables */


    private TextView ourScore;
    private TextView opponentScore;
    private TextView playerTurn;
    private TextView tileBag;

    //private ScrabbleRules scrabbleRulesButton;

    private ScrabbleController theController;

    //buttons for various actions
    private ImageButton swapTileButton;
    private ImageButton skipButton;
    private ImageButton shuffleTileButton;
    private ImageButton playButton;

    //buttons that make up a player's hand
    private ImageButton tileOneButton;
    private ImageButton tileTwoButton;
    private ImageButton tileThreeButton;
    private ImageButton tileFourButton;
    private ImageButton tileFiveButton;
    private ImageButton tileSixButton;
    private ImageButton tileSevenButton;

    private Button recallButton;
    private Button quitGameButton;
    private Button rulesButton;
    private Button newGameButton;
    // the most recent game state, as given to us by the ScrabbleLocalGame
    private ScrabbleGameState state;


    // the android activity that we are running
    private GameMainActivity myActivity;
    //the surfaceView we're drawing on
    private ScrabbleSurfaceView surface;

    MediaPlayer mediaPlayer;
    private AlertDialog dialog;
    ScrabbleGameState latestState = new ScrabbleGameState();

    /**
     * constructor
     * @param name
     * 		the player's name
     */
    public ScrabbleHumanPlayer(String name) {
        super(name);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    @Override
    protected void initAfterReady() {
        surface.setGame(game);//tells the surface what game we're playing
    }
    //note: we went into Kearney's office hours to get help with the logic of this function

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view hierarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    public GameMainActivity getActivity(){ return myActivity;}

    /**
     * displays the current player's hand to that player
     */
    protected void updateDisplay() {

        /**
         * External Citation
         * Date: 10 March 2019
         * Reason: Struggled to understand how to update GUI to show the human player's hand
         * Resource: Kyle Kearney, lab professor
         * Solution: He helped us to understand what was going on in the game framework to get this
         * the GUI to update.
         */
        //updates the gui to match the current turn and score
        if(state.getTurn() == 0) {
            this.playerTurn.setText("It is Player 1's Turn");
        }
        else
        {
            this.playerTurn.setText("It is Player 2's Turn");
        }


        this.ourScore.setText("" + state.getPlayerZeroScore());
        this.opponentScore.setText("" + state.getPlayerOneScore());
        //updates gui to show current size of tile bag
        this.tileBag.setText("" + state.getTileBag().size());

        ImageButton[] buttons = {tileOneButton, tileTwoButton, tileThreeButton, tileFourButton,
                tileFiveButton, tileSixButton, tileSevenButton};
        //updates the image resources in the hand to match the value in the tile array
        //make each image button look like the tiles in the human player's hand
            for (Tile t : state.getHandCurrent()) {
                int androidId = t.getAndroidId();
                int index = state.getHandCurrent().indexOf(t);
                buttons[index].setImageResource(androidId);
            }

        //for each button...
        for(int i = 0; i < 7; i++){
            //if i is less than the length of hand,
            int handLength = state.getHandCurrent().size();
            if(i < handLength){
                //find the corresponding thing in the hand and set the button to that
                Tile correspondingTile = state.getHandCurrent().get(i);
                int androidId = correspondingTile.getAndroidId();
                buttons[i].setImageResource(androidId);
            }
            else{
                buttons[i].setImageResource(R.drawable.empty_spot_in_hand_indicator);
            }
        }
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
        if (!(info instanceof ScrabbleGameState)) {return;}
        else if( surface == null)
        {
            return;
        }

        // update our state; then update the display
        this.state = (ScrabbleGameState) info;

        theController.setUpdatedState(this.state);//makes sure the state isn't null
        // (in theory, at least)
        theController.setGame(this.game);
       // scrabbleRulesButton.setGame(this.game);



        updateDisplay();

        surface.setState((ScrabbleGameState) info);//makes sure the state isn't null
        surface.invalidate();//updates the gui view

    }//note: we used Meredith's Uno game from last semester as an example and reference


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

        //music still works despite error being shown 
        mediaPlayer = MediaPlayer.create(activity.getApplicationContext(), R.raw.background_music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);//makes the music repeat

        surface = myActivity.findViewById(R.id.surfaceView);//tells the surface what activity to
        // look for

        if (state != null) {
            receiveInfo(state);
        }

        ourScore = activity.findViewById(R.id.playerScore);
        opponentScore = activity.findViewById(R.id.opponentScore);
        playerTurn = activity.findViewById(R.id.whosTurn);
        tileBag = activity.findViewById(R.id.tilesRemainingText);

        theController = new ScrabbleController(ourScore, opponentScore, playerTurn , state, game,
                this );

        //sets the listeners for the gameplay buttons
        swapTileButton = activity.findViewById(R.id.swapTileButtton);
        swapTileButton.setOnClickListener(theController);

        skipButton = activity.findViewById(R.id.passImageButton);
        skipButton.setOnClickListener(theController);

        shuffleTileButton = activity.findViewById(R.id.shuffleImageButton);
        shuffleTileButton.setOnClickListener(theController);

        playButton = activity.findViewById(R.id.playButton);
        playButton.setOnClickListener(theController);

        //sets the listeners for the player hand tiles for onClick
        tileOneButton = activity.findViewById(R.id.tileOneButton);
        tileOneButton.setOnClickListener(theController);

        tileTwoButton = activity.findViewById(R.id.tileTwoButton);
        tileTwoButton.setOnClickListener(theController);

        tileThreeButton = activity.findViewById(R.id.tileThreeButton);
        tileThreeButton.setOnClickListener(theController);

        tileFourButton = activity.findViewById(R.id.tileFourButton);
        tileFourButton.setOnClickListener(theController);

        tileFiveButton = activity.findViewById(R.id.tileFiveButton);
        tileFiveButton.setOnClickListener(theController);

        tileSixButton = activity.findViewById(R.id.tileSixButton);
        tileSixButton.setOnClickListener(theController);

        tileSevenButton = activity.findViewById(R.id.tileSevenButton);
        tileSevenButton.setOnClickListener(theController);

        recallButton = activity.findViewById(R.id.recallTiles);
        recallButton.setOnClickListener(theController);

        quitGameButton = activity.findViewById(R.id.QuitGame);
        quitGameButton.setOnClickListener(theController);

        surface = myActivity.findViewById(R.id.surfaceView);
        surface.setOnTouchListener(theController);


    }


}// class ScrabbleHumanPlayer



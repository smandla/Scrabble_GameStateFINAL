package com.example.scrabble_gamestate.scrabble;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Game;
import com.example.scrabble_gamestate.game.GameHumanPlayer;
import com.example.scrabble_gamestate.game.GameMainActivity;

public class ScrabbleController implements View.OnTouchListener, View.OnClickListener
{

    private TextView ourScore;
    private TextView opponentScore;
    private Spinner menu = null;
    private ImageView theirProfile = null;
    private ImageView ourProfile = null;
    private ScrabbleGameState ourGameState;
    private View selectedView = null;
    private GameHumanPlayer ourPlayer = null;
    private Game ourGame;

    public ScrabbleController(TextView ourPlayerScore,TextView theirPlayerScore,
                              ScrabbleGameState theGameState,
                              Game theGame, GameHumanPlayer aPlayer ) {
        ourScore = ourPlayerScore;
        opponentScore = theirPlayerScore;
        ourGameState = theGameState;
        ourGame = theGame;
        ourPlayer = aPlayer;

        /*theirProfile = theirProfPic;
        ourProfile = ourProfPic; */

    }

    public void setUpdatedState(ScrabbleGameState state) {
        ourGameState = state;
    }
    @Override
    public void onClick(View button) {

        switch (button.getId()) {

            //have a case for each tile button to see if its been clicked
            //set recentlyClicked instance var to thatt button
            //then onTouvh handles placing of it?

            //what if make tiles just images? then just onTouch?

            case R.id.playButton:
                PlayWordAction playAction = new PlayWordAction(ourPlayer);
                ourGame.sendAction(playAction);
                //ourGameState.playWord(ourGameState.getTurn());
                if(ourPlayer.getPlayerNum() == 0) {
                    this.ourScore.setText("" + ourGameState.getPlayerOneScore());
                    this.opponentScore.setText("" + ourGameState.getPlayerTwoScore());
                }
                else
                {
                    this.ourScore.setText("" + ourGameState.getPlayerTwoScore());
                    this.opponentScore.setText("" + ourGameState.getPlayerOneScore());
                }
                break;

            case R.id.passImageButton:
                SkipTurnAction skipAction = new SkipTurnAction(ourPlayer);
                ourGame.sendAction(skipAction);
                //ourGameState.skipTurn(ourGameState.getTurn());
                break;

            case R.id.swapTileButtton:
                if(selectedView.isSelected() == true) {
                    //ourGameState.exchangeTile(ourGameState.getTurn(),
                            //ourGameState.getPositionInHand());
                    ExchangeTileAction swapTile = new ExchangeTileAction(ourPlayer);
                    ourGame.sendAction(swapTile);
                    break;
                }
                else
                {
                    break;
                }

            case R.id.shuffleImageButton:
                ShuffleTileAction shuffleAction = new ShuffleTileAction(ourPlayer);
                ourGame.sendAction(shuffleAction);
                //ourGameState.shuffleTiles(ourGameState.getTurn());
                break;

            case R.id.dictionaryButton:
                CheckDictionaryAction dictionaryAction = new CheckDictionaryAction(ourPlayer);
                ourGame.sendAction(dictionaryAction);
                //ourGameState.checkDictionary(ourGameState.getTurn());
                break;

            default:
                break;

        }
    }
        @Override
        public boolean onTouch (View v, MotionEvent event){
            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();

            //based on location of touch, round to nearest multiple of 62 (cell size) and put tile there
            //make a new place tile action and send it based on that location

            if( selectedView != null) {
                selectedView.setSelected(false);//unselects previously selected view
            }
            selectedView = v;//sets view to be selected to new view that has been touched
            selectedView.setSelected(true);

            switch (v.getId())
            {
                case  R.id.tileOneButton:





            return true;
        }

       /* @Override
        public boolean onDrag (View v, DragEvent event){
            float xTouch = (int) event.getX();
            float yTouch = (int) event.getY();

            int action = event.getAction();

            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                {

                }
                case DragEvent.ACTION_DRAG_ENTERED:
                {

                }
                case DragEvent.ACTION_DRAG_LOCATION:
                {

                }
                case DragEvent.ACTION_DROP:
                {

                }
                case DragEvent.ACTION_DRAG_EXITED:
                {

                }
                case DragEvent.ACTION_DRAG_ENDED:
                {

                }
                default:
                {
                    break;
                }

            }


            return true;
        }*/
        public boolean onLongClick(View v, MotionEvent event)
        {
            return true;
        }

}

package com.example.scrabble_gamestate.scrabble;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.GameHumanPlayer;

public class ScrabbleController implements View.OnTouchListener, View.OnClickListener, View.OnDragListener {

    private TextView ourScore;
    private TextView opponentScore;
    private Spinner menu = null;
    private ImageView theirProfile = null;
    private ImageView ourProfile = null;
    private ScrabbleGameState ourGameState;
    private View selectedView = null;
    private GameHumanPlayer ourPlayer = null;

    public ScrabbleController(TextView ourPlayerScore, TextView theirPlayerScore, ScrabbleGameState theGameState ) {
        ourScore = ourPlayerScore;
        opponentScore = theirPlayerScore;
        ourGameState = theGameState;
        /*theirProfile = theirProfPic;
        ourProfile = ourProfPic; */

    }
    @Override
    public void onClick(View button) {

        switch (button.getId()) {
            case R.id.playButton:
                ourGameState.playWord(ourGameState.getTurn());
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
                ourGameState.skipTurn(ourGameState.getTurn());
                break;

            case R.id.swapTileButtton:
                if(selectedView.isSelected() == true) {
                    ourGameState.exchangeTile(ourGameState.getTurn(), ourGameState.getPositionInHand());
                    break;
                }
                else
                {
                    break;
                }

            case R.id.shuffleImageButton:
                ourGameState.shuffleTiles(ourGameState.getTurn());
                break;

            case R.id.dictionaryButton:
                ourGameState.checkDictionary(ourGameState.getTurn());
                break;

            default:
                break;

        }
    }
        @Override
        public boolean onTouch (View v, MotionEvent event){
            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();
            if( selectedView != null) {
                selectedView.setSelected(false);//unselects previously selected view
            }
            selectedView = v;//sets view to be selected to new view that has been touched
            selectedView.setSelected(true);


            return true;
        }

        @Override
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
        }

}

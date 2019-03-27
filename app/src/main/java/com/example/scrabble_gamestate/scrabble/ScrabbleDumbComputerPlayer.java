package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GameComputerPlayer;
import com.example.scrabble_gamestate.game.infoMsg.GameInfo;
import com.example.scrabble_gamestate.game.util.Tickable;

/**
 * A computer-version of a counter-player.  Since this is such a simple game,
 * it just sends "+" and "-" commands with equal probability, at an average
 * rate of one per second.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2013
 */
public class ScrabbleDumbComputerPlayer extends GameComputerPlayer implements Tickable {

    /**
     * Constructor for objects of class CounterComputerPlayer1
     *
     * @param name
     * 		the player's name
     */
    public ScrabbleDumbComputerPlayer(String name) {
        // invoke superclass constructor
        super(name);

        // start the timer, ticking 20 times per second
        getTimer().setInterval(50);
        getTimer().start();
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        //half the time, just skip turn instead of playing a word
        if (Math.random() >= 0.5){
            game.sendAction(new SkipTurnAction(this));
            return;
        }

        //the other half of the time, play a word
        //TODO: implement algorithm

    }


}


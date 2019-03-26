package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Displays an array of the alphabet and changes the letter on the selected blank tile to match
 * the selected letter.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class SelectBlankTileLetterAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectBlankTileLetterAction(GamePlayer player) {
        super(player);
    }
}

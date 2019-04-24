package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Changes the current turn to the other player without the current player playing a word when
 * the skip button is pressed.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class SkipTurnAction extends GameAction {
    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public SkipTurnAction(GamePlayer player) {
        super(player);
    }
}

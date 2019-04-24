package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;
/**
 *Class that checks to see if the word played is in the
 * dictionary.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */

public class CheckDictionaryAction extends GameAction {
    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public CheckDictionaryAction(GamePlayer player) {
        super(player);
    }
}

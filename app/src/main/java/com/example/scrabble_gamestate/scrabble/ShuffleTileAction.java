package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Shuffles the tiles in the player's hand.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */

public class ShuffleTileAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ShuffleTileAction(GamePlayer player) {
        super(player);
    }
}

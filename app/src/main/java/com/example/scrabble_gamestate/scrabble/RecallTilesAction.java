package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Recalls all tiles placed by the player in that turn.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class RecallTilesAction extends GameAction {
    /**
     * constructor
     *
     * @param player the player who created the action
     */
    public RecallTilesAction(GamePlayer player) {
        super(player);
    }
}

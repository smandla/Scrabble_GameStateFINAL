package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Places a selected tile from the player's hand and displays it in a selected square of the
 * board, in the player's color.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class PlaceTileAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public PlaceTileAction(GamePlayer player) {
        super(player);
    }
}

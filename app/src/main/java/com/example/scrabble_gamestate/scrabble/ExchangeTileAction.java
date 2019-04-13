package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.actionMsg.GameAction;

/**
 *Class that exchanges one tile in the player's hand for another random tile from the remaining
 * tiles in the tileBag when button is pushed.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavva Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ExchangeTileAction extends GameAction {

    private int pos; //position in hand of tile to be swapped

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ExchangeTileAction(GamePlayer player, int position) {

        super(player);
        pos = position;
    }
}

package com.example.scrabble_gamestate.scrabble;

import com.example.scrabble_gamestate.game.GamePlayer;
import com.example.scrabble_gamestate.game.Tile;
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

    int x;
    int y;
    Tile tile;

    /**
     * constructor
     *
     * @param player the player who created the action
     * @param xLoc  the x location to place the tile
     * @param yLoc  the y location to place the tile
     * @param t the tile to be placed
     */
    public PlaceTileAction(GamePlayer player, int xLoc, int yLoc, Tile t) {

        super(player);

        x = xLoc;
        y = yLoc;
        tile = t;
    }

    //getters and setters
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Tile getTile(){
        return tile;
    }


}

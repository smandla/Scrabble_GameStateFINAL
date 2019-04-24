package com.example.scrabble_gamestate.game;

import java.io.Serializable;

/**
 * Holds the information of the tiles and their values.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class Tile implements Serializable {

    //instance vars
    private int pointVal;
    private char tileLetter; //for blank tiles, use a dash
    private int androidId;
    private int xCoord;
    private int yCoord;


    /**
     * Generic constructor for Tile class
     */
    public Tile()
    {
        pointVal = -1; //to avoid confusion with blank tiles, which have 0 value
        tileLetter = '!'; //to avoid confusion with blank tiles, which have '-' value
        androidId = 0;
        xCoord = -1;
        yCoord = -1;
    }

    /**
     * Constructor for Tile class
     *
     * @param points  the Tile's point value
     * @param letter  the Tile's letter
     * @param id  the android id for the image of the tile
     */
    public Tile(int points, char letter, int id)
    {
        pointVal = points;
        tileLetter = letter;
        androidId = id;
        xCoord = -1;
        yCoord = -1;
    }

    /**
     * Copy Constructor
     *
     * @param tile the tile we will make a copy of
     */
    public Tile(Tile tile) {
        pointVal = tile.pointVal;
        tileLetter = tile.tileLetter;
        androidId = tile.androidId;
        xCoord = tile.xCoord;
        yCoord = tile.yCoord;
    }

    //getters and setters
    public int getAndroidId(){
        return androidId;
    }
    //will not change point value, so no setter

    public int getPointVal(){
        return pointVal;
    }
    //will not change point value, so no setter

    public char getTileLetter(){
        return tileLetter;
    }
    //will not change letter, so no setter


    public int getxCoord(){
        return xCoord;
    }

    public void setxCoord(int x){
        xCoord = x;
    }

    public int getyCoord(){
        return yCoord;
    }

    public void setyCoord(int y){
        yCoord = y;
    }


    /**
     * Overridden method for comparing two tiles
     *
     * @param obj the object to compare to
     *
     * @return true if the tiles have same letter and coord values, false if otherwise
     */
    @Override
    public boolean equals(Object obj){

        Tile comparisonTile = (Tile) obj;
        if(comparisonTile.tileLetter == this.tileLetter && comparisonTile.xCoord == this.xCoord &&
                comparisonTile.yCoord == this.yCoord) {
            return true;
        }
        else{
            return false;
        }
    }


}

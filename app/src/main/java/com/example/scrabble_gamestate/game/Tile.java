package com.example.scrabble_gamestate.game;

/**
 * Holds the information of the tiles and their values.
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavva Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class Tile {

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

}

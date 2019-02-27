package com.example.scrabble_gamestate;

public class Tile {
    /**
     * stufffff
     *
     * @author Sydney Wells
     * @author Sarah Bunger
     * @author Kavva Mandla
     * @author Meredith Marcinko
     */
    //instance vars
    int pointVal;
    char tileLetter; //for blank tiles, use a single space
    int androidId;
    boolean tilePlayed; //if true, tile cannot be moved on board; if false, tile can be moved

    /**
     * Generic constructor for Tile class
     */
    public Tile()
    {
        pointVal = -1; //to avoid confusion with blank tiles, which have 0 value
        tileLetter = '!'; //to avoid confusion with blank tiles, which have ' ' value
        androidId = 0;
        tilePlayed = false;
    }

    /**
     * Constructor for Tile class
     */
    public Tile(int points, char letter, int id, boolean played)
    {
        pointVal = points;
        tileLetter = letter;
        androidId = id;
        tilePlayed = played;
    }
}

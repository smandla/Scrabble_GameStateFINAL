package com.example.scrabble_gamestate;

public class Tile {

    //instance vars
    int pointVal;
    char tileLetter; //for blank tiles, use a single space
    int androidId;

    /**
     * Constructor for Tile class
     */
    public Tile(int points, char letter, int id)
    {
        pointVal = points;
        tileLetter = letter;
        androidId = id;
    }
}

package com.example.scrabble_gamestate.scrabble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.example.scrabble_gamestate.game.Tile;

import static org.junit.Assert.*;

public class ScrabbleGameStateTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void makeTileBag() {
    }

    @Test
    public void shuffleTileBag() {
        ScrabbleGameStateTest testStates = new ScrabbleGameStateTest();
        testStates.shuffleTileBag();


    }

    @Test
    public void drawTile() {
    }

    @Test
    public void quitGame() {
    }

    @Test
    public void placeTile() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        Tile[][] testBoard = testStates.getBoard();
        assertTrue( testBoard[4][5] == null );

    }

    @Test
    public void recallTiles() {

    }

    @Test
    public void playWord() {
    }

    @Test
    public void skipTurn() {
    }

    @Test
    public void shuffleTiles() {
    }

    @Test
    public void exchangeTile() {
    }

    @Test
    public void selectBlankTileLetter() {
    }

    @Test
    public void checkDictionary() {
    }
}
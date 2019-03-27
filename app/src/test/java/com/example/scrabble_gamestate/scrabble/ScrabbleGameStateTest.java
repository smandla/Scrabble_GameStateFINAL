package com.example.scrabble_gamestate.scrabble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.R;

import java.util.ArrayList;

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


    }

    @Test
    public void drawTile() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        testStates.drawTile(testStates.getHand1());
        assertTrue(testStates.getHand1().size() == 7 );
    }

    @Test
    public void quitGame() {
    }

    @Test
    public void placeTile() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        Tile[][] testBoard = testStates.getBoard();
        assertTrue( testBoard[4][5] == null );
        assertFalse( testBoard[4][5] != null );

    }

    @Test
    public void recallTiles() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        Tile ourTile = new Tile(1, 'J', R.drawable.tile_j);
        testStates.placeTile(1, 3,4, ourTile );
        testStates.recallTiles(1);
        assertTrue(testStates.getOnBoard() == null);


    }

    @Test
    public void playWord() {
    }

    @Test
    public void skipTurn() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        int turn = testStates.getTurn();
        testStates.skipTurn(turn);
        assertTrue( turn != testStates.getTurn());
    }

    @Test
    public void shuffleTiles() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        ArrayList<Tile> handTest = testStates.getHand1();
        testStates.shuffleTiles(1);
        assertTrue( handTest != testStates.getHand1());
    }

    @Test
    public void exchangeTile() {
    }

    @Test
    public void selectBlankTileLetter() {
    }

    @Test
    public void checkDictionary() {
        ScrabbleGameState testStates = new ScrabbleGameState();
        int turn = testStates.getTurn();
        testStates.setTurn(1);
        assertFalse( turn != testStates.getTurn());
    }

}
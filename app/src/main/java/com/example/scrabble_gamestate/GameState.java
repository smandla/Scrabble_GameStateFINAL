package com.example.scrabble_gamestate;

import java.util.ArrayList;

public class GameState {

    //constants: the point value of each tile
    final int A_VAL = 1;
    final int B_VAL = 3;
    final int C_VAL = 3;
    final int D_VAL = 2;
    final int E_VAL = 1;
    final int F_VAL = 4;
    final int G_VAL = 2;
    final int H_VAL = 4;
    final int I_VAL = 1;
    final int J_VAL = 8;
    final int K_VAL = 5;
    final int L_VAL = 1;
    final int M_VAL = 3;
    final int N_VAL = 1;
    final int O_VAL = 1;
    final int P_VAL = 3;
    final int Q_VAL = 10;
    final int R_VAL = 1;
    final int S_VAL = 1;
    final int T_VAL = 1;
    final int U_VAL = 1;
    final int V_VAL = 4;
    final int W_VAL = 4;
    final int X_VAL = 8;
    final int Y_VAL = 4;
    final int Z_VAL = 10;
    final int BLANK_VAL = 0;

    //instance variable
    int playerOneScore;
    int playerTwoScore;

    int playerOneId;
    int playerTwoId;

    ArrayList<Tile> tileBag = new ArrayList<Tile>(98);
    ArrayList<Tile> hand1 = new ArrayList<Tile>(7);
    ArrayList<Tile> hand2 = new ArrayList<Tile>(7);

    //if true, tile has been played and cannot be moved; if false, tile can be moved
    boolean tilePlayed;

    //indicates which players turn it is; 1 for player 1, 2 for player 2
    int turn;

    /**
     * Constructor for objects of class GameState
     */
    public GameState(){
        playerOneScore = 0;
        playerTwoScore = 0;

        playerOneId = 1;
        playerTwoId = 2;

        turn = 1;

        makeTileBag();
        shuffleTileBag();

        for(int i=0; i < 7; i++)
        {
            drawTile(hand1);
            drawTile(hand2);
        }

    }

    /**
     * makeDeck
     * Method to make the deck for a new game
     */
    public void makeDeck()
    {
        deck.add(new Card(0,0,'n',R.drawable.green0));//1


}

package com.example.scrabble_gamestate;

import java.util.ArrayList;

public class GameState {


    /*
     *Information about the resources each player has (e.g., cards, pawns, money)
     *  - tiles (point value of each tile: int (define as constant))
     *  - quantity of each tile (ex: 10
     * Information about the state of any resources (e.g., card is visible, pawn is yellow)
     *
     *
     * Whose turn is it?
     * Detailed information about shared resources (e.g., the game board, contents of a draw pile)
     * The visibility of certain information from the perspective of each player
     * Current score of each player
     * Current state of a timer
     * Current state of the dice
     * What stage of the game you are at (e.g., setup phase, placement phase, buy phase, main play stage, etc.).
     * You may need to create additional classes to describe specific elements of the game state (e.g., a playing card, a pawn, a tile, etc.)
     */

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


    int playerOneScore;
    int playerTwoScore;

    int playerOneId;
    int playerTwoId;

    ArrayList<Tile> tileBag = new ArrayList<Tile>(98);
    ArrayList<Tile> hand1 = new ArrayList<Tile>(7);
    ArrayList<Tile> hand2 = new ArrayList<Tile>(7);

    //if true, tile has been played and cannot be moved; if false, tile can be moved
    boolean tilePlayed;




}

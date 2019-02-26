package com.example.scrabble_gamestate;

import java.util.ArrayList;
import java.util.Collections;

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
    //int playerThreeScore;
    //int playerFourScore;


    int playerOneId;
    int playerTwoId;

    ArrayList<Tile> tileBag = new ArrayList<Tile>(100);
    ArrayList<Tile> hand1 = new ArrayList<Tile>(7);
    ArrayList<Tile> hand2 = new ArrayList<Tile>(7);
    //ArrayList<Tile> hand3 = new ArrayList<Tile>(7);
    //ArrayList<Tile> hand4 = new ArrayList<Tile>(7);

    //indicates which players turn it is; 1 for player 1, 2 for player 2, etc.
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

    }//constructor

    /**
     * makeTileBag
     * Method to make the bag of tiles for a new game
     */
    public void makeTileBag() {

        //blank tiles
        ///////////////////////////to be added!

        //a tiles
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_2));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_3));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_4));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_5));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_6));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_7));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_8));
        tileBag.add(new Tile(A_VAL, 'a', R.drawable.tile_a_9));

        //b tiles
        tileBag.add(new Tile(B_VAL, 'b', R.drawable.tile_b));
        tileBag.add(new Tile(B_VAL, 'b', R.drawable.tile_b_2));

        //c tiles
        tileBag.add(new Tile(C_VAL, 'c', R.drawable.tile_c));
        tileBag.add(new Tile(C_VAL, 'c', R.drawable.tile_c_2));

        //d tiles
        tileBag.add(new Tile(D_VAL, 'd', R.drawable.tile_d));
        tileBag.add(new Tile(D_VAL, 'd', R.drawable.tile_d_2));
        tileBag.add(new Tile(D_VAL, 'd', R.drawable.tile_d_3));
        tileBag.add(new Tile(D_VAL, 'd', R.drawable.tile_d_4));

        //e tiles
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_2));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_3));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_4));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_5));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_6));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_7));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_8));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_9));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_10));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_11));
        tileBag.add(new Tile(E_VAL, 'e', R.drawable.tile_e_12));

        //f tiles
        tileBag.add(new Tile(F_VAL, 'f', R.drawable.tile_f));
        tileBag.add(new Tile(F_VAL, 'f', R.drawable.tile_f_2));

        //g tiles
        tileBag.add(new Tile(G_VAL, 'g', R.drawable.tile_g));
        tileBag.add(new Tile(G_VAL, 'g', R.drawable.tile_g_2));
        tileBag.add(new Tile(G_VAL, 'g', R.drawable.tile_g_3));

        //h tiles
        tileBag.add(new Tile(H_VAL, 'h', R.drawable.tile_h));
        tileBag.add(new Tile(H_VAL, 'h', R.drawable.tile_h_2));

        //i tiles
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_2));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_3));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_4));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_5));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_6));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_7));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_8));
        tileBag.add(new Tile(I_VAL, 'i', R.drawable.tile_i_9));

        //j tile
        tileBag.add(new Tile(J_VAL, 'j', R.drawable.tile_j));

        //k tile
        tileBag.add(new Tile(K_VAL, 'k', R.drawable.tile_k));

        //l tiles
        tileBag.add(new Tile(L_VAL, 'l', R.drawable.tile_l));
        tileBag.add(new Tile(L_VAL, 'l', R.drawable.tile_l_2));
        tileBag.add(new Tile(L_VAL, 'l', R.drawable.tile_l_3));
        tileBag.add(new Tile(L_VAL, 'l', R.drawable.tile_l_4));

        //m tiles
        tileBag.add(new Tile(M_VAL, 'm', R.drawable.tile_m));
        tileBag.add(new Tile(M_VAL, 'm', R.drawable.tile_m_2));

        //n tiles
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n));
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n_2));
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n_3));
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n_4));
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n_5));
        tileBag.add(new Tile(N_VAL, 'n', R.drawable.tile_n_6));

        //o tiles
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_2));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_3));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_4));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_5));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_6));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_7));
        tileBag.add(new Tile(O_VAL, 'o', R.drawable.tile_o_8));

        //p tiles
        tileBag.add(new Tile(P_VAL, 'p', R.drawable.tile_p));
        tileBag.add(new Tile(P_VAL, 'p', R.drawable.tile_p_2));

        //q tile
        tileBag.add(new Tile(Q_VAL, 'q', R.drawable.tile_q));

        //r tiles
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r));
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r_2));
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r_3));
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r_4));
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r_5));
        tileBag.add(new Tile(R_VAL, 'r', R.drawable.tile_r_6));

        //s tiles
        tileBag.add(new Tile(S_VAL, 's', R.drawable.tile_s));
        tileBag.add(new Tile(S_VAL, 's', R.drawable.tile_s_2));
        tileBag.add(new Tile(S_VAL, 's', R.drawable.tile_s_3));
        tileBag.add(new Tile(S_VAL, 's', R.drawable.tile_s_4));

        //t tiles
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t));
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t_2));
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t_3));
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t_4));
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t_5));
        tileBag.add(new Tile(T_VAL, 't', R.drawable.tile_t_6));

        //u tiles
        tileBag.add(new Tile(U_VAL, 'u', R.drawable.tile_u));
        tileBag.add(new Tile(U_VAL, 'u', R.drawable.tile_u_2));
        tileBag.add(new Tile(U_VAL, 'u', R.drawable.tile_u_3));
        tileBag.add(new Tile(U_VAL, 'u', R.drawable.tile_u_4));

        //v tiles
        tileBag.add(new Tile(V_VAL, 'v', R.drawable.tile_v));
        tileBag.add(new Tile(V_VAL, 'v', R.drawable.tile_v_2));

        //w tiles
        tileBag.add(new Tile(W_VAL, 'w', R.drawable.tile_w));
        tileBag.add(new Tile(W_VAL, 'w', R.drawable.tile_w_2));

        //x tile
        tileBag.add(new Tile(X_VAL, 'x', R.drawable.tile_x));

        //y tiles
        tileBag.add(new Tile(Y_VAL, 'y', R.drawable.tile_y));
        tileBag.add(new Tile(Y_VAL, 'y', R.drawable.tile_y_2));

        //z tile
        tileBag.add(new Tile(Z_VAL, 'z', R.drawable.tile_z));

    }//makeTileBag

    /**
     * Method to shuffle the tiles in the bag
     */
    public void shuffleTileBag()
    {
        //Collections.shuffle - shuffles an arrayList
        Collections.shuffle(tileBag);

    }//shuffleTileBag

    /**
     * Method to add a tile to a specific hand
     *
     * @param hand  The hand to add the tile to
     */
    public void drawTile(ArrayList<Tile> hand)
    {
        hand.add(tileBag.get(0));
        tileBag.remove(0);

    }//drawTile

    @Override
    public String toString(){


        return null;
    }

    /**
     *
     */
    public boolean quitGame(){
        return false;

    }

    /**
     *
     */
    public boolean placeTile(){

        return false;
    }

    /**
     *
     */
    public boolean recallTiles(){

        return false;
    }

    /**
     *
     */
    public boolean playWord(){
        return false;

    }

    /**
     *
     */
    public boolean skipTurn(){
        return false;

    }

    /**
     *
     */
    public boolean shuffleTiles(){

        return false;
    }

    /**
     *
     */
    public boolean exchangeTile(){
        return false;

    }

    /**
     *
     */
    public boolean selectBlankTileLetter(){

        return false;
    }
}

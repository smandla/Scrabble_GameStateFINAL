package com.example.scrabble_gamestate.scrabble;


import com.example.scrabble_gamestate.R;
import com.example.scrabble_gamestate.game.Tile;
import com.example.scrabble_gamestate.game.infoMsg.GameState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Vector;



/**
 *The State of the game. Includes values for all tiles, the tile bag, and each square on the board.
 *
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class ScrabbleGameState extends GameState{

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

    //makes an array of values that correspond to the board bonuses
    final int[][] letterBonuses =      {{1,1,1,2,1,1,1,1,1,1,1,2,1,1,1},
                                        {1,1,1,1,1,3,1,1,1,3,1,1,1,1,1},
                                        {1,1,1,1,1,1,2,1,2,1,1,1,1,1,1},
                                        {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                        {1,3,1,1,1,3,1,1,1,3,1,1,1,3,1},
                                        {1,1,2,1,1,1,2,1,2,1,1,1,2,1,1},
                                        {1,1,1,2,1,1,1,1,1,1,1,2,1,1,1},
                                        {1,1,2,1,1,1,2,1,2,1,1,1,2,1,1},
                                        {1,3,1,1,1,3,1,1,1,3,1,1,1,3,1},
                                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                        {2,1,1,1,1,1,1,2,1,1,1,1,1,1,2},
                                        {1,1,1,1,1,1,2,1,2,1,1,1,1,1,1},
                                        {1,1,1,1,1,3,1,1,1,3,1,1,1,1,1},
                                        {1,1,1,2,1,1,1,1,1,1,1,2,1,1,1}};

    final int[][] wordBonuses =    {{3,1,1,1,1,1,1,3,1,1,1,1,1,1,3},
                                    {1,2,1,1,1,1,1,1,1,1,1,1,1,2,1},
                                    {1,1,2,1,1,1,1,1,1,1,1,1,2,1,1},
                                    {1,1,1,2,1,1,1,1,1,1,1,2,1,1,1},
                                    {1,1,1,1,2,1,1,1,1,1,2,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {3,1,1,1,1,1,1,2,1,1,1,1,1,1,3},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                    {1,1,1,1,2,1,1,1,1,1,2,1,1,1,1},
                                    {1,1,1,2,1,1,1,1,1,1,1,2,1,1,1},
                                    {1,1,2,1,1,1,1,1,1,1,1,1,2,1,1},
                                    {1,2,1,1,1,1,1,1,1,1,1,1,1,2,1},
                                    {3,1,1,1,1,1,1,3,1,1,1,1,1,1,3}};

    //instance variable
    private int playerZeroScore;
    private int playerOneScore;

    private int playerZeroId;
    private int playerOneId;

    private int positionInHand;//the index of a tile in a hand

    private ArrayList<Tile> tileBag = new ArrayList<Tile>(100);
    private ArrayList<Tile> hand1 = new ArrayList<Tile>(7); //should be hand0
    private ArrayList<Tile> hand2 = new ArrayList<Tile>(7); //should be hand1

    //shows the location of any tile placed on the board during one turn
    private ArrayList<Tile> onBoard;

    private Tile[][] board;

    //indicates which players turn it is
    private int turn;

    private boolean gameWon;

    private boolean enoughPlayers; //because of quit functionality, must make sure there's at least
    // 2 players

    private static HashSet<String> dictionary = null;//the basic dictionary for playWord computer
    private static HashSet<String> humanDictionary = null;//the basic dictionary for playWord human

    /**
     * Constructor for objects of class ScrabbleGameState
     */
    public ScrabbleGameState() {
        playerZeroScore = 0;
        playerOneScore = 0;

        playerZeroId = 0;
        playerOneId = 1;

        turn = 0;
        board = new Tile[15][15];
        //set entire array to null, representing board with no tiles played
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = null;
            }
        }

        makeTileBag();
        shuffleTileBag();

        //add seven tiles to each player's hand
        drawTile(hand1);
        drawTile(hand2);


        onBoard = new ArrayList<Tile>(7);

        gameWon = false;

        enoughPlayers = true;

    }//constructor


    /**
     * Deep copy constructor
     *
     * @param state The one true state of the game, to be copied
     */
    public ScrabbleGameState(ScrabbleGameState state) {
        playerZeroScore = state.playerZeroScore;
        playerOneScore = state.playerOneScore;

        playerZeroId = state.playerZeroId;
        playerOneId = state.playerOneId;

        turn = state.turn;
        positionInHand = state.positionInHand;
        //new board
        board = new Tile[15][15];
        copyBoard(state, board);

        //making a Tile copy of each Tile in The One True tileBag, then adding it to a copy tileBag
        //don't need to actually make/shuffle a new tilebag,
        // as it already exists in The One True State
        for (Tile t : state.tileBag) {
            Tile copy = new Tile(t);
            tileBag.add(copy);
        }

        for (Tile t : state.hand1) {
            Tile copy = new Tile(t);
            hand1.add(copy);
        }


        for (Tile t : state.hand2) {
            Tile copy = new Tile(t);
            hand2.add(copy);
        }

        onBoard = new ArrayList<Tile>(7);
        for (Tile t : state.onBoard) {
            Tile copy = new Tile(t);
            onBoard.add(copy);
        }

        gameWon = state.gameWon;

        enoughPlayers = state.enoughPlayers;
    }

    /**
     * creates a copy of the board
     *
     * @param state the one true state of the game
     * @param board the current board object
     */
    private static void copyBoard(ScrabbleGameState state, Tile[][] board) {
        //remember that this array of tiles might actually contain tiles, not just null values,
        //so we must copy over Tile objects
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                //using Tile copy constructor to duplicate orig tile obj
                // and prevent pointing to same obj
                //also, remember that the Tile copy constructor deals with copying over the Tile's
                //primitive parameters


                /**
                 * need to make an if and else statement for when the board is null because it will
                 * be at the beginning of the game.
                 */

                if (state.board[i][j] == null){
                    board[i][j] = null;
                }

                else{
                    board[i][j] = new Tile(state.board[i][j]);
                }

            }
        }
    }


    //getters and setters
    public int getPlayerZeroScore(){
        return playerZeroScore;
    }

    public void setPlayerZeroScore(int score){
        playerZeroScore = score;
    }

    public int getPlayerOneScore(){
        return playerOneScore;
    }

    public void setPlayerOneScore(int score){
        playerOneScore = score;
    }

    public int getPlayerZeroId(){
        return playerZeroId;
    }

    public void setPlayerZeroId(int id){
        playerZeroId = id;
    }

    public int getPlayerOneId(){
        return playerOneId;
    }

    public void setPlayerOneId(int id){
        playerOneId = id;
    }

    public ArrayList<Tile> getTileBag(){
        return tileBag;
    }
    //no tileBag setter needed

    public ArrayList<Tile> getHand1(){
        return hand1;
    }
    //no hand1 setter needed

    public ArrayList<Tile> getHand2(){
        return hand2;
    }
    //no hand2 setter needed
    /**
     * External Citation
     * Date: 24 April 2019
     * Problem: handCurrent wasn't updating with hands 1 and 2
     * Resource: Dr. Tribelhorn
     * Solution: instead of an instance variable, called getter method instead,
     * and had getter method return hand1 or hand2
     */
    public ArrayList<Tile> getHandCurrent(){
        if(turn == 0)
        {
            return hand1;
        }
        else
        {
            return hand2;
        }

    }

    public Tile[][] getBoard(){
        return board;
    }
    //no board setter needed

    public ArrayList<Tile> getOnBoard(){
        return onBoard;
    }
    //no tileOnBoard setter needed

    public int getTurn(){
        return turn;
    }

    public void setTurn(int turnId){
        turn = turnId;
    }

    public boolean getGameWon(){
        return gameWon;
    }

    public void setGameWon(boolean gWon){
        gameWon = gWon;
    }

    public boolean getEnoughPlayers(){
        return enoughPlayers;
    }

    public void setPlayerOneId(boolean enoughP){
        enoughPlayers = enoughP;
    }

    public int getPositionInHand() { return positionInHand;}

    public void setDictionary( HashSet<String> sentDict)
    {
        dictionary = sentDict;
    }

    public HashSet<String> getDictionary(){
        return dictionary;
    }

    public void setHumanDictionary( HashSet<String> sentDict)
    {
        humanDictionary = sentDict;
    }
    public HashSet<String> getHumanDictionary(){
        return humanDictionary;
    }
    //end of getters and setters

    /**
     * makeTileBag
     * Method to make the bag of tiles for a new game
     */
    public void makeTileBag() {

        //blank tiles
        //TODO uncomment after alpha
        //tileBag.add(new Tile(BLANK_VAL, '-', R.drawable.tile_blank));
        //tileBag.add(new Tile(BLANK_VAL, '-', R.drawable.tile_blank_2));

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
     * Helper method to shuffle the tiles in the bag
     */
    public void shuffleTileBag() {
        //Collections.shuffle - shuffles an arrayList
        Collections.shuffle(tileBag);

    }//shuffleTileBag

    /**
     * Helper method to add a tile to a specific hand
     *
     * @param hand The hand to add the tile to
     */
    public void drawTile(ArrayList<Tile> hand) {
        while (hand.size() < 7) {
            hand.add(tileBag.get(0));
            tileBag.remove(0);
        }
    }//drawTile


    /**
     * Method that halts the game, reverting it to the original game state
     * after pushing quit button.
     */
    public boolean quitGame() {
        System.exit(0);
        //appropriately updating game state
        //enoughPlayers = false;

        return true; //should always remain true, because you should be able to quit whenever

    }

    public boolean rulesGame(){
        //Intent rulesIntent = new Intent(this., ScrabbleRules.class);


        return true;
    }


    /**
     * Method that shows player rules of the game.
     */

    public boolean rulesOfGame(){
        return true;
    }
    /**
     * Method that checks if there's an adjacent tile- helper method for placeTile.
     * @param xPosition the x coordinate of the selected board tile
     * @param yPosition the y coordinate of the selected board tile
     */
    public boolean checkIfAdjacent(int xPosition, int yPosition)
    {   //checks if there's a tile to the left if its not on an edge
        if(xPosition != 0 && this.board[xPosition-1][yPosition] != null)
        {
            return true;
        }
        //checks if there's a tile to the right if its not on an edge
        if(xPosition != 14 && this.board[xPosition+1][yPosition] != null)
        {
            return true;
        }
        //checks if there's a tile above if its not on an edge
        if(yPosition != 0 && this.board[xPosition][yPosition-1] != null)
        {
            return true;
        }
        //checks if there's a tile below if its not on an edge
        if(yPosition != 14 && this.board[xPosition][yPosition+1] != null)
        {
            return true;
        }
        //for any tile that's been placed this round, see if it's adjacent to the one we're placing
        for( Tile t: this.onBoard)
        {
            //checks if there's a tile to the right
            if(t.getxCoord() == xPosition + 1 && t.getyCoord() == yPosition)
            {
                return true;
            }
            //checks if there's a tile to the left
            if(t.getxCoord() == xPosition - 1 && t.getyCoord() == yPosition)
            {
                return true;
            }
            //checks if there's a tile above
            if(t.getxCoord() == xPosition && t.getyCoord() == yPosition - 1)
            {
                return true;
            }
            //checks if there's a tile below
            if(t.getxCoord() == xPosition && t.getyCoord() == yPosition + 1)
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that checks if it's your turn and the selected location is a valid point on the board
     * -i.e. both free of other tiles and within the boundaries of the board. It then updates the
     * player's view.
     *
     * @param turnId the id of the player whose turn it is currently
     * @param xPosition the x coordinate of the selected board tile
     * @param yPosition the y coordinate of the selected board tile
     * @param tile the selected tile in the player's hand
     */
    public boolean placeTile(int turnId, int xPosition, int yPosition, Tile tile) {
        if(turnId == turn) {
            //checks if there's a tile where we're placing one
            if(board[xPosition][yPosition] == null) {
                //checks if the center tile is empty, or if we're playing on that tile
                if(board[7][7] != null || (xPosition == 7 && yPosition == 7)) {
                    //checks if its adjacent to at least one tile
                    if( checkIfAdjacent(xPosition, yPosition) == true ||
                            (xPosition == 7 && yPosition == 7) ) {

                        board[xPosition][yPosition] = tile;
                        tile.setxCoord(xPosition);
                        tile.setyCoord(yPosition);
                        onBoard.add(tile);

                        //look through player's hand for a Tile that matches tile (parameter),
                        // using equals if the two match, remove that one from the hand
                        Tile removeMe = null;

                        ArrayList<Tile> currentHand;
                        if (turn == 0) {
                            currentHand = hand1;
                        } else {
                            currentHand = hand2;
                        }
                        for (Tile t : currentHand) {
                            if (t.getTileLetter() == tile.getTileLetter()) {
                                removeMe = t;
                            }
                        }
                        if (removeMe != null) {
                            currentHand.remove(removeMe);
                        }
                        return true;
                    }
                }
                return false;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Method that checks if it's the player's turn and removes all tiles that have been placed on
     * the board in that turn and returns them to the player's hand.
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean recallTiles(int turnId) {
        if(turnId == turn && onBoard != null) {
            for(Tile t: onBoard) {
                board[t.getxCoord()][t.getyCoord()] = null;
                getHandCurrent().add(t);
            }
            onBoard.clear();
            return true;
        }
        else
        {
            return false;
        }
    }//end recallTiles

    /**
     * Method that checks if it's the player's turn, and if so, resets the game state to match the
     * current view of the board when the player presses the "Play" button, as well as switches the
     * current turn and updates the score.
     *
     * Player 1 must place first tile in center of the board. Tiles can only be
     *placed when there is another tile next to it.
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean playWord(int turnId) {

        /**
         * External Citation
         * Date: 15 March 2019
         * Reason: Struggles with logic of checking to make sure that the words were valid
         * Resources: Sydney's boyfriend Andrew; Kyle Kearney
         * Solution: Both assisted with walking through the logic involved in this method and
         * offered guidance on how to approach the problem.
         */

        if(turnId == turn && onBoard != null) {

            Vector<String> wordsPlayed = new Vector<>();
            Tile[][] tempBoard = new Tile[15][15];
            this.copyBoard(this, tempBoard);
            //iterates thru the onBoard array and adds all of them to the new board array
            for( Tile t: onBoard)
            {
                tempBoard[t.getxCoord()][t.getyCoord()] = t;
            }
            for(int row = 0; row < 15; row++)//Scan each row for horizontal words
            {
                String wordToCheck="";	//Start with empty word
                for(int position = 0; position < 15; position++)
                {
                    if(tempBoard[position][row] != null)//If we got a letter
                    {
                        wordToCheck+=tempBoard[position][row].getTileLetter();
                        //Add it to the current word
                    }
                    else
                    {
                        if (wordToCheck.length()>= 2)//Ignore empty word, single letter
                            wordsPlayed.add(wordToCheck);
                        wordToCheck="";	//Clear wordToCheck for next time

                    }

                }
                if (wordToCheck.length()> 1)	//One more time in case we're at the
                    // rightmost edge
                    wordsPlayed.add(wordToCheck);
            }
            for(int col = 0; col < 15; col++)//Scan each row for vertical words
            {
                String wordToCheck="";	//Start with empty word
                for(int position = 0; position < 15; position++)
                {
                    if(tempBoard[col][position] != null)//If we got a letter
                    {
                        wordToCheck+=tempBoard[col][position].getTileLetter();
                        //Add it to the current word
                    }
                    else
                    {
                        if (wordToCheck.length()>= 2)	//Ignore empty word, single letter
                            wordsPlayed.add(wordToCheck);
                        wordToCheck="";	//Clear wordToCheck for next time

                    }

                }
                if (wordToCheck.length()>1)	//One more time in case we're at the
                    // bottom-most edge
                    wordsPlayed.add(wordToCheck);
            }
            for(String s: wordsPlayed)
            {

                if(humanDictionary.contains(s) == false)//if its not a word, dont do it
                {
                    return false;
                }
            }



            int counter = 0;
            int wordBonusVal = 1;
            for(Tile t: onBoard) {
                wordBonusVal *= (wordBonuses[t.getxCoord()][t.getyCoord()]);
                counter += letterBonuses[t.getxCoord()][t.getyCoord()] * t.getPointVal();

                //the following if statements take care of letters that have already been played
                //(so will not show up in onBoard) but must be added to the player's score

                //add tile value to points if the space to the left of Tile t isn't empty and
                //the Tile in that space isn't already in the list (each tile will almost
                //always have at least one neighbor that's already part of the list, thanks to
                //the connectedness of words)
                try {

                    if (board[t.getxCoord() - 1][t.getyCoord()] != null &&
                            !onBoard.contains(board[t.getxCoord() - 1][t.getyCoord()])) {
                        counter += board[t.getxCoord() - 1][t.getyCoord()].getPointVal();
                    }

                    //tile above
                    if (board[t.getxCoord()][t.getyCoord() + 1] != null &&
                            !onBoard.contains(board[t.getxCoord()][t.getyCoord() + 1])) {
                        counter += board[t.getxCoord()][t.getyCoord() + 1].getPointVal();
                    }

                    //tile to the right
                    if (board[t.getxCoord() + 1][t.getyCoord()] != null &&
                            !onBoard.contains(board[t.getxCoord() + 1][t.getyCoord()])) {
                        counter += board[t.getxCoord() + 1][t.getyCoord()].getPointVal();
                    }

                    //tile below
                    if (board[t.getxCoord()][t.getyCoord() - 1] != null &&
                            !onBoard.contains(board[t.getxCoord()][t.getyCoord() - 1])) {
                        counter += board[t.getxCoord()][t.getyCoord() - 1].getPointVal();
                    }
                }
                catch (ArrayIndexOutOfBoundsException exception)
                {
                        //do nothing
                }
            }

            if(turn == 0) {
                playerZeroScore += (counter * wordBonusVal);
                turn++;
                this.drawTile(hand1);
            }
            else {
                playerOneScore += (counter * wordBonusVal);
                turn--;
                this.drawTile(hand2);
            }
            onBoard.clear();
            return true;
        }
        else{
            return false;
        }

    }//end playWord


    /**
     * Method that checks if it's the player's turn, and if so, resets the game state to match the
     * current view of the board when the player presses the "Play" button, as well as switches the
     * current turn and updates the score. This checks with the computer's version of the dictionary
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean playWordComputer(int turnId) {

        if(turnId == turn && onBoard != null) {

            Vector<String> wordsPlayed = new Vector<>();
            Tile[][] tempBoard = new Tile[15][15];
            this.copyBoard(this, tempBoard);
            //iterates thru the onBoard array and adds all of them to the new board array
            for( Tile t: onBoard)
            {
                tempBoard[t.getxCoord()][t.getyCoord()] = t;
            }
            for(int row = 0; row < 15; row++)//Scan each row for horizontal words
            {
                String wordToCheck="";	//Start with empty word
                for(int position = 0; position < 15; position++)
                {
                    if(tempBoard[position][row] != null)//If we got a letter
                    {
                        wordToCheck+=tempBoard[position][row].getTileLetter();
                        //Add it to the current word
                    }
                    else
                    {
                        if (wordToCheck.length()>= 2)//Ignore empty word, single letter
                            wordsPlayed.add(wordToCheck);
                        wordToCheck="";	//Clear wordToCheck for next time

                    }

                }
                if (wordToCheck.length()> 1)	//One more time in case we're at the
                    // rightmost edge
                    wordsPlayed.add(wordToCheck);
            }
            for(int col = 0; col < 15; col++)//Scan each row for vertical words
            {
                String wordToCheck="";	//Start with empty word
                for(int position = 0; position < 15; position++)
                {
                    if(tempBoard[col][position] != null)//If we got a letter
                    {
                        wordToCheck+=tempBoard[col][position].getTileLetter();
                        //Add it to the current word
                    }
                    else
                    {
                        if (wordToCheck.length()>= 2)	//Ignore empty word, single letter
                            wordsPlayed.add(wordToCheck);
                        wordToCheck="";	//Clear wordToCheck for next time

                    }

                }
                if (wordToCheck.length()>1)	//One more time in case we're at the
                    // bottom-most edge
                    wordsPlayed.add(wordToCheck);
            }
            for(String s: wordsPlayed)
            {

                if(dictionary.contains(s) == false)//if its not a word, dont do it
                {
                    return false;
                }
            }



            int counter = 0;
            int wordBonusVal = 1;
            for(Tile t: onBoard) {
                wordBonusVal *= (wordBonuses[t.getxCoord()][t.getyCoord()]);
                counter += letterBonuses[t.getxCoord()][t.getyCoord()] * t.getPointVal();

                //the following if statements take care of letters that have already been played
                //(so will not show up in onBoard) but must be added to the player's score

                //add tile value to points if the space to the left of Tile t isn't empty and
                //the Tile in that space isn't already in the list (each tile will almost
                //always have at least one neighbor that's already part of the list, thanks to
                //the connectedness of words)
                try {

                    if (board[t.getxCoord() - 1][t.getyCoord()] != null &&
                            !onBoard.contains(board[t.getxCoord() - 1][t.getyCoord()])) {
                        counter += board[t.getxCoord() - 1][t.getyCoord()].getPointVal();
                    }

                    //tile above
                    if (board[t.getxCoord()][t.getyCoord() + 1] != null &&
                            !onBoard.contains(board[t.getxCoord()][t.getyCoord() + 1])) {
                        counter += board[t.getxCoord()][t.getyCoord() + 1].getPointVal();
                    }

                    //tile to the right
                    if (board[t.getxCoord() + 1][t.getyCoord()] != null &&
                            !onBoard.contains(board[t.getxCoord() + 1][t.getyCoord()])) {
                        counter += board[t.getxCoord() + 1][t.getyCoord()].getPointVal();
                    }

                    //tile below
                    if (board[t.getxCoord()][t.getyCoord() - 1] != null &&
                            !onBoard.contains(board[t.getxCoord()][t.getyCoord() - 1])) {
                        counter += board[t.getxCoord()][t.getyCoord() - 1].getPointVal();
                    }
                }
                catch (ArrayIndexOutOfBoundsException exception)
                {
                    //do nothing
                }
            }

            if(turn == 0) {
                playerZeroScore += (counter * wordBonusVal);
                turn++;
                this.drawTile(hand1);
            }
            else {
                playerOneScore += (counter * wordBonusVal);
                turn--;
                this.drawTile(hand2);
            }
            onBoard.clear();
            return true;
        }
        else{
            return false;
        }

    }//end playWordComputer

    /**
     * Method that checks if it's the player's turn, and if so, calls the SkipTurnAction class,
     * which changes the turn id to that of the other player.
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean skipTurn(int turnId) {
        this.recallTiles(turnId);

        if(turnId == turn){
            if(turn == 1) {
                turn--;
            }
            else {
                turn++;
            }
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * method that checks if it is the player's turn, recalls all tiles to the player's hand, and
     * shuffles the tiles in the hand.
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean shuffleTiles(int turnId) {

        if(turnId == turn){
            this.recallTiles(turnId);
            if(turn == 0) {
                Collections.shuffle(getHandCurrent());
            }
            else {
                Collections.shuffle(getHandCurrent());
            }
            return true;
        }
        else {
            return false;
        }
    }

    /** method that checks if it is the player's turn, recalls all tiles to the player's hand, and
     * removes a selected tile from the player's hand, replacing it with a random tile from the tile
     * bag, and then returns the old tile to the tilebag.
     *
     * @param turnId the id of the player whose turn it is currently
     * @param position placement of the selected tile in the hand array
     */
    public boolean exchangeTile(int turnId, int position) {
        if(turnId == turn){
            this.recallTiles(turnId);
            if(turn == 0) {
                tileBag.add(getHandCurrent().get(position));
                getHandCurrent().remove(position);
                this.drawTile(getHandCurrent());
            }
            else
            {
                tileBag.add(getHandCurrent().get(position));
                getHandCurrent().remove(position);
                this.drawTile(getHandCurrent());
            }
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * method that checks if it is the player's turn, then adds a selected letter to a selected
     * blank tile in the player's hand without changing the point value of the tile.
     *
     * @param turnId the id of the player whose turn it is currently
     */
    public boolean selectBlankTileLetter(int turnId, int position) {
        if(turnId == turn){
            if(turn == 0 && hand1.get(position).getPointVal() == 0) {
                //need to implement more of UI and user code in order to receive selected tile
                //info
                //with that info, this would rewrite the tile to display the selected letter
            }
            if (turn == 1 && hand2.get(position).getPointVal() == 0)
            {
                //same as above
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Replaces each tile in the opponent's hand with a fake one (not to be confused with a blank)
     * so that we can still check the win condition (running out of tiles after the tile bag is
     * empty) while preventing players from knowing what tiles their opponent has
     */
    public void preventCheating(){
        ArrayList<Tile> opponentHand;

        Tile genericTile = new Tile(-1, '#', R.drawable.empty_spot_in_hand_indicator);

        if(this.turn == 0){
            opponentHand = hand2;
        }
        else{
            opponentHand = hand1;
        }

        for(int i = 0; i < opponentHand.size(); i++){
            opponentHand.remove(i);
            opponentHand.add(genericTile);
        }
    }

}

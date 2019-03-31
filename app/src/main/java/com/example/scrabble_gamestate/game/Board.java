package com.example.scrabble_gamestate.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.example.scrabble_gamestate.R;

import java.util.ArrayList;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class Board extends SurfaceView {

    private static final int TILE_WIDTH_AND_HEIGHT = 62; //each tile or cell is 62x62 dp
    private static final int BOARD_SIZE = 15; //board is a 15x15 grid

    /**
     * Constructor for Board objects.
     */
    public Board(Context context) {

        super(context);
        init();
    }

    /**
     * Constructor for Board objects.
     */
    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Constructor for Board objects.
     */
    public Board(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * A helper method
     */
    private void init() {

        setWillNotDraw(false);

        //picture = new ArrayList<Bitmap>();
    }

    /**
     * Overrides SurfaceView's onDraw method
     * @param: Canvas  The Canvas object to draw on.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(0xFFFFFF00);

        //make and resize the images for cells without bonuses
        Bitmap noBonusCellOrig = BitmapFactory.decodeResource(getResources(), R.drawable.nobonus);
        Bitmap noBonusCell = Bitmap.createScaledBitmap(noBonusCellOrig, TILE_WIDTH_AND_HEIGHT,
                TILE_WIDTH_AND_HEIGHT, false);

        Bitmap doubleLetterCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.doubleletter);
        Bitmap doubleLetterCell = Bitmap.createScaledBitmap(doubleLetterCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap doubleWordCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.doubleword);
        Bitmap doubleWordCell = Bitmap.createScaledBitmap(doubleWordCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap tripleLetterCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.tripleletter);
        Bitmap tripleLetterCell = Bitmap.createScaledBitmap(tripleLetterCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        Bitmap tripleWordCellOrig =
                BitmapFactory.decodeResource(getResources(), R.drawable.tripleword);
        Bitmap tripleWordCell = Bitmap.createScaledBitmap(tripleWordCellOrig,
                TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, false);

        //start off by drawing a board of cells without bonuses
        for(int row = 0; row < BOARD_SIZE; row++){
            for(int col = 0; col < BOARD_SIZE; col++){
                float x = row * TILE_WIDTH_AND_HEIGHT;
                float y = col * TILE_WIDTH_AND_HEIGHT;
                canvas.drawBitmap(noBonusCell,x,y,null);
            }
        }

        //adding in double letter bonuses
        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,0*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,7*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,14*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,2*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,12*TILE_WIDTH_AND_HEIGHT,6*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,2*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,12*TILE_WIDTH_AND_HEIGHT,8*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,0*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,7*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,14*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,6*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,8*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleLetterCell,3*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleLetterCell,11*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);

        //adding in double word bonuses
        canvas.drawBitmap(doubleWordCell,1*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,13*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,2*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,12*TILE_WIDTH_AND_HEIGHT,2*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,3*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,11*TILE_WIDTH_AND_HEIGHT,3*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,4*TILE_WIDTH_AND_HEIGHT,4*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,10*TILE_WIDTH_AND_HEIGHT,4*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,7*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,4*TILE_WIDTH_AND_HEIGHT,10*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,9*TILE_WIDTH_AND_HEIGHT,10*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,3*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,11*TILE_WIDTH_AND_HEIGHT,11*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,2*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,12*TILE_WIDTH_AND_HEIGHT,12*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(doubleWordCell,1*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(doubleWordCell,13*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);

        //adding in triple letter bonuses
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,1*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,1*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,13*TILE_WIDTH_AND_HEIGHT,5*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,1*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,13*TILE_WIDTH_AND_HEIGHT,9*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleLetterCell,5*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleLetterCell,9*TILE_WIDTH_AND_HEIGHT,13*TILE_WIDTH_AND_HEIGHT,null);

        //adding in triple word bonuses
        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,7*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,0*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,7*TILE_WIDTH_AND_HEIGHT,null);

        canvas.drawBitmap(tripleWordCell,0*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,7*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);
        canvas.drawBitmap(tripleWordCell,14*TILE_WIDTH_AND_HEIGHT,14*TILE_WIDTH_AND_HEIGHT,null);

    }
}


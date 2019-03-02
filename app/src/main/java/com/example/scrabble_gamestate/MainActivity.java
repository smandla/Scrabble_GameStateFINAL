package com.example.scrabble_gamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavya Mandla
 * @author Meredith Marcinko
 * @version February 2019
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button runTestButton;
    EditText editT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editT = findViewById(R.id.editText);

        runTestButton = findViewById(R.id.runTestButton);
        runTestButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View button) {

        String currentText;

        //clear any previous text
        editT.setText("");

        //create instance and deep copy
        ScrabbleGameState firstInstance = new ScrabbleGameState();
        ScrabbleGameState secondInstance = new ScrabbleGameState(firstInstance);

        //calling each method in ScrabbleGameState once and appending strings
        firstInstance.placeTile(firstInstance.getTurn(), 7, 7, firstInstance.getHand1().get(3));
        currentText = "\n Player 1 placed an A tile on the center square.";
        firstInstance.placeTile(firstInstance.getTurn(), 8, 7, firstInstance.getHand1().get(4));
        currentText = currentText + "\n Player 1 placed an A tile on the square to the right of the other A.\n";

        firstInstance.playWord(firstInstance.getTurn());
        currentText = currentText + "\n Player 1 played the word aa, which is actually a valid word in Scrabble.\n";

        firstInstance.checkDictionary(firstInstance.getTurn());
        currentText = currentText + "\n Player 2 checked the dictionary, trying to find a word to play.\n";

        firstInstance.placeTile(firstInstance.getTurn(), 8, 8, firstInstance.getHand2().get(6));
        currentText = currentText + "\n Player 2 placed a b tile on the double letter score below the rightmost a tile.\n";

        firstInstance.playWord(firstInstance.getTurn());
        currentText = currentText + "\n Player 2 played the word ab.\n";

        firstInstance.exchangeTile(firstInstance.getTurn(), 6);
        currentText = currentText + "\n Player 1 exchanged one of his many a tiles for a random one.\n";

        firstInstance.placeTile(firstInstance.getTurn(), 9, 8, firstInstance.getHand1().get(0));
        currentText = currentText + "\n Player 1 placed a blank tile on the board to the right of the b tile.\n";

        firstInstance.selectBlankTileLetter(firstInstance.getTurn(), 0);
        currentText = currentText + "\n Player 1 selected a value for the blank tile.\n";

        firstInstance.shuffleTiles(firstInstance.getTurn());
        currentText = currentText + "\n Player 2 shuffled her tiles, trying to think of a word.\n";

        firstInstance.skipTurn(firstInstance.getTurn());
        currentText = currentText + "\n Player 2 decided to skip her turn.\n";

        firstInstance.quitGame();
        currentText = currentText + "\n Player 1 decided to quit the game.\n";

        //create another instance and copy
        ScrabbleGameState thirdInstance = new ScrabbleGameState();
        ScrabbleGameState fourthInstance = new ScrabbleGameState(thirdInstance);

        //calling toString, assuring that the results are equivalent, and appending results

        String secondString = secondInstance.toString();
        String fourthString = fourthInstance.toString();

        String first = firstInstance.toString();

        if(secondString.equals(fourthString) == true){
            editT.setText(secondString, TextView.BufferType.NORMAL);

            currentText = currentText + editT.getText().toString();
            editT.setText(currentText + " successful", TextView.BufferType.NORMAL);

        }
        else{
            editT.setText("Strings are not equal.", TextView.BufferType.NORMAL);
        }
    }
}

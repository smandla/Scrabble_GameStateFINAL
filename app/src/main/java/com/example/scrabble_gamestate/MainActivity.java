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
        GameState firstInstance = new GameState();
        GameState secondInstance = new GameState(firstInstance); //TODO Change to deep copy constructor

        //calling each method in GameState once and appending strings
        //TODO Call each action method in GameState on firstInstance at least once


        //create another instance and copy
        GameState thirdInstance = new GameState();
        GameState fourthInstance = new GameState(thirdInstance); //TODO Change to deep copy constructor

        //calling toString, assuring that the results are equivalent, and appending results
        String secondString = secondInstance.toString();
        String fourthString = fourthInstance.toString();


        if(secondString.equals(fourthString) == true){
            editT.setText(secondString, TextView.BufferType.NORMAL);

            currentText = editT.getText().toString();
            editT.setText(currentText + " successful", TextView.BufferType.NORMAL);

        }
        else{
            System.out.println("Strings are not equal.");
        }
    }
}

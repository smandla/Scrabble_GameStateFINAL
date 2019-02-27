package com.example.scrabble_gamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

        runTestButton = findViewById(R.id.runTestButton);

    }

    @Override
    public void onClick(View button) {

        editT.getText().clear();  //TODO Check that this actually clears text
        GameState firstInstance = new GameState();

        //GameState secondInstance = new GameState(firstInstance); //TODO Change to deep copy constructor

        //TODO Call each action method in GameState on firstInstance at least once

        editT.setText("testing", TextView.BufferType.NORMAL);

        GameState thirdInstance = new GameState();

        //GameState fourthInstance = new GameState(thirdInstance); //TODO Change to deep copy constructor

        //String secondString = secondInstance.toString();
        //String fourthString = fourthInstance.toString();

        String currentText = editT.getText().toString();

        editT.setText(currentText + " successful", TextView.BufferType.NORMAL);

        /*if(secondString.equals(fourthString) == true){
            System.out.println(secondString);
            System.out.println(fourthString);
        }
        else{
            System.out.println("Strings are not equal.");
        }*/



    }
}

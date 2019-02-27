package com.example.scrabble_gamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *This is the primary activity for the Scrabble game
 *
 * @author Sydney Wells
 * @author Sarah Bunger
 * @author Kavva Mandla
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
        GameState= //TODO finish

    }
}

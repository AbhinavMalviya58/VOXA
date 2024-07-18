package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class guessTheNumber extends AppCompatActivity {

    EditText guessTheNumber;
    TextView guessTheNumberTextView;
    Button guessTheNumberButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number);

        guessTheNumber = findViewById(R.id.guessTheNumber);
        guessTheNumberTextView = findViewById(R.id.guessTheNumberTextView);
        guessTheNumberButton = findViewById(R.id.guessTheNumberBtn);

//        Random random = new Random();
//        int numberToGuess = random.nextInt(100) + 1; // Generates a random number between 1 and 100
//        int numberOfTries = 0;
//        boolean hasGuessedCorrectly = false;
        guessTheNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = guessTheNumber.getText().toString();
                if(s.isEmpty()){
                    guessTheNumber.setError("Empty");
                    Toast.makeText(guessTheNumber.this, "Number is empty", Toast.LENGTH_SHORT).show();
                }else {
                int kg = Integer.parseInt(s);
                double pound = 2.205 * kg;
                guessTheNumberTextView.setText("The corresponding value in pound is " + pound);
                }
            }
        });

    }
}
package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class guessTheNumber extends AppCompatActivity {

    int num;
    int numCheck;
    int chance = 10;
    int iterate = 0;
    Vibrator vibrator;
    String str;
    EditText guessTheNumber;
    TextView guessTheNumberTextView, number;
    Button guessTheNumberButton, resetBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_the_number);

        guessTheNumber = findViewById(R.id.guessTheNumber);
        guessTheNumberTextView = findViewById(R.id.guessTheNumberTextView);
        guessTheNumberButton = findViewById(R.id.guessTheNumberBtn);
        resetBtn = findViewById(R.id.resetBtn);
        number = findViewById(R.id.number);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Toolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        num = (int) (100 * Math.random());
//        number.setText(Integer.toString(num));

        guessTheNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = guessTheNumber.getText().toString();
                if(s.isEmpty()){
                    guessTheNumber.setError("Empty");
                    Toast.makeText(guessTheNumber.this, "Number is empty", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        numCheck = Integer.parseInt(guessTheNumber.getText().toString());
                    }
                    catch(Exception e) {
                    }

                    if (num == numCheck || chance == iterate) {
                        if (chance == iterate) {
                            str = "***You lost***";
                            guessTheNumberButton.setEnabled(false);
                        } else
                            guessTheNumberTextView.setText("Congratulations You guessed the number");
                    }
                    if (numCheck <= 100 && numCheck >= 0) {
                        iterate++;
                        if (num < numCheck) {
                            guessTheNumberTextView.setText("Too high Try again.");
                        }
                        if (num > numCheck) {
                            guessTheNumberTextView.setText("Too low Try again.");
                        }
                    } else {
                        guessTheNumberTextView.setText("Invalid Option");
                    }
                }
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iterate = 0;
                guessTheNumber.getText().clear();
                guessTheNumberButton.setEnabled(true);
//                num = (int) (100 * Math.random());
//                number.setText(Integer.toString(num));
                recreate();
            }
        });

    }
}
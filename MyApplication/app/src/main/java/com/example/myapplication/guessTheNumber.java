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

//        guessTheNumber.findViewById(R.id.guessTheNumber);
//        guessTheNumberTextView.findViewById(R.id.guessTheNumberTextView);
        guessTheNumberButton.findViewById(R.id.guessTheNumberBtn);

        guessTheNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(guessTheNumber.this, " xyz ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
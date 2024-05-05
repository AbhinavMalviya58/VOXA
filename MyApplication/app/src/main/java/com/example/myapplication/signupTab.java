package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class signupTab extends AppCompatActivity {
Button login_btn1st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_tab);
        login_btn1st = findViewById(R.id.login_btn1st);
        login_btn1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupTab.this,loginTab.class));
                Animatoo.INSTANCE.animateSwipeRight(signupTab.this);
            }
        });
    }
}
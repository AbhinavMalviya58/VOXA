package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {
    ImageView profilePic;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        profilePic = findViewById(R.id.profilePic);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.imganim);
        profilePic.startAnimation(animation);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "Your Profile", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, profilePage.class));
                Animatoo.INSTANCE.animateShrink(MainActivity.this);
            }
        });
    }
    
}
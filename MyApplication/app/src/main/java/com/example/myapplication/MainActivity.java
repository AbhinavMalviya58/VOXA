package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView profilePic;
    TextView guessTheNumber;
    RecyclerView recyclerView;
    Button button;
    Animation animation;
    ArrayList<userModel> arrUserModel = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilePic = findViewById(R.id.profilePic);
//        guessTheNumber = findViewById(R.id.guessTheNumberHome);
        recyclerView = findViewById(R.id.media);

        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.imganim);
//        profilePic.startAnimation(animation);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.dp, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.google, "Abhinav Malviya", "hai ", "@string/random_text"));
//        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.img1, "Abhinav Malviya", "hai ", "@string/random_text"));
//        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.img2, "Abhinav Malviya", "hai ", "@string/random_text"));
//        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.images, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));
        arrUserModel.add(new userModel(R.drawable.logo, R.drawable.facebook, "Abhinav Malviya", "hai ", "@string/random_text"));

        RecyclerUserModelAdapter adapter = new RecyclerUserModelAdapter(this, arrUserModel);
        recyclerView.setAdapter(adapter);

//        guessTheNumber.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Guess The Number", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, guessTheNumber.class));
//                Animatoo.INSTANCE.animateSlideRight(MainActivity.this);
//            }
//        });

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(MainActivity.this,
                        "Your Profile", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, profilePage.class));
                Animatoo.INSTANCE.animateSlideUp(MainActivity.this);
            }
        });
    }
}
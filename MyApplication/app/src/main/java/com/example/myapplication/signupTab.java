package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapplication.Models.Users;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupTab extends AppCompatActivity {
    ActivityMainBinding binding;
    TextView name, signup_email, signup_password, signup_confirm;
    Button login_btn1st, signup_button;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser mUser;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_tab);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        login_btn1st = findViewById(R.id.login_btn1st);
        signup_button = findViewById(R.id.signup_button);
        name = findViewById(R.id.name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);


        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.createUserWithEmailAndPassword(signup_email.getText().toString(),signup_password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Users user = new Users(name.getText().toString(),signup_email.getText().toString(),signup_password.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(signupTab.this, "User Created Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signupTab.this, "User already login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        login_btn1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupTab.this,loginTab.class));
                Animatoo.INSTANCE.animateSwipeRight(signupTab.this);
            }
        });
    }
}
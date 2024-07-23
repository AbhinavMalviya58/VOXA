package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
    ImageView logo;

    Animation animation;
    TextView name, signup_email, signup_password, signup_confirm;
    private CheckBox showPassword;
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
        showPassword = findViewById(R.id.showPassword);
        name = findViewById(R.id.name);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        logo = findViewById(R.id.logo);

        animation = AnimationUtils.loadAnimation(signupTab.this,R.anim.imganim);
        logo.startAnimation(animation);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    signup_password.setTransformationMethod(null);
                }else {
                    signup_password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Enter your name");
                    return;
                }if(TextUtils.isEmpty(signup_email.getText().toString())){
                    signup_email.setError("Email is compulsory");
                    return;
                }if (TextUtils.isEmpty(signup_password.getText().toString())){
                    signup_password.setError("please entre your password");
                    return;
                }else {
//                    Toast.makeText(signupTab.this, "", Toast.LENGTH_SHORT).show();
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
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.myapplication.Models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class loginTab extends AppCompatActivity {
private EditText login_password, login_email;
LinearLayout google, facebook;
TextView join_voxa;
Button signup_btn1st, login_button;
private CheckBox showPassword;
Animation animation;
ImageView logo;
FirebaseAuth mAuth;
FirebaseDatabase database;
FirebaseUser mUser;
GoogleSignInClient gsc;
GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_tab);

//        signup_btn1st = findViewById(R.id.signup_btn1st);
        login_button = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_email);
        google = findViewById(R.id.google);
        facebook= findViewById(R.id.facebook);
        logo = findViewById(R.id.logo);
        login_password = findViewById(R.id.login_password);
        showPassword = findViewById(R.id.showPassword);
        join_voxa = findViewById(R.id.join_voxa);


        animation = AnimationUtils.loadAnimation(loginTab.this,R.anim.imganim);
//        logo.startAnimation(animation);

//        Database
        database = FirebaseDatabase.getInstance();

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    login_password.setTransformationMethod(null);
                }else {
                    login_password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

       gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
               .requestIdToken(getString(R.string.Web_client_ID))
               .requestEmail()
               .build();

       mAuth = FirebaseAuth.getInstance();
       mUser = mAuth.getCurrentUser();
       gsc = GoogleSignIn.getClient(this,gso);

       GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
       if(account != null){
           navigateToSecondActivity();
       }
       join_voxa.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(loginTab.this, signupTab.class);
               startActivity(intent);
               Animatoo.INSTANCE.animateSwipeLeft(loginTab.this);
               Toast.makeText(loginTab.this, "Join VOXA", Toast.LENGTH_SHORT).show();
           }
       });
       google.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               SignIn();
               Toast.makeText(loginTab.this, "Sign in with Google", Toast.LENGTH_SHORT).show();
           }
       });
       facebook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(loginTab.this, "Sign in with Facebook", Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void SignIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account =  task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                navigateToSecondActivity();
            }catch (ApiException e){
                Toast.makeText(getApplicationContext()," Something went wrong ",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken){

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Users users = new Users();
                    users.setUserName(user.getDisplayName());
//                    users.setProfilePic(user.getPhotoUrl().toString());
                    users.setUserId(user.getUid());
                    database.getReference().child("Users").child(user.getUid()).setValue(users);
//                    UpdateUI(user);
                }
                else {
                    Toast.makeText(loginTab.this,""+task.getException(),Toast.LENGTH_SHORT).show();
//                    UpdateUI(null);
                    finish();
                }
            }
        });
    }

    private void UpdateUI(FirebaseUser user) {
        Intent intent = new Intent(loginTab.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    void navigateToSecondActivity(){
        finish();
        startActivity(new Intent(loginTab.this, MainActivity.class));
        Animatoo.INSTANCE.animateZoom(loginTab.this);
    }
}

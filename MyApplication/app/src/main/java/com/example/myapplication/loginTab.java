package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
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
Button signup_btn1st;
ImageView google;
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

        signup_btn1st = findViewById(R.id.signup_btn1st);
        google = findViewById(R.id.google);

        database = FirebaseDatabase.getInstance();
        signup_btn1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginTab.this,signupTab.class));
                Animatoo.INSTANCE.animateSwipeLeft(loginTab.this);
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

       google.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SignIn();
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
                    UpdateUI(user);
                }
                else {
                    Toast.makeText(loginTab.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    UpdateUI(null);
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

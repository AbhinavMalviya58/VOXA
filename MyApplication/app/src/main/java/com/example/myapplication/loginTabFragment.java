package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class loginTabFragment extends Fragment {

    EditText login_email,login_password;
    TextView forgotPassword;
    ImageView google,facebook;
    Button login_button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase Database;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        login_email = view.findViewById(R.id.login_email);
        login_password = view.findViewById(R.id.login_password);
        login_button = view.findViewById(R.id.login_button);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        google = view.findViewById(R.id.google);
        facebook = view.findViewById(R.id.facebook);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        Database = FirebaseDatabase.getInstance();

         

//          Login Button Set on click listener
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforAoth();
            }

            private void perforAoth() {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if(!email.matches(emailPattern)){
                    login_email.setError("Enter Connext Email");
                } else if (password.isEmpty() || password.length()<6) {
                    login_password.setError("Enter proper password <6,a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                }else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserTonextActivity();
                                Toast.makeText(getActivity(), "This is my Toast message!",
                                        Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getActivity(), "Toast message!"+task.getException(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        private void sendUserTonextActivity() {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            getActivity().overridePendingTransition(0, 0);
                        }
                    });
                }
            }
        });
//        forgot Password set on click listener
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Forgot Password",Toast.LENGTH_LONG).show();
            }
        });
//           facebook set on click listener
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast .makeText(getActivity(),"facebook",Toast.LENGTH_LONG).show();
            }
        });
//          Google Set On Click Listener

        return view;
    }
}
package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signupTabFragment extends Fragment {

    EditText signup_email,signup_password,signup_confirm;
    Button signup_button;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);

        signup_email = view.findViewById(R.id.signup_email);
        signup_password = view.findViewById(R.id.signup_password);
        signup_confirm = view.findViewById(R.id.signup_confirm);
        signup_button = view.findViewById(R.id.signup_button);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

    signup_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            perforAoth();
        }

        private void perforAoth() {
            String email = signup_email.getText().toString();
            String password = signup_password.getText().toString();
            String confirmPassword = signup_confirm.getText().toString();

            if(!email.matches(emailPattern)){
                signup_email.setError("Enter Connext Email");
            } else if (password.isEmpty() || password.length()<6) {
                signup_password.setError("Enter proper password <6,a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
            } else if (!password.equals(confirmPassword)) {
                signup_confirm.setError("Password Not Match Both field");
            }else {
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isComplete()){
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
                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                    }
                });
            }
        }
    });
        return view;
    }
}
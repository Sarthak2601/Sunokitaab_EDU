package com.sunokitaab.sunokitaab.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sunokitaab.sunokitaab.MainUi;
import com.sunokitaab.sunokitaab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signIn extends AppCompatActivity {

    TextView emailID;
    TextView password;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().setTitle("Login");

        emailID = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if ( firebaseUser != null){
                    Toast.makeText(signIn.this, "Hi " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                    Intent signIn = new Intent(signIn.this, MainUi.class);
                    startActivity(signIn);
                }
            }
        };


        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView3);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signIn.this, signUp.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String pwd = password.getText().toString();

                if(email.isEmpty()){
                    emailID.setError("Please enter E-mail address");
                    emailID.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter Password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(signIn.this, "Please enter the required fields to continue", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){

                    firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(signIn.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!(task.isSuccessful())){
                                Toast.makeText(signIn.this, "Log-in Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent = new Intent(signIn.this, MainUi.class);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    Toast.makeText(signIn.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });}

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}

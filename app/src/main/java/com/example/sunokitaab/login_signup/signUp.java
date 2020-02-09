package com.example.sunokitaab.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunokitaab.MainUi;
import com.example.sunokitaab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {

    private static final String TAG = "signUp" ;
    Button button;
    TextView em,pwd,cpwd,nme,school,phoneNumber;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;
    Spinner being;
    String identity = "";

    public void signUp(View view){
        final String email = em.getText().toString();
        String password = pwd.getText().toString();
        String cpassword = cpwd.getText().toString();
        final String schoolName = school.getText().toString();
        final String phone = phoneNumber.getText().toString();

        being.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    identity = being.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                being.requestFocus();
            }
        });

        final String userName = nme.getText().toString();

        if(email.isEmpty() && password.isEmpty() && userName.isEmpty() && cpassword.isEmpty()){

            Toast.makeText(signUp.this, "Please enter the required fields", Toast.LENGTH_SHORT).show();
        }

        else if(email.isEmpty()){

            em.setError("Please enter your E-mail");
            em.requestFocus();

        }
        else if(userName.isEmpty()){
            nme.setError("Please enter your Name");
            nme.requestFocus();
        }
        else if(password.isEmpty()){
            pwd.setError("Please enter your Password");
            pwd.requestFocus();
        }

        else if(cpassword.isEmpty()){
            cpwd.setError("Please confirm your Password");
            cpwd.requestFocus();
        }

        else if(schoolName.isEmpty()){
            school.setError("Please enter your School Name");
            school.requestFocus();
        }

        else if(phone.isEmpty()){
            phoneNumber.setError("Please enter your Phone Number");
            phoneNumber.requestFocus();
        }
        else if(identity.isEmpty() || identity.equals("Select Identity")){
            Toast.makeText(this, "Please enter your Identity", Toast.LENGTH_SHORT).show();
            being.requestFocus();
        }
        else{

            if (!(cpassword.equals(password))) {

                cpwd.setError("Password doesn't match.");
                cpwd.requestFocus();

            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                          //  Toast.makeText(signUp.this, "Hi " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                            Intent signInn = new Intent(signUp.this, MainUi.class);

                            Map<String, Object> user = new HashMap<>();
                            user.put("Name ", userName);
                            user.put("Email", email);
                            user.put("School", schoolName);
                            user.put("Phone Number", phone);
                            user.put("Identity", identity);

                            db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "onSuccess: Data Entered in Store");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: Data entry failed. " + e.getMessage());
                                            e.printStackTrace();
                                        }
                                    });

                            startActivity(signInn);
                            finish();

                        }
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                                Toast.makeText(signUp.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");

        em = findViewById(R.id.editText3);
        pwd = findViewById(R.id.editText4);
        button = findViewById(R.id.button);
        cpwd = findViewById(R.id.editText5);
        firebaseAuth = FirebaseAuth.getInstance();
        nme = findViewById(R.id.name);
        school = findViewById(R.id.school);
        phoneNumber = findViewById(R.id.phoneNumber);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        being = findViewById(R.id.spinner);

        ArrayAdapter<String> beingAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.being));
        beingAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        being.setAdapter(beingAdapter);

    }
}

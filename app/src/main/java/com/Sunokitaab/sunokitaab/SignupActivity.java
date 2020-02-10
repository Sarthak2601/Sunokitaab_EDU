package com.Sunokitaab.sunokitaab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUpUser(View view) {
        Toast.makeText(this, "You have been Signed up!", Toast.LENGTH_SHORT ).show();
        finish();
    }
}

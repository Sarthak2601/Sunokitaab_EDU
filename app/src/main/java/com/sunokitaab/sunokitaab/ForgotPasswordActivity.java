package com.sunokitaab.sunokitaab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void setNewPassword(View view) {
        Toast.makeText(this, "Password Reset!", Toast.LENGTH_SHORT ).show();
        finish();
    }
}

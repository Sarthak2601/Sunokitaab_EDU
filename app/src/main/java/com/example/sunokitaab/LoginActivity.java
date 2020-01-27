package com.example.sunokitaab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sunokitaab.Class_Subject_Audios.Audios;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    List<AuthUI.IdpConfig> providers;
    private static final int REQ_CODE = 2;
    FirebaseAuth firebaseAuth;

    public static final String EXTRA_MESSAGE = "com.example.sunokitaab.MESSAGE email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        File folder = new File(Environment.getExternalStorageDirectory() + "/SunoKitaab");
        if(!folder.exists()){
            folder.mkdir();
        }

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        if(firebaseUser != null){
            Toast.makeText(this, "Hi " + firebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainUi.class));
            finish();
        }
        else{
            showSignInOptions();
        }



    }
    public void showSignInOptions(){

        startActivityForResult(

                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .build(),REQ_CODE
        );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(LoginActivity.this, "Hi " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainUi.class));
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this, ""+response.getError(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}

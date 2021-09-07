package com.example.shoponline.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shoponline.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            Toast.makeText(this,"Vui lòng đăng nhập !",Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);
            finish();
        }
    }

    public void login(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));
    }
}
package com.example.shoponline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoponline.R;
import com.example.shoponline.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    Button btn_signUp;
    EditText name, email, password, confirmPassword;
    TextView signIn;

    FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        btn_signUp = findViewById(R.id.btn_sign_up);
        signIn = findViewById(R.id.tv_sign_in);
        name = findViewById(R.id.edt_reg_name);
        email = findViewById(R.id.edt_reg_email);
        password = findViewById(R.id.edt_reg_password);
        confirmPassword = findViewById(R.id.edt_reg_cfpassword);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });


    }

    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Không Được Để Trống Tên", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Không Được Để Trống Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Không Được Để Trống Mật Khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length() < 6) {
            Toast.makeText(this, "Mật Khẩu Quá Ngắn ! Mật Khẩu Trên 6 Ký Tự !", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.equals(confirmPassword.getText().toString()) == false) {
            Toast.makeText(this, "Mật Khẩu Nhập Lại Không Giống !", Toast.LENGTH_SHORT).show();
            return;
        }

        //Tạo tài khoản vào Firebase Auth
        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    UserModel user = new UserModel(userName, userEmail, userPassword);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("User").child(id).setValue(user);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegistrationActivity.this, "Lỗi:" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
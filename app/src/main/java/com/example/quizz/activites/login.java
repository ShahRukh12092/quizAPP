package com.example.quizz.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    FirebaseAuth mAuth;
    public ProgressBar pr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        TextView start = findViewById(R.id.signupClick);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
                finish();
            }
        });


        TextView loge = findViewById(R.id.loginEmail);
        TextView logp = findViewById(R.id.loginPassword);
        Button btn = findViewById(R.id.loginBtn);
        pr = findViewById(R.id.progressBar2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loge.getText().toString().trim();
                String pass = logp.getText().toString().trim();

                if (email.isEmpty()) {
                    loge.setError("email is requird");
                    return;
                }
                if (pass.isEmpty()) {
                    logp.setError("password should not be empty");
                    return;
                }
                if (pass.length() < 8) {
                    loge.setError("password should be greater than 8 characters");
                    return;
                }
                pr.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successfully signed up", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), QuizMainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            pr.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Error" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
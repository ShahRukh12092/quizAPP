package com.example.quizz.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizz.R;
import com.example.quizz.activites.QuizMainActivity;
import com.example.quizz.activites.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        TextView start= findViewById(R.id.signinClick);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });


        EditText semail = findViewById(R.id.signupEmail);
        EditText spassw = findViewById(R.id.signupPassword);
        EditText scpass = findViewById(R.id.signupConf_Password);
        ProgressBar pr =findViewById(R.id.progressBar);
        Button btn= findViewById(R.id.signupbtn);
        mAuth= FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = semail.getText().toString().trim();
                String pass = spassw.getText().toString().trim();
                String cpass = scpass.getText().toString().trim();

                if(email.isEmpty()){
                    semail.setError("email is requird");
                    return;
                }
                if (pass.isEmpty()){
                    spassw.setError("password should not be empty");
                    return;
                }
                if (cpass.isEmpty()){
                    scpass.setError("password should not be empty");
                    return;
                }
                if (pass.length() < 8){
                    spassw.setError("password should be greater than 8 characters");
                    return;
                }
                if (cpass.length() < 8 ){
                    scpass.setError("password should be greater than 8 characters");
                    return;
                }

                if(!pass.equals(cpass)){
                    Toast.makeText(getApplicationContext(),"password and confirm password do not match",Toast.LENGTH_LONG).show();
                    return;
                }
                pr.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"user successfully signed up",Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(getApplicationContext(), QuizMainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"Error"+task.getException(),Toast.LENGTH_LONG).show();
                        }

                    }

                });

            }

        });

    }
}
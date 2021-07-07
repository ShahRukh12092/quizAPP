package com.example.quizz.activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    TextView textView;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=firebaseAuth.getInstance();
        textView=findViewById(R.id.Useremail);
        textView.setText(firebaseAuth.getCurrentUser().getEmail());


        logout=findViewById(R.id.logoutactivtiy);

        logout.setOnClickListener(view -> {
            firebaseAuth.getInstance().signOut();
            Intent intent=new Intent(this,login.class);
            startActivity(intent);
        });

    }
}
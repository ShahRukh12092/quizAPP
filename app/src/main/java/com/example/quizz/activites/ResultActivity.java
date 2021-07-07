package com.example.quizz.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz.R;
import com.example.quizz.models.Question;
import com.example.quizz.models.Quiz;
import com.google.gson.Gson;

public class ResultActivity extends AppCompatActivity {

    Gson gson=new Gson();
    Quiz quiz=new Quiz();
    Button home;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        home=findViewById(R.id.goHome);
        setupviews();

        home.setOnClickListener(view -> {
            Intent intent=new Intent(this,QuizMainActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void setupviews() {
     String quizdata = getIntent().getStringExtra("Quiz");
     quiz=gson.fromJson(quizdata,Quiz.class);
        Log.i("TAG-Score", "setupviews: -->"+quiz.question.toString());
        Log.d("json",quizdata);


     calculateScore();
        //setAnswerView();
    }

    private void calculateScore() {
    int score=0;
        TextView textView = findViewById(R.id.score);
        for (int counter=1; counter <= quiz.question.size(); counter++) {
            Question question = quiz.question.get("question"+counter);
            if(question.userAnswer.equals(question.answer)){
                score += 10;
            }

        }
        textView.setText(score+"");
        Log.i("TAG-Score", "calculateScore: -->"+score);

    }
}
package com.example.quizz.activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.R;
import com.example.quizz.adapter.Option_adapter;
import com.example.quizz.models.Question;
import com.example.quizz.models.Quiz;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

public class Questions extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    Intent intent;
    Gson gson =new Gson();
    Map<String,Question> questions;
    ArrayList<Quiz> quizzes;
    int index;
    TextView des;
    Button prev,next,submit;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        des=findViewById(R.id.description);
        prev =findViewById(R.id.Previous);
        next =findViewById(R.id.next);
        submit =findViewById(R.id.submit);

        quizzes=new ArrayList<>();
        questions= Map.of();
        index=1;
        setupfirebase();
        setupeventlistner();
    }

    private void setupeventlistner() {
        next.setOnClickListener(view -> {
            index=index+1;
            bindview();
        });

        prev.setOnClickListener(view -> {
            index=index-1;
            bindview();
        });
        submit.setOnClickListener(view -> {
            Log.d("submit",questions.toString());
            Intent intent=new Intent(this,ResultActivity.class);
             String json=gson.toJson(quizzes.get(0));
             intent.putExtra("Quiz",json);
             startActivity(intent);
             finish();
        });
    }

    private void setupfirebase() {
        firebaseFirestore= FirebaseFirestore.getInstance();
        String date= getIntent().getStringExtra("DATE");
        Log.d("hello",date);
        if (date != null){
            firebaseFirestore.collection("Quizzes").whereEqualTo("title",date)
                    .get()
                    .addOnSuccessListener(it->{
                        quizzes= (ArrayList<Quiz>) it.toObjects(Quiz.class);
                        Log.d("shah",it.toObjects(Quiz.class).toString());
                        questions=quizzes.get(0).question;
                        Log.d("rukh",questions.toString());
                        bindview();
                    });
        }


    }

    private void bindview() {

        //Question question=new Question("hello","1","2","3","4","56");
        Question question = questions.get("question"+index);
        RecyclerView recyclerView;

        Log.d("size", String.valueOf(questions.size()));

        prev.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        if(index==1){
            next.setVisibility(View.VISIBLE);
        }else if(index==questions.size()){
            prev.setVisibility(View.VISIBLE);
            Log.d("oye","check kar yr");
            submit.setVisibility(View.VISIBLE);
            Log.d("oye2","check kar yr gin mazy");
            Log.d("oye3", String.valueOf(questions.size()));
            Log.d("oye4", String.valueOf(index));
            next.setVisibility(View.INVISIBLE);
        }else {
            prev.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
        des.setText(question.description);
        recyclerView=findViewById(R.id.recyclerView1);
        Option_adapter adapter=new Option_adapter(question);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }
}
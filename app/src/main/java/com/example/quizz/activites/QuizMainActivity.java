package com.example.quizz.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.R;
import com.example.quizz.adapter.adapter;
import com.example.quizz.models.Quiz;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuizMainActivity extends AppCompatActivity
{

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    FirebaseFirestore firebaseFirestore;
    ArrayList<Quiz> arrayList = new ArrayList<Quiz>();
    adapter adapter=new adapter(arrayList,this);
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);
        recyclerView=findViewById(R.id.recyclerview);

        navbr();
        setupfirestore();
        recyclerView();
        setdatepicker();

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
    private void setdatepicker() {

        FloatingActionButton floatAction = findViewById(R.id.fab);
        floatAction.setOnClickListener(view -> {
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().build();
        datePicker.show(getSupportFragmentManager(), "Date Picker");

        datePicker.addOnPositiveButtonClickListener(v->{
            Intent intent= new Intent(this,Questions.class);

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("DD-MM-YYYY");
            String date=simpleDateFormat.format(v);

            Log.d("d",date);
            intent.putExtra("DATE",date);

            startActivity(intent);
            finish();

        });
        datePicker.addOnNegativeButtonClickListener(v -> {
            Log.d("date",datePicker.getHeaderText());
        });
        datePicker.addOnCancelListener(v->{
            Log.d("date",datePicker.getHeaderText());
        });

        });
    }
    private void setupfirestore() {
        firebaseFirestore= FirebaseFirestore.getInstance();
        CollectionReference collection= firebaseFirestore.collection("Quizzes");
        collection.addSnapshotListener((value, error) -> {

            if(value == null || error != null){
                Toast.makeText(this,"error ",Toast.LENGTH_LONG).show();
                return;
            }
            Log.d("data",value.toObjects(Quiz.class).toString());
            arrayList.clear();
            arrayList.addAll(value.toObjects(Quiz.class));
            adapter.notifyDataSetChanged();
        });
    }

    private void recyclerView() {


/*
        Quiz quiz= new Quiz("123","quiz1");
        arrayList.add(quiz);
        arrayList.add(new Quiz("321","Quiz2"));
        arrayList.add(new Quiz("321","Quiz3"));
        arrayList.add(new Quiz("321","Quiz4"));
        arrayList.add(new Quiz("321","Quiz5"));
        arrayList.add(new Quiz("321","Quiz6"));
        arrayList.add(new Quiz("321","Quiz7"));
        arrayList.add(new Quiz("321","Quiz8"));
*/


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);


    }

    private void navbr() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=(NavigationView)findViewById(R.id.navmenu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.Home :
                        Toast.makeText(getApplicationContext(),"Home Panel is Open",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout :
                        Intent intent=new Intent(QuizMainActivity.this,ProfileActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
    }
}









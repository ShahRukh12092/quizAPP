package com.example.quizz.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.R;
import com.example.quizz.activites.Questions;
import com.example.quizz.models.Quiz;
import com.example.quizz.utlis.iconpicker;

import java.util.ArrayList;


public class adapter extends RecyclerView.Adapter<adapter.myviwholder>
{

    ArrayList<Quiz> datalist;
    Context context;
    public adapter() {
    }

    public adapter(ArrayList<Quiz> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public myviwholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_items,parent,false);
        return new myviwholder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull myviwholder holder, int position) {

        holder.titile.setText(datalist.get(position).title);
        //holder.container.setCardBackgroundColor(Color.parseColor(colorpicker.getcolor()));
        holder.icon.setImageResource(iconpicker.geticon());
        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(context, Questions.class);
            intent.putExtra("DATE",datalist.get(position).title);
            Log.d("post",datalist.get(position).title);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviwholder extends RecyclerView.ViewHolder {

        TextView titile;
        ImageView icon;
        CardView container;
        public myviwholder(@NonNull View itemView) {
            super(itemView);

        titile=itemView.findViewById(R.id.quiztitle);
        icon =itemView.findViewById(R.id.quizicon);
        container=itemView.findViewById(R.id.cardContainer);

        }
    }

}

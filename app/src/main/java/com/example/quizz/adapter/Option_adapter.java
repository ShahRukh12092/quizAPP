package com.example.quizz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.R;
import com.example.quizz.models.Question;

public class Option_adapter extends RecyclerView.Adapter<Option_adapter.myviwholder>
{

    Question question=new Question();
    String[] options;

    public Option_adapter(Question question) {
    this.question=question;
    options=new String[]{question.option1,question.option2,question.option3,question.option4};

    }





    @NonNull
    @Override
    public myviwholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.option_items,parent,false);
        return new myviwholder((view));

    }

    @Override
    public void onBindViewHolder(@NonNull myviwholder holder, int position) {

        holder.option.setText(options[position]);

        holder.itemView.setOnClickListener(v ->{
            question.userAnswer=options[position];
            notifyDataSetChanged();
        });

        if(question.userAnswer==options[position]){
            holder.itemView.setBackgroundResource(R.drawable.selected);
        }
        else {
            holder.itemView.setBackgroundResource(R.drawable.notselected);
        }


    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    class myviwholder extends RecyclerView.ViewHolder {

        TextView option;

        public myviwholder(@NonNull View itemView) {
            super(itemView);

            option=itemView.findViewById(R.id.quizoptions);


        }
    }

}

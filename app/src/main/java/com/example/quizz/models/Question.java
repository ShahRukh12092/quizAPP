package com.example.quizz.models;

public class Question {


    public String description=" ";
    public String option1=" ";
    public String option2=" ";
    public String option3=" ";
    public String option4=" ";
    public String answer=" ";
    public String userAnswer=" ";



    public Question(){

    }

    public Question(String description, String option1, String option2, String option3, String option4, String answer) {
        this.description = description;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.userAnswer=" ";

    }


    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", answer='" + answer + '\'' +
                ", useranswer='" + userAnswer + '\'' +'}';
    }
}

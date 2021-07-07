package com.example.quizz.models;

import java.util.HashMap;
import java.util.Map;

public class Quiz {

 public Quiz() {
 }

 public String id= "";
 public String title ="";

 public Map<String,Question> question;


 public Quiz(String id, String title, HashMap<String, Question> question) {
  this.id = id;
  this.title = title;
  this.question = question;
 }

 @Override
 public String toString() {
  return "Quiz{" +
          "id='" + id + '\'' +
          ", title='" + title + '\'' +
          ", question=" + question +
          '}';
 }
}

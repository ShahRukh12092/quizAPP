package com.example.quizz.utlis;

import java.util.ArrayList;

public class colorpicker {

     static int current = 0;

     static ArrayList<String> colors= new ArrayList<String>();

    public colorpicker() {

        colors.add("3EB9DF");
        colors.add("36858C");
        colors.add("D36280");
        colors.add("E44F55");
        colors.add("FA8056");
        colors.add("7D659F");
        colors.add("B5BFC6");
        colors.add("51BAB3");
    }
/*
    static String[] color = {
            "3EB9DF",
            "36858C",
            "D36280",
            "E44F55",
            "FA8056",
            "818BCA",
            "7D659F",
            "51BAB3",
            "B5BFC6"
    };*/

    public static String getcolor() {
        current = (current + 1) % colors.size();
        return colors.get(current);
    }
}



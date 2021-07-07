package com.example.quizz.utlis;

import com.example.quizz.R;

public class iconpicker {

    static int current=0;



    static int[] icons = {
        R.drawable.ic_icon1,
        R.drawable.ic_icon2,
        R.drawable.ic_icon3,
        R.drawable.ic_icon4,
        R.drawable.ic_icon5,
        R.drawable.ic_icon6,
        R.drawable.ic_icon7,
        R.drawable.ic_icon8
    };

    public static int geticon(){
        current=(current+1)%icons.length;
        return icons[current];
    }

}

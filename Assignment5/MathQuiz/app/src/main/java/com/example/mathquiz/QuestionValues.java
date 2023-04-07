package com.example.mathquiz;

import androidx.annotation.NonNull;

public class QuestionValues {
    // i'm going to be real - i did this so i didn't have to deal with 2D arrays
    private int[] pair;
    private int val;

    public QuestionValues(int tens, int ones){
        pair = new int[]{tens, ones};
        val = tens * 10 + ones;
    }

    public int[] getPair(){
        return pair;
    }

    public int getTens(){
        return pair[0];
    }

    public int getOnes(){
        return pair[1];
    }

    public int getVal(){
        return val;
    }

    @NonNull
    @Override
    public String toString() {
        return Integer.toString(val);
    }
}

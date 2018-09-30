package com.example.abiralrai.fitdiaryproject;


import android.content.Context;

public class Equations {
    public static int dpToPx(Context context, float dp) {
        // Took from http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }
}

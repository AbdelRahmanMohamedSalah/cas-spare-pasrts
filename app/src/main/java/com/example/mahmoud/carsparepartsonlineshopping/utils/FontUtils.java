package com.example.mahmoud.carsparepartsonlineshopping.utils;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.R;

public class FontUtils {

    public static void setPoppinsFont(TextView textView) {
        Typeface tf = ResourcesCompat.getFont(textView.getContext(), R.font.poppins);
        textView.setTypeface(tf);
    }
}

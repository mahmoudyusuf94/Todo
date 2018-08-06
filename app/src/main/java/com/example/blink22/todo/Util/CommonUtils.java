package com.example.blink22.todo.Util;

import android.text.format.DateFormat;

import java.util.Date;

public class CommonUtils {

    private static final String EASY_DATE_FORMAT = "EEEE, MMMM dd, yyyy";

    public static CharSequence formatDate(Date date){
        return DateFormat.format(EASY_DATE_FORMAT,date);
    }

}

package com.utilities;

public class Format {

    // Split the date sent as a string in the format of "YYYY-MM-DD"
    public static String formatDate(String date){
        String [] formatter = date.split("-");
        return formatter[2]+"/"+formatter[1];
    }
}

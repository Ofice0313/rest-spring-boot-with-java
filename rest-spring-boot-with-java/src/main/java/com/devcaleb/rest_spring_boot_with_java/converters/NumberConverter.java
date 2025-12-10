package com.devcaleb.rest_spring_boot_with_java.converters;

import com.devcaleb.rest_spring_boot_with_java.exceptions.UnsupportedMathOperationException;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) throws IllegalArgumentException{
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String number){
        if(number == null || number.isEmpty()) return false;
        String strNumber = number.replace(",", ".");
        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}

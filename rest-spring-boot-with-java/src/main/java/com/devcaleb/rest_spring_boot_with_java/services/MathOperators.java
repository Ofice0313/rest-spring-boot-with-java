package com.devcaleb.rest_spring_boot_with_java.services;

import org.springframework.stereotype.Service;

@Service
public class MathOperators {

    public Double sum(Double numberOne, Double numberTwo) {
        return numberOne + numberTwo;
    }

    public Double subtraction(Double numberOne, Double numberTwo) {
        return numberTwo - numberOne;
    }

    public Double multiplication(Double numberOne, Double numberTwo) {
        return numberTwo * numberOne;
    }

    public Double division(Double numberOne, Double numberTwo) {
        return numberTwo / numberOne;
    }

    public Double squareRoot(Double number) {
        return Math.sqrt(number);
    }

    public Double average(Double numberOne, Double numberTwo){
        return (numberOne + numberTwo) / 2;
    }
}

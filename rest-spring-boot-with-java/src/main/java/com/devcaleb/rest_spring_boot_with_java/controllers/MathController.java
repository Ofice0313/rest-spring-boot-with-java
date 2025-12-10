package com.devcaleb.rest_spring_boot_with_java.controllers;

import com.devcaleb.rest_spring_boot_with_java.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    //http://localhost:8080/math/sum/3/4
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    )throws Exception{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/sub/6/4
    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    )throws Exception{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/mul/3/4
    @RequestMapping("/mul/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    )throws Exception{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/div/3/4
    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    )throws Exception{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    //http://localhost:8080/math/sqrt/3
    @RequestMapping("/sqrt/{numberOne}")
    public Double squareRoot(
            @PathVariable("numberOne") String numberOne
    )throws Exception{
        if (!isNumeric(numberOne))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        if (convertToDouble(numberOne) < 0) throw new UnsupportedMathOperationException("Please enter a positive number");
        return Math.sqrt(convertToDouble(numberOne));
    }

    //http://localhost:8080/math/avg/3/4/2
    @RequestMapping("/avg/{numberOne}/{numberTwo}/{numberThree}")
    public Double average(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo,
            @PathVariable("numberThree") String numberThree

    )throws Exception{
        if (!isNumeric(numberOne) || !isNumeric(numberTwo) || !isNumeric(numberThree))
            throw new UnsupportedMathOperationException("Please set a numeric value");
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / convertToDouble(numberThree);
    }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException{
        if(strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Please set a numeric value");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public boolean isNumeric(String number){
        if(number == null || number.isEmpty()) return false;
        String strNumber = number.replace(",", ".");
        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }


}

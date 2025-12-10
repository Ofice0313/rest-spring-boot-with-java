package com.devcaleb.rest_spring_boot_with_java.controllers;

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
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new IllegalArgumentException();
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private Double convertToDouble(String strNumber) throws IllegalArgumentException{
        if(strNumber == null || strNumber.isEmpty()) throw new IllegalArgumentException();
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    public boolean isNumeric(String number){
        if(number == null || number.isEmpty()) return false;
        String strNumber = number.replace(",", ".");
        return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");

    }
    //http://localhost:8080/math/sum/3/4

    //http://localhost:8080/math/sum/3/4
}

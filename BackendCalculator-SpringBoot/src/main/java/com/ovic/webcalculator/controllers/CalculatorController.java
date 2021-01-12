package com.ovic.webcalculator.controllers;

import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CalculatorController {


    @GetMapping("/add")
    public String add(@RequestParam(value = "first", defaultValue = "0") double firstNumber, @RequestParam(value = "second", defaultValue = "0") double secondNumber) {
        return String.format((firstNumber + secondNumber) + "");
    }

    @GetMapping("/subtract")
    public String subtract(@RequestParam(value = "first", defaultValue = "0") double firstNumber, @RequestParam(value = "second", defaultValue = "0") double secondNumber) {
        return String.format((firstNumber - secondNumber) + "");
    }

    @GetMapping("/divided")
    public String divided(@RequestParam(value = "first", defaultValue = "1") double firstNumber, @RequestParam(value = "second", defaultValue = "1") double secondNumber) {
        if(secondNumber == 0)  {
          return "ERROR!";
        } else {
            return String.format((firstNumber / secondNumber) + "");
        }
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam(value = "first", defaultValue = "1") double firstNumber, @RequestParam(value = "second", defaultValue = "1") double secondNumber) {
        return String.format((firstNumber * secondNumber) + "");
    }

    @GetMapping("/percent")
    public String percent(@RequestParam(value = "first", defaultValue = "1") double firstNumber) {
        return String.format((firstNumber / 100) + "");
    }

    @GetMapping("/squaring")
    public String squaring(@RequestParam(value = "first", defaultValue = "1") double firstNumber) {
        return String.format((firstNumber * firstNumber) + "");
    }

    @GetMapping("/root")
    public String root(@RequestParam(value = "first", defaultValue = "1") double firstNumber) {
        if(firstNumber<0){
            return "ERROR!";
        }
        return String.format((Math.sqrt(firstNumber)) + "");
    }

    @GetMapping("/inverse")
    public String inverse(@RequestParam(value = "first", defaultValue = "1") double firstNumber) {
        if(firstNumber == 0){
            return "ERROR!" ;
        } else {
            return String.format((1 / firstNumber) + "");
        }
    }

     @GetMapping("/sign")
     public String sign(@RequestParam(value = "first", defaultValue = "1") double firstNumber) {
         return String.format(firstNumber - (2*firstNumber) + "");
     }




}


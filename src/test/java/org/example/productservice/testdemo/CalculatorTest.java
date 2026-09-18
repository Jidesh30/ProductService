package org.example.productservice.testdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CalculatorTest {

    /*
    When---Then---
     */
    @Test
    void whenAddTwoNumbersThenRightResultExpected() {
        //Arrange
        int a = 10;
        int b = 20;
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(a, b);

        //Assert
//        if(result == 30){
//            System.out.println("Success");
//        } else {
//            throw new RuntimeException("Test failed: Expected 30, but got " + result);
//        }
        Assertions.assertEquals(30, result);
    }

    @Test
    void divide() {
        //Arrange
        int a = 10;
        int b = 0;
        Calculator calculator = new Calculator();

        //Act
//        int result = calculator.divide(a, b);

        //Assert
        Assertions.assertThrows(ArithmeticException.class, ()-> calculator.divide(a,b));

    }
}
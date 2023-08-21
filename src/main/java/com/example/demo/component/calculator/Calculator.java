package com.example.demo.component.calculator;

public class Calculator {

    BinOperation binOperation;


    public Calculator(BinOperation binOperation) {
        this.binOperation = binOperation;
    }

    public double doOperation(double operand1, double operand2){
        return binOperation.operation(operand1, operand2);
    }
}

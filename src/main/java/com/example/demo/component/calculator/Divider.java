package com.example.demo.component.calculator;

import org.springframework.stereotype.Component;

@Component
public class Divider implements BinOperation{
    @Override
    public double operation(double operand1, double operand2) {
        return operand1/operand2;
    }
}

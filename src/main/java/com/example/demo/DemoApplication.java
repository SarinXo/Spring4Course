package com.example.demo;

import com.example.demo.component.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(DemoApplication.class, args);
        Calculator c1 = (Calculator) context.getBean("multCalculator");
        Calculator c2 = (Calculator) context.getBean("divCalculator");
        System.out.printf("mult %f\n", c1.doOperation(3, 5));
        System.out.printf("div %f\n", c2.doOperation(10, 5));
    }

}

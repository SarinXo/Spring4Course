package com.example.demo.component.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Calculator divCalculator(BinOperation divider){
        return new Calculator(divider);
    }
    @Bean
    public Calculator multCalculator(BinOperation multiplier){
        return new Calculator(multiplier);
    }
}

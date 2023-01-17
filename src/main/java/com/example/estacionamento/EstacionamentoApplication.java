package com.example.estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.estacionamento")
public class EstacionamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstacionamentoApplication.class, args);
    }

}

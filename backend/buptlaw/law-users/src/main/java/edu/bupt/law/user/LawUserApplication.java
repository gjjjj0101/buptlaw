package edu.bupt.law.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
public class LawUserApplication {
    public static void main(String[] args){
        SpringApplication.run(LawUserApplication.class, args);
    }
}

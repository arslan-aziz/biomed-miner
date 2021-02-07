package com.arslan_aziz.food_for_thought.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.arslan_aziz.food_for_thought")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
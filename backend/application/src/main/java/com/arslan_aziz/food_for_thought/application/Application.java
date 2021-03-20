package com.arslan_aziz.food_for_thought.application;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(scanBasePackages = "com.arslan_aziz.food_for_thought")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
  @Bean
  public Executor taskExecutor() {
	  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	  executor.setCorePoolSize(2);
	  executor.setMaxPoolSize(2);
	  executor.setQueueCapacity(500);
	  executor.setThreadNamePrefix("biomed-app");
	  executor.initialize();
	  return executor;
  }
}
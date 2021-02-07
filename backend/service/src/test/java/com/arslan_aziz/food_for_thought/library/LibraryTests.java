package com.arslan_aziz.food_for_thought.library;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.arslan_aziz.food_for_thought.service.ArticleProcessor;

@SpringBootTest()
public class LibraryTests {

  @SpringBootApplication
  static class TestConfiguration {
  }

}
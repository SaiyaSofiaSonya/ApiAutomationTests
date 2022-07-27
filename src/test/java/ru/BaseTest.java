package ru;

import api.assertions.Assertions;
import api.requests.Request;
import core.Endpoints;
import io.restassured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashSet;

public class BaseTest {
 protected Request request = new Request();
 protected Assertions assertions = new Assertions();
 protected HashSet<Integer>  usedId = new HashSet<>();

 @BeforeAll
 public static void setupTest() {
     RestAssured.baseURI = Endpoints.ENDPOINT_POST;
 }

 @AfterAll
 public static void clearTestData() {
//  for (int id: usedId) {
//   request.deletePost("/" + id);
//  }
 }
}

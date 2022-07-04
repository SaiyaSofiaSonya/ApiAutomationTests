package ru;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specifications {
    public final RequestSpecification requestSpec;

    public Specifications() {
        requestSpec = given().contentType(ContentType.JSON);
    }
}

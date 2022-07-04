package ru;

import api.PostApi;
import com.github.javafaker.Faker;

import java.util.Random;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static PostApi getRandomPost() {
        String title = faker.lorem().fixedString(10);
        String body = faker.lorem().fixedString(50);
        int userId = new Random().nextInt(10);
        return new PostApi(userId, title, body);
    }
}

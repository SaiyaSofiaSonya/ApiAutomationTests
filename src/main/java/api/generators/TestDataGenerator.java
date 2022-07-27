package api.generators;

import api.models.PostApi;
import com.github.javafaker.Faker;

import java.util.Random;

public class TestDataGenerator {
    private static final Faker FAKER = new Faker();

    public static PostApi getRandomPost() {
        String title = FAKER.lorem().fixedString(10);
        String body = FAKER.lorem().fixedString(50);
        int userId = new Random().nextInt(10);
        return new PostApi(userId, title, body);
    }
}

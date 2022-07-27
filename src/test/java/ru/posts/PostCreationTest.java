package ru.posts;

import api.generators.TestDataGenerator;
import core.Endpoints;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import api.models.PostApi;
import io.restassured.response.Response;
import ru.BaseTest;

public class PostCreationTest extends BaseTest {

    @Test
    @Description("Добавление поста")
    public void newPost() {
        PostApi expectedPost = TestDataGenerator.getRandomPost();
        Response response = request.createPost(expectedPost, "/posts/");
        assertions.checkResponseStatusOk(response, 201);
    }

    @Test
    @Disabled("Новый пост не возвращается")
    @Description("Добавление поста и поиск его в базе")
    public void addNewPostAndGetIt() {
        PostApi expectedPost = TestDataGenerator.getRandomPost();
        Response responsePost = request.createPost(expectedPost, "/posts/");
        assertions.checkResponseStatusOk(responsePost, 201);
        int id = request.extractId(responsePost);
        usedId.add(id);
        Response responseGet = request.getPost("/posts/" + id);
        System.out.println(Endpoints.ENDPOINT_POST + "/" + id);
        assertions.checkResponseStatusOk(responseGet, 201);
    }
}

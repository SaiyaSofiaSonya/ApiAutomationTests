package ru;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import api.PostApi;
import io.restassured.response.Response;

public class PostCreationTest extends Request {

    @Test
    @Description("Добавление поста")
    public void newPost() {
        PostApi expectedPost = TestDataGenerator.getRandomPost();
        String endpoint = Endpoints.ENDPOINT_POST;
        Response response = sendPost(expectedPost, endpoint);
        checkResponseStatusOk(response, 201);
    }

    @Test
    @Disabled("Новый пост не возвращается")
    @Description("Добавление поста и поиск его в базе")
    public void addNewPostAndGetIt() {
        PostApi expectedPost = TestDataGenerator.getRandomPost();
        Response responsePost = sendPost(expectedPost, Endpoints.ENDPOINT_POST);
        checkResponseStatusOk(responsePost, 201);
        int id = extractId(responsePost);
        Response responseGet = sendGet(Endpoints.ENDPOINT_POST + id);
        System.out.println(Endpoints.ENDPOINT_POST + "/" + id);
        checkResponseStatusOk(responseGet, 201);
    }
}

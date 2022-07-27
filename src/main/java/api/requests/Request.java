package api.requests;

import api.models.PostApi;
import api.Specifications;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class Request {
    private final Specifications specifications = new Specifications();

    @Step("Получение поста")
    public Response getPost(String endpoint) {
        return specifications
                .getRequestSpec()
                .get(endpoint);
    }

    @Step("Создание поста")
    public Response createPost(PostApi expectedPost, String endpoint) {
        return specifications
                .getRequestSpec()
                .body(expectedPost)
                .post(endpoint);
    }

    @Step("Удаление поста")
    public void deletePost(String endpoint) {

    }

    @Step("Добавление нового поста и получение id созданного запроса")
    public int extractId(Response response) {
        return response
                .then()
                .extract()
                .response()
                .as(PostApi.class, ObjectMapperType.GSON)
                .getId();
    }

}

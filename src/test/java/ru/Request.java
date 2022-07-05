package ru;

import api.PostApi;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.messages.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class Request extends Specifications {
    @Step("Отправка get запроса")
    public Response sendGet(String endpoint) {
        return requestSpec.get(endpoint);
    }

    @Step("Отправка post запроса")
    public Response sendPost(PostApi expectedPost, String endpoint) {
        return requestSpec
                .body(expectedPost)
                .post(endpoint);
    }

    @Step("Проверяем поля в ответе  в ответе")
    public void checkBodyParams(Response response) {
        try {
            response
                    .then()
                    .extract()
                    .jsonPath()
                    .getList(".", PostApi.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Step("Проверяем, что статус в ответе 2XX")
    public void checkResponseStatusOk(Response response, int status) {
        response.then()
                .log().ifValidationFails()
                .assertThat()
                .statusCode(status);
    }

    @Step("Проверка количества постов в ответе")
    public void checkPostNumber(Response response, int number) {
        List<PostApi> posts = Arrays.asList(response
                .then()
                .extract()
                .response()
                .as(PostApi[].class, ObjectMapperType.GSON));
        assertThat(posts.size() == number);
   }

    @Step("Проверка, что ответ не пустой")
    public void checkIfResponseNotEmpty(String response) {
        assertThat(response)
                .isNotEmpty();
    }

    @Step("Проверка валидности схемы Json")
    public void checkValidSchema(Response response, String schema) {
        response
                .then()
                .log().ifValidationFails()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(schema));
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

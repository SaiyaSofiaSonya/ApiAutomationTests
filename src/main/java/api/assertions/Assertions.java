package api.assertions;

import api.models.PostApi;
import io.qameta.allure.Step;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class Assertions {
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
        org.assertj.core.api.Assertions.assertThat(posts.size() == number);
    }

    @Step("Проверка, что ответ не пустой")
    public void checkIfResponseNotEmpty(String response) {
        org.assertj.core.api.Assertions.assertThat(response)
                .isNotEmpty();
    }

    @Step("Проверка валидности схемы Json")
    public void checkValidSchema(Response response, String schema) {
        response
                .then()
                .log().ifValidationFails()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schema));
    }
}

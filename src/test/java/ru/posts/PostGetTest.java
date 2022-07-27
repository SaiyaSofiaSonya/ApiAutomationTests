package ru.posts;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import ru.BaseTest;

public class PostGetTest extends BaseTest {
    @Test
    @Disabled("Дополнить скоуп")
    @Description("Проверяем, что схема ответа валидна")
    public void validSchema() {
        Response response = request.getPost("/posts/");
        assertions. checkResponseStatusOk(response, 200);
        assertions.checkValidSchema(response, "post_schema.json");
    }

    @Test
    @Description("Проверяем, что ответ не пустой")
    public void responseSize() {
        Response response = request.getPost("/posts/");
        assertions.checkResponseStatusOk(response, 200);
        assertions.checkIfResponseNotEmpty(response
                .then()
                .extract()
                .asString());
    }

    @Test
    @Description("Проверяем корректность параметров тела ответа")
    public void responseBodyParams() {
        Response response = request.getPost("/posts/");
        assertions.checkResponseStatusOk(response, 200);
        assertions.checkBodyParams(response);
    }

    @Test
    @Description("Проверяем, что количество постов - 100")
    public void responseHas100Posts() {
        Response response = request.getPost("/posts/");
        assertions.checkResponseStatusOk(response, 200);
        assertions.checkPostNumber(response, 100);
    }
}

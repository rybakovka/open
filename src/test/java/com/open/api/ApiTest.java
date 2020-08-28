package com.open.api;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.mapping.Page;
import com.mapping.Person;
import com.mapping.User;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Тесты API
 * @author Константин Рыбаков
 */
public class ApiTest {

    @BeforeClass
    public void setUp() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.requestSpecification = requestSpec;

        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = responseSpec;
    }

    @Test(description = "Проверка, что все поля пришли.")
    public void testGetRq() {
        Page page = given()
                .when().get("/api/users?page=2")
                .then().statusCode(200).extract().body().as(Page.class);
        for (User user : page.getData()) {
            Assert.assertNotNull(user.getId(), String.format("Поле id заполнено", user.getId()));
            Assert.assertNotNull(user.getEmail(), String.format("Для пользователя %s поле email заполнено", user.getId()));
            Assert.assertNotNull(user.getFirstName(), String.format("Для пользователя %s поле first_name заполнено", user.getId()));
            Assert.assertNotNull(user.getLastName(), String.format("Для пользователя %s поле last_name заполнено", user.getId()));
            Assert.assertNotNull(user.getAvatar(), String.format("Для пользователя %s поле avatar заполнено", user.getId()));
        }
    }

    @Test(description = "Создание пользователя его отправка и проверка ответа.")
    public void testPostRq() {
        Person sendPerson = new Person("Nico", "manager");
        Person receivedPerson = given()
                .contentType(ContentType.JSON).body(sendPerson)
                .when().post("/api/users")
                .then().statusCode(201).extract().body().as(Person.class);
        Assert.assertEquals(sendPerson, receivedPerson, "Значения ответа и запроса совпадают");
    }
}

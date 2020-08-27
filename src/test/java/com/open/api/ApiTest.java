package com.open.api;

import static io.restassured.RestAssured.given;

import mapping.Page;
import mapping.Person;
import mapping.User;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Тесты API
 * @author Константин Рыбаков
 */
public class ApiTest {

    @Test(description = "Проверить, что все поля пришли.")
    public void getRq() {
        Page page = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .as(Page.class);
        for (User user : page.getData()) {
            Assert.assertTrue((null != user.getId()
                                && (null != user.getEmail())
                                && (null != user.getFirst_name())
                                && (null != user.getLast_name())
                                && (null != user.getAvatar())),
                    String.format("Для пользователя %s нет пустых полей", user.getId()));
        }
    }

    @Test(description = "Создать пользователя, отправить его и проверить ответ.")
    public void postRq() {
        Person sendPerson = new Person("Nico", "manager");
        Person receivedPerson = given()
                .contentType("application/json").body(sendPerson)
                .when().post("https://reqres.in/api/users")
                .as(Person.class);
        Assert.assertEquals(sendPerson, receivedPerson);
    }
}

package com.open.api;

import static io.restassured.RestAssured.given;

import mapping.Page;
import mapping.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Тесты API
 */
public class ApiTest {

    @Test
    public void getR() {
        Page page = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .as(Page.class);
        for (Page.User user : page.data) {
            Assert.assertNotNull(user.id);
            Assert.assertNotNull(user.email);
            Assert.assertNotNull(user.first_name);
            Assert.assertNotNull(user.last_name);
            Assert.assertNotNull(user.avatar);
        }
    }

    @Test
    public void postR() {

        Person sendPerson = new Person("Nico", "manager");

        Person receivedPerson = given()
                .contentType("application/json").body(sendPerson)
                .when().post("https://reqres.in/api/users")
                .as(Person.class);

        Assert.assertEquals(sendPerson, receivedPerson);
    }
}

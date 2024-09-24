package com.felipe.rest;

import com.felipe.dto.CreateUserRequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserResourceTest {

    @Test
    @DisplayName("deve criar um usuário com sucesso!")
    public void createUserTest() {
        var user = new CreateUserRequest();
        user.setName("teste usuario");
        user.setAge(30);

        Response response = given()
                .contentType(ContentType.JSON).body(user)
                .when()
                .post("/users")
                .then()
                .extract().response();

        assertEquals(201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));

    }

  /**  @Test
    @DisplayName("")
    public void createUserValidationErrorTest() {

    }**/

}
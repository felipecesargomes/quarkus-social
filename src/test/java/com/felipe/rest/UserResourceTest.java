package com.felipe.rest;

import com.felipe.dto.CreateUserRequest;
import com.felipe.dto.ResponseError;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserResourceTest {

    @Test
    @DisplayName("deve criar um usuário com sucesso!")
    @Order(1)
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

    @Test
    @DisplayName("Deve retornar erro quando json não é válido")
    @Order(2)
    public void createUserValidationErrorTest() {
        var user = new CreateUserRequest();
        user.setAge(null);
        user.setName(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .extract().response();

        assertEquals(ResponseError.UNPROCESSABLE_ENTITY_STATUS, response.statusCode());
        assertEquals("Validation Error", response.jsonPath().getString("message"));

        List<Map<String, String>> errors = response.jsonPath().getList("errors");
        assertNotNull(errors.get(0).get("message"));
        assertNotNull(errors.get(1).get("message"));
        //assertEquals("Nome é requerido!",errors.get(1).get("message"));
        //assertEquals("Idade é obrigatório!",errors.get(0).get("message"));


    }

    @TestHTTPResource("/users")
    URL apiURL;

    @Test
    @DisplayName("deve lista todos os usuários")
    @Order(3)
    public void listAllUsersTests() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(1));
    }



}
package com.felipe.rest;

import com.felipe.domain.User;
import com.felipe.dto.CreatePostRequest;
import com.felipe.repository.UserRepository;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
class PostResourceTest {

    @Inject
    UserRepository userRepository;
    Long userId;

    @BeforeEach
    @Transactional
    public void setUP() {
        var user = new User();
        user.setAge(30);
        user.setNome("Felipe");

        userRepository.persist(user);

        userId = user.getId();
    }

    @Test
    @DisplayName("Deve criar um post para um usuário")
    public void createPostTest() {

        CreatePostRequest postRequest = new CreatePostRequest();

        postRequest.setText("Algum texto");

        var userId = 1;

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", userId)
                .when()
                .post()
                .then()
                .statusCode(201);
    }

    @Test
    @DisplayName("Deve retornar 404 quando tentar criar um post para um usuário inexistente")
    public void inexistentUserTest() {

        CreatePostRequest postRequest = new CreatePostRequest();

        postRequest.setText("Algum texto");

        var userId = 999999;

        given()
                .contentType(ContentType.JSON)
                .body(postRequest)
                .pathParam("userId", userId)
                .when()
                .post()
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("deve retornar 400 quando followerId header não está presente")
    public void listPostUserNotFoundTest() {

    }

    @Test
    @DisplayName("deve retornar 400 quando follower não existe")
    public void listPostFollowerNotFoundTest() {

    }

    @Test
    @DisplayName("deve retornar 403 quando follower não é um seguidor")
    public void listPostNotAFollower() {

    }

    @Test
    @DisplayName("deve retornar posts")
    public void listPostsTest() {

    }

}
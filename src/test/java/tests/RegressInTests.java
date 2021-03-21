package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ResponseGetColors;
import pojo.User;

import java.util.HashMap;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RegressInTests {

    @BeforeAll
    @DisplayName("Precondition step")
    static void setup( ) {
        RestAssured.filters(new AllureRestAssured( ));
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    @DisplayName("Test 01. Reqres.In API test for method POST: Create User")
    void addUser( ) {
        User user = new User( );
        step("Generated user:\n" + user.toString( ));

        Response response = given( )
                .contentType(JSON)
                .body(user)
                .when( )
                .post("/api/users");
        response.then( ).statusCode(201).log( ).body( );
        User createdUser = response.then( ).extract( ).body( ).as(User.class);

        step("Created user: \n" + createdUser.toString( ));
        assertThat(createdUser).usingRecursiveComparison( ).isEqualTo(user);
    }

    @Test
    @DisplayName("Test 02. Reqres.In API test for method GET: All color")
    void getALl( ) {
        Response response = given( )
                .contentType(JSON)
                .when( )
                .get("/api/unknown");
        response.then( ).statusCode(200).log( ).body( );

        ResponseGetColors colors = response.then( ).extract( ).body( ).as(ResponseGetColors.class);
        step("ResponseGetColors:\n" + colors.toString( ));
    }

    @Test
    @DisplayName("Test 03. Reqres.In API test for method PUT: Update user")
    void updateUser( ) {
        User user = new User( );
        step("Generated_user:\n" + user.toString( ));

        Response response = given( )
                .contentType(JSON)
                .body(user)
                .when( )
                .put("/api/users/2");
        response.then( ).statusCode(200).log( ).body( );
        User getCreatedUser = response.then( ).extract( ).body( ).as(User.class);

        step("Get_Created_user:\n" + getCreatedUser.toString( ));
        assertThat(getCreatedUser).usingRecursiveComparison( ).isEqualTo(user);

        User updatedUser = new User( );
        step("Updated_user:\n" + updatedUser.toString( ));

        response = given( )
                .contentType(JSON)
                .body(updatedUser)
                .when( )
                .put("/api/users/2");
        response.then( ).statusCode(200).log( ).body( );
        User getUpdatedUser = response.then( ).extract( ).body( ).as(User.class);

        step("Get_Updated_user:" + getUpdatedUser.toString( ));
        assertThat(getUpdatedUser).usingRecursiveComparison( ).isEqualTo(updatedUser);
    }

    @Test
    @DisplayName("Test 04. Reqres.In API test for method POST: Failed Login")
    void failedLogin( ) {
        User user = new User( );
        step("Generated user:\n" + user.toString( ));

        HashMap<String, String> login = new HashMap<>( );
        login.put("email", user.getEmail( ));

        given( )
                .contentType(JSON)
                .body(login)
                .when( )
                .post("/api/login")
                .then( )
                .body("error", is("Missing password"))
                .statusCode(400)
                .log( );
    }

    @Test
    @DisplayName("Test 05. Reqres.In API test for method POST: Failed register")
    void failedRegister( ) {
        User user = new User( );
        step("Generated_user:\n" + user.toString( ));
        HashMap<String, String> register = new HashMap<>( );
        register.put("email", user.getEmail( ));

        given( )
                .contentType(JSON)
                .body(register)
                .when( )
                .post("/api/register")
                .then( )
                .body("error", is("Missing password"))
                .statusCode(400)
                .log( );
    }

    @Test
    @DisplayName("Test 06. Reqres.In API test for method GET: Single Resource")
    void getResource( ) {
        given( )
                .contentType(JSON)
                .when( )
                .get("/api/unknown/2")
                .then( )
                .rootPath("data")
                .body("id", notNullValue( ),
                        "name", notNullValue( ),
                        "year", notNullValue( ),
                        "color", notNullValue( ),
                        "pantone_value", notNullValue( ))
                .rootPath("support")
                .body("url", notNullValue( ),
                        "text", notNullValue( ))
                .statusCode(200)
                .log( );
    }
}
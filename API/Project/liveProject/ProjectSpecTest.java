package liveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectSpecTest {
    RequestSpecification reqSpec;
    int id;
    String key;

    @BeforeClass
    public void setUp() {
        // Request Specification
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.github.com")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization","Bearer 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")
                .build();
    }

    @Test(priority = 0)
    public void sendPost() {
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("title", "TestAPIKey");
        reqBody.put("key", "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIKpX9eAuEd+KJ7STVZCzTjqo1+oVvJiKKXjekGDOxaSj");

        Response response = given()
                .spec(reqSpec)
                .body(reqBody).log().all()
                .when()
                .post("/user/keys");

        response.getBody().prettyPrint();

        // Assertion with response specification
        id = response.then().extract().path("id");
        System.out.println("ID : " + id);
        response.then().statusCode(201);
    }

    @Test(priority = 1)
    public void sendGet() {
        Response response = given()
                .spec(reqSpec)
                .pathParam("keyId", id).log().all()
                .when()
                .get("/user/keys/{keyId}");
        response.getBody().prettyPrint();
        String report = response.getBody().asPrettyString();
        Reporter.log(report);
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    public void sendDelete() {
        Response response = given()
                .spec(reqSpec)
                .pathParam("keyId", id)
                .when().delete("/user/keys/{keyId}");

        response.getBody().prettyPrint();
        String report = response.getBody().asPrettyString();
        Reporter.log(report);

        response.then()
                .statusCode(204);
    }

}

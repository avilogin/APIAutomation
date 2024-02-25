package org.example.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyleGet {
    public static void main(String[] args) {

        RequestSpecification r = RestAssured.given();

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/254").log().all();
        r.when().get();
        r.then().log().all().statusCode(200);
    }
}

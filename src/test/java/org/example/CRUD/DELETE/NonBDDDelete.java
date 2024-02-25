package org.example.CRUD.DELETE;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

@Test
public class NonBDDDelete {
    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    public void nonBDDDeleteMethod()
    {
        String token = "f3296a51c084f71";


        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking/1066");
        req.contentType(ContentType.JSON);
        req.cookie("token",token);

        res= req.when().delete();

        valRes = res.then().log().all();
        valRes.statusCode(201);

    }








}

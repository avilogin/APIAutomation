package org.example.CRUD.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

@Test
public class NonBDDPatch {
    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    public void nonBDDPatchMethod()
    {
        String payload = "{\n" +
                "    \"firstname\" : \"Avijeet\",\n" +
                "    \"lastname\" : \"Sharma\",\n" +
                "    \"totalprice\" : 100,\n" +
                "    \"depositpaid\" : false\n" +
                "}";
        String token = "80b21c0328bc5f5";


        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking/1973");
        req.contentType(ContentType.JSON);
        req.cookie("token",token);

        req.body(payload).log().all();

        res= req.when().patch();

        valRes = res.then().log().all();
        valRes.statusCode(200);
        valRes.body("firstname", Matchers.equalTo("Avijeet"));
        valRes.body("lastname", Matchers.equalTo("Sharma"));
        valRes.body("totalprice", Matchers.equalTo(100));
        valRes.body("depositpaid", Matchers.equalTo(false));

    }
    //1:56:27







}

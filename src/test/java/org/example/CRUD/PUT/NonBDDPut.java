package org.example.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

@Test
public class NonBDDPut {
    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    public void nonBDDPutMethod()
    {
        String payload = "{\n" +
                "    \"firstname\" : \"Lirics\",\n" +
                "    \"lastname\" : \"Dash\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        String token = "ea77e7870e82b60";


        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking/2285");
        req.contentType(ContentType.JSON);
        req.cookie("token",token);

        req.body(payload).log().all();

        res= req.when().put();

        valRes = res.then().log().all();
        valRes.statusCode(200);
        valRes.body("firstname", Matchers.equalTo("Lirics"));
        valRes.body("lastname", Matchers.equalTo("Dash"));

    }







}

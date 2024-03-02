package org.example.misc;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayloadUsingString {

    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    Integer bookingId;


    @Test
    public void nonBDDCreateBooking()
    {
        String payload = "{\n" +
                "    \"firstname\" : \"Avijeet\",\n" +
                "    \"lastname\" : \"Sharmaa\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";



        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking");
        req.contentType(ContentType.JSON);

        req.body(payload).log().all();

        res= req.when().post();
        bookingId = res.then().extract().path("bookingid");

        valRes = res.then().log().all();
        valRes.statusCode(200);
        System.out.println("Booking ID is :- "+bookingId);

    }
}
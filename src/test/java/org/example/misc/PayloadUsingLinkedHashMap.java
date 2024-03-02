package org.example.misc;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PayloadUsingLinkedHashMap {

    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    Integer bookingId;


    @Test
    public void nonBDDCreateBooking()
    {

        //using LinkedHashMap we will be creating the payload
        //As we are using LinkedHashMap so the request will keep the order

        Map<String, Object> jsonBody = new LinkedHashMap<>();//took first argument as string as its string and 2nd as Object as for different keys we have different types of values
        Faker fake = new Faker();//this class will create random values for the keys
        jsonBody.put("firstname",fake.name().firstName());
        jsonBody.put("lastname",fake.name().lastName());
        jsonBody.put("totalprice",fake.random().nextInt(1,1000));
        jsonBody.put("depositpaid",fake.random().nextBoolean());

        Map<String, Object> bookingDatesMap = new HashMap<>();//created another map as bookingdates is another map
        bookingDatesMap.put("checkin","2018-01-01");
        bookingDatesMap.put("checkout","2019-01-01");

        jsonBody.put("bookingdates",bookingDatesMap);
        jsonBody.put("additionalneeds","Breakfast");
        System.out.println(jsonBody);


        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking");
        req.contentType(ContentType.JSON);

        req.body(jsonBody).log().all();//have library gson which will convert the jsonBody into JSON format automatically.

        res= req.when().post();
        bookingId = res.then().extract().path("bookingid");

        valRes = res.then().log().all();
        valRes.statusCode(200);
        System.out.println("Booking ID is :- "+bookingId);

    }
}
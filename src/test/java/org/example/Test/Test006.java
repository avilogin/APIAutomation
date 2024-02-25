package org.example.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.SortedMap;

import static org.assertj.core.api.Assertions.assertThat;

public class Test006 {

    RequestSpecification r =  RestAssured.given();
    ValidatableResponse ValRes;
    Response res;
    String token;
    Integer bookinId;

    @BeforeTest
    public void getToken()
    {
        System.out.println("---Getting the Token------");

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payload).log().all();

        res = r.when().post();
        ValRes = res.then().log().all();

        // Rest Assured -> Matchers (Hamcrest) - 1-2% Times you will be using it
        ValRes.body("token", Matchers.notNullValue());

        // TestNG assertion
        token = res.then().log().all().extract().path("token");
        Assert.assertNotNull(token);

        // AssertJ
        assertThat(token).isNotNull().isNotBlank().isNotEmpty();
        System.out.println(token);
    }

    @BeforeTest
    public void getBookingID()
    {

        System.out.println("---Getting the Booking ID------");

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


        r.baseUri("https://restful-booker.herokuapp.com").basePath("/booking");
        r.contentType(ContentType.JSON);
        //r.cookie("token",token);

        r.body(payload).log().all();

        res= r.when().post();

        // TestNG assertion
        bookinId = res.then().log().all().extract().path("bookingid");
        Assert.assertNotNull(bookinId);
        System.out.println(bookinId);
        res.then().log().all().statusCode(200);

    }

    @Test
    public void UpdateBooking()
    {
        System.out.println("---Updating the Booking------");

        String putPayload = "{\n" +
                "    \"firstname\" : \"Lirics\",\n" +
                "    \"lastname\" : \"Dash\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";


        r.baseUri("https://restful-booker.herokuapp.com").basePath("/booking/"+bookinId);
        r.contentType(ContentType.JSON);
        r.cookie("token",token);

        r.body(putPayload).log().all();

        res= r.when().put();

        ValRes = res.then().log().all();
        ValRes.statusCode(200);
        ValRes.body("firstname", Matchers.equalTo("Lirics"));
        ValRes.body("lastname", Matchers.equalTo("Dash"));
        ValRes.body("additionalneeds", Matchers.equalTo("Lunch"));
    }
}

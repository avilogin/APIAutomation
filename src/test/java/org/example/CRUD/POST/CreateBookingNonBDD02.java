package org.example.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateBookingNonBDD02 {

    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;
    String token;
    String bookingId;

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
        //res.then().log().all().statusCode(200);

        bookingId = res.then().log().all().extract().toString();
        System.out.println("Response is = "+bookingId);

        //valRes = res.then().log().all();
        //valRes.statusCode(200);
        //Assert.assertEquals("Avijeet","Avijeet");
        //valRes.body("firstname", Matchers.equalTo("Avijeet"));
        //valRes.body("lastname", Matchers.equalTo("Sharmaa"));

    }
}

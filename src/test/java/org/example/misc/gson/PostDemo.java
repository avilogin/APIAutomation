package org.example.misc.gson;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PostDemo {
    RequestSpecification req = RestAssured.given();
    Response res;
    ValidatableResponse valRes;

    Integer bookingId;

    @Test
    public void testPost()
    {
        //writing post method codes

        Booking postPayload = new Booking();
        postPayload.setFirstname("Avijeet");
        postPayload.setLastname("Jana");
        postPayload.setTotalprice(200);
        postPayload.setDepositpaid(false);

        //Setting the data for Booking dates class
        BookingDates dates = new BookingDates();
        dates.setCheckin("2018-01-01");
        dates.setCheckout("2020-01-01");
        postPayload.setBookingdates(dates);
        postPayload.setAdditionalneeds("Biryani");
        System.out.println(postPayload);


        //Serializing the Object to JSON String using GSON
        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(postPayload);


        req.baseUri("https://restful-booker.herokuapp.com").basePath("/booking");
        req.contentType(ContentType.JSON);
        req.body(jsonStringBooking).log().all();
        res= req.when().post();
        String jsonRestring = res.asString();
        valRes = res.then().log().all();
        valRes.statusCode(200);

       //Deserializing the JSON String to Object
       BookingRes bookingResObject =  gson.fromJson(jsonRestring,BookingRes.class);

       //we can now validate the response

        //Using assertj we'll validate bookingID is not null
        assertThat(bookingResObject.getBookingid()).isNotNull();


        Assert.assertEquals(bookingResObject.getBooking().getFirstname(),"Avijeet");
        Assert.assertEquals(bookingResObject.getBooking().getLastname(),"Jana");
        Assert.assertEquals(bookingResObject.getBooking().getTotalprice(),200);
        Assert.assertEquals(bookingResObject.getBooking().getDepositpaid(),false);
        Assert.assertEquals(bookingResObject.getBooking().getBookingdates().getCheckin(),"2018-01-01");
        Assert.assertEquals(bookingResObject.getBooking().getBookingdates().getCheckout(),"2020-01-01");
        Assert.assertEquals(bookingResObject.getBooking().getAdditionalneeds(),"Biryani");











    }
}

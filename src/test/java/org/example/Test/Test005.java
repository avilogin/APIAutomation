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
import static org.assertj.core.api.Assertions.*;

public class Test005 {
    RequestSpecification r =  RestAssured.given();
    ValidatableResponse ValRes;
    Response res;
    String token;

    @BeforeTest
    public void getToken()
    {


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

    @Test
    public void UpdateBooking()
    {

        String putPayload = "{\n" +
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


        r.baseUri("https://restful-booker.herokuapp.com").basePath("/booking/3878");
        r.contentType(ContentType.JSON);
        r.cookie("token",token);

        r.body(putPayload).log().all();

        res= r.when().put();

        ValRes = res.then().log().all();
        ValRes.statusCode(200);
        ValRes.body("firstname", Matchers.equalTo("Lirics"));
        ValRes.body("lastname", Matchers.equalTo("Dash"));
    }
}

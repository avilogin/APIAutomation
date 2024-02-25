package org.example.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class GetAllBookingsNonBDDTestNG {

    @Test(enabled = false)
    public void testGetbookingpositive()
    {

        RequestSpecification r = new RestAssured().given();

        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.when().get();
        r.then().log().all().statusCode(200);
    }

    }


package org.example.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateTokenNonBDD {
    @Test
 public void CreateTokNonBDD()
    {
        RequestSpecification r =  RestAssured.given();//used RequestSpecification interface to use that code multiple times
        ValidatableResponse ValRes;
        Response res;

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

                r.baseUri("https://restful-booker.herokuapp.com");
                r.basePath("/auth");
                r.contentType(ContentType.JSON);
                r.body(payload).log().all();

                res = r.when().post();//Response interface have many methods that we can use

                ValRes = res.then().log().all();//ValidatableResponse interface have many methods that we can use
                String responsestring = res.toString();
                System.out.println(responsestring);
                ValRes.statusCode(200);

    }


}

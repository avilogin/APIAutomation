package org.example.DDT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppVWO {

    RequestSpecification r =  RestAssured.given();

    String id;
    Response res;

    @Test(dataProvider = "getData",dataProviderClass = UtilFromExcel.class)
    public void testLoginData(String username, String password)
    {
        System.out.println("Username - "+username);
        System.out.println("Password - "+password);

        //adding codes for Login API

        String loginPayload = "{\n" +
                "  \"username\": \""+username+"\",\n" +
                "  \"password\": \""+password+"\",\n" +
                "  \"remember\": false,\n" +
                "  \"recaptcha_response_field\": \"\"\n" +
                "}\n";


        r.baseUri("https://app.vwo.com").basePath("/login");
        r.contentType(ContentType.JSON);
        r.body(loginPayload).log().all();
        res= r.when().post();
        res.then().log().all().statusCode(200);

        JsonPath js = new JsonPath(String.valueOf(res));
        id = js.getString("id");
        Assert.assertNotNull(id);
        System.out.println(id);


    }
}

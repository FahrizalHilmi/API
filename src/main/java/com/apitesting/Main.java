package com.apitesting;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.restassured.path.json.JsonPath;

public class Main {
    @Test
    @Epic("API Testing")
    public static void main(String[] args) {
        Allure.step("Step 1: Send a POST request to the API");
        String response = given().log().all().body(" {\r\n" + //
                        "  \"QREchoTestRQ\": {\r\n" + //
                        "    \"msgType\": \"0800\",\r\n" + //
                        "    \"transmissionDateTime\": \"0425103200\",\r\n" + //
                        "    \"msgSTAN\": \"014143\",\r\n" + //
                        "    \"networkCode\": \"301\"\r\n" + //
                        "  }\r\n" + //
                        "} ").header("Content-Type", "application/json")
                                .when().post("http://10.14.236.29/btop/btop/xxx/echo").then().log().all()
                                .extract().response().asString();

JsonPath js = new JsonPath(response);
        String msgType = js.get("QREchoTestRS.msgType");
        System.out.println("msgType: "+ msgType);

        Assert.assertEquals(msgType, "0810", "msgType is not as expected");
        Assert.assertEquals(msgType.length(), 4, "msgType is not as expected");
        System.out.println("msgType is as expected");
    }
}
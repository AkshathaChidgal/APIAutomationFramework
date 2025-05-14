package com.theautomationtesting.tests.crud;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.theautomationtesting.base.BaseTest;
import com.theautomationtesting.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

    @Test
    @Owner("Akshatha")
    @Description("TC#2 - Verify Health")
    public void testGETHealthCheck()
    {
        requestSpecification.basePath(APIConstants.PING_URK);
        response= RestAssured.given(requestSpecification).when().log().all().get();
        System.out.println(response.asString());

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(201);

    }

}

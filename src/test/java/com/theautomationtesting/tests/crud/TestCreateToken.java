package com.theautomationtesting.tests.crud;

import com.theautomationtesting.base.BaseTest;
import com.theautomationtesting.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestCreateToken extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Akshatha")
    @Description("TC#2 - Create Token and verify")
    public void testTokenPOST_Positive() {
        // Setup and Making a Request
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response= RestAssured.given(requestSpecification).when().body(payloadManager.setPayloadAuth()).log().all().post();
        System.out.println(response.asString());

        // Extraction
        String token=payloadManager.getTokenFromJSON(response.asString());

        // Verification Part
        assertActions.verifyStringKeyNotNull(token);
    }
}

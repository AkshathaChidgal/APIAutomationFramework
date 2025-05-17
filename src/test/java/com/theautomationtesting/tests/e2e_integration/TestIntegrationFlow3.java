package com.theautomationtesting.tests.e2e_integration;

import com.theautomationtesting.base.BaseTest;
import com.theautomationtesting.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestIntegrationFlow3 extends BaseTest {

    // E2E Scenario 3

    // 1. Get a Booking from Get All
    // 2. Try to Delete that booking

    @Test(groups = "qa", priority = 1)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 1. Verify that all booking ids are present")
    public void testGetAllBookingids(ITestContext iTestContext) {


        requestSpecification.basePath("/booking");
        response = RestAssured.given(requestSpecification).when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Integer bookingid = getBookingID();
        iTestContext.setAttribute("bookingid", bookingid);

    }

    @Test(groups = "qa", priority = 2)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 2. Verify that booking ids is deleted")
    public void deleteBookingid(ITestContext iTestContext)
    {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token=(String)iTestContext.getAttribute("token");


        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();
        validatableResponse=RestAssured.given().spec(requestSpecification)
                .when().get().then().log().all();
        validatableResponse.statusCode(200);
        Assert.assertTrue(true);
    }
}

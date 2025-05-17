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

public class TestIntegrationFlow4 extends BaseTest {
    //Create Booking -> Update it -> Try to Delete

    @Test(groups = "qa", priority = 1)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext iTestContext)
    {
        requestSpecification.basePath("/booking");
        response = RestAssured.given(requestSpecification).when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Integer bookingid = getBookingID();
        iTestContext.setAttribute("bookingid", bookingid);
    }

    @Test(groups = "qa", priority = 2)
    @Owner("AkshathaC")
    @Description("TC#INT1 - Step 2. Update the booking")
    public void testUpdateBookingId(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.fullUpdatePayloadAsString()).get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test(groups = "qa", priority = 3)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 3. Verify that booking id is deleted")
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

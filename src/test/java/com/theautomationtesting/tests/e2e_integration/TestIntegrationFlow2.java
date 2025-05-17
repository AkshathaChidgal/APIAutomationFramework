package com.theautomationtesting.tests.e2e_integration;

import com.theautomationtesting.base.BaseTest;
import com.theautomationtesting.endpoints.APIConstants;
import com.theautomationtesting.pojo.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.requestSpecification;

public class TestIntegrationFlow2 extends BaseTest {


    // Create Booking -> Delete it -> Verify
    @Test(groups = "qa", priority = 1)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreate_Booking(ITestContext iTestContext)
    {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).log().all().post();
        System.out.println(response.asString());

        validatableResponse  = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse1 =payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse1.getBooking().getFirstname(),"Akshatha");
        assertActions.verifyStringKey(bookingResponse1.getBooking().getLastname(),"Chidgal");
        assertActions.verifyStringKeyNotNull(bookingResponse1.getBookingid());

        iTestContext.setAttribute("bookingid",bookingResponse1.getBookingid());
        System.out.println();
    }




    @Test(groups = "qa", priority = 2)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 2. Delete the Booking by ID")
    public void testDeletebooking(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token =getToken();
        iTestContext.setAttribute("token",token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }



    @Test(groups = "qa", priority = 3)
    @Owner("Akshatha")
    @Description("TC#INT1 - Step 3. Verify that the Booking By ID")
    public  void verifyBookingidisDeleted(ITestContext iTestContext)
    {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token=(String)iTestContext.getAttribute("token");

        String basePathGETSingleBookingid = APIConstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        requestSpecification.basePath(basePathGETSingleBookingid).cookie("token",token);
        validatableResponse=RestAssured.given().spec(requestSpecification)
                .when().get().then().log().all();
        validatableResponse.statusCode(404);
        Assert.assertTrue(true);

    }


}

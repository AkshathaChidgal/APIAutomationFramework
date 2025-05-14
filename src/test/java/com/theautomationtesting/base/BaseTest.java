package com.theautomationtesting.base;

import com.theautomationtesting.asserts.AssertActions;
import com.theautomationtesting.endpoints.APIConstants;
import com.theautomationtesting.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    // CommonToAll Testcase
    // Base URL, Content Type - json - common


    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;



    @BeforeTest
    public void setup()
    {
        System.out.println("Start of the test");
        payloadManager=new PayloadManager();
        assertActions=new AssertActions();

//        requestSpecification= RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();


    }
//Test case will run from crud folder as this is the parent class


public String getToken()
{
    requestSpecification=RestAssured.given();
    requestSpecification.baseUri(APIConstants.BASE_URL)
            .basePath(APIConstants.AUTH_URL);

    //Setting the payload

    String payload= payloadManager.setPayloadAuth();


    // Get the Token
    response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
    String token = payloadManager.getTokenFromJSON(response.asString());
    return token;
}


    @AfterTest
    // tearDown() is a method used to clean up after each test case runs.
    public void tearDown()
    {
        System.out.println("Test is finished");
    }

}

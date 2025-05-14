package com.theautomationtesting.asserts;
import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {

    //creating reusable functions

    public void verifyResponseBody(String actual,String expected,String description)
    {
        assertEquals(actual,expected,description);
    }
    //method overloading concept
    public void verifyResponseBody(int actual,int expected,String description)
    {
        assertEquals(actual,expected,description);
    }
    public void verifyStatusCode(Response response ,Integer expected)
    {
        assertEquals(response.getStatusCode(),expected);
    }

    public void verifyStringKey(String keyExpect,String keyActual)
    {
        //assertJ
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);
    }
    public void verifyStringKeyNotNull(Integer keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }
    public void verifyStringKeyNotNull(String keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }
}

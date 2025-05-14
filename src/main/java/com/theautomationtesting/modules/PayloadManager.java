package com.theautomationtesting.modules;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.theautomationtesting.pojo.request.Auth;
import com.theautomationtesting.pojo.request.Booking;
import com.theautomationtesting.pojo.request.Bookingdates;
import com.theautomationtesting.pojo.response.BookingResponse;
import com.theautomationtesting.pojo.response.TokenResponse;
import org.checkerframework.checker.units.qual.A;

public class PayloadManager {


    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    Gson gson=new Gson();
    public String createPayloadBookingAsString(){

        Booking booking=new Booking();
        booking.setFirstname("Akshatha");
        booking.setLastname("Chidgal");
        booking.setDepositpaid(true);
        booking.setTotalprice(111);


        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2021-01-01");
        bookingdates.setCheckout("2021-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        //Java Object -> convert into json ->Serialization

         gson=new Gson();
        String jsonStringBooking=gson.toJson(booking);
        return jsonStringBooking;
    }

    public BookingResponse bookingResponseJava(String responseString)
    {
        gson=new Gson();
        BookingResponse bookingResponse=gson.fromJson(responseString,BookingResponse.class);
        return bookingResponse;
    }


    public String setPayloadAuth()
    {
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
//Java object ->json
        gson=new Gson();
        String jsonPayloadAuth=gson.toJson(auth);
        return jsonPayloadAuth;
    }

    //Json string ->Java Object - Deserialization
    public String getTokenFromJSON(String tokenResponse)
    {
        gson=new Gson();
        TokenResponse tokenResponse1=gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public String createPayloadBookingAsStringWrongBody(){
        Booking booking = new Booking();
        booking.setFirstname("会意; 會意");
        booking.setLastname("会意; 會意");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("5025-02-01");
        bookingdates.setCheckout("5025-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("会意; 會意");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }

    public String createPayloadBookingFakerJS(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1,1000));
        booking.setDepositpaid(faker.random().nextBoolean());

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        // Java Object -> JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;

    }

    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Akshatha");
        booking.setLastname("Chidgal");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);


    }

    public String partialUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Akshatha");
        booking.setLastname("Chidgal");
        return gson.toJson(booking);

    }


}

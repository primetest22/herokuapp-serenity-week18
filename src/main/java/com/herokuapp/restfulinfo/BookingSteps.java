package com.herokuapp.restfulinfo;

import com.herokuapp.model.HeroKuappPojo;
import constants.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {

    @Step
    public ValidatableResponse authUser(String username, String password) {
        HeroKuappPojo herokuappPojo = new HeroKuappPojo();
        herokuappPojo.setUsername(username);
        herokuappPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuappPojo)
                .when()
                .post(EndPoints.CREATE_AUTH)
                .then();
    }

    @Step
    public ValidatableResponse createBooking(String firstname,String lastname,int totalprice,
         boolean depositpaid, HashMap<Object, Object> bookingsDatesData,String additionalneeds) {
        HeroKuappPojo herokuappPojo = new HeroKuappPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingsDatesData);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuappPojo)
                .when()
                .post(EndPoints.GET_BOOKING)
                .then();
    }

    @Step
    public ValidatableResponse updateBooking(int bookingID,String firstname,String lastname,int totalprice,
       boolean depositpaid,HashMap<Object, Object> bookingsDatesData,String additionalneeds) {
        HeroKuappPojo herokuappPojo = new HeroKuappPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingsDatesData);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(herokuappPojo)
                .pathParam("bookingID",bookingID)
                .auth().preemptive().basic("admin","password123")
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse deleteBooking(int bookingID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingID",bookingID)
                .auth().preemptive().basic("admin","password123")
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

    @Step
    public ValidatableResponse getBookingByID(int bookingID) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingID",bookingID)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();
    }


}

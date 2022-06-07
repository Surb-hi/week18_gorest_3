package in.co.gorest.model.goreststeps;

import in.co.gorest.model.constants.EndPoints;
import in.co.gorest.model.model.RecordPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UsersSteps {
    @Step("Creating Users with perameter")
    public ValidatableResponse createUsers(String name, String gender,String email,String status) {
        RecordPojo recordPojo=new RecordPojo();
        recordPojo.setName(name);
        recordPojo.setGender(gender);
        recordPojo.setEmail(email);
        recordPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .body(recordPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }
    @Step("Getting the Users information from Id")
    public HashMap<String, Object> getCreatedUsersId(int usersId) {

        HashMap<String, Object> usersMap = SerenityRest.given().log().all()
                .when()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("usersId",usersId)
                .get(EndPoints.CREATED_USER_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return usersMap;
    }

    @Step("Update users with perameter")
    public ValidatableResponse updateusers(int usersId,String name, String gender,String email,String status) {
        RecordPojo recordPojo = new RecordPojo();
        recordPojo.setName(name);
        recordPojo.setGender(gender);
        recordPojo.setEmail(email);
        recordPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .body(recordPojo)
                .when()
                .pathParam("usersId",usersId)
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }


    @Step("Deleting Users information with UsersId")
    public ValidatableResponse deleteUsers(int usersId) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("usersId",usersId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting UsersId information with ")
    public ValidatableResponse getServicesById(int usersId) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer c426452f777927f6e49219f45652a5fd08178e3f873af217a5b982a6fdd15dac")
                .pathParam("usersId",usersId)
                .when()
                .get(EndPoints.CREATED_USER_ID)
                .then();
    }
}

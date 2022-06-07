package in.co.gorest.model.gorestinfo;

import in.co.gorest.model.goreststeps.UsersSteps;
import in.co.gorest.model.testbase.TestBase;
import in.co.gorest.model.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCURDTestWithSteps extends TestBase {
    static String name = "name" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = "email@" + TestUtils.getRandomValue();
    static String status = "ACTIVE";
    static int usersId;


    @Steps
    UsersSteps userSteps;

    @Title("This will create a new Users ")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUsers(name,gender,email,status);
        response.log().all().statusCode(201);
        usersId = response.log().all().extract().path("id");
    }

    @Title("Verify that the Users added in to stack")
    @Test
    public void test002() {
        HashMap<String, Object> UsersMap = userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(UsersMap, hasValue(name));
        System.out.println(usersId);
    }
    @Title("This will Updated created id")
    @Test
    public void test003() {
        name="name"+TestUtils.getRandomValue();
        ValidatableResponse response =userSteps.updateusers(usersId,name,gender,email,status);
        response.log().all().statusCode(200);
        HashMap<String, Object> usersMap =userSteps.getCreatedUsersId(usersId);
        Assert.assertThat(usersMap,hasValue(name));
        System.out.println();
    }

    @Title("This will Delate created id")
    @Test
    public void test004() {
        userSteps.deleteUsers(usersId).statusCode(204);
        userSteps.getServicesById(usersId).statusCode(404);
    }

}

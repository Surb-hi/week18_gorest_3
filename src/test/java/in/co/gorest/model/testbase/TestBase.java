package in.co.gorest.model.testbase;


import in.co.gorest.model.constants.Path;
import in.co.gorest.model.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBase {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.basePath = Path.USERS;

        //RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
    }

}

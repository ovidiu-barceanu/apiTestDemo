package demoEndpoint.tests;

import demoEndpoint.DemoWebServices;
import demoEndpoint.contracts.GetPeopleController;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

    public static String starWarsUrl = "https://swapi.dev/api/";

    protected static final DemoWebServices demoWebServices = DemoWebServices.getInstance();


    public void checkName(Response response, String expectedName){

        GetPeopleController getPeopleController = response.as(GetPeopleController.class);
        assertThat("Check name Id", getPeopleController.getName(), equalTo(expectedName));
    }





}

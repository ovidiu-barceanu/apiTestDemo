package demoEndpoint.tests.getPeopleTests;

import demoEndpoint.tests.TestBase;
import io.restassured.response.Response;
//import org.junit.Test;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPeopleTests extends TestBase {


    @Test(description = "Get non existing people and check that the response is 404")
    public void getNonExistingPeopleById(){
        Response response = demoWebServices.getPeople("91");

        assertThat("Checks that the response has Status code 404 - Not Found.",
                response.getStatusCode(), equalTo(404));

        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void getPeopleById(){
        Response response = demoWebServices.getPeople("1");

        assertThat("Checks that the response has Status code 200 - OK.",
                response.getStatusCode(), equalTo(200));

        System.out.println(response.getBody().asPrettyString());

        checkName(response, "Luke Skywalker");
    }
}

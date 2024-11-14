package demoEndpoint;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

import static demoEndpoint.tests.TestBase.starWarsUrl;

public class DemoWebServices {

    private static final Logger logger = LogManager.getLogger();


    private DemoWebServices(){}
    public static DemoWebServices getInstance(){return new DemoWebServices();}

//    private SelfServiceWebService(){}
//    public static SelfServiceWebService getInstance() {return new SelfServiceWebService();}


    public Response getPeople(String peopleId ){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.pathParam("peopleId", peopleId);
        requestSpecification.contentType(ContentType.JSON);
//        requestSpecification.body(marketControllerMarketSetup);

        return starWarsPeopleApiRequest("people/{peopleId}", Method.GET, requestSpecification)
                .then().extract().response();
    }


    private Response starWarsPeopleApiRequest(String methodName, Method httpMethod, RequestSpecification requestSpecification) {
        final String starWarsApiBaseUri = starWarsUrl;
        logger.info("Request base URL: " + starWarsApiBaseUri + "/" + methodName);

        return given()
                .filters(new RequestLoggingFilter(System.out))
                .filters(new ResponseLoggingFilter(System.out))
                .spec(requestSpecification)
//                .header("Authorization", "Bearer " + selfServiceAuthentication.getAuthTokenId()) // Adding Authorization header
                .baseUri(starWarsApiBaseUri)
                .when().request(httpMethod, methodName)
                .then().extract().response();

    }
}

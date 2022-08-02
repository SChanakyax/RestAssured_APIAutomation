import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;

public class OptimizedRESTAssuredResponseSpecification {

    private static ResponseSpecification responseSpec;

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }

    @Test
    public void restUsZipCode90210_checkPlaceNameInTheResponseBody_ResponseSpecificationBuilder(){
        given().spec(requestSpec).
                when().
                get("http://zippopotam.us/us/90210").then()
                .spec(responseSpec).
                and().
                assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    /*So, for example, you want to simulate a scenario where you first
    have to create a customer using some customer details and it returns
    you a customer ID and then, for example, place an order for that same
     customer, using the customer ID. And you can't predict the customer ID
      before you run the tests, so you need to create a new customer and then
       retrieve, extract and store the customer ID from it in order to be able to
       use it in subsequent tests. And that's where the extraction method that's being
        offered by REST Assured really comes in handy.

     */
    @Test
    public void requestUsZipCode90210_extractPlaceNameFromResponseBody_assertEqualToBeverlyHills() {

        String placeName =

                given().
                        spec(requestSpec).
                        when().
                        get("http://zippopotam.us/us/90210").
                        then().
                        extract().
                        path("places[0].'place name'");

        Assert.assertEquals("Beverly Hills", placeName);
    }
}

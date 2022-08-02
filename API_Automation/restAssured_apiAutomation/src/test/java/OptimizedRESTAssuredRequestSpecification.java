import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OptimizedRESTAssuredRequestSpecification {

    private static RequestSpecification requestSpecification;


    @BeforeClass
    public static void createRequestSpecification(){
        //http://api.zippopotam.us/us/90210
        requestSpecification = new RequestSpecBuilder().setBaseUri("http://api.zippopotam.us").build();
    }

    @Test
    public void restUsZipCode90210_checkPlaceNameInTheResponseBody_SpecificationBuilder(){
        given().spec(requestSpecification).when().get("us/90210").then().assertThat().statusCode(200);
    }

}

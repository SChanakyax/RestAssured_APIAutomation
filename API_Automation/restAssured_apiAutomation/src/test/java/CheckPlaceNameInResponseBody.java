import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CheckPlaceNameInResponseBody {

    @Test
    public void restUsZipCode90210_checkStatusCode_expected_200() {
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().statusCode(200);
    }

    @Test
    public void restUsZipCode90210_checkContentType_expectedJSON() {
        given().that().get("http://zippopotam.us/us/90210").then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void restUsZipCode90210_logRestAssuredRequestResponse() {
        given().log().all().when().get("http://zippopotam.us/us/90210").then().log().body();
    }

    @Test
    public void restUsZipCode90210_checkPlaceNameInTheResponseBody_expectBeverlyHills() {
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));

    }

    @Test
    public void restUsZipCode90210_checkPlaceNameInTheResponseBody_expectBeverlyHills_HasItem(){
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().body("places.'place name'", hasItem("Beverly Hills"));

    }

    @Test
    public void  restUsZipCode90210_checkNumberOfPlaceNameInTheResponseBody_expectOne(){
        given().when().get("http://zippopotam.us/us/90210").then().assertThat().body("places.'place name'", hasSize(1));
    }


}

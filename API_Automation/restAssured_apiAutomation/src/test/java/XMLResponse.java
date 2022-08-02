import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class XMLResponse {

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectedBeverlyHills() {
        given().when().get("http://api.zippopotam.us/us/90210").then().assertThat().body("response.places.place.placeName", equalTo("Beverly Hills"));
        //Use local due to access operations "http://localhost:9876/us/90210
     //   given().spec(requestSpecification).when().get("us/90210").then().assertThat().statusCode(200);
    }

    @Test
    public void requestDeZipCode24848_checkThirdPlaceNameInTheResponseBody_expectedKropp() {
        given().when().get("http://api.zippopotam.us/us/90210").then().assertThat().body("response.places.place[2].placeName", equalTo("Kropp"));
    }

    @Test
    public void requestDeZipCode24848_checkThirdPlaceNameInTheResponseBody_expectedLastPlace() {
        given().when().get("http://api.zippopotam.us/us/90210").then().assertThat().body("response.places.place[-1].placeName", equalTo("Kropp"));
    }


    @Test
    public void requestDeZipCode24848_checkThirdPlaceNameInTheResponseBody_expectedLatitude() {
        given().when().get("http://api.zippopotam.us/us/90210").then().assertThat().body("response.places.place[1].@latitude", equalTo("54.45"));
    }



}

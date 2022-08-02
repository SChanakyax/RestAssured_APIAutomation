
import com.tngtech.java.junit.dataprovider.*;

import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(DataProviderRunner.class)
public class ParameterizedTest {

    @DataProvider
    public static Object[][] zipCodeAndPlace(){
        return new Object[][] {
                {"us", "90210", "Beverly Hills"},
                {"ca", "B2R", "Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodeAndPlace")
    public void requestZipCodesFromCollection_checkPlaceNameInResponseBody_expectSpecifiedFieldName(String countryCode, String zipCode, String expectedPlaceName) {
         given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().
                assertThat().
                body("places[0].'place name'", equalTo(expectedPlaceName));
    }

    /*
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode12345_checkPlaceNameInResponseBody_expectSchenectady() {

        given().
                when().
                get("http://zippopotam.us/us/12345").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Schenectady"));
    }

    @Test
    public void requestCaZipCodeB2R_checkPlaceNameInResponseBody_expectWaverley() {

        given().
                when().
                get("http://zippopotam.us/ca/B2R").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Waverley"));
    }

     */

}

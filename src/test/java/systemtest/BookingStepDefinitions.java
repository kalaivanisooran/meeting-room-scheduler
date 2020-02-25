package systemtest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingStepDefinitions extends AbstractSpringConfigurationTest {

    private final Logger log = LoggerFactory.getLogger(BookingStepDefinitions.class);

    private static final String BOOKING_END_POINT ="/bookingroom/bookroom";

    private ResponseEntity<String> response = null;

    @Given("^User logged the adminpanel$")
    public void user_logged_the_admin_panel(){
        //TODO check the Oauth token here
        Assertions.assertTrue(true);
    }

    @When("^User provide valid input (.*)$")
    public void user_provide_valid_input(String request){
        String url = buildUrl(HOST, PORT, BOOKING_END_POINT);
        HttpEntity<?> requestEntity = new HttpEntity<>(request, getDefaultHttpHeaders());
        response = invokeRESTCall(url, HttpMethod.POST, requestEntity);
    }

    @Then("^Then Booking the meeting room for that schedule should be successful$")
    public void  chk_Meeting_room_booking_process(){
        Assertions.assertEquals(200,response.getStatusCode());
    }

    @And("^User will get the (.*)$")
    public void chk_response(String expectedStatusCode){
        Assertions.assertEquals(expectedStatusCode,response.getStatusCode());
    }
}

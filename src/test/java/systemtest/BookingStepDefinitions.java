package systemtest;

import cucumber.api.java.en.Given;
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

    private ResponseEntity<String> response = null;

    @Given("^User logged the adminpanel$")
    public void user_logged_the_adminpanel() {
        Assertions.assertTrue(true);
    }

    @When("User provide valid input")
    public void user_provide_valid_input(){
        Assertions.assertTrue(true);

        //TODO make the request call more generic
        String url = buildUrl(HOST, PORT, "/bookingroom/bookroom");
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("roomId","1");
        requestBody.put("usrEmpId","1234");
        requestBody.put("userName","team");
        requestBody.put("bookingDate",new Date()); //TODO need to check date format
        requestBody.put("bookingStartTime","11:00:00");
        requestBody.put("bookingEndTime","12:00:00");

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, getDefaultHttpHeaders());
        response = invokeRESTCall(url, HttpMethod.POST, requestEntity);
    }

}

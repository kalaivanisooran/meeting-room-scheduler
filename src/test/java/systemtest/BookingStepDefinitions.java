package systemtest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.joining;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingStepDefinitions extends AbstractSpringConfigurationTest {

    private final Logger log = LoggerFactory.getLogger(BookingStepDefinitions.class);

    private static final String BOOKING_END_POINT ="/meetingroom/bookroom";

    private ResponseEntity<String> response = null;

    private String access_token;

    private HttpHeaders headers = new HttpHeaders();

    @Given("^User logged the admin panel$")
    public void user_logged_the_admin_panel() throws JSONException {
        //TODO set the profile to run the end-to-end test
        ResponseEntity<String> authResponse = this.requestForAccessToken();
        Assertions.assertNotNull(authResponse,"Authorization response should not be empty");
        Assertions.assertEquals(200,authResponse.getStatusCode().value());

        access_token = new JSONObject(authResponse.getBody()).getString("access_token");
    }

    @When("^User provide content-type (.*)$")
    public void user_provide_content_type(String content_type){
        Assertions.assertEquals("application/json",content_type,"Content type should be application/json");

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);
    }

    @And("^User provide (.*) input (.*)$")
    public void user_provide_valid_input(String inputType, String request){
        Assertions.assertNotNull(request);
        Assertions.assertNotEquals("",request.trim(),"Request should not be empty");

        String url = buildUrl(HOST, PORT, BOOKING_END_POINT);
        HttpEntity<?> requestEntity = new HttpEntity<>(request, headers);
        response = invokeRESTCall(url, HttpMethod.POST, requestEntity);
        Assertions.assertNotNull(response);
    }

    @And("^User will get respective (.*)$")
    public void user_will_get_respective_status_code(Integer expectedStatusCode){
        Assertions.assertEquals(expectedStatusCode,response.getStatusCode().value());
    }

    @And("^response message should be (.*)$")
    public void response_message_should_be(String expectedMessage){
        Assertions.assertEquals(expectedMessage,response.getBody());
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, String.valueOf(StandardCharsets.UTF_8));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private ResponseEntity<String> requestForAccessToken(){

        String url = buildUrl(HOST, PORT, "/oauth/token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("glassdoor","glassdoor");

        Map<String,String> requestParam = new HashMap<>();
        requestParam.put("username","1234");
        requestParam.put("password","1234");
        requestParam.put("grant_type","password");

        String encodedURL = requestParam.keySet().stream()
                .map(key -> key + "=" + encodeValue(requestParam.get(key)))
                .collect(joining("&", url.concat("?"), ""));

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        return invokeRESTCall(encodedURL, HttpMethod.POST, requestEntity);
    }
}

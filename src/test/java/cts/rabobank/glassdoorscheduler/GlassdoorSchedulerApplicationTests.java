package cts.rabobank.glassdoorscheduler;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cts.rabobank.glassdoorscheduler.entity.Room;

@SpringBootTest(classes = GlassdoorSchedulerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlassdoorSchedulerApplicationTests {

	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() {

		/*HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<Room[]> response = restTemplate.exchange(
				"http://localhost:8086/roominfo/rooms",
				HttpMethod.GET, entity, Room[].class);
		
		System.out.println("response:"+response.getStatusCodeValue());		
		assertEquals(2, response.getBody().length, "length should be 2"); */
		
	}
		
}		

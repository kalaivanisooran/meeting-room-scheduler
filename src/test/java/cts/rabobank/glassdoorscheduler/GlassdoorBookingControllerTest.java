package cts.rabobank.glassdoorscheduler;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Time;
import java.util.Date;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingIdentity;
import cts.rabobank.glassdoorscheduler.entity.Room;

@SpringBootTest(classes = GlassdoorSchedulerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GlassdoorBookingControllerTest {

	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testbookroom() {

		RestTemplate restTemplate = new RestTemplate();

	    final String baseUrl = "http://localhost:8086/bookingroom/bookroom";
	    //URI uri = new URI(baseUrl);

	    Booking booking = new Booking();
	    BookingIdentity bookingEntity = new BookingIdentity();    
	    
	    
	    bookingEntity.setBookingDate(new Date());
	    bookingEntity.setBookingStartTime(Time.valueOf("11:00:00"));
	    bookingEntity.setBookingEndTime(Time.valueOf("11:00:00"));
	  
	    //ResponseEntity<String> result = restTemplate.postForEntity(uri, booking, String.class);
	     
	    //Verify request succeed
	    //Assert.assertEquals(201, result.getStatusCodeValue());
		
	}


		
}		

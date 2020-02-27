package cts.rabobank.glassdoorscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class GlassdoorSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlassdoorSchedulerApplication.class, args);
	}

}

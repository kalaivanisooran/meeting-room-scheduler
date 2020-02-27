package cts.rabobank.glassdoorscheduler.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Searching {

	private Integer roomId;
	private Integer usrEmpId;
	private LocalDate bookingDate;
	private LocalTime bookingEndTime;
	private LocalTime bookingStartTime;
}

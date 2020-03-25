package cts.rabobank.glassdoorscheduler.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Getter
@Setter
public class MeetingDetail {

    private static final long serialVersionUID = 1L;

    private String roomName;

    private String purpose;

    private LocalDate bookingStartDate;

    private LocalTime bookingStartTime;

    private LocalTime bookingEndTime;
}
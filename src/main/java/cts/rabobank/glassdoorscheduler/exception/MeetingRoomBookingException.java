package cts.rabobank.glassdoorscheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MeetingRoomBookingException extends RuntimeException {

    public MeetingRoomBookingException(String exception) {
        super(exception);
    }

}
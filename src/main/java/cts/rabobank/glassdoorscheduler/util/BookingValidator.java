package cts.rabobank.glassdoorscheduler.util;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import org.springframework.validation.Errors;

import java.util.Objects;
import java.util.Optional;

public class BookingValidator {

    public void chkBookingRoomInputField(final BookingInfo bookingInfo, final Errors errors){
        this.chkInputFieldHasError(errors);
        this.chkEndTimeGreaterThanStartTime(bookingInfo);
    }

    private void chkInputFieldHasError(final Errors errors){
        if(errors.hasErrors()) {
            throw new InvalidInputRequestException(Optional.ofNullable(
                    Objects.requireNonNull(errors.getFieldError())
                            .getDefaultMessage())
                    .orElse("Invalid Input requested"));
        }
    }

    private void chkEndTimeGreaterThanStartTime(final BookingInfo bookingInfo){
        if(bookingInfo.getBookingStartTime().getTime() >= bookingInfo.getBookingEndTime().getTime()){
            throw new InvalidInputRequestException("End Time should be greater than Start Time");
        }
    }
}

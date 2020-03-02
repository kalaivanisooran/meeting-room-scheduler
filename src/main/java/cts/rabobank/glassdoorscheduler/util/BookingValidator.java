package cts.rabobank.glassdoorscheduler.util;

import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import java.util.Objects;
import java.util.Optional;

public class BookingValidator {

    @Autowired
    BookingService bookingService;

    public void chkBookingRoomInputField(final BookingInfo bookingInfo, final Errors errors){
        this.chkInputFieldHasError(errors);
        this.chkEndTimeGreaterThanStartTime(bookingInfo);
        this.chkMeetingRoomAvailable(bookingInfo);
    }

    private void chkMeetingRoomAvailable(final BookingInfo bookingInfo){

        Searching checkRoom = new Searching();
        checkRoom.setRoomId(bookingInfo.getRoomId());
        checkRoom.setBookingDate(bookingInfo.getBookingDate());
        checkRoom.setBookingStartTime(bookingInfo.getBookingStartTime());
        checkRoom.setBookingEndTime(bookingInfo.getBookingEndTime());

        if (!bookingService.searchMeetingRooms(checkRoom).isEmpty()) {
            throw new InvalidInputRequestException("Requested Meeting room is not available");
        }
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
        if(bookingInfo.getBookingStartTime().compareTo(bookingInfo.getBookingEndTime()) >= 0){
            throw new InvalidInputRequestException("End Time should be greater than Start Time");
        }
    }
}

package cts.rabobank.glassdoorscheduler.util;

import cts.rabobank.glassdoorscheduler.entity.BookingInfo;
import cts.rabobank.glassdoorscheduler.entity.Searching;
import cts.rabobank.glassdoorscheduler.exception.InvalidInputRequestException;
import cts.rabobank.glassdoorscheduler.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookingValidator {

    @Autowired
    BookingService bookingService;

    public void chkBookingRoomInputField(final BookingInfo bookingInfo, final Errors errors){
        this.chkInputFieldHasError(errors);
        this.chkInputModeType(bookingInfo.getMode());
        this.chkEndTimeGreaterThanStartTime(bookingInfo);
        //TODO need to check the avaiablity
        //this.chkMeetingRoomAvailable(bookingInfo);
    }

//    private void chkMeetingRoomAvailable(final BookingInfo bookingInfo){
//        if (!bookingService.canBookingAllowed(bookingInfo)) {
//            throw new InvalidInputRequestException("Requested Meeting room is not available");
//        }
//    }

    private void chkInputFieldHasError(final Errors errors){
        if(errors!=null){
            if(errors.hasErrors()) {
                throw new InvalidInputRequestException(Optional.ofNullable(
                        Objects.requireNonNull(errors.getFieldError())
                                .getDefaultMessage())
                        .orElse("Invalid Input requested"));
            }
        }

    }

    private void chkEndTimeGreaterThanStartTime(final BookingInfo bookingInfo){
        if(bookingInfo.getBookingStartTime().compareTo(bookingInfo.getBookingEndTime()) >= 0){
            throw new InvalidInputRequestException("End Time should be greater than Start Time");
        }
    }

    private void chkInputModeType(String selectedMode){
        List<String> modeList = Arrays.asList("today","tomorrow","week","month","custom");

        if(!modeList.contains(selectedMode)){
            throw new InvalidInputRequestException("Invalid mode type");
        }
    }
}

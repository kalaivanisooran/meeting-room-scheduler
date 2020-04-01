package cts.rabobank.glassdoorscheduler.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import cts.rabobank.glassdoorscheduler.entity.Booking;
import cts.rabobank.glassdoorscheduler.entity.BookingInfo;

public class SearchSpecifications {

        private static final Logger logger = LoggerFactory.getLogger(SearchSpecifications.class);

        public static Specification<Booking> searchMeetingRooms(BookingInfo bookingInfo, LocalDate bookingEndDate, Long userId) {
                return (booking, query, cb) -> {

                        List<Predicate> predicates = new ArrayList<Predicate>();

                        if (bookingInfo.getBookingMode().equals("custom")) {

                                In<LocalDate> inClause = cb.in(booking.get("bookingStartDate"));
                                for (LocalDate date : bookingInfo.getCustomBookingDate()) {

                                        logger.info("custom booking dastes:"+date);
                                    inClause.value(date);
                                }

                                predicates.add(cb.and(cb.and(inClause)));

                        } else {
                                if (bookingInfo.getBookingStartDate() != null) {
                                        predicates.add(cb.and(cb.between(booking.get("bookingStartDate"), bookingInfo.getBookingStartDate(), bookingEndDate)));
                                }
                        }


                        if (bookingInfo.getBookingStartTime() != null) {
                                predicates.add(cb.and(cb.equal(booking.get("bookingStartTime"), bookingInfo.getBookingStartTime())));
                        }
                        if (bookingInfo.getBookingEndTime() != null) {
                                predicates.add(cb.and(cb.equal(booking.get("bookingEndTime"), bookingInfo.getBookingEndTime())));
                        }
                        if (bookingInfo.getRoomId() != null && bookingInfo.getRoomId().intValue() > 0) {
                                predicates.add(cb.and(cb.equal(booking.get("roomInfo"), bookingInfo.getRoomId())));
                        }
                        if (bookingInfo.getUsrEmpId() != null) {
                                predicates.add(cb.and(cb.equal(booking.get("userInfo"), userId)));
                        }

                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                };

        }
}

package cts.rabobank.glassdoorscheduler.repo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cts.rabobank.glassdoorscheduler.entity.Booking;

@Repository
public interface BookingRepo extends CrudRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

//    @Query("SELECT B FROM Booking B WHERE B.roomInfo.id=:roomId AND B.bookingDate=:bookDate   "
//    		+ "AND ((:fTime BETWEEN B.bookingStartTime AND B.bookingEndTime) "
//    		+ "OR (:eTime BETWEEN B.bookingStartTime AND  B.bookingEndTime))  "
//    		+ "OR (:fTime < B.bookingStartTime and :eTime > B.bookingEndTime)")
//    Optional<Booking> doBookingSlotAvailable(@Param("bookDate") LocalDate bookDate,
//    							   @Param("roomId") Long roomId,
//    							   @Param("fTime") LocalTime fTime,
//    							   @Param("eTime") LocalTime eTime);
    
    
    @Modifying
    @Query("DELETE FROM Booking B WHERE B.id=:bookId")
    public void deleteById(@Param("bookId") Long bookId);

	//List<Booking> findAllByOrderByIdDesc(Specification<Booking> searchMeetingRooms);
}
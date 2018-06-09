package com.appcom.waffa.respository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appcom.waffa.constant.Status;
import com.appcom.waffa.entity.Booking;
import com.appcom.waffa.entity.User;

@Repository("bookingRepository")
public interface BookingRepository extends CrudRepository<Booking,Integer> {
	
public List<Booking> findAllBookingByUserAndIsActive(User user,Status isActive);

public List<Booking> findAllBookingByUserAndIsCancled(User user,Status isCancled);

public List<Booking> findAllBookingByUserAndIsDone(User user,Status isDone);

public Booking findOneById(int bookingId);

public List<Booking> findAllByIsPending(Status isPending);

public List<Booking> findAllBookingByStartDateAndIsActive(Date date,Status isActive);

 






}

package com.waffa.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waffa.constant.Status;
import com.waffa.entity.Booking;
import com.waffa.entity.User;

@Repository("bookingRepository")
public interface BookingRepository extends JpaRepository<Booking,Integer> {
	
public List<Booking> findAllBookingByUserAndIsActive(User user,Status isActive);

public List<Booking> findAllBookingByUserAndIsCancled(User user,Status isCancled);

public List<Booking> findAllBookingByUserAndIsDone(User user,Status isDone);

public Booking findOneById(int bookingId);

public List<Booking> findAllByIsPending(Status isPending);



}

package com.leantech.booking.repository;

import com.leantech.booking.model.Booking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    public void deleteBookingById(Long id);

    public Optional<Booking> findBookingById(Long id);
    
}

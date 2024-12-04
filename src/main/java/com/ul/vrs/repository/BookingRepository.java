package com.ul.vrs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ul.vrs.entity.booking.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

}

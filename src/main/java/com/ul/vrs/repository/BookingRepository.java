// package com.ul.vrs.repository;

// import java.util.Optional;
// import java.util.UUID;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.ul.vrs.entity.booking.Booking;

// @Repository
// public interface BookingRepository extends JpaRepository<Booking, UUID> {

//     // Find a booking by ID with eager fetching for relationships (optional)
//     Optional<Booking> findById(UUID bookingId);

//     // Check if a booking with the same UUID exists
//     boolean existsByBookingId(UUID bookingId);

//     // Find all bookings by a specific customer ID
//     Optional<Booking> findByCustomer_AccountId(String accountId);

//     // Custom delete by UUID to ensure additional checks if necessary
//     void deleteByBookingId(UUID bookingId);
// }
